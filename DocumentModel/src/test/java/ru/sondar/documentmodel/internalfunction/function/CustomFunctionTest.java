package ru.sondar.documentmodel.internalfunction.function;

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
import ru.sondar.core.logging.PCLogging;
import ru.sondar.documentmodel.TestVariables;
import ru.sondar.documentmodel.internalfunction.Function;
import ru.sondar.documentmodel.internalfunction.TriggerType;
import ru.sondar.documentmodel.internalfunction.action.SetAction;
import ru.sondar.documentmodel.internalfunction.exception.IncorrectValueFormatException;
import ru.sondar.documentmodel.internalfunction.interfaces.NavigatorInterface;
import ru.sondar.documentmodel.internalfunction.interfaces.SetterInterface;
import ru.sondar.documentmodel.internalfunction.interfaces.ValueCheckerInterface;

/**
 *
 * @author GlebZemnieks
 */
public class CustomFunctionTest extends TestCase {

    public CustomFunctionTest(String testName) {
        super(testName);
    }

    class testClass implements SetterInterface {

        public String test = "before";

        @Override
        public void setValueByAction(Object obj) {
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
        SetterInterface temp2 = new testClass();

        @Override
        public Object getObject(Object obj) {
            PCLogging logging = new PCLogging();
            (logging).Log("TEST", "Search object : '" + obj + "'");
            (logging).Log("TEST", "Search object class: '" + obj.getClass() + "'");
            (logging).Log("TEST", "check (String) obj == 1 : " + "1".equals((String) obj));
            (logging).Log("TEST", "check (String) obj == 2 : " + "2".equals((String) obj));
            if ("1".equals((String) obj)) {
                (logging).Log("TEST", "return object : " + ((testClass) temp).test);
                return temp;
            }
            if ("2".equals((String) obj)) {
                (logging).Log("TEST", "return object : " + ((testClass) temp2).test);
                return temp2;
            }
            (logging).Log("TEST", "return null");
            return null;
        }
    }

    class Navigator2 implements NavigatorInterface {

        ValueCheckerInterface temp = new testClass2();

        @Override
        public Object getObject(Object obj) {
            return temp;
        }
    }

    public void testMakeFunction() {
        Function function = new CustomFunction();
        NavigatorInterface navigatorInterface = new Navigator();
        SetAction set = new SetAction(navigatorInterface);
        set.setTargetId("1");
        set.setValue("after1");
        NavigatorInterface navigatorInterface2 = new Navigator();
        SetAction set2 = new SetAction(navigatorInterface2);
        set2.setTargetId("1");
        set2.setValue("after2");
        function.AddAction(set);
        function.AddAction(set2);
        assertEquals(((testClass) navigatorInterface.getObject("1")).test, "before");
        assertEquals(((testClass) navigatorInterface2.getObject("1")).test, "before");
        function.makeFunction(TriggerType.allAction);
        assertEquals(((testClass) navigatorInterface.getObject("1")).test, "after1");
        assertEquals(((testClass) navigatorInterface2.getObject("1")).test, "after2");

    }

    public void testPrint() {
        Function function = new CustomFunction();
        SetAction set = new SetAction(new Navigator());
        set.setTargetId(1);
        set.setValue("after1");
        SetAction set2 = new SetAction(new Navigator());
        set2.setTargetId(2);
        set2.setValue("after2");
        function.AddAction(set);
        function.AddAction(set2);
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(TestVariables.testFolder + "InternalFunction\\function_temp1.txt", false);
        function.printXMLString(fileModule);
        fileModule.close();
    }

    /**
     * Test of parseXMLString method, of class AutoFill.
     *
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    public void testParseXMLString() throws ParserConfigurationException, SAXException, IOException {
        Function function = new CustomFunction();
        NavigatorInterface navigatorInterface = new Navigator();
        SetAction set = new SetAction(navigatorInterface);
        set.setTargetId(1);
        set.setValue("after1");
        SetAction set2 = new SetAction(navigatorInterface);
        set2.setTargetId(2);
        set2.setValue("after2");
        function.AddAction(set);
        function.AddAction(set2);
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(TestVariables.testFolder + "InternalFunction\\function_temp2.txt", false);
        function.printXMLString(fileModule);
        fileModule.close();
        File inputFile = new File(TestVariables.testFolder + "InternalFunction\\function_temp2.txt");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputFile);
        Element root = doc.getDocumentElement();
        doc.getDocumentElement().normalize();
        Function function2 = new CustomFunction();
        function2.parseXMLString(root);
        NavigatorInterface navigatorInterface2 = new Navigator();
        function2.setNavigator(navigatorInterface2);
        System.out.println("Function : \n" + function2.toString());

        assertEquals(((testClass) navigatorInterface2.getObject("1")).test, "before");
        assertEquals(((testClass) navigatorInterface2.getObject("2")).test, "before");
        function2.makeFunction(TriggerType.allAction);
        assertEquals(((testClass) navigatorInterface2.getObject("1")).test, "after1");
        assertEquals(((testClass) navigatorInterface2.getObject("2")).test, "after2");

    }

}
