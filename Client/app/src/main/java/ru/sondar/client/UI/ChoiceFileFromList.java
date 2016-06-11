package ru.sondar.client.UI;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import ru.sondar.client.filemodule.android.FileModule;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filesystem.SonDarFileSystem;

public class ChoiceFileFromList extends ListActivity {

	/**
	 * Tag for logging
	 */
	public String logTag = "SDActivity:ChoiceFileFromList";

	/**
	 * File list adapter
	 */
	private ArrayAdapter<String> mAdapter;
	/**
	 * File Module object
	 */
	FileModuleInterface fileModule;
	/**
	 * File system object
	 */
	SonDarFileSystem fileSystem;
	/**
	 * Current folder name
	 */
	String nowFolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		fileModule = new FileModule(this);
		fileSystem = new SonDarFileSystem(Environment.getExternalStorageDirectory()+"/sondar");
		nowFolder = (String)getIntent().getSerializableExtra("folderName");
		fileSystem.addFolder(nowFolder);
		fileSystem.addFolder(Folder.temp.toString());
		fileSystem.init(fileModule);
	    mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fileSystem.getFolderByName(nowFolder).getFileList());
	    setListAdapter(mAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);
	    Toast.makeText(getApplicationContext(),
	            "Your choice is  " + l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
	    try {
			// TODO Why ?
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String newFileName = l.getItemAtPosition(position).toString();
		Config.Log("TEST", "file name : " + newFileName);
		Config.Log("TEST", "nowFolder : " + nowFolder + " : add uuid : " + (nowFolder.equals(Folder.example.toString())));
		if(nowFolder.equals(Folder.example.toString())){
			newFileName = newFileName.replace(".xml", "") + "_" + fileSystem.getUUID() + ".xml";
			Config.Log("TEST", "added UUID to file name : " + newFileName);
		}
 	   	fileSystem.copyFile(fileModule, nowFolder, l.getItemAtPosition(position).toString(), Folder.temp.toString(), newFileName);
		Intent intent = new Intent(this, DocumentSessionActivity.class); 
		intent.putExtra("folderName",nowFolder);
		intent.putExtra("fileName",newFileName);
		startActivity(intent);
	}
	
	public void onPause(){
		super.onPause();
		finish();
		super.onDestroy();
	}

	 
}