package ru.sondar.core.filesystem;

import junit.framework.TestCase;
import ru.sondar.core.filemodule.*;
import ru.sondar.core.filemodule.pc.*;
import ru.sondar.core.filesystem.exception.*;

/**
 * Test class for {@link ru.sondar.core.filesystem.SonDarFolder}
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SonDarFolderTest extends TestCase {

    public static final String TESTDATAFOLDER = "E:\\Development\\SonDar\\Core\\FileSystem\\JUnitTest\\fileSystem";

    FileModuleInterface fileModule = new FileModule();

    public SonDarFolderTest(String testName) {
        super(testName);
    }

    /**
     * <b>Case 1</b>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file.</li>
     * <li> Start initialization</li>
     * <p>
     * <b>Expected</b><br>SonDarFolderState.None</p>
     */
    public void testInit1() throws Exception {
        SonDarFolder instance = new SonDarFolder(TESTDATAFOLDER, "TestFolder1");
        instance.isInSystem = true;
        instance.init(fileModule);
        assertEquals(instance.getState(), SonDarFolderState.None);
    }

    /**
     * <b>Case 2</b>
     * <li> Create SonDarFolder object in empty</li>
     * <li> Start initialization</li>
     * <p>
     * <b>Expected</b><br>SonDarFolderState.ReduildPending</p>
     */
    public void testInit2() throws Exception {
        SonDarFolder instance = new SonDarFolder(TESTDATAFOLDER, "TestFolder2");
        instance.isInSystem = true;
        try {
            instance.init(fileModule);
            assertEquals(instance.getState(), SonDarFolderState.ReduildPending);
        } catch (SomeTroubleWithFolderException error) {
            return;
        }
        fail("No Ecxeption");
    }

    /**
     * <b>Case 3</b>
     * <li> Create SonDarFolder object in folder with correct configuration file
     * and one file.</li>
     * <li> Start initialization</li>
     * <p>
     * <b>Expected</b><br>SonDarFolderState.None; File list coincides</p>
     */
    public void testInit3() throws Exception {
        SonDarFolder instance = new SonDarFolder(TESTDATAFOLDER, "TestFolder3");
        instance.isInSystem = true;
        instance.init(fileModule);
        assertEquals(SonDarFolderState.None, instance.getState());
        String[] ex = {"test.txt"};
        assertEquals(ex.length, instance.config.configFileList.size());
        for (int i = 0; i < instance.config.configFileList.size(); i++) {
            assertEquals(ex[i], instance.config.configFileList.get(i));
        }
    }

    /**
     * <b>Case 4</b>
     * <li> Create SonDarFolder object in folder with incorrect configuration
     * file format</li>
     * <li> Start initialization</li>
     * <p>
     * <b>Expected</b><br>SonDarFolderState.ReduildPending</p>
     */
    public void testInit4() throws Exception {
        SonDarFolder instance = new SonDarFolder(TESTDATAFOLDER, "TestFolder4");
        instance.isInSystem = true;
        try {
            instance.init(fileModule);
        } catch (SomeTroubleWithFolderException error) {
            assertEquals(SonDarFolderState.ReduildPending, instance.getState());
            return;
        }
        fail("No exception");
    }

    /**
     * <b>Case 5</b>
     * <li> Create SonDarFolder object in folder with incorrect configuration
     * file format</li>
     * <li> Start initialization</li>
     * <li> Start initialization</li>
     * <li> Start initialization</li>
     * <p>
     * <b>Expected</b><br>No side effect; SonDarFolderState.None</p>
     */
    public void testInit5() throws Exception {
        SonDarFolder instance = new SonDarFolder(TESTDATAFOLDER, "TestFolder1");
        instance.isInSystem = true;
        instance.init(fileModule);
        instance.init(fileModule);
        instance.init(fileModule);
        assertEquals(instance.getState(), SonDarFolderState.None);
    }

    /**
     * <b>Case 6</b>
     * <li> Create SonDarFolder object in folder with correct configuration file
     * and one file.</li>
     * <li> Start initialization</li>
     * <li> Add new file to folder</li>
     * <li> Create SonDarFolder object in same folder</li>
     * <li> Start initialization</li>
     * <p>
     * <b>Expected</b><br>SonDarFolderState.None; File list coincides(2)</p>
     */
    public void testAddFile1() throws Exception {
        Runtime.getRuntime().exec(TESTDATAFOLDER + "\\forTest.bat 3 5");
        Thread.sleep(250);
        SonDarFolder instance = new SonDarFolder(TESTDATAFOLDER, "TestFolder5");
        instance.isInSystem = true;
        instance.init(fileModule);
        FileModuleWriteThreadInterface test = instance.addFile(fileModule, "test2.txt");
        test.write("Xaxaxa");
        test.close();
        SonDarFolder instance2 = new SonDarFolder(TESTDATAFOLDER, "TestFolder5");
        instance2.isInSystem = true;
        instance2.init(fileModule);
        assertEquals(instance2.config.configFileList.size(), 2);
        String[] ex = {"test.txt", "test2.txt"};
        assertEquals(ex.length, instance2.config.configFileList.size());
        for (int count = 0; count < instance2.config.configFileList.size(); count++) {
            assertEquals(ex[count], instance2.config.configFileList.get(count));
        }
    }
}
