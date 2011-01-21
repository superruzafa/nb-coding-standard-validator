/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.superruzafa.codingstandardvalidator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alfonso
 */
public class CodingStandardViolationTest {

    private CodingStandardViolation violation;

    public CodingStandardViolationTest() {
    }

    @Before
    public void setUp() {
        violation = new CodingStandardViolation(100, "Error 100", CodingStandardViolationSeverity.Error);
    }

    @After
    public void tearDown() {
        violation = null;
    }

    /**
     * Test of getLine method, of class CodingStandardViolation.
     */
    @Test
    public void testGetLine() {
        assertEquals(100, violation.getLine());
    }

    /**
     * Test of getMessage method, of class CodingStandardViolation.
     */
    @Test
    public void testGetMessage() {
        assertEquals("Error 100", violation.getMessage());
    }

    /**
     * Test of getSeverity method, of class CodingStandardViolation.
     */
    @Test
    public void testGetSeverity() {
        assertEquals(CodingStandardViolationSeverity.Error, violation.getSeverity());
    }

}