package org.superruzafa.codingstandardvalidator;

/**
 * A violation of a coding standard.
 * 
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 * @since 0.1
 */
public class CodingStandardViolation {

    /**
     * Line where the violation was happened.
     */
    protected int line;

    /**
     * Column where the violation was happened.
     */
    protected int column;

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
        this(line, 0, message, severity);
    }

    /**
     * Creates a new CodingStandardViolation object.
     *
     * @param line     Line where the violation was happened.
     * @param column   Column where the violation was happened.
     * @param message  Message describing the violation.
     * @param severity Violation severity level.
     */
    public CodingStandardViolation(int line, int column, String message,
            CodingStandardViolationSeverity severity) {
        this.line = line;
        this.column = column;
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
     * Gets the line's column where the violation was happened.
     *
     * @return Line's column.
     */
    public int getColumn()
    {
        return column;
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
