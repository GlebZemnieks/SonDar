package ru.sondar.documentmodel.objectmodel;

import ru.sondar.documentmodel.objectmodel.SDCheckBox;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.objectmodel.SDCheckBox;
import static ru.sondar.documentmodel.objectmodel.TestVariables.testFolder;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

/**
 *
 * @author GlebZemnieks
 */
public class SDCheckBoxTest {

    SDCheckBox box = new SDCheckBox();

    @Before
    public void setUp() {
        box = new SDCheckBox();
        box.setText("test");
        box.setID(16);
        box.setChecked(true);
    }

    /**
     * Test of getText method, of class SDCheckBox.
     */
    @Test
    public void testGetText() {
        assertEquals("test", box.getText());
    }

    /**
     * Test of setText method, of class SDCheckBox.
     */
    @Test
    public void testSetText() {
        assertEquals("test", box.getText());
        box.setText("test2");
        assertEquals("test2", box.getText());
    }

    /**
     * Test of getChecked method, of class SDCheckBox.
     */
    @Test
    public void testGetChecked() {
        assertEquals(true, box.getChecked());
    }

    /**
     * Test of setChecked method, of class SDCheckBox.
     */
    @Test
    public void testSetChecked() {
        assertEquals(true, box.getChecked());
        box.setChecked(false);
        assertEquals(false, box.getChecked());
    }

    /**
     * Test of getSelectedItem method, of class SDSpinner.
     */
    @Test
    public void testSupportDependencyInterface() {
        assertEquals("true", this.box.getValue());
        this.box.setValue("False");
        assertEquals("false", this.box.getValue());
        this.box.setValue("True");
        assertEquals("true", this.box.getValue());
    }

    /**
     * Test of parseCurrentObjectField method, of class SDCheckBox.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField() throws Exception {
        try {
            this.box.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "check_1.txt"));
        } catch (ObjectStructureException errException) {
            return;
        }
        fail("No exception");
    }

    /**
     * 2 Test of parseCurrentObjectField method, of class SDCheckBox.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField2() throws Exception {
        try {
            this.box.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "check_2.txt"));
        } catch (ObjectStructureException exception) {
            return;
        }
        fail("No exception");
    }

    /**
     * 3 Test of parseCurrentObjectField method, of class SDCheckBox.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField3() throws Exception {
        this.box.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "check_3.txt"));
        assertEquals(false, box.getChecked());
        assertEquals("testtest", box.getText());
        assertEquals(18, box.getID());
    }

    /**
     * Test of printCurrentObjectField method, of class SDCheckBox.
     *
     * @throws ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\check_temp.txt");
        box.printObjectToXML(fileModule);
        fileModule.close();
        SDCheckBox box2 = new SDCheckBox();
        box2.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "check_temp.txt"));
        assertEquals(box.getChecked(), box2.getChecked());
        assertEquals(box.getText(), box2.getText());
        assertEquals(box.getID(), box2.getID());
    }

}
