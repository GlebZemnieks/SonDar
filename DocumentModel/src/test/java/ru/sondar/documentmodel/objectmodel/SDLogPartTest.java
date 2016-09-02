/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.documentmodel.objectmodel;

import ru.sondar.documentmodel.objectmodel.SDLogPart;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.objectmodel.SDLogPart;
import static ru.sondar.documentmodel.objectmodel.TestVariables.testFolder;
import ru.sondar.core.exception.parser.ObjectStructureException;

/**
 *
 * @author GlebZemnieks
 */
public class SDLogPartTest {

    SDLogPart log = new SDLogPart();
    ArrayList<String> test = new ArrayList<>();

    @Before
    public void setUp() {
        log = new SDLogPart();
        test = new ArrayList<>();
        test.add("test");
        test.add("test2");
        test.add("test3");
        log.setLogList(test);
    }

    /**
     * Test of getLogList method, of class SDLogPart.
     */
    @Test
    public void testGetLogList() {
        ArrayList<String> test2 = log.getLogList();
        assertEquals(test.size(), test2.size());
        assertEquals(test.get(0), test2.get(0));
        assertEquals(test.get(1), test2.get(1));
        assertEquals(test.get(2), test2.get(2));
    }

    /**
     * Test of setLogList method, of class SDLogPart.
     */
    @Test
    public void testSetLogList() {
        ArrayList<String> test2 = new ArrayList<>();
        test2.add("test");
        log.setLogList(test2);
        ArrayList<String> test3 = log.getLogList();
        assertEquals(test2.size(), test3.size());
        assertEquals(test2.get(0), test3.get(0));
    }

    /**
     * Test of addLogFile method, of class SDLogPart.
     */
    @Test
    public void testAddLogFile() {
        log.addLogFile("test4");
        ArrayList<String> test2 = log.getLogList();
        assertEquals(test.size(), test2.size());
        assertEquals(test.get(0), test2.get(0));
        assertEquals(test.get(1), test2.get(1));
        assertEquals(test.get(2), test2.get(2));
        assertEquals("test4", test2.get(3));
    }

    /**
     * Test of parseCurrentObjectField method, of class SDLogPart.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField() throws Exception {
        this.log.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "log_1.txt"));
        assertEquals(0, log.getLogList().size());
    }

    /**
     * 2 Test of parseCurrentObjectField method, of class SDLogPart.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField2() throws Exception {
        this.log.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "log_2.txt"));
        assertEquals(1, log.getLogList().size());
        assertEquals("test", log.getLogList().get(0));
    }

    /**
     * 3 Test of parseCurrentObjectField method, of class SDLogPart.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField3() throws Exception {
        this.log.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "log_3.txt"));
        assertEquals(10, log.getLogList().size());
        assertEquals("test", log.getLogList().get(0));
        assertEquals("test", log.getLogList().get(2));
        assertEquals("test", log.getLogList().get(8));
    }

    /**
     * Test of printCurrentObjectField method, of class SDLogPart.
     *
     * @throws ru.sondar.core.exception.parser.ObjectStructureException
     */
    @Test
    public void testPrintCurrentObjectField() throws ObjectStructureException {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\log_temp.txt", false);
        this.log.printObjectToXML(fileModule);
        fileModule.close();
        this.log.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "log_temp.txt"));
        ArrayList<String> test2 = log.getLogList();
        assertEquals(test.size(), test2.size());
        assertEquals(test.get(0), test2.get(0));
        assertEquals(test.get(1), test2.get(1));
        assertEquals(test.get(2), test2.get(2));
    }

}
