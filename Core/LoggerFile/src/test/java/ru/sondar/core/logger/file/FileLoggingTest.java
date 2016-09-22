/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.core.logger.file;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.pc.FileModule;

/**
 *
 * @author GlebZemnieks
 */
public class FileLoggingTest {

    public static String path = "E:\\Development\\SonDar\\Core\\LoggerFile\\JUnitTest\\";

    FileLogging logger;

    public FileLoggingTest() {
        FileModuleInterface fileModule = new FileModule();
        logger = new FileLogging(fileModule, path + "test1.txt", true);
        logger.Log("test", "HELLO BRO(Test msg)");
        logger = new FileLogging(fileModule, path + "test1.txt", false);
        logger.Log("test", "HELLO BRO(Test msg)");
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of Log method, of class FileLogging.
     */
    @Test
    public void testLog_String_String() {
    }

}
