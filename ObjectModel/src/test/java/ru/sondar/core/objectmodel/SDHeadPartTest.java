/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.core.objectmodel;

import java.util.Date;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import static ru.sondar.core.objectmodel.TestVariables.testFolder;

/**
 *
 * @author GlebZemnieks
 */
public class SDHeadPartTest {

    SDHeadPart head = new SDHeadPart();

    @Before
    public void setUp() {
        head = new SDHeadPart();
    }

    /**
     * Test of setUUID method, of class SDHeadPart.
     */
    @Test
    public void testSetUUID() {
        UUID uuid = UUID.randomUUID();
        head.setUUID(uuid);
        assertEquals(uuid, head.getUUID());
    }

    /**
     * Test of setPluginUUID method, of class SDHeadPart.
     */
    @Test
    public void testSetPluginUUID() {
        UUID uuid = UUID.randomUUID();
        head.setPluginUUID(uuid);
        assertEquals(uuid, head.getPluginUUID());
    }

    /**
     * Test of setCreationTime method, of class SDHeadPart.
     */
    @Test
    public void testSetCreationTime() {
        Date date = new Date();
        head.setCreationTime(date);
        assertEquals(date, head.getCreationTime());
    }

    /**
     * Test of setLastModificationTime method, of class SDHeadPart.
     */
    @Test
    public void testSetLastModificationTime() {
        Date date = new Date();
        head.setLastModificationTime(date);
        assertEquals(date, head.getLastModificationTime());
    }

    /**
     * Test of parseCurrentObjectField method, of class SDHeadPart.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField() throws Exception {
        this.head.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "head_1.txt"));
    }

    /**
     * 2 Test of parseCurrentObjectField method, of class SDHeadPart.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParseCurrentObjectField2() throws Exception {
        this.head.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "head_2.txt"));
        assertEquals("00000000-0000-0000-0000-000000000001", head.getUUID().toString());
        assertEquals("00000000-0000-0000-0000-000000000002", head.getPluginUUID().toString());
        assertEquals(new Date(Long.parseLong("123456780")), head.getCreationTime());
        assertEquals(new Date(Long.parseLong("123456789")), head.getLastModificationTime());
    }

    /**
     * Test of printCurrentObjectField method, of class SDHeadPart.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPrintCurrentObjectField() throws Exception {
        Date date = new Date();
        head.setCreationTime(date);
        head.setLastModificationTime(date);
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\head_temp.txt");
        this.head.printObjectToXML(fileModule);
        fileModule.close();
        this.head.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "head_temp.txt"));
        assertEquals("00000000-0000-0000-0000-000000000000", head.getUUID().toString());
        assertEquals("00000000-0000-0000-0000-000000000000", head.getPluginUUID().toString());
        assertEquals(date, head.getCreationTime());
        assertEquals(date, head.getLastModificationTime());

    }

}
