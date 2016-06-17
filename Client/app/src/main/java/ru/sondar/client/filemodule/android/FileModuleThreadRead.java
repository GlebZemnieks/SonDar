package ru.sondar.client.filemodule.android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.content.Context;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleReadThreadInterface;
import ru.sondar.core.filemodule.exception.SonDarFileNotFoundException;
import ru.sondar.core.filemodule.exception.ThreadIsCloseException;

public class FileModuleThreadRead extends FileModuleThread implements FileModuleReadThreadInterface {

    /**
     * Thread for reading
     */
    private FileReader streamToRead;

    /**
     * Constructor
     *
     * @param context
     * @param fileName
     */
    public FileModuleThreadRead(Context context, String fileName) {
        super(context, fileName);
        try {
            Config.Log("FileModuleLog", "Try to create InputStreamReader for file '" + fileName + "'");
            this.streamToRead = new FileReader(new File(fileName));

        } catch (FileNotFoundException e) {
            Config.Log("FileModuleLog", "File '" + this.fileName + "' not found");
            throw new SonDarFileNotFoundException();
        }
        Config.Log("FileModuleLog", "InputStreamReader for file '" + fileName + "' successful created");
    }

    @Override
    public void close() {
        super.close();
        try {
            this.streamToRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read() {
        if (isClose()) {
            throw new ThreadIsCloseException();
        }
        Config.Log("FileModuleLog", "Read text from file '" + fileName + "'");
        String temp = "";
        try {
            int c = 0;
            while ((c = streamToRead.read()) != -1 && ((char) (c)) != '\n') {
                temp += (char) c;
                //Logging.Log("FileModuleLog", "!!!  :  '" + temp + "' += '" + (char)c + "'");
            }
            Config.Log("FileModuleLog", "-->TEXT:  '" + temp + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (temp != "") {
            return temp;
        }
        return null;
    }

}