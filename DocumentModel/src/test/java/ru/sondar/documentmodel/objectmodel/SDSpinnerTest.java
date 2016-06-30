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
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

/**
 *
 * @author GlebZemnieks
 */
public class SDSpinnerTest {

    SDSpinner spinner = new SDSpinner();

    @Before
    public void setUp() {
        spinner = new SDSpinner();
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
        spinner.setList(new String[]{"item1", "item2", "item3"});
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
        this.spinner.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "spinner_3.txt"));
    }
    
    /**
     * 3 Test of parseCurrentObjectField method, of class SDSpinner.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseWordsBase() throws Exception {
        SDDocument document = new SDDocument();
        WordsBase base = new WordsBase();
        ArrayList<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        base.addNewBase("test", list);
        document.setWordsBasePart(base);
        SDSequenceObject sequence = new SDSequenceObject();
        spinner.sequence = sequence;
        sequence.document = document;
        this.spinner.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "spinner_4.txt"));
        assertEquals(spinner.getList().size(), list.size());
        assertEquals(spinner.getList().get(0), list.get(0));
        assertEquals(spinner.getList().get(1), list.get(1));
    }

    /**
     * Test of printCurrentObjectField method, of class SDSpinner.
     *
     * @throws
     * ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\spinner_temp.txt", false);
        this.spinner.printObjectToXML(fileModule);
        fileModule.close();
        SDSpinner spinner2 = new SDSpinner();
        spinner2.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "spinner_temp.txt"));
        assertEquals(spinner.getList().size(), spinner2.getList().size());
        assertEquals(spinner.getList().get(0), spinner2.getList().get(0));
        assertEquals(spinner.getList().get(1), spinner2.getList().get(1));
        assertEquals(spinner.getDefaultItemSelected(), spinner2.getDefaultItemSelected());
        assertEquals(spinner.getSelectedItem(), spinner2.getSelectedItem());

    }

}
