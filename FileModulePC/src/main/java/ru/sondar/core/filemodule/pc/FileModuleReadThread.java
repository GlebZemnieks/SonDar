package ru.sondar.core.filemodule.pc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleReadThreadInterface;
import ru.sondar.core.filemodule.exception.*;

/**
 * Default realization interface
 * {@link ru.sondar.core.filemodule.FileModuleReadThreadInterface} for windows.
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FileModuleReadThread extends FileModuleThread implements FileModuleReadThreadInterface {

    /**
     * BufferedReader object file representation for current thread
     */
    BufferedReader in;

    public FileModuleReadThread(String fileName) {
        super(fileName);
        Config.Log(LOGTAG, "Try to open file '" + this.fileName + "'");
        this.file = new File(fileName);
        if (!file.exists()) {
            super.close();
            Config.Log(LOGTAG, "File '" + this.fileName + "' not found");
            throw new SonDarFileNotFoundException("File '" + this.fileName + "' not found");
        }
        try {
            this.in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        } catch (FileNotFoundException ex) {
            //TODO Do nothing, while have not requirement or stable reproduction.
            throw new RuntimeException("File \"" + this.fileName + "\" was found, but BufferedReader building fail. It's impossible but It happened. Sorry!");
        }
        Config.Log(LOGTAG, "File '" + this.fileName + "' successful opened");
    }

    @Override
    public String read() {
        if (isClose()) {
            throw new ThreadIsCloseException();
        }
        Config.Log(LOGTAG, "Read text from file '" + this.fileName + "'");
        String temp = null;
        try {
            temp = in.readLine();
        } catch (IOException ex) {
            //TODO Do nothing, while have not requirement or stable reproduction.
            throw new RuntimeException("Reading file \"" + this.fileName + "\" fail. It's impossible but It happened. Sorry!");
        }
        Config.Log(LOGTAG, "-->TEXT:  '" + temp + "'");
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
            //TODO Do nothing, while have not requirement or stable reproduction.
            throw new RuntimeException("Closing file \"" + this.fileName + "\" fail. It's impossible but It happened. Sorry!");
        }
    }

}
