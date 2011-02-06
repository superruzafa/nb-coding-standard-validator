/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superruzafa.codingstandardvalidator.ui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.superruzafa.codingstandardvalidator.CodingStandardValidationReport;
import org.superruzafa.codingstandardvalidator.CodingStandardValidatorException;
import org.superruzafa.codingstandardvalidator.codesniffer.CodeSniffer;

public final class CodingStandardValidatorAction implements ActionListener {

    private final DataObject context;
    private CodeSniffer codeSniffer;
    private final static String[] validatableMimeTypesArray = {
        "text/x-php5"
    };

    public CodingStandardValidatorAction(DataObject context) {
        this.context = context;
        codeSniffer = Lookup.getDefault().lookup(CodeSnifferBuilder.class).getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        FileObject fileObject = context.getPrimaryFile();
        CodingStandardViolationsTopComponent window = CodingStandardViolationsTopComponent.findInstance();
        if (fileObject.isFolder()) {
            window.setReport(validateDirectory(fileObject));
        } else {
            window.setReport(validateFile(fileObject), codeSniffer.getCodingStandardName());
        }

        if (!window.isOpened()) {
            window.open();
        }
        window.requestActive();
    }

    protected CodingStandardValidationReport validateFile(FileObject fileObject) {
        CodingStandardValidationReport report = null;
        try {
            report = codeSniffer.validate(fileObject);
        } catch (CodingStandardValidatorException ex) {
            report = null;
            Exceptions.printStackTrace(ex);
        }
        return report;
    }

    protected CodingStandardValidationReport[] validateDirectory(FileObject fileObject) {
        ArrayList<CodingStandardValidationReport> reports = new ArrayList<CodingStandardValidationReport>();
        for (FileObject subFileObject : fileObject.getChildren()) {
            if (subFileObject.isFolder()) {
                reports.addAll(Arrays.asList(validateDirectory(subFileObject)));
            } else if (Arrays.asList(validatableMimeTypesArray).contains(subFileObject.getMIMEType())) {
                reports.add(validateFile(subFileObject));
            }
        }
        return reports.toArray(new CodingStandardValidationReport[reports.size()]);
    }
}
