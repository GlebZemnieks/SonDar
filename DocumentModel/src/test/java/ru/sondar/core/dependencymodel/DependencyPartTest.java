/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.core.dependencymodel;

import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.dependencymodel.DependencyItem;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static ru.sondar.core.documentmodel.TestVariables.testFolder;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.objectmodel.TestVariables;

/**
 *
 * @author GlebZemnieks
 */
public class DependencyPartTest {

    DependencyPart dependency = new DependencyPart();

    @Before
    public void setUp() {
        dependency = new DependencyPart();
    }

    /**
     * Test of parseItemFromXML method, of class DependencyPart.
     */
    @Test
    public void testParseItemFromXML() {
        dependency.parseItemFromXML(TestVariables.getRootElementByFile("DependencyTest", "dependency_1.txt"));
        Iterator<DependencyItem> items = dependency.iterator();
        String[] exString = new String[]{"test", "test2", "test3", "test4"};
        int[] exInteger = new int[]{5, 8, 12, 44};
        int i = 0;
        while (items.hasNext()) {
            DependencyItem next = items.next();
            assertEquals(next.objectName, exString[i]);
            assertEquals(next.cellId, exInteger[i]);
            i++;
        }
    }

    /**
     * Test of printObjectToXML method, of class DependencyPart.
     */
    @Test
    public void testPrintObjectToXML() {
        dependency.parseItemFromXML(TestVariables.getRootElementByFile("DependencyTest", "dependency_1.txt"));
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "DependencyTest\\dependency_temp.txt");
        dependency.printObjectToXML(fileModule);
        fileModule.close();
        dependency.parseItemFromXML(TestVariables.getRootElementByFile("DependencyTest", "dependency_temp.txt"));
        Iterator<DependencyItem> items = dependency.iterator();
        String[] exString = new String[]{"test", "test2", "test3", "test4"};
        int[] exInteger = new int[]{5, 8, 12, 44};
        int i = 0;
        while (items.hasNext()) {
            DependencyItem next = items.next();
            assertEquals(next.objectName, exString[i]);
            assertEquals(next.cellId, exInteger[i]);
            i++;
        }
    }

}
