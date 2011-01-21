package org.superruzafa.codingstandardvalidator;

/**
 * A violation of a coding standard.
 * 
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 */
public class CodingStandardViolation {

    /**
     * Line where the violation was happened.
     */
    protected int line;

    /**
     * A message describing the violation.
     */
    protected String message;

    /**
     * A violation severity level.
     */
    protected CodingStandardViolationSeverity severity;

    /**
     * Creates a new CodingStandardViolation object.
     *
     * @param line     Line where the violation was happened.
     * @param message  Message describing the violation.
     * @param severity Violation severity level.
     */
    public CodingStandardViolation(int line, String message,
            CodingStandardViolationSeverity severity) {
        this.line = line;
        this.message = message;
        this.severity = severity;
    }

    /**
     * Gets the code line where the violation was happened.
     *
     * @return Code line.
     */
    public int getLine()
    {
        return line;
    }

    /**
     * Gets the message that describes the violation.
     *
     * @return Violation message.
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Gets the violation severity level.
     *
     * @return Severity level.
     */
    public CodingStandardViolationSeverity getSeverity()
    {
        return severity;
    }
}
