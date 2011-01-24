/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superruzafa.codingstandardvalidator;

import org.openide.filesystems.FileObject;
import java.io.File;
import org.openide.filesystems.FileUtil;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alfonso
 */
public class CodingStandardValidationReportBuilderTest {

    private CodingStandardValidationReportBuilder builder;

    @Before
    public void setUp() {
        builder = new CodingStandardValidationReportBuilder();
    }

    @After
    public void tearDown() {
        builder = null;
    }

    /**
     * Test of setIsValid method, of class CodingStandardValidationReportBuilder.
     */
    @Test
    public void testSetIsValid() {
        builder.setIsValid(true);
        assertTrue(builder.getReport().isValid());
        builder.setIsValid(false);
        assertFalse(builder.getReport().isValid());
    }

    /**
     * Test of setFileObject method, of class CodingStandardValidationReportBuilder.
     */
    @Test
    public void testSetFileObject() {
        File file = new File("fullreport.sample.txt");
        FileObject fileObject = FileUtil.toFileObject(FileUtil.normalizeFile(file));
        builder.setFileObject(fileObject);
        assertEquals(fileObject, builder.getReport().getFileObject());
    }

    /**
     * Test of setCodingStandard method, of class CodingStandardValidationReportBuilder.
     */
    @Test
    public void testSetCodingStandard() {
        builder.setCodingStandard("MyCodingStandard");
        assertEquals("MyCodingStandard", builder.getReport().getCodingStandard());
    }

    /**
     * Test of setViolations method, of class CodingStandardValidationReportBuilder.
     */
    @Test
    public void testSetViolations() {
        CodingStandardViolation[] violations = new CodingStandardViolation[]{
            new CodingStandardViolation(1, "MyMessage", CodingStandardViolationSeverity.Error)
        };
        builder.setViolations(violations);
        assertArrayEquals(violations, builder.getReport().getViolations());
    }

    /**
     * Test of setValidator method, of class CodingStandardValidationReportBuilder.
     */
    @Test
    public void testSetValidator() {
        builder.setValidator("MyValidator");
        assertEquals("MyValidator", builder.getReport().getValidator());
    }

    /**
     * Test of setDateTime method, of class CodingStandardValidationReportBuilder.
     */
    @Test
    public void testSetDateTime() {
        Date date = new Date();
        builder.setDateTime(date);
        assertEquals(date, builder.getReport().getDateTime());
    }
}
