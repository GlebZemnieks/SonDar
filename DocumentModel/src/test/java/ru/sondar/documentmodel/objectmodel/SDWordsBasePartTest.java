package ru.sondar.documentmodel.objectmodel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import static ru.sondar.documentmodel.objectmodel.TestVariables.testFolder;
import ru.sondar.documentmodel.serializer.XMLSerializer;

/**
 *
 * @author GlebZemnieks
 */
public class SDWordsBasePartTest {

    public SDWordsBasePart base;

    @Before
    public void setUp() {
        base = new SDWordsBasePart();
        WordBase list1 = new WordBase();
        list1.add("test1");
        list1.add("test2");
        WordBase list2 = new WordBase();
        list2.add("test3");
        list2.add("test4");
        base.addNewBase("base1", list1);
        base.addNewBase("base2", list2);
    }

    /**
     * Test of getList method, of class WordsBase.
     */
    @Test
    public void testGetList() {
        assertEquals("test1", base.getList("base1").get(0));
        assertEquals("test2", base.getList("base1").get(1));
        assertEquals("test3", base.getList("base2").get(0));
        assertEquals("test4", base.getList("base2").get(1));
    }

    /**
     * 2 Test of getList method, of class WordsBase.
     */
    @Test
    public void testGetList2() {
        assertEquals("test1", base.getList("base1").get(0));
        assertEquals("test2", base.getList("base1").get(1));
        assertEquals("test3", base.getList("base2").get(0));
        assertEquals("test4", base.getList("base2").get(1));
        try {
            assertEquals("test4", base.getList("base3").get(1));
        } catch (RuntimeException error) {
            return;
        }
        fail("No exception");
    }

    /**
     * Test of addNewBase method, of class WordsBase.
     */
    @Test
    public void testAddNewBase() {
        WordBase list3 = new WordBase();
        list3.add("test5");
        list3.add("test6");
        base.addNewBase("base3", list3);
        assertEquals("test1", base.getList("base1").get(0));
        assertEquals("test2", base.getList("base1").get(1));
        assertEquals("test3", base.getList("base2").get(0));
        assertEquals("test4", base.getList("base2").get(1));
        assertEquals("test5", base.getList("base3").get(0));
        assertEquals("test6", base.getList("base3").get(1));
    }

    /**
     * 2 Test of addNewBase method, of class WordsBase.
     */
    @Test
    public void testAddNewBase2() {
        WordBase list3 = new WordBase();
        list3.add("test5");
        list3.add("test6");
        boolean test = true;
        try {
            base.addNewBase("base2", list3);
        } catch (RuntimeException error) {
            test = false;
        }
        assertEquals("test1", base.getList("base1").get(0));
        assertEquals("test2", base.getList("base1").get(1));
        assertEquals("test3", base.getList("base2").get(0));
        assertEquals("test4", base.getList("base2").get(1));
        if (test) {
            fail("No exception");
        }
    }

    /**
     * Test of parseObjectFromXML method, of class WordBase.
     */
    @Test
    public void testParseObjectFromXML2() {
        WordBase temp = new WordBase();
        new XMLSerializer().parseWordsBase(temp, TestVariables.getRootElementByFile("ObjectTest", "wordbase_2.txt"));
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
        WordBase list1 = new WordBase();
        list1.add("test3");
        list1.add("test4");
        list1.add("a1", "test3");
        list1.add("a1", "test4");
        list1.add("a3", "test3");
        list1.add("a3", "test4");
        list1.add("a3", "test3");
        list1.add("a3", "test4");
        list1.add("a4", "test3");
        list1.add("a4", "test4");
        base.addNewBase("base3", list1);

        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "ObjectTest\\wordsbase_list_temp.txt", false);
        new XMLSerializer().printWordsBasePart(base, fileModule);
        fileModule.close();
        WordBase temp = new WordBase();
        new XMLSerializer().parseWordsBase(temp, TestVariables.getRootElementByFile("ObjectTest", "wordsbase_list_temp.txt"));
    }

}
