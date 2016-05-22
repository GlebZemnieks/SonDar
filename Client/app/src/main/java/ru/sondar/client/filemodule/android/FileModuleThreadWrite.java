package ru.sondar.client.filemodule.android;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.content.Context;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.exception.ThreadIsCloseException;

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
            Config.Log("FileModuleLog", "Try to create OutputStreamWriter for file '" + fileName + "'");
            streamToWrite = new PrintWriter(fileName, "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Config.Log("FileModuleLog", "OutputStreamWriter for file '" + fileName + "' successful created");
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
            Config.Log("FileModuleLog", "Try to create OutputStreamWriter for file '" + fileName + "'");
            streamToWrite = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Config.Log("FileModuleLog", "OutputStreamWriter for file '" + fileName + "' successful created");
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
        Config.Log("FileModuleLog", "Write text in file '" + fileName + "'\n-->TEXT: " + textForWriting);
        streamToWrite.write(textForWriting);
        return 0;
    }

}
