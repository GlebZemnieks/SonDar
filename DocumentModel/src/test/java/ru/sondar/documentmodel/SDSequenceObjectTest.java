package ru.sondar.documentmodel;

import ru.sondar.documentmodel.SDSequenceObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static ru.sondar.documentmodel.TestVariables.testFolder;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.exception.ObjectNotFountException;
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
        text.setObjectName("text1");
        sequence.AddXMLObject(text);
        SDCheckBox box = new SDCheckBox();
        box.setText("test");
        box.setChecked(true);
        sequence.AddXMLObject(box);
        SDEditText edit = new SDEditText();
        edit.setText("test");
        edit.setObjectName("edit1");
        edit.setTextLength(10);
        sequence.AddXMLObject(edit);
        sequence.enumirateSequence(0);
    }
    
    /**
     * Test of getXMLObjectByName method, of class SDSequenceObject.
     */
    @Test
    public void testisExistByName() {
        assertEquals(true, sequence.isObjectWithNameExist("text1"));
        assertEquals(true, sequence.isObjectWithNameExist("edit1"));
        assertEquals(false, sequence.isObjectWithNameExist(""));
        assertEquals(false, sequence.isObjectWithNameExist("123"));
        assertEquals(false, sequence.isObjectWithNameExist("a1"));
        assertEquals(false, sequence.isObjectWithNameExist("1a"));
        assertEquals(false, sequence.isObjectWithNameExist("evrilhbevlhbefklvhbdlkfbvlkehbrlhvbdlfkbhv'sdb;dscvi"));
        assertEquals(false, sequence.isObjectWithNameExist("test"));
    }

    /**
     * Test of getXMLObjectByName method, of class SDSequenceObject.
     */
    @Test
    public void testGetXMLObjectByName() {
        assertEquals(SDText.class, sequence.getXMLObjectByName("text1").getClass());
        assertEquals(SDEditText.class, sequence.getXMLObjectByName("edit1").getClass());
    }

    /**
     * Test of getXMLObjectByName method, of class SDSequenceObject.
     */
    @Test
    public void testGetXMLObjectByName2() {
        try {
            sequence.getXMLObjectByName("test");
        } catch (ObjectNotFountException error) {
            return;
        }
        fail("No exception");
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
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "SequenceTest\\sequence_temp.txt", false);
        this.sequence.printSequence(fileModule);
        fileModule.close();
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
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "SequenceTest\\div_object_temp.txt", false);
        this.sequence.printObjectToXML(fileModule);
        fileModule.close();
    }

    /**
     * Test of getObjectByType method, of class SDSequenceObject.
     */
    @Test
    public void testGetObjectByType() {
        assertEquals(SDCheckBox.class, SDMainObjectType.CheckBox.getObjectByType().getClass());
        assertEquals(SDDate.class, SDMainObjectType.Date.getObjectByType().getClass());
        assertEquals(SDSequenceObject.class, SDMainObjectType.DivContainer.getObjectByType().getClass());
        assertEquals(SDEditText.class, SDMainObjectType.EditText.getObjectByType().getClass());
        assertEquals(SDEndln.class, SDMainObjectType.EndLn.getObjectByType().getClass());
        assertEquals(SDSpinner.class, SDMainObjectType.Spinner.getObjectByType().getClass());
        assertEquals(SDText.class, SDMainObjectType.Text.getObjectByType().getClass());
    }

}
