package org.superruzafa.codingstandardvalidator;

import java.util.Date;
import org.openide.filesystems.FileObject;

/**
 * A CodingStandardValidationReport builder.
 * 
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 */
public class CodingStandardValidationReportBuilder {

    /**
     * true when no strict errors found while validating.
     */
    protected boolean valid;

    /**
     * Netbeans validated file.
     */
    protected FileObject fileObject;

    /**
     * Coding standard name.
     */
    protected String codingStandard;

    /**
     * Detected violations.
     */
    protected CodingStandardViolation[] violations;

    /**
     * Validator name.
     */
    protected String validator;

    /**
     * Validation date and time.
     */
    protected Date dateTime;

    /**
     * Creates a new CodingStandardValidationReportBuilder.
     */
    public CodingStandardValidationReportBuilder()
    {
        valid = false;
        fileObject = null;
        codingStandard = "";
        violations = new CodingStandardViolation[0];
        validator = "";
        dateTime = new Date();
    }

    /**
     * Gets the built report.
     *
     * @return A report built with the builder info.
     */
    public CodingStandardValidationReport getReport()
    {
        EditableCodingStandardValidationReport report = new EditableCodingStandardValidationReport();
        report.setDateTime(dateTime);
        report.setIsValid(valid);
        report.setFileObject(fileObject);
        report.setValidator(validator);
        report.setCodingStandard(codingStandard);
        report.setViolations(violations);

        return report;
    }

    /**
     * Sets either the validation contains strict errors or not.
     *
     * @param valid true if no strict errors were found.
     *              false otherwise.
     */
    public void setIsValid(boolean valid)
    {
        this.valid = valid;
    }

    /**
     * Sets the validated Netbeans file.
     * 
     * @param fileObject Netbeans file.
     */
    public void setFileObject(FileObject fileObject)
    {
        this.fileObject = fileObject;
    }

    /**
     * Sets the coding standard use to validate the file.
     *
     * @param codingStandard Coding standard name.
     */
    public void setCodingStandard(String codingStandard)
    {
        this.codingStandard = codingStandard;
    }

    /**
     * Sets the detected violations.
     *
     * @param violations Detected violations.
     */
    public void setViolations(CodingStandardViolation[] violations)
    {
        this.violations = violations;
    }

    /**
     * Set the validator use to validate the file.
     *
     * @param validator Validator name.
     */
    public void setValidator(String validator)
    {
        this.validator = validator;
    }

    /**
     * Sets the date and time of the validation.
     *
     * @param date Date and time.
     */
    public void setDateTime(Date date)
    {
        this.dateTime = date;
    }
}

class EditableCodingStandardValidationReport extends CodingStandardValidationReport
{
    public void setIsValid(boolean valid)
    {
        this.valid = valid;
    }

    public void setFileObject(FileObject fileObject)
    {
        this.fileObject = fileObject;
    }

    public void setCodingStandard(String codingStandard)
    {
        this.codingStandard = codingStandard;
    }

    public void setViolations(CodingStandardViolation[] violations)
    {
        this.violations = violations;
    }

    public void setValidator(String validator)
    {
        this.validator = validator;
    }

    public void setDateTime(Date date)
    {
        this.dateTime = date;
    }
}
