package ru.sondar.core.filesystem;

import junit.framework.TestCase;
import ru.sondar.core.filemodule.*;
import ru.sondar.core.filemodule.pc.*;
import ru.sondar.core.filesystem.exception.*;

/**
 * Test class for {@link ru.sondar.core.filesystem.SonDarFileSystem}
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SonDarFileSystemTest extends TestCase {

    public static final String TESTDATAFOLDER = TestVariables.testFolder + "fileSystem";

    FileModuleInterface fileModule = new FileModule();

    public SonDarFileSystemTest(String testName) {
        super(testName);
    }

    /**
     * <b>Case 1</b>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file.</li>
     * <li> Start initialization</li>
     * <li> Get folder by name and check status</li>
     * <p>
     * <b>Expected</b><br>SonDarFolderState.None</p>
     */
    public void testAddFolder1() {
        SonDarFileSystem instance = new SonDarFileSystem(TESTDATAFOLDER);
        instance.addFolder("TestFolder1");
        instance.init(fileModule);
        assertEquals(instance.getFolderByName("TestFolder1").getState(), SonDarFolderState.None);
    }

    /**
     * <b>Case 2</b>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file.</li>
     * <li> Create SonDarFolder object in empty folder with missing
     * configuration file.</li>
     * <li> Start initialization</li>
     * <li> Get folder by name and check status</li>
     * <p>
     * <b>Expected</b><br>SonDarFolderState.None(1);SonDarFolderState.ReduildPending(2)</p>
     */
    public void testAddFolder2() {
        SonDarFileSystem instance = new SonDarFileSystem(TESTDATAFOLDER);
        instance.addFolder("TestFolder1");
        instance.addFolder("TestFolder2");
        instance.init(fileModule);
        assertEquals(instance.getFolderByName("TestFolder1").getState(), SonDarFolderState.None);
        assertEquals(instance.getFolderByName("TestFolder2").getState(), SonDarFolderState.ReduildPending);
    }

    /**
     * <b>Case 3</b>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file.</li>
     * <li> Create SonDarFolder object in empty folder with missing
     * configuration file.</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file and one file.</li>
     * <li> Start initialization</li>
     * <li> Get folder by name and check status</li>
     * <p>
     * <b>Expected</b><br>SonDarFolderState.None(1);SonDarFolderState.ReduildPending(2);SonDarFolderState.None(3)</p>
     */
    public void testAddFolder3() {
        SonDarFileSystem instance = new SonDarFileSystem(TESTDATAFOLDER);
        instance.addFolder("TestFolder1");
        instance.addFolder("TestFolder2");
        instance.addFolder("TestFolder3");
        instance.init(fileModule);
        assertEquals(instance.getFolderByName("TestFolder1").getState(), SonDarFolderState.None);
        assertEquals(instance.getFolderByName("TestFolder2").getState(), SonDarFolderState.ReduildPending);
        assertEquals(instance.getFolderByName("TestFolder3").getState(), SonDarFolderState.None);
    }

    /**
     * <b>Case 4</b>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file.</li>
     * <li> Create SonDarFolder object in empty folder with missing
     * configuration file.</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file and one file.</li>
     * <li> Create SonDarFolder object in empty folder with incorrect
     * configuration file.</li>
     * <li> Start initialization</li>
     * <li> Get folder by name and check status</li>
     * <p>
     * <b>Expected</b><br>SonDarFolderState.None(1);SonDarFolderState.ReduildPending(2);SonDarFolderState.None(3);SonDarFolderState.ReduildPending(4)</p>
     */
    public void testAddFolder4() {
        SonDarFileSystem instance = new SonDarFileSystem(TESTDATAFOLDER);
        instance.addFolder("TestFolder1");
        instance.addFolder("TestFolder2");
        instance.addFolder("TestFolder3");
        instance.addFolder("TestFolder4");
        instance.init(fileModule);
        assertEquals(instance.getFolderByName("TestFolder1").getState(), SonDarFolderState.None);
        assertEquals(instance.getFolderByName("TestFolder2").getState(), SonDarFolderState.ReduildPending);
        assertEquals(instance.getFolderByName("TestFolder3").getState(), SonDarFolderState.None);
        assertEquals(instance.getFolderByName("TestFolder4").getState(), SonDarFolderState.ReduildPending);
    }

    /**
     * <b>Case 5</b>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file.</li>
     * <li> Start initialization</li>
     * <li> Get folder by another name</li>
     * <p>
     * <b>Expected</b><br>FolderNotFoundException</p>
     */
    public void testAddFolder5() {
        SonDarFileSystem instance = new SonDarFileSystem(TESTDATAFOLDER);
        instance.addFolder("TestFolder1");
        instance.init(fileModule);
        try {
            assertEquals(instance.getFolderByName("Yxaxa").getState(), SonDarFolderState.None);
        } catch (FolderNotFoundException error) {
            return;
        }
        fail("No Exception");
    }

    /**
     * <b>Case 6</b>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file.</li>
     * <li> Start initialization</li>
     * <li> Get folder by name</li>
     * <p>
     * <b>Expected</b><br>Name coincides</p>
     */
    public void testGetFolderByName1() {
        String folderName = "TestFolder1";
        SonDarFileSystem instance = new SonDarFileSystem(TESTDATAFOLDER);
        instance.addFolder("TestFolder1");
        assertEquals(folderName, instance.getFolderByName(folderName).getFolderName());
    }

    /**
     * <b>Case 7</b>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file.</li>
     * <li> Start initialization</li>
     * <li> Get folder by another name</li>
     * <p>
     * <b>Expected</b><br>FolderNotFoundException</p>
     */
    public void testGetFolderByName2() {
        String folderName = "TestFolder1";
        SonDarFileSystem instance = new SonDarFileSystem(TESTDATAFOLDER);
        instance.addFolder("TestFolder1");
        try {
            assertEquals(folderName, instance.getFolderByName("dfghbdfg").getFolderName());
        } catch (FolderNotFoundException error) {
            return;
        }
        fail("No exception");
    }

    /**
     * <b>Case 8</b>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file and one file.</li>
     * <li> Start initialization</li>
     * <li> Add file to folder</li>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in same folder.</li>
     * <li> Get folder by another name</li>
     * <p>
     * <b>Expected</b><br>File list coincides</p>
     */
    public void testAddFile() throws Exception {
        String folderName = "TestFolder5";
        String fileName = "hello.txt";
        SonDarFileSystem instance = new SonDarFileSystem(TESTDATAFOLDER);
        instance.addFolder(folderName);
        Runtime.getRuntime().exec(TESTDATAFOLDER + "\\forTest.bat 3 5");
        Thread.sleep(400);
        instance.init(fileModule);
        FileModuleWriteThreadInterface result = instance.addFile(fileModule, folderName, fileName);
        result.write("Xaxaxa");
        result.close();
        String[] ex = {"test.txt", fileName};
        SonDarFileSystem instance2 = new SonDarFileSystem(TESTDATAFOLDER);
        instance2.addFolder(folderName);
        instance2.init(fileModule);
        SonDarFolder temp = instance2.getFolderByName(folderName);
        assertEquals(ex.length, temp.getFileList().size());
        for (int i = 0; i < temp.getFileList().size(); i++) {
            assertEquals(ex[i], temp.getFileList().get(i));
        }
    }

    /**
     * <b>Case 9</b>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file and one file.</li>
     * <li> Start initialization</li>
     * <li> Add file to folder</li>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in same folder.</li>
     * <li> Get folder by name</li>
     * <li> Check file list coincides(2)</li>
     * <li> Delete file from folder</li>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in same folder.</li>
     * <li> Get folder by name</li>
     * <li> Check file list coincides(1)</li>
     * <p>
     * <b>Expected</b><br>File list coincides</p>
     */
    public void testDeleteFile() throws Exception {
        String folderName = "TestFolder5";
        String fileName = "hello.txt";
        SonDarFileSystem instance = new SonDarFileSystem(TESTDATAFOLDER);
        instance.addFolder(folderName);
        Runtime.getRuntime().exec(TESTDATAFOLDER + "\\forTest.bat 3 5");
        Thread.sleep(400);
        instance.init(fileModule);
        FileModuleWriteThreadInterface result = instance.addFile(fileModule, folderName, fileName);
        result.write("Xaxaxa");
        result.close();
        String[] ex = {"test.txt", fileName};
        SonDarFileSystem instance2 = new SonDarFileSystem(TESTDATAFOLDER);
        instance2.addFolder(folderName);
        instance2.init(fileModule);
        SonDarFolder temp = instance2.getFolderByName(folderName);
        assertEquals(ex.length, temp.getFileList().size());
        for (int i = 0; i < temp.getFileList().size(); i++) {
            assertEquals(ex[i], temp.getFileList().get(i));
        }
        instance2.deleteFile(fileModule, folderName, fileName);
        SonDarFileSystem instance3 = new SonDarFileSystem(TESTDATAFOLDER);
        instance3.addFolder(folderName);
        instance3.init(fileModule);
        SonDarFolder temp2 = instance3.getFolderByName(folderName);

        String[] ex2 = {"test.txt"};
        assertEquals(ex2.length, temp2.getFileList().size());
        for (int i = 0; i < temp2.getFileList().size(); i++) {
            assertEquals(ex2[i], temp2.getFileList().get(i));
        }
    }

    /**
     * <b>Case 10</b>
     * <li> Create SonDarFileSystem object</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file.</li>
     * <li> Create SonDarFolder object in empty folder with correct
     * configuration file and one file.</li>
     * <li> Start initialization</li>
     * <li> Add file to folder</li>
     * <li> Get folder by name</li>
     * <li> Check file list coincides(1+1)</li>
     * <li> Move file from folders</li>
     * <li> Get folder by name</li>
     * <li> Check file list coincides(0+2)</li>
     * <p>
     * <b>Expected</b><br>File list coincides</p>
     */
    public void testMoveFile() throws Exception {
        String folderName1 = "TestFolder5";
        String folderName2 = "TestFolder6";
        String fileName = "hello.txt";
        Runtime.getRuntime().exec(TESTDATAFOLDER + "\\forTest.bat 3 5");
        Runtime.getRuntime().exec(TESTDATAFOLDER + "\\forTest.bat 1 6");
        Thread.sleep(300);
        SonDarFileSystem instance = new SonDarFileSystem(TESTDATAFOLDER);
        instance.addFolder(folderName1);
        instance.addFolder(folderName2);
        instance.init(fileModule);
        FileModuleWriteThreadInterface result = instance.addFile(fileModule, folderName1, fileName);
        result.write("Xaxaxa");
        result.close();
        instance.moveFile(fileModule, folderName1, fileName, folderName2, fileName);
        SonDarFolder temp = instance.getFolderByName(folderName2);
        assertEquals(temp.getFileList().size(), 1);
        assertEquals(temp.getFileList().get(0), "hello.txt");
        instance.getFolderByName(folderName2).getFile(fileModule, fileName);

    }

}
