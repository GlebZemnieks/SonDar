package ru.sondar.core.filemodule.pc;

import junit.framework.TestCase;
import ru.sondar.core.filemodule.exception.ThreadIsCloseException;
import ru.sondar.core.logging.LoggerInterface;
import ru.sondar.core.logging.PCLogging;
import static ru.sondar.core.filemodule.pc.FileModuleTest.TESTDATAFOLDER;

/**
 * Test class for {@link ru.sondar.core.filemodule.pc.FileModuleWriteThread}
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FileModuleThreadWriteTest extends TestCase {

    public LoggerInterface Logging = new PCLogging();

    public FileModuleThreadWriteTest(String testName) {
        super(testName);
    }

    /**
     * <b>Case 1</b>
     * <li> Create write thread by raw constructor</li>
     * <li> Write "test" to file</li>
     * <li> Delete file</li>
     * <li> Close thread</li>
     * <li> Check exit code</li>
     * <p>
     * <b>Expected</b><br>Exit code is 0</p>
     */
    public void testWrite() {
        String textForWriting = "test";
        FileModuleWriteThread instance = new FileModuleWriteThread(TESTDATAFOLDER + "FileModuleThreadWriteTest.txt", false);
        int expResult = 0;
        int result = instance.write(textForWriting);
        instance.delFile();
        instance.close();
        assertEquals(expResult, result);
    }

    /**
     * <b>Case 2</b>
     * <li> Create write thread by raw constructor</li>
     * <li> Close thread</li>
     * <li> Write "test" to file</li>
     * <p>
     * <b>Expected</b><br>ThreadIsCloseException</p>
     */
    public void testWrite2() {
        String textForWriting = "test";
        FileModuleWriteThread instance = new FileModuleWriteThread(TESTDATAFOLDER + "FileModuleThreadWriteTest2.txt", false);
        instance.write(textForWriting);
        instance.close();
        try {
            instance.write(textForWriting);
        } catch (ThreadIsCloseException er) {
            return;
        }
        fail("No exception");
    }

    /**
     * <b>Case 3</b>
     * <li> Create write thread by raw constructor</li>
     * <li> Write "test" to file</li>
     * <li> Close thread</li>
     * <li> Create read thread by raw constructor</li>
     * <li> Check file content</li>
     * <li> Close thread</li>
     * <p>
     * <b>Expected</b><br>File content is "test"</p>
     */
    public void testWriteWithReadCheck() {
        String textForWriting = "test";
        FileModuleWriteThread instance = new FileModuleWriteThread(TESTDATAFOLDER + "FileModuleThreadWriteTest3.txt", false);
        instance.write(textForWriting);
        instance.close();
        FileModuleReadThread checkInstance = new FileModuleReadThread(TESTDATAFOLDER + "FileModuleThreadWriteTest3.txt");
        String expResult = "test";
        String result = checkInstance.read();
        checkInstance.delFile();
        checkInstance.close();
        assertEquals(expResult, result);
    }

}
