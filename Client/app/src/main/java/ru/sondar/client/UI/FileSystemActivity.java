package ru.sondar.client.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import ru.sondar.client.ALogging;
import ru.sondar.client.ClientConfiguration;
import ru.sondar.client.PrepareTestDocumentOnDisk_NonReleaseCode;
import ru.sondar.client.R;
import ru.sondar.client.filemodule.android.FileModule;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filesystem.SonDarFileSystem;
import ru.sondar.core.filesystem.SonDarFolder;

public class FileSystemActivity extends Activity {

    /**
     * Tag for logging
     */
	public String logTag = "SDActivity:FileSystemActivity";
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new ClientConfiguration(this);
		if(ClientConfiguration.testingEnabled.equals("Auto")) {
			prepare();
		}
        this.logUUID = UUID.randomUUID();
		Config.Log(logTag,"start");
		Config.Log(logTag,"File module prepare");
		fileModule = new FileModule(this);
		fileSystem = new SonDarFileSystem(Environment.getExternalStorageDirectory()+"/sondar");
		fileSystem.addFolder(Folder.example.toString());
		fileSystem.addFolder(Folder.work.toString());
		fileSystem.addFolder(Folder.temp.toString());
		fileSystem.addFolder(Folder.done.toString());
		fileSystem.addFolder(Folder.log.toString());
	}

    /**
     * If here are no files in example folder block application work and show default layout
     */
    public boolean isApplicationActive = true;

    @Override
	public void onResume(){
		super.onResume();
		Config.Log(logTag,"Preparing file system ...");
		if(ClientConfiguration.testingEnabled.equals("Auto")) {
			prepare();
		}
		fileSystem.init(fileModule);
		Config.Log(logTag,"File system init successfully");
		if(isSomeDocumentInTemp(fileSystem)){
			Config.Log(logTag,"isSomeDocumentInTemp is success -> start UI.DocumentSessionActivity");
			return;
		}
		Config.Log(logTag,"isSomeDocumentInTemp is fail -> create new layout");
		LinearLayout layout = new LinearLayout(this);
		layout.addView(getCreateNewButton(this,fileSystem, fileModule));
		layout.addView(getOpenButton(this,fileSystem, fileModule));
		if(ClientConfiguration.testingEnabled.equals("Hand")) {
			layout.addView(getRefreshButton(this));
		}
		Config.Log(logTag, "set Layout");
        if(isApplicationActive) {
            Config.Log(logTag, "Application is active. Show work layout");
            setContentView(layout);
        } else {
            Config.Log(logTag, "Application is not active. Show default layout");
            setContentView(R.layout.activity_main123);
        }
	}

    /**
     * If we have not closed document, open it without choice
     * @param fileSystem
     */
	public boolean isSomeDocumentInTemp(SonDarFileSystem fileSystem){
		SonDarFolder temp = fileSystem.getFolderByName(Folder.temp.toString());
		if(temp.getFileList().size()!=0){
			Config.Log(logTag,"isSomeDocumentInTemp is success -> start UI.DocumentSessionActivity");
			Intent intent = new Intent(this, DocumentSessionActivity.class);
			intent.putExtra("fileName",temp.getFileList().get(0));
			intent.putExtra("logUUID",logUUID.toString());
			startActivity(intent);
			finish();
			return true;
		}
		return false;
	}

    /**
     * Create "Create new" button
     * @param oldActivity
     * @param fileSystem
     * @param fileModule
     * @return
     */
	public Button getCreateNewButton(final Activity oldActivity, final SonDarFileSystem fileSystem,final FileModuleInterface fileModule){
		Button createNew = new Button(this);
		createNew.setText("Create New");
		OnClickListener createNewOnClickListener = new OnClickListener() {
	       @Override
	       public void onClick(View v) {
               if(fileSystem.getFolderByName(Folder.example.toString()).getFileList().size() == 1) {
                   String newFileName = fileSystem.getFolderByName(Folder.example.toString()).getFileList().get(0).replace(".xml", "") + "_" + fileSystem.getUUID() + ".xml";
                   fileSystem.copyFile(fileModule, Folder.example.toString(), fileSystem.getFolderByName(Folder.example.toString()).getFileList().get(0), Folder.temp.toString(), newFileName);
                   Intent intent = new Intent(oldActivity, DocumentSessionActivity.class);
                   intent.putExtra("fileName", newFileName);
                   intent.putExtra("logUUID", logUUID.toString());
                   startActivity(intent);
                   finish();
               }
               if(fileSystem.getFolderByName(Folder.example.toString()).getFileList().size() > 1){
                   Intent intent = new Intent(oldActivity, ChoiceFileFromList.class);
                   intent.putExtra("folderName",Folder.example.toString());
                   intent.putExtra("logUUID",logUUID.toString());
                   startActivity(intent);
                   finish();
               }

	          }
		};
        SonDarFolder example = fileSystem.getFolderByName(Folder.example.toString());
        if(example.isEmpty()){
            createNew.setEnabled(false);
            this.isApplicationActive = false;
        }
		createNew.setOnClickListener(createNewOnClickListener);
		return createNew;
	}

    /**
     * Create "Open" button
     * @param oldActivity
     * @param fileSystem
     * @param fileModule
     * @return
     */
	public Button getOpenButton(final Activity oldActivity, final SonDarFileSystem fileSystem,final FileModuleInterface fileModule){
		Button open = new Button(this);
		open.setText("Open");
		OnClickListener createNewOnClickListener = new OnClickListener() {
		       @Override
		       public void onClick(View v) {
		    	   Intent intent = new Intent(oldActivity, ChoiceFileFromList.class);
				   intent.putExtra("folderName",Folder.work.toString());
				   intent.putExtra("logUUID",logUUID.toString());
				   startActivity(intent);
				   finish();
		       }
			};
		SonDarFolder work = fileSystem.getFolderByName(Folder.work.toString());
		if(work.isEmpty()){
			open.setEnabled(false);
		}
		open.setOnClickListener(createNewOnClickListener);
		return open;
	}

    /**
     * Create "Refresh" button
     * @param oldActivity
     * @return
     */
    public Button getRefreshButton(final Activity oldActivity){
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
		if(work.isEmpty()){
            open.setEnabled(false);
        }
        open.setOnClickListener(createNewOnClickListener);
        return open;
    }

    /**
     * Create folder hierarchy and default file
     */
	public void prepare(){
		File mainDir = new File(Environment.getExternalStorageDirectory()+"/sondar");
		mainDir.delete();
		mainDir.mkdir();
	    File file = new File(Environment.getExternalStorageDirectory()+"/sondar/example");
		file.delete();
		file.mkdir();
		file = new File(Environment.getExternalStorageDirectory()+"/sondar/done");
		file.delete();
		file.mkdir();
		file = new File(Environment.getExternalStorageDirectory()+"/sondar/work");
		file.delete();
		file.mkdir();
		file = new File(Environment.getExternalStorageDirectory()+"/sondar/log");
		file.delete();
		file.mkdir();
		file = new File(Environment.getExternalStorageDirectory()+"/sondar/temp");
		file.delete();
		file.mkdir();
		try {
			file = new File(Environment.getExternalStorageDirectory()+"/sondar/example/config.txt");
			file.delete();
			prepareConfigFile(file);
			file = new File(Environment.getExternalStorageDirectory()+"/sondar/done/config.txt");
			file.delete();
			prepareConfigFile(file);
			file = new File(Environment.getExternalStorageDirectory()+"/sondar/work/config.txt");
			file.delete();
			prepareConfigFile(file);
			file = new File(Environment.getExternalStorageDirectory()+"/sondar/log/config.txt");
			file.delete();
			prepareConfigFile(file);
			file = new File(Environment.getExternalStorageDirectory()+"/sondar/temp/config.txt");
			file.delete();
			prepareConfigFile(file);
		} catch (IOException e) {
			Config.Log("Prepare","Error",e);
		}
		new PrepareTestDocumentOnDisk_NonReleaseCode(this);
	}
	private void prepareConfigFile(File file) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
		out.write("<configFile>\n</configFile>\n");
		out.close();
	}
}
