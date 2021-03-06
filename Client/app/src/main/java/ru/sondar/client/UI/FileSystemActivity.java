package ru.sondar.client.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.xml.parsers.ParserConfigurationException;

import ru.sondar.client.ClientConfiguration;
import ru.sondar.client.PrepareTestDocumentOnDisk_NonReleaseCode;
import ru.sondar.client.R;
import ru.sondar.client.filemodule.android.FileModule;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filesystem.SonDarFileSystem;
import ru.sondar.core.filesystem.SonDarFolder;
import ru.sondar.core.filesystem.filechecker.FileCheckerInterface;
import ru.sondar.core.logger.Logger;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.SDDocument;

public class FileSystemActivity extends Activity {

    /**
     * Tag for logging
     */
    public String logTag = "SDActivity:FileSystemActivity";
    /**
     * If here are no files in example folder block application work and show default layout
     */
    public boolean isApplicationActive = true;
    /**
     * File module object
     */
    FileModuleInterface fileModule;
    /**
     * File system object
     */
    SonDarFileSystem fileSystem;
    /**
     * Session UUID
     */
    UUID logUUID;
    /**
     * Checker for document
     */
    FileCheckerInterface documentChecker = new FileCheckerInterface() {
        @Override
        public boolean isFileValid(File file) {
            SDDocument document = new SDDocument();
            try {
                document.loadDocument(file.getAbsolutePath());
            } catch (SAXException | IOException | ParserConfigurationException | ObjectStructureException e) {
                return false;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            fileModule = new FileModule(this);
            new ClientConfiguration(fileModule, Environment.getExternalStorageDirectory().getAbsolutePath());
            if (ClientConfiguration.testingEnabled.equals("Auto")) {
                prepare();
            }
            this.logUUID = UUID.randomUUID();
            Logger.Log(logTag, "start");
            Logger.Log(logTag, "File module prepare");
            fileModule = new FileModule(this);
            fileSystem = new SonDarFileSystem(Environment.getExternalStorageDirectory() + "/sondar");
            fileSystem.addFolder(Folder.example.toString(), documentChecker);
            fileSystem.addFolder(Folder.work.toString(), documentChecker);
            fileSystem.addFolder(Folder.temp.toString(), documentChecker);
            fileSystem.addFolder(Folder.done.toString(), documentChecker);
            fileSystem.addFolder(Folder.log.toString());
        } catch (Exception error) {
            Logger.Log("FileSystemActivity", "onCreate -> Exception", error);
            throw error;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            Logger.Log(logTag, "Preparing file system ...");
            if (ClientConfiguration.testingEnabled.equals("Auto")) {
                prepare();
            }
            fileSystem.init(fileModule, 1);
            Logger.Log(logTag, "File system init successfully");
            if (isSomeDocumentInTemp(fileSystem)) {
                Logger.Log(logTag, "isSomeDocumentInTemp is success -> start UI.DocumentSessionActivity");
            } else {
                Logger.Log(logTag, "isSomeDocumentInTemp is fail -> create new layout");
            }
            LinearLayout layout = new LinearLayout(this);
            Button createNew = getCreateNewButton(this, fileSystem, fileModule);
            layout.addView(createNew);
            layout.addView(getOpenButton(this, fileSystem, fileModule));
            if (fileSystem.getFolderByName(Folder.example.toString()).isEmpty()) {
                createNew.setEnabled(false);
                this.isApplicationActive = false;
            }
            if (ClientConfiguration.testingEnabled.equals("Hand")) {
                layout.addView(getRefreshButton(this));
            }
            Logger.Log(logTag, "set Layout");
            if (isApplicationActive) {
                Logger.Log(logTag, "Application is active. Show work layout");
                setContentView(layout);
            } else {
                Logger.Log(logTag, "Application is not active. Show default layout");
                setContentView(R.layout.activity_main123);
            }
        } catch (Exception error) {
            Logger.Log("FileSystemActivity", "onResume -> Exception", error);
            throw error;
        }
    }

    /**
     * If we have not closed document, open it without choice
     *
     * @param fileSystem
     */
    public boolean isSomeDocumentInTemp(SonDarFileSystem fileSystem) {
        SonDarFolder temp = fileSystem.getFolderByName(Folder.temp.toString());
        if (temp.getFileList().size() != 0) {
            Intent intent = new Intent(this, DocumentSessionActivity.class);
            intent.putExtra("fileName", temp.getFileList().get(0));
            intent.putExtra("logUUID", logUUID.toString());
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }

    /**
     * Create "Create new" button
     *
     * @param oldActivity
     * @param fileSystem
     * @param fileModule
     * @return
     */
    public Button getCreateNewButton(final Activity oldActivity, final SonDarFileSystem fileSystem, final FileModuleInterface fileModule) {
        Button createNew = new Button(this);
        createNew.setText("Create New");
        OnClickListener createNewOnClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileSystem.getFolderByName(Folder.example.toString()).getFileList().size() == 1) {
                    String newFileName = fileSystem.getFolderByName(Folder.example.toString()).getFileList().get(0).replace(".xml", "") + "_" + fileSystem.getUUID() + ".xml";
                    fileSystem.copyFile(fileModule, Folder.example.toString(), fileSystem.getFolderByName(Folder.example.toString()).getFileList().get(0), Folder.temp.toString(), newFileName);
                    Intent intent = new Intent(oldActivity, DocumentSessionActivity.class);
                    intent.putExtra("fileName", newFileName);
                    intent.putExtra("logUUID", logUUID.toString());
                    startActivity(intent);
                    finish();
                }
                if (fileSystem.getFolderByName(Folder.example.toString()).getFileList().size() > 1) {
                    Intent intent = new Intent(oldActivity, ChoiceFileFromList.class);
                    intent.putExtra("folderName", Folder.example.toString());
                    intent.putExtra("logUUID", logUUID.toString());
                    startActivity(intent);
                    finish();
                }

            }
        };
        createNew.setOnClickListener(createNewOnClickListener);
        return createNew;
    }

    /**
     * Create "Open" button
     *
     * @param oldActivity
     * @param fileSystem
     * @param fileModule
     * @return
     */
    public Button getOpenButton(final Activity oldActivity, final SonDarFileSystem fileSystem, final FileModuleInterface fileModule) {
        Button open = new Button(this);
        open.setText("Open");
        OnClickListener createNewOnClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(oldActivity, ChoiceFileFromList.class);
                intent.putExtra("folderName", Folder.work.toString());
                intent.putExtra("logUUID", logUUID.toString());
                startActivity(intent);
                finish();
            }
        };
        SonDarFolder work = fileSystem.getFolderByName(Folder.work.toString());
        if (work.isEmpty()) {
            open.setEnabled(false);
        }
        open.setOnClickListener(createNewOnClickListener);
        return open;
    }

    /**
     * Create "Refresh" button
     *
     * @param oldActivity
     * @return
     */
    public Button getRefreshButton(final Activity oldActivity) {
        Button open = new Button(this);
        open.setText("Refresh");
        OnClickListener createNewOnClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                prepare();
                Intent intent = new Intent(oldActivity, FileSystemActivity.class);
                startActivity(intent);
                finish();
            }
        };
        SonDarFolder work = fileSystem.getFolderByName(Folder.work.toString());
        if (work.isEmpty()) {
            open.setEnabled(false);
        }
        open.setOnClickListener(createNewOnClickListener);
        return open;
    }

    /**
     * Create folder hierarchy and default file
     */
    public void prepare() {
        File mainDir = new File(Environment.getExternalStorageDirectory() + "/sondar");
        mainDir.delete();
        mainDir.mkdir();
        File file = new File(Environment.getExternalStorageDirectory() + "/sondar/example");
        file.delete();
        file.mkdir();
        file = new File(Environment.getExternalStorageDirectory() + "/sondar/done");
        file.delete();
        file.mkdir();
        file = new File(Environment.getExternalStorageDirectory() + "/sondar/work");
        file.delete();
        file.mkdir();
        file = new File(Environment.getExternalStorageDirectory() + "/sondar/log");
        file.delete();
        file.mkdir();
        file = new File(Environment.getExternalStorageDirectory() + "/sondar/temp");
        file.delete();
        file.mkdir();
        try {
            file = new File(Environment.getExternalStorageDirectory() + "/sondar/example/config.txt");
            file.delete();
            prepareConfigFile(file);
            file = new File(Environment.getExternalStorageDirectory() + "/sondar/done/config.txt");
            file.delete();
            prepareConfigFile(file);
            file = new File(Environment.getExternalStorageDirectory() + "/sondar/work/config.txt");
            file.delete();
            prepareConfigFile(file);
            file = new File(Environment.getExternalStorageDirectory() + "/sondar/log/config.txt");
            file.delete();
            prepareConfigFile(file);
            file = new File(Environment.getExternalStorageDirectory() + "/sondar/temp/config.txt");
            file.delete();
            prepareConfigFile(file);
        } catch (IOException e) {
            Logger.Log("Prepare", "Error", e);
        }
        new PrepareTestDocumentOnDisk_NonReleaseCode(this);
    }

    private void prepareConfigFile(File file) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
        out.write("<configFile>\n</configFile>\n");
        out.close();
    }
}
