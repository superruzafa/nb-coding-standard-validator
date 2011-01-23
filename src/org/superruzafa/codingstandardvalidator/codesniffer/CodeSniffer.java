package org.superruzafa.codingstandardvalidator.codesniffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.superruzafa.codingstandardvalidator.*;

/**
 * A PHP Code Sniffer frontend.
 *
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 */
public class CodeSniffer implements CodingStandardValidator {

    private final String VALIDATOR_ID = "PHP Code Sniffer";

    /**
     * Default PHP Code Sniffer script path
     */
    private static final String DefaultScriptPath = "phpcs";

    /**
     * Regular expression to parse PHP code sniffer installed codings standards.
     */
    private static final String InstalledCodingStandardsJustOneRegex = "The only coding standard installed is\\s+([^\\s]+)";

    /**
     * Regular expression to parse PHP code sniffer installed codings standards.
     */
    private static final String InstalledCodingStandardsTwoOrMoreRegex = "The installed coding standards are\\s+([^\\s,]+)((\\s*,\\s*[^\\s,]+)*)\\s+and\\s+([^\\s,]+)";

    /**
     * Pattern to parse PHP code sniffer installed codings standards.
     */
    private static final Pattern installedCodingStandardsJustOnePattern = Pattern.compile(InstalledCodingStandardsJustOneRegex);
    
    /**
     * Pattern to parse PHP code sniffer installed codings standards.
     */
    private static final Pattern installedCodingStandardsTwoOrMorePattern = Pattern.compile(InstalledCodingStandardsTwoOrMoreRegex);

    /**
     * PHP Code Sniffer script path.
     */
    protected String scriptPath;

    /**
     * Current coding standard against which the code is validated.
     */
    protected String codingStandard;

    /**
     * Current PHP Code Sniffer report type.
     */
    protected CodeSnifferReportType reportType;

    /**
     * Current severity strictness threshold.
     */
    protected CodingStandardViolationSeverity strictnessThreshold;

    /**
     * List of installed coding standards.
     */
    private String[] installedCodingStandards;

    /**
     * Creates a new CodeSniffer object.
     */
    public CodeSniffer() {
        scriptPath = DefaultScriptPath;
        codingStandard = "";
        installedCodingStandards = new String[0];
        reportType = CodeSnifferReportType.Full;
        strictnessThreshold = CodingStandardViolationSeverity.Error;
    }

    @Override
    public CodingStandardValidationReport validate(FileObject file) throws CodingStandardValidatorException {
        CodingStandardValidationReportBuilder reportBuilder = new CodingStandardValidationReportBuilder();
        ArrayList<String> parameters = new ArrayList<String>();

        reportBuilder.setValidator(VALIDATOR_ID);
        reportBuilder.setCodingStandard(codingStandard);
        reportBuilder.setFileObject(file);

        if (!("".equals(codingStandard))) {
            parameters.add("--standard=" + codingStandard);
        }
        switch (reportType) {
            case Full:
                parameters.add("--report=full");
                break;
            case Xml:
                parameters.add("--report=xml");
                break;
        }
        parameters.add(FileUtil.toFile(file).getAbsolutePath());

        StringBuilder output = new StringBuilder();
        try {
            int result = run(parameters, output);
            reportBuilder.setIsValid(result == 0);
            CodeSnifferReportParser parser = parserFactory(reportType);
            if (parser != null
                    && parser.parse(output.toString())) {
                reportBuilder.setViolations(parser.getViolations());
            }
        } catch (Exception e) {
            CodingStandardValidatorException csve = new CodingStandardValidatorException();
            csve.initCause(e);
            throw csve;
        }

        return reportBuilder.getReport();
    }

    @Override
    public CodingStandardViolationSeverity getStrictnessThreshold() {
        return strictnessThreshold;
    }

    @Override
    public void setStrictnessThreshold(CodingStandardViolationSeverity threshold) {
        strictnessThreshold = threshold;
    }

    /**
     * Gets the PHP Code Sniffer script path.
     *
     * @return PHP Code Sniffer path.
     */
    public String getScriptPath() {
        return scriptPath;
    }

    /**
     * Gets the PHP Code Sniffer script path.
     *
     * @param scriptPath PHP Code Sniffer path.
     */
    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    /**
     * Gets the current coding standard against which the code is validated.
     *
     * @return Coding standard name.
     */
    public String getCodingStandardName() {
        return codingStandard;
    }

    /**
     * Sets the current coding standard against which the code is validated.
     *
     * @param codingStandard Coding standard name.
     */
    public void setCodingStandardName(String codingStandardName) {
        this.codingStandard = codingStandardName;
    }

    /**
     * Gets the type of report outputted by PHP Code Sniffer.
     *
     * @return Report type.
     */
    public CodeSnifferReportType getReportType() {
        return reportType;
    }

    /**
     * Sets the type of report outputted by PHP Code Sniffer.
     *
     * @param reportType Report type.
     */
    public void setReportType(CodeSnifferReportType reportType) {
        this.reportType = reportType;
    }

    /**
     * Gets the last loaded installed coding standards.
     *
     * @return Coding standards names.
     */
    public String[] getInstalledCodingStandards() {
        return installedCodingStandards;
    }

    /**
     * Ask PHP Code Sniffer looking for installed coding standards.
     *
     * @return true if the request could be completed successfully.
     *         false otherwise.
     * 
     * @throws CodingStandardValidatorException
     */
    public boolean loadInstalledCodingStandards() throws CodingStandardValidatorException {
        ArrayList<String> codingStandardsNames = new ArrayList<String>();
        ArrayList<String> parameters = new ArrayList<String>();

        parameters.add("-i");
        try {
            StringBuilder output = new StringBuilder();
            run(parameters, output);
            String output2 = output.toString().trim();
            Matcher matcherJustOne = installedCodingStandardsJustOnePattern.matcher(output2);
            Matcher matcherTwoOrMore = installedCodingStandardsTwoOrMorePattern.matcher(output2);
            if (matcherTwoOrMore.matches()) {
                codingStandardsNames.add(matcherTwoOrMore.group(1));
                if (!matcherTwoOrMore.group(2).equals("")) {
                    codingStandardsNames.addAll(Arrays.asList(matcherTwoOrMore.group(2).replaceFirst("\\s*,\\s*", "").split("\\s*,\\s")));
                }
                codingStandardsNames.add(matcherTwoOrMore.group(4));
            } else if (matcherJustOne.matches()) {
                codingStandardsNames.add(matcherJustOne.group(1));
            }
        } catch (Exception e) {
            installedCodingStandards = new String[0];
            CodingStandardValidatorException csve = new CodingStandardValidatorException();
            csve.initCause(e);
            throw csve;
        }

        installedCodingStandards = codingStandardsNames.toArray(new String[codingStandardsNames.size()]);
        return true;
    }

    /**
     * Runs PHP Code Sniffer
     *
     * @param parameters Parameters to PHP Code Sniffer
     * @param output StringBuilder to be filled with the script output.
     *
     * @return Script returned value.
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    private int run(ArrayList<String> parameters, StringBuilder output) throws IOException, InterruptedException {
        int result = -1;
        parameters.add(0, scriptPath);
        String[] command = parameters.toArray(new String[parameters.size()]);
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line = br.readLine();
        while (line != null) {
            output.append(line);
            output.append("\n");
            line = br.readLine();
        }
        result = process.waitFor();

        return result;
    }

    /**
     * Creates a report parser given a report type.
     *
     * @param reportType Type of report.
     *
     * @return Report parser.
     */
    private CodeSnifferReportParser parserFactory(CodeSnifferReportType reportType) {
        CodeSnifferReportParser parser = null;

        switch (reportType) {
            case Xml:
                parser = new CodeSnifferXmlReportParser();
                break;
            case Full:
            case Default:
                parser = new CodeSnifferFullReportParser();
                break;
        }

        return parser;
    }
}
