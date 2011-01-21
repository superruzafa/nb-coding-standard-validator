package org.superruzafa.codingstandardvalidator;

import org.openide.filesystems.FileObject;

/**
 * A Coding Standard Validator
 * 
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 */
public interface CodingStandardValidator {

    /**
     * Validates a code file against this Coding Standard Validator.
     *
     * @param file Code file.
     *
     * @return true if the code has no strict violations.
     *         false otherwise.
     * 
     * @see #getStrictnessThreshold
     */
    public boolean validate(FileObject file) throws CodingStandardValidatorException;

    /**
     * Gets the violations from the last validation.
     *
     * @return Violations from the last validation.
     */
    public CodingStandardViolation[] getViolations();

    /**
     * Gets the severity value at which a severity is considered strict.
     *
     * @return Severity level.
     */
    public CodingStandardViolationSeverity getStrictnessThreshold();

    /**
     * Sets the severity value at which a severity is considered strict.
     *
     * @param threshold Severity level threshold.
     */
    public void setStrictnessThreshold(CodingStandardViolationSeverity threshold);
}
