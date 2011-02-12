/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superruzafa.codingstandardvalidator.ui;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.windows.WindowManager;
import org.superruzafa.codingstandardvalidator.CodingStandardValidationReport;
import org.superruzafa.codingstandardvalidator.CodingStandardValidatorException;
import org.superruzafa.codingstandardvalidator.codesniffer.CodeSniffer;

public final class CodingStandardValidatorAction implements ActionListener {

    private final DataObject context;
    private CodeSniffer codeSniffer;
    private final static List<String> validatableMimeTypes = Arrays.asList(new String[]{
                "text/x-php5"
            });

    public CodingStandardValidatorAction(DataObject context) {
        this.context = context;
        codeSniffer = Lookup.getDefault().lookup(CodeSnifferBuilder.class).getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        final FileObject fileObject = context.getPrimaryFile();
        final CodingStandardViolationsTopComponent window = CodingStandardViolationsTopComponent.findInstance();

        SwingWorker worker = new SwingWorker<Void, Void>() {

            private final int MillisecondsToShow = 1000;
            private ArrayList<CodingStandardValidationReport> reports = new ArrayList<CodingStandardValidationReport>();
            private ProgressMonitor monitor;
            private Throwable catchedException;

            @Override
            protected Void doInBackground() {
                reports.clear();
                /**
                 * @todo I18n
                 */
                monitor = new ProgressMonitor(WindowManager.getDefault().getMainWindow(), "Validating coding standard...", "Enumerating files...", 0, 0);
                monitor.setMillisToDecideToPopup(MillisecondsToShow);
                ArrayList<FileObject> files = enumerateFiles(fileObject);
                monitor.setMaximum(files.size());
                int i = 0;
                URI uri = FileUtil.toFile(fileObject).toURI();
                while (!monitor.isCanceled() && i < files.size()) {
                    FileObject file = files.get(i);
                    monitor.setNote(uri.relativize(FileUtil.toFile(file).toURI()).getPath());
                    try {
                        reports.add(codeSniffer.validate(file));
                    } catch (CodingStandardValidatorException ex) {
                        catchedException = ex;
                        monitor.close();
                    }
                    monitor.setProgress(i);
                    ++i;
                }

                return null;
            }

            @Override
            protected void done() {
                if (catchedException != null) {
                    NotifyDescriptor nd = new NotifyDescriptor.Message(String.format("The resource couldn't be validated: %s", catchedException.getMessage()), NotifyDescriptor.ERROR_MESSAGE);
                    DialogDisplayer.getDefault().notify(nd);
                } else if (!monitor.isCanceled()) {
                    if (fileObject.isFolder()) {
                        window.setReport(reports.toArray(new CodingStandardValidationReport[reports.size()]), fileObject);
                    } else if (reports.size() > 0) {
                        window.setReport(reports.get(0));
                    }
                    if (!window.isOpened()) {
                        window.open();
                    }
                    window.requestActive();
                }
                monitor.close();
            }

            protected ArrayList<FileObject> enumerateFiles(FileObject masterFile) {
                ArrayList<FileObject> files = new ArrayList<FileObject>();
                if (masterFile.isFolder()) {
                    for (FileObject file : masterFile.getChildren()) {
                        if (file.isFolder()) {
                            files.addAll(enumerateFiles(file));
                        } else if (validatableMimeTypes.contains(file.getMIMEType())) {
                            files.add(file);
                        }
                    }
                } else {
                    files.add(masterFile);
                }

                return files;
            }
        };

        WindowManager.getDefault().getMainWindow().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        worker.execute();
        WindowManager.getDefault().getMainWindow().setCursor(Cursor.getDefaultCursor());
    }
}
