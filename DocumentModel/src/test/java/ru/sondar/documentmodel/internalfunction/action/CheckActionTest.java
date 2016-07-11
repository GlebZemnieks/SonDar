package ru.sondar.documentmodel.internalfunction.action;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import junit.framework.TestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.TestVariables;
import static ru.sondar.documentmodel.internalfunction.Action.actionTag;
import ru.sondar.documentmodel.internalfunction.ActionType;
import ru.sondar.documentmodel.internalfunction.exception.IncorrectObjectTypeException;
import ru.sondar.documentmodel.exception.ObjectNotFountException;
import ru.sondar.documentmodel.internalfunction.interfaces.NavigatorInterface;
import ru.sondar.documentmodel.internalfunction.interfaces.ValueCheckerInterface;

/**
 *
 * @author GlebZemnieks
 */
public class CheckActionTest extends TestCase {

    public CheckActionTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Test of makeAction method, of class CheckAction.
     */
    public void testAction1() {
        CheckAction temp = new CheckAction(null);
        assertEquals(false, temp.isActive);
        assertEquals(ActionType.checkAction, temp.actionType);
    }

    /**
     * Test of makeAction method, of class CheckAction.
     */
    public void testAction2() {
        NavigatorInterface test = new NavigatorInterface() {
            public Object getObject(Object obj) {
                return temp;
            }
            ValueCheckerInterface temp = new ValueCheckerInterface() {
                @Override
                public boolean ifValue(Object obj) {
                    return "test".equals((String) obj);
                }
            };
        };
        CheckAction temp = new CheckAction(test);
        temp.setActionName("test");
        assertEquals(temp.getActionName(), "test");
        temp.setValue("test");
        assertEquals(true, temp.makeAction());
        temp.setValue("No test");
        assertEquals(false, temp.makeAction());
    }

    public void testAction3() {
        NavigatorInterface test = new NavigatorInterface() {
            public Object getObject(Object obj) {
                return null;
            }
        };
        CheckAction temp = new CheckAction(test);
        temp.setActionName("test");
        assertEquals(temp.getActionName(), "test");
        try {
            assertEquals(true, temp.makeAction());
        } catch (ObjectNotFountException error) {
            return;
        }
        fail("No exception");
    }

    public void testAction4() {
        NavigatorInterface test = new NavigatorInterface() {
            public Object getObject(Object obj) {
                return "test";
            }
        };
        CheckAction temp = new CheckAction(test);
        temp.setActionName("test");
        assertEquals(temp.getActionName(), "test");
        try {
            assertEquals(true, temp.makeAction());
        } catch (IncorrectObjectTypeException error) {
            return;
        }
        fail("No exception");
    }

    public void testPrint1() {
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(TestVariables.testFolder + "InternalFunction\\checkAction_temp1.txt", false);
        CheckAction check = new CheckAction(null);
        check.printXMLString(fileModule);
        fileModule.close();
    }

    public void testPrint2() {
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(TestVariables.testFolder + "InternalFunction\\checkAction_temp2.txt", false);
        CheckAction check = new CheckAction(null);
        check.setActionName("testName");
        check.setTargetId("3");
        check.setValue("hello");
        check.printXMLString(fileModule);
        fileModule.close();
    }

    public void testParse1() throws SAXException, IOException, ParserConfigurationException {
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(TestVariables.testFolder + "InternalFunction\\checkAction_temp3.txt", false);
        CheckAction check = new CheckAction(null);
        check.setActionName("test");
        check.setTargetId("test");
        check.setValue("Xoxoxo");
        check.printXMLString(fileModule);
        fileModule.close();
        File inputFile = new File(TestVariables.testFolder + "InternalFunction\\checkAction_temp3.txt");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputFile);
        Element root = doc.getDocumentElement();
        doc.getDocumentElement().normalize();
        assertEquals(root.getTagName(), actionTag);
        CheckAction check2 = new CheckAction(null);
        check2.setActionName("123");
        check2.parseXMLString(root);
        assertEquals(check.getActionName(), check2.getActionName());
    }

}
