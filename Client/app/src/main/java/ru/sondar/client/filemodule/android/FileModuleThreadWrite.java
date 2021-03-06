package ru.sondar.client.filemodule.android;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.exception.ThreadIsCloseException;
import ru.sondar.core.logger.Logger;

public class FileModuleThreadWrite extends FileModuleThread implements FileModuleWriteThreadInterface {

    /**
     * Stream for writing
     */
    private PrintWriter streamToWrite;


    /**
     * Constructor without append mode
     *
     * @param context
     * @param fileName
     */
    public FileModuleThreadWrite(Context context, String fileName) {
        super(context, fileName);
        try {
            Logger.Log("FileModuleLog", "Try to create OutputStreamWriter for file '" + fileName + "'");
            streamToWrite = new PrintWriter(fileName, "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Logger.Log("FileModuleLog", "OutputStreamWriter for file '" + fileName + "' successful created");
    }

    /**
     * Constructor with append mode
     *
     * @param context
     * @param fileName
     * @param isAppend
     */
    public FileModuleThreadWrite(Context context, String fileName, boolean isAppend) {
        super(context, fileName);
        try {
            streamToWrite = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void flush() {
        this.streamToWrite.flush();
    }

    @Override
    public void close() {
        super.close();
        this.streamToWrite.close();
    }

    @Override
    public int write(String textForWriting) {
        if (isClose()) {
            throw new ThreadIsCloseException();
        }
        if (debugLogging) {
            Logger.Log("FileModuleLog", "Write text --> " + textForWriting);
        }
        streamToWrite.write(textForWriting);
        return 0;
    }
}
