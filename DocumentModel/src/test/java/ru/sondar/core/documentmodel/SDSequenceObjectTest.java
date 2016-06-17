/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.core.documentmodel;

import ru.sondar.documentmodel.SDSequenceObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static ru.sondar.core.documentmodel.TestVariables.testFolder;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.objectmodel.SDCheckBox;
import ru.sondar.documentmodel.objectmodel.SDDate;
import ru.sondar.documentmodel.objectmodel.SDEditText;
import ru.sondar.documentmodel.objectmodel.SDEndln;
import ru.sondar.documentmodel.objectmodel.SDMainObjectType;
import ru.sondar.documentmodel.objectmodel.SDSpinner;
import ru.sondar.documentmodel.objectmodel.SDText;

/**
 *
 * @author GlebZemnieks
 */
public class SDSequenceObjectTest {

    SDSequenceObject sequence = new SDSequenceObject();

    @Before
    public void setUp() {
        sequence = new SDSequenceObject();
        SDText text = new SDText();
        text.setText("test");
        sequence.AddXMLObject(text);
        SDCheckBox box = new SDCheckBox();
        box.setText("test");
        box.setChecked(true);
        sequence.AddXMLObject(box);
        SDEditText edit = new SDEditText();
        edit.setText("test");
        edit.setTextLength(10);
        sequence.AddXMLObject(edit);
        sequence.enumirateSequence(0);
    }

    /**
     * Test of getXMLObjectByName method, of class SDSequenceObject.
     */
    @Test
    public void testGetXMLObjectByName() {
    }

    /**
     * Test of getXMLObject method, of class SDSequenceObject.
     */
    @Test
    public void testGetXMLObject() {
        assertEquals(3, sequence.getSequenceSize());
        assertEquals(SDText.class, sequence.getXMLObject(0).getClass());
        assertEquals(SDCheckBox.class, sequence.getXMLObject(1).getClass());
        assertEquals(SDEditText.class, sequence.getXMLObject(2).getClass());

    }

    /**
     * Test of enumirateSequence method, of class SDSequenceObject.
     */
    @Test
    public void testEnumirateSequence() {
        assertEquals(3, sequence.getSequenceSize());
        assertEquals(0, sequence.getXMLObject(0).getID());
        assertEquals(1, sequence.getXMLObject(1).getID());
        assertEquals(2, sequence.getXMLObject(2).getID());
    }

    /**
     * Test of AddXMLObject method, of class SDSequenceObject.
     */
    @Test
    public void testAddXMLObject() {
        sequence.AddXMLObject(new SDDate());
        sequence.enumirateSequence(0);
        assertEquals(4, sequence.getSequenceSize());
        assertEquals(SDText.class, sequence.getXMLObject(0).getClass());
        assertEquals(SDCheckBox.class, sequence.getXMLObject(1).getClass());
        assertEquals(SDEditText.class, sequence.getXMLObject(2).getClass());
        assertEquals(SDDate.class, sequence.getXMLObject(3).getClass());
    }

    /**
     * Test of parseSequence method, of class SDSequenceObject.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseSequence() throws Exception {
        this.sequence.parseSequence(TestVariables.getRootElementByFile("SequenceTest", "sequence_1.txt"));
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "SequenceTest\\sequence_temp.txt");
        this.sequence.printSequence(fileModule);
        fileModule.close();
    }

    /**
     * Test of printSequence method, of class SDSequenceObject.
     */
    @Test
    public void testPrintSequence() {
    }

    /**
     * Test of parseCurrentObjectField method, of class SDSequenceObject.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField() throws Exception {
        this.sequence.parseObjectFromXML(TestVariables.getRootElementByFile("SequenceTest", "div_object_1.txt"));
        assertEquals(2, sequence.getSequenceSize());
        assertEquals(0, sequence.getXMLObject(0).getID());
        assertEquals(1, sequence.getXMLObject(1).getID());
        assertEquals(SDText.class, sequence.getXMLObject(0).getClass());
        assertEquals(SDDate.class, sequence.getXMLObject(1).getClass());
    }

    /**
     * Test of printCurrentObjectField method, of class SDSequenceObject.
     */
    @Test
    public void testPrintCurrentObjectField() {
        //Так надо
        sequence.setID(-1);
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "SequenceTest\\div_object_temp.txt");
        this.sequence.printObjectToXML(fileModule);
        fileModule.close();
    }

    /**
     * Test of getObjectByType method, of class SDSequenceObject.
     */
    @Test
    public void testGetObjectByType() {
        assertEquals(SDCheckBox.class, sequence.getObjectByType(SDMainObjectType.CheckBox).getClass());
        assertEquals(SDDate.class, sequence.getObjectByType(SDMainObjectType.Date).getClass());
        assertEquals(SDSequenceObject.class, sequence.getObjectByType(SDMainObjectType.DivContainer).getClass());
        assertEquals(SDEditText.class, sequence.getObjectByType(SDMainObjectType.EditText).getClass());
        assertEquals(SDEndln.class, sequence.getObjectByType(SDMainObjectType.EndLn).getClass());
        assertEquals(SDSpinner.class, sequence.getObjectByType(SDMainObjectType.Spinner).getClass());
        assertEquals(SDText.class, sequence.getObjectByType(SDMainObjectType.Text).getClass());
    }

}
