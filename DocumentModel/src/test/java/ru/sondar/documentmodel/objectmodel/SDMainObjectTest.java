package ru.sondar.documentmodel.objectmodel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Element;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.documentfactory.InternalFunctionFactory;
import ru.sondar.documentmodel.internalfunction.Function;
import ru.sondar.documentmodel.internalfunction.InternalFunction;
import ru.sondar.documentmodel.internalfunction.function.CustomFunction;
import static ru.sondar.documentmodel.objectmodel.TestVariables.testFolder;
import ru.sondar.documentmodel.objectmodel.exception.ObjectAlreadyHaveNameException;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

/**
 *
 * @author GlebZemnieks
 */
public class SDMainObjectTest {

    class objectForTest extends SDMainObject {

        public objectForTest() {
            this.objectType = SDMainObjectType.ErrorObject;
        }

        @Override
        protected void parseCurrentObjectField(Element element) {
        }

        @Override
        public void printCurrentObjectField(FileModuleWriteThreadInterface fileModule) {
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
            this.object.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "abstract_1.txt"));
        } catch (ObjectStructureException ex) {
            return;
        }
        fail("No exception");
    }

    /**
     * 2 Test of ParseCurrentObjectField method, of class SDMainObject.
     *
     * @throws
     * ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException
     */
    @Test
    public void testParseCurrentObjectField2() throws ObjectStructureException {
        this.object.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "abstract_2.txt"));
        assertEquals(18, this.object.getID());
    }

    /**
     * Test of PrintCurrentObjectField method, of class SDMainObject.
     *
     * @throws
     * ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\abstract_temp.txt");
        this.object.printObjectToXML(fileModule);
        fileModule.delFile();
        fileModule.close();
        this.object.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "abstract_temp.txt"));
        assertEquals(0, this.object.getID());
    }

    /**
     * 2 Test of PrintCurrentObjectField method, of class SDMainObject.
     *
     * @throws
     * ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField2() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\abstract_temp.txt");
        this.object.setID(15);
        this.object.printObjectToXML(fileModule);
        fileModule.delFile();
        fileModule.close();
        this.object.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "abstract_temp.txt"));
        assertEquals(15, this.object.getID());
    }

    /**
     * 2 Test of PrintCurrentObjectField method, of class SDMainObject.
     *
     * @throws
     * ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField3() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\abstract_temp2.txt");
        this.object.setID(15);
        InternalFunction function = InternalFunctionFactory.getInternalFunction();
        function.AddXMLObject(InternalFunctionFactory.getCustomFunction());
        this.object.setInternalFunction(function);
        this.object.printObjectToXML(fileModule);
        fileModule.delFile();
        fileModule.close();
        this.object.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "abstract_temp2.txt"));
        System.out.println(this.object.function.toString());
        assertEquals(15, this.object.getID());
    }

    /**
     * 2 Test of PrintCurrentObjectField method, of class SDMainObject.
     *
     * @throws ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField4() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\abstract_temp3.txt");
        this.object.setID(15);
        InternalFunction function = InternalFunctionFactory.getInternalFunction();
        Function custom = InternalFunctionFactory.getCustomFunction();
        custom.AddAction(InternalFunctionFactory.getCheckerAction(null, null, null));
        custom.AddAction(InternalFunctionFactory.getCheckerAction(null, 12, "test", "hello"));
        custom.AddAction(InternalFunctionFactory.getSetterAction(null, null, null));
        custom.AddAction(InternalFunctionFactory.getSetterAction(null, 11, "test2", InternalFunctionFactory.getCheckerAction(null, null, null)));
        custom.AddAction(InternalFunctionFactory.getSetterAction(null, 10, "test3", null, "hello"));
        function.AddXMLObject(custom);
        this.object.setInternalFunction(function);
        this.object.printObjectToXML(fileModule);
        fileModule.delFile();
        fileModule.close();
        System.out.println(this.object.function.toString());
        assertEquals(15, this.object.getID());
    }
}