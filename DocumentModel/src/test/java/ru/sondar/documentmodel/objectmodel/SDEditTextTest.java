package ru.sondar.documentmodel.objectmodel;

import ru.sondar.documentmodel.objectmodel.SDEditText;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.objectmodel.SDEditText;
import static ru.sondar.documentmodel.objectmodel.TestVariables.testFolder;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

/**
 *
 * @author GlebZemnieks
 */
public class SDEditTextTest {

    SDEditText text = new SDEditText();

    @Before
    public void setUp() {
        text = new SDEditText();
        text.setText("test");
        text.setTextLength(20);
        text.setID(12);
    }

    /**
     * Test of getText method, of class SDEditText.
     */
    @Test
    public void testGetText() {
        assertEquals("test", text.getText());
    }

    /**
     * Test of setText method, of class SDEditText.
     */
    @Test
    public void testSetText() {
        assertEquals("test", text.getText());
        text.setText("testtest");
        assertEquals("testtest", text.getText());
    }

    /**
     * Test of getTextLength method, of class SDEditText.
     */
    @Test
    public void testGetTextLength() {
        assertEquals(20, text.getTextLength());
    }

    /**
     * Test of setTextLength method, of class SDEditText.
     */
    @Test
    public void testSetTextLength() {
        assertEquals(20, text.getTextLength());
        text.setTextLength(10);
        assertEquals(10, text.getTextLength());
    }

    /**
     * Test of parseCurrentObjectField method, of class SDEditText.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField() throws Exception {
        try {
            this.text.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "editText_1.txt"));
        } catch (ObjectStructureException error) {
            return;
        }
        fail("No exception");
    }

    /**
     * 2 Test of parseCurrentObjectField method, of class SDEditText.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField2() throws Exception {
        try {
            this.text.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "editText_2.txt"));
        } catch (ObjectStructureException error) {
            return;
        }
        fail("No exception");
    }

    /**
     * 3 Test of parseCurrentObjectField method, of class SDEditText.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField3() throws Exception {
        this.text.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "editText_3.txt"));
    }

    /**
     * Test of printCurrentObjectField method, of class SDEditText.
     *
     * @throws ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\editText_temp.txt", false);
        this.text.printObjectToXML(fileModule);
        fileModule.close();
        SDEditText text2 = new SDEditText();
        text2.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "editText_temp.txt"));
        assertEquals(this.text.getText(), text2.getText());
        assertEquals(this.text.getID(), text2.getID());
        assertEquals(this.text.getTextLength(), text2.getTextLength());

    }

}
