/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.superruzafa.codingstandardvalidator.codesniffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.openide.util.Exceptions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.superruzafa.codingstandardvalidator.CodingStandardViolation;
import org.superruzafa.codingstandardvalidator.CodingStandardViolationSeverity;
import static org.junit.Assert.*;

/**
 *
 * @author Alfonso
 */
public class CodeSnifferFullReportParserTest {

    private CodeSnifferFullReportParser parser;
    private static String fullReport = "";

    @BeforeClass
    public static void beforeClass() throws IOException
    {
        try {
            fullReport = "";
            InputStreamReader isr = new InputStreamReader(CodeSnifferFullReportParserTest.class.getResourceAsStream("fullreport.sample.txt"));
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                fullReport += line + "\n";
                line = br.readLine();
            }
        } catch (IOException ex) {
            fullReport = "";
            Exceptions.printStackTrace(ex);
            throw ex;
        }
    }

    @Before
    public void setUp() {
        parser = new CodeSnifferFullReportParser();
    }

    @After
    public void tearDown() {
        parser = null;
    }

    /**
     * Test of parse method, of class CodeSnifferFullReportParser.
     */
    @Test
    public void testParse() {

        boolean parsed = parser.parse(fullReport);
        assertTrue(parsed);

        CodingStandardViolation[] violations = parser.getViolations();
        assertEquals(9, violations.length);
        
        assertEquals(1, violations[0].getLine());
        assertEquals("There must be exactly one blank line before the tags in file comment", violations[0].getMessage());
        assertEquals(CodingStandardViolationSeverity.Error, violations[0].getSeverity());

        assertEquals(2, violations[1].getLine());
        assertEquals("PHP version not specified", violations[1].getMessage());
        assertEquals(CodingStandardViolationSeverity.Warning, violations[1].getSeverity());

        assertEquals(9, violations[8].getLine());
        assertEquals("Missing class doc comment", violations[8].getMessage());
        assertEquals(CodingStandardViolationSeverity.Error, violations[8].getSeverity());
    }

}