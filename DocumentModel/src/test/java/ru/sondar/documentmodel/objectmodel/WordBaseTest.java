/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.documentmodel.objectmodel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import static ru.sondar.documentmodel.objectmodel.TestVariables.testFolder;

/**
 *
 * @author GlebZemnieks
 */
public class WordBaseTest {

    WordBase base;

    @Before
    public void setUp() {
        base = new WordBase();
        base.add("test");
        base.add("test2");
        base.add("one", "test3");
        base.add("two", "test4");
        base.add("two", "test5");
        base.add("two", "test6");
    }

    /**
     * Test of add method, of class WordBase.
     */
    @Test
    public void testAdd() {
        assertEquals(base.size(), 6);
        assertEquals(base.get(0), "test");
        assertEquals(base.get(1), "test2");
        assertEquals(base.get(2), "test3");
        assertEquals(base.get(3), "test4");
        assertEquals(base.get(4), "test5");
        assertEquals(base.get(5), "test6");
        base.add("two","test7");
        assertEquals(base.size(), 7);
        assertEquals(base.get(6), "test7");
    }

    /**
     * Test of getList method, of class WordBase.
     */
    @Test
    public void testGetList() {
        assertEquals(base.getList(null).size(), 6);
        assertEquals(base.getList("one").size(), 3);
        assertEquals(base.getList("one").get(0), "test");
        assertEquals(base.getList("one").get(1), "test2");
        assertEquals(base.getList("one").get(2), "test3");
        assertEquals(base.getList("two").size(), 5);
        assertEquals(base.getList("two").get(0), "test");
        assertEquals(base.getList("two").get(1), "test2");
        assertEquals(base.getList("two").get(2), "test4");
        assertEquals(base.getList("two").get(3), "test5");
        assertEquals(base.getList("two").get(4), "test6");
    }

    /**
     * Test of parseObjectFromXML method, of class WordBase.
     */
    @Test
    public void testParseObjectFromXML() {
        WordBase temp = new WordBase();
        temp.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "wordbase_1.txt"));
        assertEquals(temp.size(), 3);
        assertEquals(temp.get(0), "hello");
        assertEquals(temp.get(1), "hello2");
        assertEquals(temp.get(2), "hello3");
    }

    /**
     * Test of parseObjectFromXML method, of class WordBase.
     */
    @Test
    public void testParseObjectFromXML2() {
        WordBase temp = new WordBase();
        temp.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "wordbase_2.txt"));
        assertEquals(temp.size(), 6);
        assertEquals(temp.getList("one").size(), 3);
        assertEquals(temp.getList("one").get(0), "hello");
        assertEquals(temp.getList("one").get(1), "hello2");
        assertEquals(temp.getList("one").get(2), "hello3");
        assertEquals(temp.getList("two").size(), 5);
        assertEquals(temp.getList("two").get(0), "hello");
        assertEquals(temp.getList("two").get(1), "hello2");
        assertEquals(temp.getList("two").get(2), "hello4");
        assertEquals(temp.getList("two").get(3), "hello5");
        assertEquals(temp.getList("two").get(4), "hello6");
    }

    /**
     * Test of printObjectToXML method, of class WordBase.
     */
    @Test
    public void testPrintObjectToXML() {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\wordbase_temp.txt", false);
        base.printObjectToXML(fileModule);
        fileModule.close();
        WordBase temp = new WordBase();
        temp.parseObjectFromXML(TestVariables.getRootElementByFile("ObjectTest", "wordbase_temp.txt"));
    }

}
