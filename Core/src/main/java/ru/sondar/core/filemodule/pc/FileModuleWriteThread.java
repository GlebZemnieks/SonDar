package ru.sondar.core.filemodule.pc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.exception.ThreadIsCloseException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.logging.LoggerInterface;

/**
 * Main write thread for PC
 *
 * @author Лада
 */
public class FileModuleWriteThread extends FileModuleThread implements FileModuleWriteThreadInterface {

    PrintWriter out;

    public FileModuleWriteThread(String fileName) {
        super(fileName);
        Config.Log("FileModuleLog", "Try to open file '" + fileName + "'");
        this.file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileModuleWriteThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.out = new PrintWriter(file.getAbsoluteFile(), "UTF-8");
        } catch (FileNotFoundException ex) {
            Config.Log("Error", "File '" + this.fileName + "' not found");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FileModuleWriteThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        Config.Log("FileModuleLog", "File '" + fileName + "' successful opened");
    }

    public FileModuleWriteThread(String fileName, boolean isAppend) {
        super(fileName);
        Config.Log("FileModuleLog", "Try to open file '" + fileName + "'");
        this.file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileModuleWriteThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8"));
        } catch (FileNotFoundException ex) {
            Config.Log("Error", "File '" + this.fileName + "' not found");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FileModuleWriteThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        Config.Log("FileModuleLog", "File '" + fileName + "' successful opened");
    }

    @Override
    public int write(String textForWriting) {
        if (isClose()) {
            throw new ThreadIsCloseException();
        }
        Config.Log("FileModuleLog", "Write text in file '" + fileName + "'\n-->TEXT: " + textForWriting);
        out.write(textForWriting);
        return 0;
    }

    @Override
    public void close() {
        super.close();
        this.out.close();
    }

}
