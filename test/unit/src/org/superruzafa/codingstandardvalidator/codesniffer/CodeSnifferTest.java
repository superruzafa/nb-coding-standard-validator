/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superruzafa.codingstandardvalidator.codesniffer;

import java.io.File;
import java.net.URISyntaxException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openide.filesystems.FileUtil;
import static org.junit.Assert.*;
import org.openide.filesystems.FileObject;
import org.superruzafa.codingstandardvalidator.*;

/**
 *
 * @author Alfonso
 */
public class CodeSnifferTest {

    private CodeSniffer codeSniffer;

    public CodeSnifferTest() {
    }

    @Before
    public void setUp() {
        codeSniffer = new CodeSniffer();
        codeSniffer.setScriptPath("C:\\php\\phpcs.bat");
    }

    @After
    public void tearDown() {
        codeSniffer = null;
    }

    /**
     * Test of validate method, of class CodeSniffer.
     */
    @Test
    public void testValidate() throws CodingStandardValidatorException, URISyntaxException {
        File file = new File(getClass().getResource("sample.php").toURI());
        FileObject fo = FileUtil.toFileObject(FileUtil.normalizeFile(file));
        codeSniffer.validate(fo);
        if (false) {
            for (CodingStandardViolation violation : codeSniffer.getViolations()) {
                System.out.println(violation.getSeverity() + " at line " + violation.getLine() + ": " + violation.getMessage());
            }
        }
    }

    @Test
    public void testLoadInstalledCodingStandards() throws CodingStandardValidatorException {
        codeSniffer.loadInstalledCodingStandards();
        if (false) {
            for (String cs : codeSniffer.getInstalledCodingStandards()) {
                System.out.println(cs);
            }
        }
    }

    /**
     * Test of getStrictnessThreshold method, of class CodeSniffer.
     */
    @Test
    public void testSetGetStrictnessThreshold() {
        codeSniffer.setStrictnessThreshold(CodingStandardViolationSeverity.Warning);
        assertEquals(CodingStandardViolationSeverity.Warning, codeSniffer.getStrictnessThreshold());
        codeSniffer.setStrictnessThreshold(CodingStandardViolationSeverity.Error);
        assertEquals(CodingStandardViolationSeverity.Error, codeSniffer.getStrictnessThreshold());
    }

    /**
     * Test of getScriptPath method, of class CodeSniffer.
     */
    @Test
    public void testSetGetCommandPath() {
        codeSniffer.setScriptPath("path1");
        assertEquals("path1", codeSniffer.getScriptPath());
        codeSniffer.setScriptPath("path2");
        assertEquals("path2", codeSniffer.getScriptPath());
    }

    /**
     * Test of getCodingStandardName method, of class CodeSniffer.
     */
    @Test
    public void testSetGetCodingStandardName() {
        codeSniffer.setCodingStandardName("codingStandard1");
        assertEquals("codingStandard1", codeSniffer.getCodingStandardName());
        codeSniffer.setCodingStandardName("codingStandard2");
        assertEquals("codingStandard2", codeSniffer.getCodingStandardName());
    }

    /**
     * Test of getReportType method, of class CodeSniffer.
     */
    @Test
    public void testSetGetReportType() {
        codeSniffer.setReportType(CodeSnifferReportType.Default);
        assertEquals(CodeSnifferReportType.Default, codeSniffer.getReportType());
        codeSniffer.setReportType(CodeSnifferReportType.Full);
        assertEquals(CodeSnifferReportType.Full, codeSniffer.getReportType());
        codeSniffer.setReportType(CodeSnifferReportType.Xml);
        assertEquals(CodeSnifferReportType.Xml, codeSniffer.getReportType());
    }
}
