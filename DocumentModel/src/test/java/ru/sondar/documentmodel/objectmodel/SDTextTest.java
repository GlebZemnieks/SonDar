package ru.sondar.documentmodel.objectmodel;

import ru.sondar.documentmodel.objectmodel.SDText;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.objectmodel.SDText;
import static ru.sondar.documentmodel.objectmodel.TestVariables.testFolder;
import ru.sondar.documentmodel.objectmodel.exception.NoAttributeException;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

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
        this.text.setID(14);
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
     * 2 Test of parseCurrentObjectField method, of class SDText.
     *
     * @throws NoAttributeException
     */
    @Test
    public void testParseCurrentObjectField2() throws ObjectStructureException {
        this.text.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "text_2.txt"));
    }

    /**
     * Test of printCurrentObjectField method, of class SDText.
     *
     * @throws NoAttributeException
     */
    @Test
    public void testPrintCurrentObjectField() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\text_temp.txt", false);
        this.text.printObjectToXML(fileModule);
        fileModule.close();
        SDText text2 = new SDText();
        text2.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "text_temp.txt"));
        assertEquals(this.text.getText(), text2.getText());
        assertEquals(this.text.getID(), text2.getID());

    }

    /**
     * 2 Test of printCurrentObjectField method, of class SDText.
     *
     * @throws NoAttributeException
     */
    @Test
    public void testPrintCurrentObjectField2() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\text_temp2.txt", false);
        this.text.printObjectToXML(fileModule);
        fileModule.close();
        SDText text2 = new SDText();
        text2.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "text_temp.txt"));
        assertEquals(this.text.getText(), text2.getText());
        assertEquals(this.text.getID(), text2.getID());

    }

}
