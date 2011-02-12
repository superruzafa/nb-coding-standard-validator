package org.superruzafa.codingstandardvalidator.codesniffer;

import java.util.ArrayList;
import org.superruzafa.codingstandardvalidator.CodingStandardViolation;
import org.superruzafa.codingstandardvalidator.CodingStandardViolationSeverity;

/**
 * An generic PHP Code Sniffer reports parser.
 * 
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 * @since 0.1
 */
abstract public class CodeSnifferReportParser {

    class EditableCodingStandardViolation extends CodingStandardViolation {

        public EditableCodingStandardViolation() {
            super(1, "", CodingStandardViolationSeverity.Error);
        }

        public void setLine(int line) {
            this.line = line;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setSeverity(CodingStandardViolationSeverity severity) {
            this.severity = severity;
        }
    }
    protected ArrayList<CodingStandardViolation> violations;

    /**
     * Creates a new CodeSnifferReportParser object.
     */
    public CodeSnifferReportParser() {
        violations = new ArrayList<CodingStandardViolation>();
    }

    /**
     * Parses a Code Sniffer report.
     *
     * @param report Code Sniffer script output.
     *
     * @return true if the parsed report contain no strict errors.
     *         false otherwise.
     */
    public abstract boolean parse(String report);

    /**
     * Gets the parsed violations.
     *
     * @return Parsed violations.
     */
    public CodingStandardViolation[] getViolations() {
        return violations.toArray(new CodingStandardViolation[violations.size()]);
    }
}
