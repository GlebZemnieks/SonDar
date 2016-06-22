package ru.sondar.core.filemodule.pc;

import junit.framework.TestCase;
import static junit.framework.TestCase.fail;
import ru.sondar.core.filemodule.exception.ThreadIsCloseException;

public class FileModuleThreadTest extends TestCase {

    public FileModuleThreadTest(String testName) {
        super(testName);
    }

    /**
     * <b>Case 1</b>
     * <li> Create FileModuleThread object</li>
     * <p>
     * <b>Expected</b><br>By default method isClose() return False</p>
     */
    public void testIsClose_1() {
        FileModuleThread instance = new FileModuleThread("test fileName");
        assertEquals(false, instance.isClose());
    }

    /**
     * <b>Case 2</b>
     * <li> Create FileModuleThread object</li>
     * <li> Call isClose() method</li>
     * <p>
     * <b>Expected</b><br>Method isClose() return True</p>
     */
    public void testIsClose_2() {
        FileModuleThread instance = new FileModuleThread("test fileName");
        instance.close();
        assertEquals(true, instance.isClose());
    }

    /**
     * <b>Case 3</b>
     * <li> Create FileModuleThread object</li>
     * <li> Call isClose() method</li>
     * <li> Call delFile() method</li>
     * <p>
     * <b>Expected</b><br>ThreadIsCloseException</p>
     */
    public void testIsClose_3() {
        FileModuleThread instance = new FileModuleThread("test fileName");
        instance.close();
        try {
            instance.delFile();
        } catch (ThreadIsCloseException error) {
            return;
        }
        fail("No exception");
    }

    /**
     * <b>Case 4</b>
     * <li> Create FileModuleThread object</li>
     * <li> Call isClose() method</li>
     * <li> Call isClose() method</li>
     * <p>
     * <b>Expected</b><br>ThreadIsCloseException</p>
     */
    public void testIsClose_4() {
        FileModuleThread instance = new FileModuleThread("test fileName");
        instance.close();
        try {
            instance.close();
        } catch (ThreadIsCloseException error) {
            return;
        }
        fail("No exception");
    }

}
