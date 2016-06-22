package ru.sondar.core.filemodule.pc;

import junit.framework.TestCase;
import ru.sondar.core.filemodule.exception.*;
import static ru.sondar.core.filemodule.pc.FileModuleTest.TESTDATAFOLDER;

/**
 *
 * @author Глеб
 */
public class FileModuleThreadReadTest extends TestCase {

    public FileModuleThreadReadTest(String testName) {
        super(testName);
    }

    /**
     * <b>Case 1</b>
     * <li> Create read thread by raw constructor(Exist file)</li>
     * <li> Check file content</li>
     * <li> Close thread.</li>
     * <p>
     * <b>Expected</b><br>File content equals "test"</p>
     */
    public void testRead() {
        FileModuleReadThread instance = new FileModuleReadThread(TESTDATAFOLDER + "FileModuleThreadTest.txt");
        String expResult = "test";
        String result = instance.read();
        instance.close();
        assertEquals(expResult, result);
    }

    /**
     * <b>Case 2</b>
     * <li> Create read thread by raw constructor(Exist file)</li>
     * <li> Close thread.</li>
     * <li> Read line from file</li>
     * <p>
     * <b>Expected</b><br>ThreadIsCloseException</p>
     */
    public void testReadWhenClose() {
        FileModuleReadThread instance = new FileModuleReadThread(TESTDATAFOLDER + "FileModuleThreadTest.txt");
        instance.close();
        try {
            instance.read();
        } catch (ThreadIsCloseException error) {
            return;
        }
        fail("No exeption");
    }

    /**
     * <b>Case 3</b>
     * <li> Create read thread by raw constructor(Nor exist file)</li>
     * <p>
     * <b>Expected</b><br>SonDarFileNotFoundException</p>
     */
    public void testReadWhenFileNotExist() {
        try {
            new FileModuleReadThread(TESTDATAFOLDER + "not-existFile.txt");
        } catch (SonDarFileNotFoundException error) {
            return;
        }
        fail("No exception");
    }

}
