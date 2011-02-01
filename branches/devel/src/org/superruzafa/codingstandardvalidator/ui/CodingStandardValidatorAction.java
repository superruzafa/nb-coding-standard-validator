package org.superruzafa.codingstandardvalidator.ui;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.cookies.EditorCookie;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.windows.WindowManager;
import org.superruzafa.codingstandardvalidator.CodingStandardValidationReport;
import org.superruzafa.codingstandardvalidator.CodingStandardValidatorException;
import org.superruzafa.codingstandardvalidator.codesniffer.CodeSniffer;

/**
 * Action triggered to validate a file.
 *
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 */
public final class CodingStandardValidatorAction implements ActionListener {

    private final DataObject context;

    public CodingStandardValidatorAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {

        String errorMessage = "";
        CodeSniffer codeSniffer = Lookup.getDefault().lookup(CodeSnifferBuilder.class).getInstance();

        if (!isCodeSnifferScriptSet(codeSniffer))
        {
            /**
             * @todo I18n
             */
            errorMessage = "Please, set the path for the Code Sniffer script at settings page.";
        }
        else if (!existsCodeSnifferScript(codeSniffer))
        {
            /**
             * @todo I18n
             */
            errorMessage = String.format("Cannot find the Code Sniffer script located at %s.", codeSniffer.getScriptPath());
        }
        else
        {
            try {
                WindowManager.getDefault().getMainWindow().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                context.getLookup().lookup(EditorCookie.class).saveDocument();
                CodingStandardValidationReport report = codeSniffer.validate(context.getPrimaryFile());

                CodingStandardViolationsTopComponent window = CodingStandardViolationsTopComponent.findInstance();
                window.setReport(report);
                if (!window.isOpened()) {
                    window.open();
                }
                window.requestActive();
            } catch (IOException ex) {
                errorMessage = ex.getMessage();
            } catch (CodingStandardValidatorException ex) {
                errorMessage = ex.getCause().getMessage();
            } finally
            {
                WindowManager.getDefault().getMainWindow().setCursor(Cursor.getDefaultCursor());
            }
        }
        if (!"".equals(errorMessage)) {
            /**
             * @todo I18n
             */
            NotifyDescriptor nd = new NotifyDescriptor.Message("Cannot validate file coding standard.\n" + errorMessage, NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
    }

    /**
     * Checks either the value for the Code Sniffer script path is set or not.
     *
     * @param codeSniffer Code Sniffer object.
     *
     * @return true if path is set. false otherwise.
     */
    private boolean isCodeSnifferScriptSet(CodeSniffer codeSniffer)
    {
        return !"".equals(codeSniffer.getScriptPath());
    }

    /**
     * Checks either the path for the Code Sniffer script exists or not.
     *
     * @param codeSniffer Code Sniffer object.
     *
     * @return true if path exists. false otherwise.
     */
    private boolean existsCodeSnifferScript(CodeSniffer codeSniffer)
    {
        File cs = new File(codeSniffer.getScriptPath());
        boolean exists = cs.exists();
        cs = null;
        return exists;
    }
}
