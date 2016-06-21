/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import ru.sondar.documentmodel.internalfunction.exception.IncorrectValueFormatException;
import ru.sondar.documentmodel.internalfunction.interfaces.NavigatorInterface;
import ru.sondar.documentmodel.internalfunction.interfaces.SetterInterface;
import ru.sondar.documentmodel.internalfunction.interfaces.ValueCheckerInterface;

/**
 *
 * @author GlebZemnieks
 */
public class SetActionTest extends TestCase {

    public SetActionTest(String testName) {
        super(testName);
    }

    class testClass implements SetterInterface {

        public String test = "before";

        @Override
        public void setValue(Object obj) {
            try {
                this.test = (String) obj;
            } catch (ClassCastException error) {
                throw new IncorrectValueFormatException();
            }
        }
    };

    class testClass2 implements ValueCheckerInterface {

        @Override
        public boolean ifValue(Object obj) {
            return "test".equals((String) obj);
        }
    }

    class Navigator implements NavigatorInterface {

        SetterInterface temp = new testClass();

        @Override
        public Object getObject(Object obj) {
            return temp;
        }
    }

    class Navigator2 implements NavigatorInterface {

        ValueCheckerInterface temp = new testClass2();

        @Override
        public Object getObject(Object obj) {
            return temp;
        }
    }

    public void testAction1() {
        NavigatorInterface test = new Navigator();
        SetAction set = new SetAction(test);
        set.setTargetId(test);
        set.setValue("after");
        assertEquals("before", ((testClass) test.getObject(set)).test);
        set.makeAction();
        assertEquals("after", ((testClass) test.getObject(set)).test);
    }

    public void testAction2() {
        NavigatorInterface test = new Navigator();
        SetAction set = new SetAction(test);
        set.setTargetId(test);
        set.setValue(false);
        assertEquals("before", ((testClass) test.getObject(set)).test);
        try {
            set.makeAction();
        } catch (IncorrectValueFormatException error) {
            return;
        }
        fail("No exception");
    }

    public void testAction3() {
        NavigatorInterface test = new Navigator();
        SetAction set = new SetAction(test);
        set.setTargetId(test);
        set.setValue("after");

        NavigatorInterface test2 = new Navigator2();
        CheckAction check = new CheckAction(test2);
        check.setValue("test");
        set.setCondition(check);
        assertEquals("before", ((testClass) test.getObject(set)).test);
        set.makeAction();
        assertEquals("after", ((testClass) test.getObject(set)).test);
    }

    public void testAction4() {
        NavigatorInterface test = new Navigator();
        SetAction set = new SetAction(test);
        set.setTargetId(test);
        set.setValue("after");

        NavigatorInterface test2 = new Navigator2();
        CheckAction check = new CheckAction(test2);
        check.setActionName("test");
        check.setValue("No test");
        set.setCondition(check);
        assertEquals("before", ((testClass) test.getObject(set)).test);
        set.makeAction();
        assertEquals("before", ((testClass) test.getObject(set)).test);
    }

    public void testPrint1() {
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(TestVariables.testFolder + "InternalFunction\\SetAction_temp1.txt", false);
        SetAction check = new SetAction(null);
        check.printXMLString(fileModule);
        fileModule.close();
    }

    public void testPrint2() {
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(TestVariables.testFolder + "InternalFunction\\SetAction_temp2.txt", false);
        SetAction check = new SetAction(null);
        check.setTargetId("3");
        check.setValue("hello");
        check.printXMLString(fileModule);
        fileModule.close();
    }

    public void testParse1() throws SAXException, IOException, ParserConfigurationException {
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(TestVariables.testFolder + "InternalFunction\\SetAction_temp3.txt", false);
        SetAction check = new SetAction(null);
        check.setActionName("test");
        check.setTargetId("test");
        check.setValue("Xoxoxo");
        check.printXMLString(fileModule);
        fileModule.close();
        File inputFile = new File(TestVariables.testFolder + "InternalFunction\\SetAction_temp3.txt");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputFile);
        Element root = doc.getDocumentElement();
        doc.getDocumentElement().normalize();
        assertEquals(root.getTagName(), actionTag);
        SetAction check2 = new SetAction(null);
        check2.setActionName("123");
        check2.parseXMLString(root);
        assertEquals(check.getActionName(), check2.getActionName());
    }

}
