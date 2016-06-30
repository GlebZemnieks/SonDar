package ru.sondar.documentmodel.objectmodel;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GlebZemnieks
 */
public class WordsBaseTest {

    public WordsBase base;

    @Before
    public void setUp() {
        base = new WordsBase();
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("test1");
        list1.add("test2");
        ArrayList<String> list2 = new ArrayList<>();
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
        ArrayList<String> list3 = new ArrayList<>();
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
        ArrayList<String> list3 = new ArrayList<>();
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

}
