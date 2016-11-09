package ru.sondar.documentmodel.objectmodel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import static ru.sondar.documentmodel.objectmodel.TestVariables.testFolder;
import ru.sondar.documentmodel.objectmodel.exception.ObjectAlreadyHaveNameException;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.serializer.XMLSerializer;

/**
 *
 * @author GlebZemnieks
 */
public class SDMainObjectTest {

    class objectForTest extends SDMainObject {

        public objectForTest() {
            this.objectType = SDMainObjectType.ErrorObject;
        }
    }

    objectForTest object = new objectForTest();

    @Before
    public void setUp() {
        object = new objectForTest();
    }

    /**
     * Test of getObjectType method, of class SDMainObject.
     */
    @Test
    public void testGetObjectType() {
        assertEquals(SDMainObjectType.ErrorObject, this.object.getObjectType());
    }

    /**
     * Test of chooseXMLType method, of class SDMainObject.
     */
    @Test
    public void testChooseXMLType() {
        assertEquals(SDMainObjectType.Text, this.object.chooseXMLType("Text"));
        assertEquals(SDMainObjectType.CheckBox, this.object.chooseXMLType("CheckBox"));
        assertEquals(SDMainObjectType.Date, this.object.chooseXMLType("Date"));
        assertEquals(SDMainObjectType.DivContainer, this.object.chooseXMLType("DivContainer"));
        assertEquals(SDMainObjectType.EditText, this.object.chooseXMLType("EditText"));
        assertEquals(SDMainObjectType.EndLn, this.object.chooseXMLType("EndLn"));
        assertEquals(SDMainObjectType.Spinner, this.object.chooseXMLType("Spinner"));
        assertEquals(SDMainObjectType.ErrorObject, this.object.chooseXMLType("sdfihsgdfhjv"));
    }

    /**
     * Test of setObjectName method, of class SDMainObject.
     */
    @Test
    public void testSetObjectName() {
        this.object.setObjectName("test");
        try {
            this.object.setObjectName("test2");
        } catch (ObjectAlreadyHaveNameException error) {
            return;
        }
        fail("No exception");
    }

    /**
     * Test of getObjectName method, of class SDMainObject.
     */
    @Test
    public void testGetObjectName() {
        this.object.setObjectName("test");
        assertEquals("test", this.object.getObjectName());
    }

    /**
     * Test of getID method, of class SDMainObject.
     */
    @Test
    public void testGetID() {
        assertEquals(0, this.object.getID());
    }

    /**
     * Test of setID method, of class SDMainObject.
     */
    @Test
    public void testSetID() {
        this.object.setID(5);
        assertEquals(5, this.object.getID());
    }

    /**
     * Test of ParseCurrentObjectField method, of class SDMainObject.
     */
    @Test
    public void testParseCurrentObjectField() {
        try {
            new XMLSerializer().parseObject(object, TestVariables.getRootElementByFile("ObjectTest", "abstract_1.txt"));
        } catch (ObjectStructureException ex) {
            return;
        }
        fail("No exception");
    }

    /**
     * 2 Test of ParseCurrentObjectField method, of class SDMainObject.
     *
     * @throws ru.sondar.core.exception.parser.ObjectStructureException
     */
    @Test
    public void testParseCurrentObjectField2() throws ObjectStructureException {
        new XMLSerializer().parseObject(object, TestVariables.getRootElementByFile("ObjectTest", "abstract_2.txt"));
        assertEquals(18, this.object.getID());
    }

    /**
     * Test of PrintCurrentObjectField method, of class SDMainObject.
     *
     * @throws ru.sondar.core.exception.parser.ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\abstract_temp.txt", false);
        new XMLSerializer().printObject(object, fileModule);
        fileModule.delFile();
        fileModule.close();
        new XMLSerializer().parseObject(object, TestVariables.getRootElementByFile("ObjectTest", "abstract_temp.txt"));
        assertEquals(0, this.object.getID());
    }

    /**
     * 2 Test of PrintCurrentObjectField method, of class SDMainObject.
     *
     * @throws ru.sondar.core.exception.parser.ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField2() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\abstract_temp.txt", false);
        this.object.setID(15);
        new XMLSerializer().printObject(object, fileModule);
        fileModule.delFile();
        fileModule.close();
        new XMLSerializer().parseObject(object, TestVariables.getRootElementByFile("ObjectTest", "abstract_temp.txt"));
        assertEquals(15, this.object.getID());
    }
}
