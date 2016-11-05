package ru.sondar.documentmodel.dependencymodel;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static ru.sondar.documentmodel.TestVariables.testFolder;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.objectmodel.TestVariables;
import ru.sondar.documentmodel.serializer.XMLSerializer;

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
        new XMLSerializer().parseDependencyPart(dependency, TestVariables.getRootElementByFile("DependencyTest", "dependency_1.txt"));
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
        new XMLSerializer().parseDependencyPart(dependency, TestVariables.getRootElementByFile("DependencyTest", "dependency_1.txt"));
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "DependencyTest\\dependency_temp.txt", false);
        new XMLSerializer().printDependencyPart(dependency, fileModule);
        fileModule.close();
        new XMLSerializer().parseDependencyPart(dependency, TestVariables.getRootElementByFile("DependencyTest", "dependency_temp.txt"));
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
