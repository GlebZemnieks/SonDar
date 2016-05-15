package ru.sondar.core.filemodule.pc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.exception.SonDarFileNotFoundException;
import ru.sondar.core.filemodule.exception.ThreadIsCloseException;
import ru.sondar.core.filemodule.FileModuleReadThreadInterface;

/**
 * Main read thread for PC
 *
 * @author GlebZemnieks
 */
public class FileModuleReadThread extends FileModuleThread implements FileModuleReadThreadInterface {

    BufferedReader in;

    public FileModuleReadThread(String fileName) {
        super(fileName);
        Config.Log("FileModuleLog", "Try to open file '" + this.fileName + "'");
        this.file = new File(fileName);
        if (!file.exists()) {
            this.close();
            Config.Log("FileModuleLog", "File '" + this.fileName + "' not found");
            throw new SonDarFileNotFoundException();
        }
        try {
            this.in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        } catch (FileNotFoundException ex) {
        }
        Config.Log("FileModuleLog", "File '" + this.fileName + "' successful opened");
    }

    @Override
    public String read() {
        if (isClose()) {
            throw new ThreadIsCloseException();
        }
        Config.Log("FileModuleLog", "Read text from file '" + this.fileName + "'");
        String temp = null;
        try {
            temp = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(FileModuleReadThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        Config.Log("FileModuleLog", "-->TEXT:  '" + temp + "'");
        return temp;
    }

    @Override
    public void close() {
        super.close();
        try {
            if (in != null) {
                this.in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
