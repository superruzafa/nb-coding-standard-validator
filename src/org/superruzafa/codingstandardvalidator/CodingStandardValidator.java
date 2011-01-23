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
     * @return Validation report
     * 
     * @see #getStrictnessThreshold
     */
    public CodingStandardValidationReport validate(FileObject file) throws CodingStandardValidatorException;

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
