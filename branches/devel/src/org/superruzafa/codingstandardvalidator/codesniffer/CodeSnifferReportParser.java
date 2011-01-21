package org.superruzafa.codingstandardvalidator.codesniffer;

import java.util.ArrayList;
import org.superruzafa.codingstandardvalidator.CodingStandardViolation;

/**
 * An generic PHP Code Sniffer reports parser.
 * 
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 */
abstract public class CodeSnifferReportParser {

    protected ArrayList<CodingStandardViolation> violations;

    public CodeSnifferReportParser()
    {
        violations = new ArrayList<CodingStandardViolation>();
    }

    public abstract boolean parse(String report);

    public CodingStandardViolation[] getViolations()
    {
        return violations.toArray(new CodingStandardViolation[violations.size()]);
    }
}
