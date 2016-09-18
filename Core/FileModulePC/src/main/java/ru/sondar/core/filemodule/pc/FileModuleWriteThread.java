package ru.sondar.core.filemodule.pc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.exception.ThreadIsCloseException;

/**
 * Default realization interface
 * {@link ru.sondar.core.filemodule.FileModuleWriteThreadInterface} for windows.
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FileModuleWriteThread extends FileModuleThread implements FileModuleWriteThreadInterface {

    /**
     * BufferedReader object file representation for current thread
     */
    PrintWriter out;

    public FileModuleWriteThread(String fileName, boolean isAppend) {
        super(fileName);
        this.file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                //TODO Do nothing, while have not requirement or stable reproduction.
                throw new RuntimeException("File \"" + this.fileName + "\" not found and creating new file fail. It's impossible but It happened. Sorry!");
            }
        }
        try {
            if (isAppend) {
                this.out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8"));
            } else {
                this.out = new PrintWriter(file.getAbsoluteFile(), "UTF-8");
            }
        } catch (FileNotFoundException ex) {
            //TODO Do nothing, while have not requirement or stable reproduction.
            throw new RuntimeException("File \"" + this.fileName + "\" was found(or create new), but PrintWriter building fail. It's impossible but It happened. Sorry!");
        } catch (UnsupportedEncodingException ex) {
            //TODO Do nothing, while have not requirement or stable reproduction.
            throw new RuntimeException("File \"" + this.fileName + "\" have unsupported encoding. Needed encoding \"UTF-8\". Nothing to help you. Sorry!");
        }
    }

    @Override
    public int write(String textForWriting) {
        if (isClose()) {
            throw new ThreadIsCloseException();
        }
        out.write(textForWriting);
        return 0;
    }

    @Override
    public void close() {
        super.close();
        this.out.close();
    }

}
