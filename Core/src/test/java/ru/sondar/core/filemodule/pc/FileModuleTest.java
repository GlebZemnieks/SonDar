package ru.sondar.core.filemodule.pc;

import junit.framework.TestCase;
import ru.sondar.core.filemodule.exception.SonDarFileNotFoundException;
import ru.sondar.core.filemodule.FileModuleReadThreadInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;

/**
 * Test class for {@link ru.sondar.core.filemodule.pc.FileModule}
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FileModuleTest extends TestCase {

    public static final String TESTDATAFOLDER = TestVariables.testFolder + "fileModule\\";

    public FileModuleTest(String testName) {
        super(testName);
    }

    /**
     * <b>Case 1</b>
     * <li> Create 2 file.</li>
     * <li> Open two write thread. First by raw constructor. Second by
     * FileModule object.</li>
     * <li> Write to each file word "test".</li>
     * <li> Close thread.</li>
     * <li> Create two read thread by raw constructor and check files
     * content.</li>
     * <li> Close thread.</li>
     * <p>
     * <b>Expected</b><br>Content in files equals with step 3</p>
     */
    public void testGetWriteThread() {
        String fileName1 = TESTDATAFOLDER + "FileModuleThreadTest1.txt";
        String fileName2 = TESTDATAFOLDER + "FileModuleThreadTest2.txt";
        FileModule instance = new FileModule();
        FileModuleWriteThreadInterface result = instance.getWriteThread(fileName1);
        FileModuleWriteThreadInterface expResult = new FileModuleWriteThread(fileName2, false);
        result.write("test");
        expResult.write("test");
        result.close();
        expResult.close();
        FileModuleReadThread temp1 = new FileModuleReadThread(fileName1);
        FileModuleReadThread temp2 = new FileModuleReadThread(fileName2);
        assertEquals("test", temp1.read());
        assertEquals("test", temp2.read());
        temp1.close();
        temp2.close();
    }

    /**
     * <b>Case 2</b>
     * <li> Open two write thread. First by raw constructor. Second by
     * FileModule object.(Files not exist)</li>
     * <li> Write to each file word "test".</li>
     * <li> Close thread.</li>
     * <li> Create two read thread by raw constructor and check files
     * content.</li>
     * <li> Close thread.</li>
     * <p>
     * <b>Expected</b><br>Content in files equals with step 2</p>
     */
    public void testGetWriteThreadWithCreating() {
        String fileName1 = TESTDATAFOLDER + "FileModuleThreadTest1_temp.txt";
        String fileName2 = TESTDATAFOLDER + "FileModuleThreadTest2_temp.txt";
        FileModule instance = new FileModule();
        FileModuleWriteThreadInterface result = instance.getWriteThread(fileName1);
        FileModuleWriteThreadInterface expResult = new FileModuleWriteThread(fileName2, false);
        result.write("test");
        expResult.write("test");
        result.close();
        expResult.close();
        FileModuleReadThread temp1 = new FileModuleReadThread(fileName1);
        FileModuleReadThread temp2 = new FileModuleReadThread(fileName2);
        assertEquals("test", temp1.read());
        assertEquals("test", temp2.read());
        temp1.delFile();
        temp2.delFile();
        temp1.close();
        temp2.close();
    }

    /**
     * <b>Case 3</b>
     * <li> Create 2 file write thread. First by raw constructor. Second by
     * FileModule object.(Files not exist)</li>
     * <li> Write to each file word "test".</li>
     * <li> Close thread.</li>
     * <li> Create 2 file write thread. First by raw constructor. Second by
     * FileModule object with append mode on.(Same files)</li>
     * <li> Write to each file word "test" on new line.</li>
     * <li> Close thread.</li>
     * <li> Create two read thread by raw constructor and check files
     * content.</li>
     * <li> Close thread.</li>
     * <p>
     * <b>Expected</b><br>First file have one line. Second file have two
     * line</p>
     */
    public void testGetWriteThreadWithAppending() {
        String fileName1 = TESTDATAFOLDER + "FileModuleThreadTest1_temp2.txt";
        String fileName2 = TESTDATAFOLDER + "FileModuleThreadTest2_temp2.txt";
        FileModule instance = new FileModule();
        FileModuleWriteThreadInterface result = instance.getWriteThread(fileName1);
        FileModuleWriteThreadInterface expResult = new FileModuleWriteThread(fileName2, false);
        result.write("test\n");
        expResult.write("test\n");
        result.close();
        expResult.close();
        result = instance.getWriteThread(fileName1);
        expResult = new FileModuleWriteThread(fileName2, true);
        result.write("test\n");
        expResult.write("test\n");
        result.close();
        expResult.close();
        FileModuleReadThread temp1 = new FileModuleReadThread(fileName1);
        FileModuleReadThread temp2 = new FileModuleReadThread(fileName2);
        assertEquals("test", temp1.read());
        assertEquals("test", temp2.read());
        assertEquals(null, temp1.read());
        assertEquals("test", temp2.read());
        temp1.delFile();
        temp2.delFile();
        temp1.close();
        temp2.close();
    }

    /**
     * <b>Case 4</b>
     * <li> Create two read thread. First by raw constructor. Second by
     * FileModule object</li>
     * <li> Check files content.</li>
     * <li> Close thread.</li>
     * <p>
     * <b>Expected</b><br>Content in files equals</p>
     */
    public void testGetReadThread() {
        String fileName = TESTDATAFOLDER + "FileModuleThreadTest1.txt";
        FileModule instance = new FileModule();
        FileModuleReadThread expResult = new FileModuleReadThread(fileName);
        FileModuleReadThreadInterface result = instance.getReadThread(fileName);
        assertEquals("test", result.read());
        assertEquals("test", expResult.read());
    }

    /**
     * <b>Case 5</b>
     * <li> Create read thread by FileModule object for not exist file</li>
     * <p>
     * <b>Expected</b><br>SonDarFileNotFoundException</p>
     */
    public void testGetReadThreadNoExistFile() {
        String fileName = TESTDATAFOLDER + "FileModuleThreadTest2.test";
        FileModule instanse = new FileModule();
        try {
            FileModuleReadThreadInterface test = instanse.getReadThread(fileName);
        } catch (SonDarFileNotFoundException error) {
            return;
        }
        fail("No exception");
    }

    /**
     * <b>Case 5</b>
     * <li> Create read thread by FileModule object for folder object</li>
     * <p>
     * <b>Expected</b><br>SonDarFileNotFoundException</p>
     */
    public void testGetReadThreadForFolder() {
        String fileName = TESTDATAFOLDER + "\\test";
        FileModule instanse = new FileModule();
        try {
            FileModuleReadThreadInterface test = instanse.getReadThread(fileName);
        } catch (SonDarFileNotFoundException error) {
            return;
        }
        fail("No exception");
    }
}
