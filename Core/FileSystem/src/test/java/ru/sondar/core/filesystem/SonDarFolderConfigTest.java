package ru.sondar.core.filesystem;

import java.util.ArrayList;
import junit.framework.TestCase;
import ru.sondar.core.filemodule.*;
import ru.sondar.core.filemodule.pc.*;
import ru.sondar.core.filesystem.exception.*;

/**
 * Test class for {@link ru.sondar.core.filesystem.SonDarFolderConfig}
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SonDarFolderConfigTest extends TestCase {

    public static final String TESTDATAFOLDER = "E:\\Development\\SonDar\\Core\\FileSystem\\JUnitTest\\fileSystem";

    FileModuleInterface fileModule = new FileModule();

    public SonDarFolderConfigTest(String testName) {
        super(testName);
    }

    /**
     * <b>Case 1</b>
     * <li> Create SonDarFolderConfig object in empty folder with correct
     * configuration file.</li>
     * <p>
     * <b>Expected</b><br>File list coincides</p>
     */
    public void testConfigList1() throws ConfigFileFormatException {
        SonDarFolderConfig config = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder1");
        ArrayList<String> ex = new ArrayList();
        assertEquals(ex, config.configFileList);
    }

    /**
     * <b>Case 2</b>
     * <li> Create SonDarFolderConfig object in empty folder without
     * configuration file.</li>
     * <p>
     * <b>Expected</b><br>SonDarFileNotFoundException</p>
     */
    public void testConfigList2() throws ConfigFileFormatException {
        System.out.println("setLogger");
        try {
            SonDarFolderConfig config = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder2");
        } catch (ConfigFileFormatException error) {
            return;
        }
        fail("No exception");
    }

    /**
     * <b>Case 3</b>
     * <li> Create SonDarFolderConfig object in empty folder with correct
     * configuration file and one file.</li>
     * <p>
     * <b>Expected</b><br>File list coincides</p>
     */
    public void testConfigList3() throws ConfigFileFormatException {
        SonDarFolderConfig config = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder3");
        String[] ex = {"test.txt"};
        assertEquals(ex.length, config.configFileList.size());
        for (int i = 0; i < config.configFileList.size(); i++) {
            assertEquals(ex[i], config.configFileList.get(i));
        }
    }

    /**
     * <b>Case 4</b>
     * <li> Create SonDarFolderConfig object in empty folder with incorrect
     * configuration file.</li>
     * <p>
     * <b>Expected</b><br>ConfigFileFormatException</p>
     */
    public void testConfigList4() {
        try {
            SonDarFolderConfig config = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder4");
        } catch (ConfigFileFormatException ex) {
            return;
        }
        fail("No exception");
    }

    /**
     * <b>Case 5</b>
     * <li> Create SonDarFolderConfig object in empty folder with correct
     * configuration file and one file.</li>
     * <li> Update configuration file </li>
     * <li> Create SonDarFolderConfig object in same folder</li>
     * <p>
     * <b>Expected</b><br>File list coincides</p>
     */
    public void testWriteConfig1() throws ConfigFileFormatException {
        SonDarFolderConfig config = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder3");
        config.update(fileModule, TESTDATAFOLDER, "TestFolder3");
        SonDarFolderConfig config2 = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder3");
        String[] ex = {"test.txt"};
        assertEquals(ex.length, config2.configFileList.size());
        for (int i = 0; i < config2.configFileList.size(); i++) {
            assertEquals(ex[i], config2.configFileList.get(i));
        }
    }

    /**
     * <b>Case 6</b>
     * <li> Create SonDarFolderConfig object in empty folder with correct
     * configuration file.</li>
     * <li> Update configuration file </li>
     * <li> Create SonDarFolderConfig object in same folder</li>
     * <p>
     * <b>Expected</b><br>File list coincides</p>
     */
    public void testWriteConfig2() throws ConfigFileFormatException {
        SonDarFolderConfig config = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder1");
        config.update(fileModule, TESTDATAFOLDER, "TestFolder1");
        SonDarFolderConfig config2 = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder1");
        assertEquals(new ArrayList(), config2.configFileList);
    }

    /**
     * <b>Case 7</b>
     * <li> Create SonDarFolderConfig object in empty folder with correct
     * configuration file and one file.</li>
     * <li> Add file to folder </li>
     * <li> Create SonDarFolderConfig object in same folder</li>
     * <p>
     * <b>Expected</b><br>File list coincides</p>
     */
    public void testAddFile1() throws Exception {
        Runtime.getRuntime().exec(TESTDATAFOLDER + "\\forTest.bat 3 5");
        Thread.sleep(200);
        SonDarFolderConfig config = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder5");
        config.addFile(fileModule, TESTDATAFOLDER, "TestFolder5", "test2.txt");
        SonDarFolderConfig config2 = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder5");
        String[] ex = {"test.txt", "test2.txt"};
        assertEquals(ex.length, config2.configFileList.size());
        for (int i = 0; i < config2.configFileList.size(); i++) {
            assertEquals(ex[i], config2.configFileList.get(i));
        }
    }

    /**
     * <b>Case 8</b>
     * <li> Create SonDarFolderConfig object in empty folder with correct
     * configuration file and one file.</li>
     * <li> Add file to folder </li>
     * <li> Add file with same name to folder </li>
     * <li> Create SonDarFolderConfig object in same folder</li>
     * <p>
     * <b>Expected</b><br>File list coincides(2 file)</p>
     * <p>
     * <b>Comment</b><br>By design</p>
     */
    public void testAddFile2() throws Exception {
        Runtime.getRuntime().exec(TESTDATAFOLDER + "\\forTest.bat 3 6");
        Thread.sleep(200);
        SonDarFolderConfig config = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder5");
        config.addFile(fileModule, TESTDATAFOLDER, "TestFolder5", "test2.txt");
        config.addFile(fileModule, TESTDATAFOLDER, "TestFolder5", "test2.txt");
        SonDarFolderConfig config2 = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder5");
        String[] ex = {"test.txt", "test2.txt"};
        assertEquals(ex.length, config2.configFileList.size());
        for (int i = 0; i < config2.configFileList.size(); i++) {
            assertEquals(ex[i], config2.configFileList.get(i));
        }
    }

    /**
     * <b>Case 9</b>
     * <li> Create SonDarFolderConfig object in empty folder with correct
     * configuration file.</li>
     * <li> Add file to folder </li>
     * <li> Add file with another name to folder </li>
     * <li> Create SonDarFolderConfig object in same folder</li>
     * <li> Check file list coincides(2)</li>
     * <li> Delete one of old file from folder </li>
     * <li> Add file with another name to folder </li>
     * <li> Create SonDarFolderConfig object in same folder</li>
     * <li> Check file list coincides(2)</li>
     * <p>
     * <b>Expected</b><br>File list coincides</p>
     */
    public void testDeleteFile1() throws Exception {
        Runtime.getRuntime().exec(TESTDATAFOLDER + "\\forTest.bat 1 6");
        Thread.sleep(400);
        SonDarFolderConfig config = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder6");
        config.addFile(fileModule, TESTDATAFOLDER, "TestFolder6", "test2.txt");
        config.addFile(fileModule, TESTDATAFOLDER, "TestFolder6", "test3.txt");
        SonDarFolderConfig config2 = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder6");
        String[] ex = {"test2.txt", "test3.txt"};
        assertEquals(ex.length, config2.configFileList.size());
        for (int i = 0; i < config2.configFileList.size(); i++) {
            assertEquals(ex[i], config2.configFileList.get(i));
        }
        config.addFile(fileModule, TESTDATAFOLDER, "TestFolder6", "test4.txt");
        config.DeleteFile(fileModule, TESTDATAFOLDER, "TestFolder6", "test3.txt");
        SonDarFolderConfig config3 = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder6");
        String[] ex2 = {"test2.txt", "test4.txt"};
        assertEquals(ex.length, config3.configFileList.size());
        for (int i = 0; i < config3.configFileList.size(); i++) {
            assertEquals(ex2[i], config3.configFileList.get(i));
        }
    }

    /**
     * <b>Case 10</b>
     * <li> Create SonDarFolderConfig object in empty folder with correct
     * configuration file.</li>
     * <li> Add file to folder </li>
     * <li> Add file with another name to folder </li>
     * <li> Create SonDarFolderConfig object in same folder</li>
     * <li> Check file list coincides(2)</li>
     * <li> Delete all old file from folder </li>
     * <li> Add file with another name to folder </li>
     * <li> Create SonDarFolderConfig object in same folder</li>
     * <li> Check file list coincides(1)</li>
     * <li> Create SonDarFolderConfig object in same folder</li>
     * <li> Delete all old file from folder </li>
     * <li> Check file list coincides(0)</li>
     * <p>
     * <b>Expected</b><br>File list coincides</p>
     */
    public void testDeleteFile2() throws Exception {
        Runtime.getRuntime().exec(TESTDATAFOLDER + "\\forTest.bat 1 6");
        Thread.sleep(400);
        SonDarFolderConfig config = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder6");
        config.addFile(fileModule, TESTDATAFOLDER, "TestFolder6", "test2.txt");
        config.addFile(fileModule, TESTDATAFOLDER, "TestFolder6", "test3.txt");
        SonDarFolderConfig config2 = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder6");
        String[] ex = {"test2.txt", "test3.txt"};
        assertEquals(ex.length, config2.configFileList.size());
        for (int i = 0; i < config2.configFileList.size(); i++) {
            assertEquals(ex[i], config2.configFileList.get(i));
        }
        config.addFile(fileModule, TESTDATAFOLDER, "TestFolder6", "test4.txt");
        config.DeleteFile(fileModule, TESTDATAFOLDER, "TestFolder6", "test3.txt");
        config.DeleteFile(fileModule, TESTDATAFOLDER, "TestFolder6", "test2.txt");
        SonDarFolderConfig config3 = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder6");
        String[] ex2 = {"test4.txt"};
        assertEquals(ex2.length, config3.configFileList.size());
        for (int i = 0; i < config3.configFileList.size(); i++) {
            assertEquals(ex2[i], config3.configFileList.get(i));
        }
        config.DeleteFile(fileModule, TESTDATAFOLDER, "TestFolder6", "test4.txt");
        SonDarFolderConfig config4 = new SonDarFolderConfig(TESTDATAFOLDER, "TestFolder6");
        assertEquals(new ArrayList(), config4.configFileList);
    }

}
