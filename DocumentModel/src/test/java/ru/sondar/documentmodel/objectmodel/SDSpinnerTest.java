/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.documentmodel.objectmodel;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.documentmodel.SDSequenceObject;
import static ru.sondar.documentmodel.objectmodel.TestVariables.testFolder;
import ru.sondar.core.exception.parser.NoFieldException;
import ru.sondar.core.exception.parser.ObjectStructureException;

/**
 *
 * @author GlebZemnieks
 */
public class SDSpinnerTest {

    SDSpinner spinner = new SDSpinner();

    @Before
    public void setUp() {
        SDDocument document = new SDDocument();
        SDSequenceObject sequence = new SDSequenceObject();
        document.setSequence(sequence);
        document.setWordsBasePart(getBasePart());
        spinner = new SDSpinner();
        sequence.AddXMLObject(spinner);
        spinner.setDefaultItemSelected(1);
    }

    /**
     * Test of getList method, of class SDSpinner.
     */
    @Test
    public void testGetList() {
        ArrayList<String> test = spinner.getList();
        assertEquals("item1", test.get(0));
        assertEquals("item2", test.get(1));
    }

    /**
     * Test of setList method, of class SDSpinner.
     */
    @Test
    public void testSetList() {
        ArrayList<String> test = spinner.getList();
        assertEquals("item1", test.get(0));
        assertEquals("item2", test.get(1));
        WordBase base = new WordBase();
        base.add(null, "item1");
        base.add(null, "item2");
        base.add(null, "item3");
        spinner.setList(base);
        test = spinner.getList();
        assertEquals("item1", test.get(0));
        assertEquals("item2", test.get(1));
        assertEquals("item3", test.get(2));
    }

    /**
     * Test of getDefaultItemSelected method, of class SDSpinner.
     */
    @Test
    public void testGetDefaultItemSelected() {
        assertEquals(1, spinner.getDefaultItemSelected());
    }

    /**
     * Test of setDefaultItemSelected method, of class SDSpinner.
     */
    @Test
    public void testSetDefaultItemSelected() {
        assertEquals(1, spinner.getDefaultItemSelected());
        spinner.setDefaultItemSelected(0);
        assertEquals(0, spinner.getDefaultItemSelected());
    }

    /**
     * Test of getSelectedItem method, of class SDSpinner.
     */
    @Test
    public void testGetSelectedItem() {
        assertEquals("item2", spinner.getSelectedItem());
        spinner.setDefaultItemSelected(0);
        assertEquals("item1", spinner.getSelectedItem());
    }

    /**
     * Test of getSelectedItem method, of class SDSpinner.
     */
    @Test
    public void testSupportDependencyInterface() {
        assertEquals(1, this.spinner.getValue());
        this.spinner.setValue("0");
        assertEquals(0, this.spinner.getValue());
        this.spinner.setValue("1");
        assertEquals(1, this.spinner.getValue());
        try {
            this.spinner.setValue("2");
        } catch (IllegalArgumentException errException) {
            return;
        }
        fail("No exception");
    }

    /**
     * Test of parseCurrentObjectField method, of class SDSpinner.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField() throws Exception {
        try {
            this.spinner.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "spinner_1.txt"));
        } catch (ObjectStructureException exception) {
            return;
        }
        fail("No exception");
    }

    /**
     * 2 Test of parseCurrentObjectField method, of class SDSpinner.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField2() throws Exception {
        try {
            this.spinner.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "spinner_2.txt"));
        } catch (ObjectStructureException exception) {
            return;
        }
        fail("No exception");
    }

    /**
     * 3 Test of parseCurrentObjectField method, of class SDSpinner.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField3() throws Exception {
        try {
            this.spinner.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "spinner_3.txt"));
        } catch (NoFieldException error) {
            return;
        }
        fail("No exception");
    }

    /**
     * 3 Test of parseCurrentObjectField method, of class SDSpinner.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseWordsBase() throws Exception {
        SDDocument document = new SDDocument();
        SDWordsBasePart base = new SDWordsBasePart();
        WordBase base1 = new WordBase();
        base1.add(null, "test1");
        base1.add(null, "test2");
        base.addNewBase("test", base1);
        document.setWordsBasePart(base);
        SDSequenceObject sequence = new SDSequenceObject();
        spinner.setSequence(sequence);
        sequence.document = document;
        this.spinner.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "spinner_4.txt"));
        assertEquals(spinner.getList().size(), 2);
        assertEquals(spinner.getList().get(0), "test1");
        assertEquals(spinner.getList().get(1), "test2");
    }

    /**
     * Test of printCurrentObjectField method, of class SDSpinner.
     *
     * @throws
     * ru.sondar.core.exception.parser.ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\spinner_temp.txt", false);
        this.spinner.printObjectToXML(fileModule);
        fileModule.close();
        SDSpinner spinner2 = new SDSpinner();
        try {
            spinner2.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "spinner_temp.txt"));
        } catch (NoFieldException error) {
            return;
        }
        fail("No exception");
    }
    
    public SDWordsBasePart getBasePart(){
        SDWordsBasePart wordsBase = new SDWordsBasePart();
        WordBase base = new WordBase();
        wordsBase.addNewBase("test", base);
        WordBase base2 = new WordBase();
        base2.add("test");
        base2.add("test2");
        wordsBase.addNewBase("test2", base2);
        WordBase base3 = new WordBase();
        base3.add("test");
        base3.add("test2");
        base3.add("a1","test3");
        base3.add("a1","test4");
        wordsBase.addNewBase("test3", base3);
        WordBase base4 = new WordBase();
        base4.add("test");
        base4.add("test2");
        base4.add("a1","test3");
        base4.add("a1","test4");
        base4.add("a2","test5");
        base4.add("a2","test6");
        base4.add("a2","test7");
        base4.add("a2","test8");
        wordsBase.addNewBase("test4", base4);
        return wordsBase;
    }
    
    /**
     * Test of getSelectedItem method, of class SDSpinner.
     */
    @Test
    public void testFilter() {
        spinner.setList(spinner.getSequence().document.getWordsBasePart().getList("test4"));
        assertEquals(null, spinner.getActiveFilter());
        assertEquals(8, spinner.getList().size());
        assertEquals(1, spinner.getDefaultItemSelected());
        assertEquals("test2", spinner.getSelectedItem());
        spinner.setActiveFilter("a1");
        assertEquals("a1", spinner.getActiveFilter());
        assertEquals(0, spinner.getDefaultItemSelected());
        spinner.setDefaultItemSelected(3);
        assertEquals("test4", spinner.getSelectedItem());
        spinner.setActiveFilter("a2");
        assertEquals(0, spinner.getDefaultItemSelected());
        spinner.setDefaultItemSelected(3);
        assertEquals("test6", spinner.getSelectedItem());
    }
}
