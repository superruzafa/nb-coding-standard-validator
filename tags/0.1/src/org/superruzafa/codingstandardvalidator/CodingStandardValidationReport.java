package org.superruzafa.codingstandardvalidator;

import java.util.Date;
import org.openide.filesystems.FileObject;

/**
 * Result of a validation operation.
 *
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 * @since 0.1
 */
public class CodingStandardValidationReport {

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
     * Creates a new CodingStandardValidationReport.
     */
    public CodingStandardValidationReport()
    {
        valid = false;
        fileObject = null;
        codingStandard = "";
        violations = new CodingStandardViolation[0];
        validator = "";
    }

    /**
     * Gets either strict violations were detected or not.
     *
     * @return true if no strict violations were detected.
     *         false otherwise.
     */
    public boolean isValid()
    {
        return valid;
    }

    /**
     * Gets the validated Netbeans file.
     *
     * @return Netbeans file.
     */
    public FileObject getFileObject()
    {
        return fileObject;
    }

    /**
     * Get the coding standard name used to validate the file.
     *
     * @return Coding standard name.
     */
    public String getCodingStandard()
    {
        return codingStandard;
    }

    /**
     * Gets the detected violations.
     *
     * @return Detected violations.
     */
    public CodingStandardViolation[] getViolations()
    {
        return violations;
    }

    /**
     * Gets the name of the validator.
     *
     * @return Validator name.
     */
    public String getValidator()
    {
        return validator;
    }

    /**
     * Gets the date and time when the validation was done.
     *
     * @return Date and time.
     */
    public Date getDateTime()
    {
        return dateTime;
    }
}
