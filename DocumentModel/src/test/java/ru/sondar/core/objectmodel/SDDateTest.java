/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.core.objectmodel;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import static ru.sondar.core.objectmodel.TestVariables.testFolder;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

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
        Date time = new Date();
        date.setDate(time);
        assertEquals(time, date.getDate());
    }

    /**
     * Test of parseCurrentObjectField method, of class SDDate.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField() throws Exception {
        try {
            this.date.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "date_1.txt"));
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
        this.date.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "date_2.txt"));
    }

    /**
     * Test of printCurrentObjectField method, of class SDDate.
     */
    @Test
    public void testPrintCurrentObjectField() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\date_temp.txt");
        Date now = new Date();
        date.setDate(now);
        date.setID(11);
        this.date.printObjectToXML(fileModule);
        fileModule.close();
        SDDate date2 = new SDDate();
        date2.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "date_temp.txt"));
        assertEquals(now.getTime(), date2.getDate().getTime());
        assertEquals(11, date2.getID());
    }

}
