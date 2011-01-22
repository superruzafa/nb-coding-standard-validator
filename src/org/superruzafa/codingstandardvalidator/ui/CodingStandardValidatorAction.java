package org.superruzafa.codingstandardvalidator.ui;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.windows.WindowManager;
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
        CodeSniffer codeSniffer = Lookup.getDefault().lookup(CodeSnifferBuilder.class).getInstance();
        CodingStandardViolationsTopComponent window = CodingStandardViolationsTopComponent.findInstance();
        WindowManager.getDefault().getMainWindow().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        boolean couldValidate = false;
        String errorMessage = "";
        try {
            /**
             * @todo Save file before validate.
             */
            codeSniffer.validate(context.getPrimaryFile());
            couldValidate = true;
            window.setViolations(codeSniffer.getViolations(), context);
            window.open();
            window.requestActive();
        } catch (CodingStandardValidatorException ex) {
            couldValidate = false;
            errorMessage = ex.getCause().getMessage();
        }

        WindowManager.getDefault().getMainWindow().setCursor(Cursor.getDefaultCursor());
        if (!couldValidate) {
            /**
             * @todo I18n
             */
            NotifyDescriptor nd = new NotifyDescriptor.Message("Cannot validate file coding standard.\n" + errorMessage, NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(nd);
        }
    }
}
