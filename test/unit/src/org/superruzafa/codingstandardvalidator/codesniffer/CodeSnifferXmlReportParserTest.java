/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superruzafa.codingstandardvalidator.codesniffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openide.util.Exceptions;
import org.superruzafa.codingstandardvalidator.CodingStandardViolation;
import org.superruzafa.codingstandardvalidator.CodingStandardViolationSeverity;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

/**
 *
 * @author Alfonso
 */
public class CodeSnifferXmlReportParserTest {

    private static String xmlReport;
    private CodeSnifferXmlReportParser parser;

    public CodeSnifferXmlReportParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        try {
            xmlReport = "";
            InputStreamReader isr = new InputStreamReader(CodeSnifferFullReportParserTest.class.getResourceAsStream("xmlreport.sample.xml"));
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                xmlReport += line + "\n";
                line = br.readLine();
            }
        } catch (IOException ex) {
            xmlReport = "";
            Exceptions.printStackTrace(ex);
            throw ex;
        }
    }

    @Before
    public void setUp() {
        parser = new CodeSnifferXmlReportParser();
    }

    @After
    public void tearDown() {
        parser = null;
    }

    /**
     * Test of parse method, of class CodeSnifferXmlReportParser.
     */
    @Test
    public void testParse() {
        boolean parsed = parser.parse(xmlReport);
        assertTrue(parsed);

        CodingStandardViolation[] violations = parser.getViolations();
        assertEquals(6, violations.length);

        assertEquals(8, violations[0].getLine());
        assertEquals(1, violations[0].getColumn());
        assertEquals(CodingStandardViolationSeverity.Error, violations[0].getSeverity());
        assertEquals("@category tag comment indented incorrectly; expected 1 spaces but found 3", violations[0].getMessage());

        assertEquals(15, violations[4].getLine());
        assertEquals(5, violations[4].getColumn());
        assertEquals(CodingStandardViolationSeverity.Warning, violations[4].getSeverity());
        assertEquals("PHP version not specified", violations[4].getMessage());
    }

    /**
     * Test of parseViolationNode method, of class CodeSnifferXmlReportParser.
     */
    @Test
    public void testParseViolationNode() throws ParserConfigurationException {

        CodingStandardViolation violation = null;
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder bd = fact.newDocumentBuilder();
        Document doc = bd.newDocument();

        Element node = (Element)doc.createElement("not-error-nor-warnign");
        violation = parser.parseViolationNode(node);
        assertNull(violation);

        node = (Element)doc.createElement("error");
        node.setAttribute("line", "123");
        node.setAttribute("column", "456");
        node.appendChild( doc.createTextNode("Error message") );
        violation = parser.parseViolationNode(node);
        assertNotNull(violation);
        assertEquals(CodingStandardViolationSeverity.Error, violation.getSeverity());
        assertEquals(123, violation.getLine());
        assertEquals(456, violation.getColumn());
        assertEquals("Error message", violation.getMessage());

        node = (Element)doc.createElement("warning");
        node.setAttribute("line", "987");
        node.setAttribute("column", "654");
        node.appendChild( doc.createTextNode("Warning message") );
        violation = parser.parseViolationNode(node);
        assertNotNull(violation);
        assertEquals(CodingStandardViolationSeverity.Warning, violation.getSeverity());
        assertEquals(987, violation.getLine());
        assertEquals(654, violation.getColumn());
        assertEquals("Warning message", violation.getMessage());
    }
}
