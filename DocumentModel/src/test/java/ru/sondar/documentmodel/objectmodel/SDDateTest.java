package ru.sondar.documentmodel.objectmodel;

import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import static ru.sondar.documentmodel.objectmodel.TestVariables.testFolder;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.serializer.XMLSerializer;

/**
 *
 * @author GlebZemnieks
 */
public class SDDateTest {

    SDDate date = new SDDate();

    @Before
    public void setUp() {
        date = new SDDate();
    }

    /**
     * Test of setDate method, of class SDDate.
     */
    @Test
    public void testSetDate() {
        Date time = Calendar.getInstance().getTime();
        date.setCalendar(time);
        assertEquals(time, date.getCalendar().getTime());
    }

    /**
     * Test of parseCurrentObjectField method, of class SDDate.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField() throws Exception {
        try {
            new XMLSerializer().parseObjectFromXML(date, TestVariables.getRootElementByFile("ObjectTest", "date_1.txt"));
        } catch (ObjectStructureException exception) {
            return;
        }
        fail("No exception");
    }

    /**
     * 2 Test of parseCurrentObjectField method, of class SDDate.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField2() throws Exception {
        new XMLSerializer().parseObjectFromXML(date, TestVariables.getRootElementByFile("ObjectTest", "date_2.txt"));
    }

    /**
     * Test of printCurrentObjectField method, of class SDDate.
     *
     * @throws ru.sondar.core.parser.exception.ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\date_temp.txt", false);
        Date now = new Date();
        date.setCalendar(now);
        date.setID(11);
        new XMLSerializer().printObjectToXML(date, fileModule);
        fileModule.close();
        SDDate date2 = new SDDate();
        new XMLSerializer().parseObjectFromXML(date2, TestVariables.getRootElementByFile("ObjectTest", "date_temp.txt"));
        assertEquals(now.getTime(), date2.getCalendar().getTime().getTime());
        assertEquals(11, date2.getID());
    }

}
