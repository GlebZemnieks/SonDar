/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.core.objectmodel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.objectmodel.exception.NoAttributeException;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

/**
 *
 * @author GlebZemnieks
 */
public class SDTextTest {

    public SDText text;

    @Before
    public void setUp() {
        this.text = new SDText();
        this.text.setText("test");
    }

    /**
     * Test of getText method, of class SDText.
     */
    @Test
    public void testGetText() {
        assertEquals("test", this.text.getText());
    }

    /**
     * Test of setText method, of class SDText.
     */
    @Test
    public void testSetText() {
        assertEquals("test", this.text.getText());
        this.text.setText("hello");
        assertEquals("hello", this.text.getText());
    }

    /**
     * Test of parseCurrentObjectField method, of class SDText.
     *
     * @throws NoAttributeException
     */
    @Test
    public void testParseCurrentObjectField() throws ObjectStructureException {
        try {
            this.text.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "text_1.txt"));
        } catch (ObjectStructureException error) {
            return;
        }
        fail("No exception");
    }

    /**
     * Test of printCurrentObjectField method, of class SDText.
     *
     * @throws NoAttributeException
     */
    @Test
    public void testPrintCurrentObjectField() throws ObjectStructureException {
        this.text.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "text_2.txt"));
    }

}
