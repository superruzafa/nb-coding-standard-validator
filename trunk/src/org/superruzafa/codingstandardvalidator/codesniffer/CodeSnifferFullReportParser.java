package org.superruzafa.codingstandardvalidator.codesniffer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.superruzafa.codingstandardvalidator.CodingStandardViolation;
import org.superruzafa.codingstandardvalidator.CodingStandardViolationSeverity;

/**
 * PHP Code Sniffer full reports parser.
 *
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 * @since 0.1
 */
public class CodeSnifferFullReportParser extends CodeSnifferReportParser {

    protected static final String startingLineRegex = "^\\s*(\\d+)\\s*\\|\\s*([^\\s]+)\\s*\\|(.+)$";
    protected static final String continuationLineRegex = "^\\s*\\|\\s*\\|(.+)$";
    protected static Pattern startingLinePattern = Pattern.compile(startingLineRegex);
    protected static Pattern continuationLinePattern = Pattern.compile(continuationLineRegex);

    @Override
    public boolean parse(String report) {
        violations.clear();
        EditableCodingStandardViolation violation = null;
        Matcher startingLineMatcher, continuationLineMatcher;

        for (String line : report.split("\n")) {
            startingLineMatcher = startingLinePattern.matcher(line);
            if (startingLineMatcher.matches()) {
                violation = new EditableCodingStandardViolation();
                violation.setLine(Integer.valueOf(startingLineMatcher.group(1)));
                violation.setMessage(startingLineMatcher.group(3).trim());
                violation.setSeverity(parseSeverity(startingLineMatcher.group(2)));
                violations.add(violation);
            } else {
                continuationLineMatcher = continuationLinePattern.matcher(line);
                if (continuationLineMatcher.matches() && violations != null) {
                    violation.setMessage(violation.getMessage() + " " + continuationLineMatcher.group(1).trim());
                }
            }
        }

        return true;
    }

    private CodingStandardViolationSeverity parseSeverity(String word) {
        CodingStandardViolationSeverity severity = CodingStandardViolationSeverity.Error;

        if ("ERROR".compareTo(word.trim().toUpperCase()) == 0)
        {
            severity = CodingStandardViolationSeverity.Error;
        }
        else if ("WARNING".compareTo(word.trim().toUpperCase()) == 0)
        {
            severity = CodingStandardViolationSeverity.Warning;
        }

        return severity;
    }
}
