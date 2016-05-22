package ru.sondar.client.UI;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import ru.sondar.client.ALogging;
import ru.sondar.client.PrepareTestDocumentOnDisk_NonReleaseCode;
import ru.sondar.client.filemodule.android.FileModule;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filesystem.SonDarFileSystem;
import ru.sondar.core.filesystem.SonDarFolder;
import ru.sondar.core.logging.LoggerInterface;

public class FileSystemActivity extends Activity {

	public String logTag = "SDActivity:FileSystemActivity";
	LoggerInterface Logging = new ALogging();
	FileModuleInterface fileModule;
	SonDarFileSystem fileSystem;
	UUID logUUID;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.Logging = new ALogging();		
		this.Logging.Log(logTag,"start");
		//prepare();
		Toast.makeText(getApplicationContext(),
			(Environment.getExternalStorageDirectory()+"/sondar/log/log-file.txt").toString(), Toast.LENGTH_SHORT).show();

		this.Logging.Log(logTag,"file module prepare");
		fileModule = new FileModule(this);
		logUUID = UUID.randomUUID();
		//this.Logging =  new FileLogging(fileModule, Environment.getExternalStorageDirectory()+"/sondar/log/log_" + logUUID.toString() + ".txt");
		fileSystem = new SonDarFileSystem(Environment.getExternalStorageDirectory()+"/sondar",Logging);
		fileSystem.addFolder(Folder.example.toString());
		fileSystem.addFolder(Folder.work.toString());
		fileSystem.addFolder(Folder.temp.toString());
		fileSystem.addFolder(Folder.done.toString());
		fileSystem.addFolder(Folder.log.toString());
		
	}
	
	public void onResume(){
		super.onResume();
		this.Logging.Log(logTag,"file system prepare");
		fileSystem.init(fileModule);
		this.Logging.Log(logTag,"file system init done");
		isSomeDocumentInTemp(fileSystem);
		this.Logging.Log(logTag,"isSomeDocumentInTemp is fail -> create new layout");
		LinearLayout layout = new LinearLayout(this);
		layout.addView(getCreateNewButton(this,fileSystem, fileModule));
		layout.addView(getOpenButton(this,fileSystem, fileModule));
		Logging.Log(logTag, "set Layout");
		setContentView(layout);
	}
	
	public void isSomeDocumentInTemp(SonDarFileSystem fileSystem){
		SonDarFolder temp = fileSystem.getFolderByName(Folder.temp.toString());
		if(temp.getFileList()!=null){
		    Intent intent = new Intent(this, DocumentSessionActivity.class); 
		    intent.putExtra("fileName",temp.getFileList()[0]);
		    intent.putExtra("logUUID",logUUID.toString());
			this.Logging.Log(logTag,"isSomeDocumentInTemp is success -> start UI.DocumentSessionActivity");
		    startActivity(intent);
			   finish();
		}
	}
	
	public Button getCreateNewButton(final Activity oldActivity, final SonDarFileSystem fileSystem,final FileModuleInterface fileModule){
		Button createNew = new Button(this);
		createNew.setText("Create New");
		OnClickListener createNewOnCliclListener = new OnClickListener() {
	       @Override
	       public void onClick(View v) {
	    	   String newFileName = fileSystem.getFolderByName(Folder.example.toString()).getFileList()[0].replace(".xml", "") + "_" + fileSystem.getUUID() +".xml";
	    	   fileSystem.copyFile(fileModule, Folder.example.toString(), fileSystem.getFolderByName(Folder.example.toString()).getFileList()[0], Folder.temp.toString(), newFileName);
			   Intent intent = new Intent(oldActivity, DocumentSessionActivity.class); 
			   intent.putExtra("fileName",newFileName);
			   intent.putExtra("logUUID",logUUID.toString());
			   startActivity(intent);
			   finish();
	       }
		};
		createNew.setOnClickListener(createNewOnCliclListener);
		return createNew;
	}
	public Button getOpenButton(final Activity oldActivity, final SonDarFileSystem fileSystem,final FileModuleInterface fileModule){
		Button open = new Button(this);
		open.setText("Open");
		OnClickListener createNewOnCliclListener = new OnClickListener() {
		       @Override
		       public void onClick(View v) {
		    	   Intent intent = new Intent(oldActivity, ChoiseFileFromList.class); 
				   intent.putExtra("folderName",Folder.work.toString());
				   intent.putExtra("logUUID",logUUID.toString());
				   startActivity(intent);
				   finish();
		       }
			};
		SonDarFolder work = fileSystem.getFolderByName(Folder.work.toString());
		if(work.getFileList()==null){
			open.setEnabled(false);
		}
		open.setOnClickListener(createNewOnCliclListener);
		return open;
	}
	
	public void prepare(){
		File mainDir = new File(Environment.getExternalStorageDirectory()+"/sondar");
		mainDir.mkdir();
	    File file = new File(Environment.getExternalStorageDirectory()+"/sondar/example");
		file.mkdir();
		file = new File(Environment.getExternalStorageDirectory()+"/sondar/done");
		file.mkdir();
		file = new File(Environment.getExternalStorageDirectory()+"/sondar/work");
		file.mkdir();
		file = new File(Environment.getExternalStorageDirectory()+"/sondar/log");
		file.mkdir();
		file = new File(Environment.getExternalStorageDirectory()+"/sondar/temp");
		file.mkdir();
		try {
			file = new File(Environment.getExternalStorageDirectory()+"/sondar/example/config.txt");
			file.delete();
			file.createNewFile();
			Logging.Log("test","test " + file.exists());
			file = new File(Environment.getExternalStorageDirectory()+"/sondar/done/config.txt");
			file.delete();
			file.createNewFile();
			file = new File(Environment.getExternalStorageDirectory()+"/sondar/work/config.txt");
			file.delete();
			file.createNewFile();
			file = new File(Environment.getExternalStorageDirectory()+"/sondar/log/config.txt");
			file.delete();
			file.createNewFile();
			file = new File(Environment.getExternalStorageDirectory()+"/sondar/temp/config.txt");
			file.delete();
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new PrepareTestDocumentOnDisk_NonReleaseCode(this,Logging);
	}
}
