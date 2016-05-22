package ru.sondar.client.UI;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import ru.sondar.client.ALogging;
import ru.sondar.client.filemodule.android.FileModule;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filesystem.SonDarFileSystem;
import ru.sondar.core.logging.EmptyLogging;
import ru.sondar.core.logging.LoggerInterface;

public class ChoiseFileFromList extends ListActivity {

	public String logTag = "SDActivity:ChoiseFileFromList";
	LoggerInterface Logging = new EmptyLogging();
	private ArrayAdapter<String> mAdapter;
	
	SonDarFileSystem fileSystem;
	FileModuleInterface fileModule;
	String nowFolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		Logging = new ALogging();
		fileModule = new FileModule(this);
		//this.Logging =  new FileLogging(fileModule, Environment.getExternalStorageDirectory()+"/sondar/log/log_" + UUID.fromString((String) getIntent().getSerializableExtra("logUUID")) + ".txt");
		fileSystem = new SonDarFileSystem(Environment.getExternalStorageDirectory()+"/sondar",Logging);
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
	            "Вы выбрали " + l.getItemAtPosition(position).toString() + " элемент", Toast.LENGTH_SHORT).show();
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	   	fileSystem.copyFile(fileModule, nowFolder, l.getItemAtPosition(position).toString(), Folder.temp.toString(), l.getItemAtPosition(position).toString());
		Intent intent = new Intent(this, DocumentSessionActivity.class); 
		intent.putExtra("folderName",nowFolder);
		intent.putExtra("fileName",l.getItemAtPosition(position).toString());
		startActivity(intent);
	}
	
	public void onPause(){
		super.onPause();
		finish();
		super.onDestroy();
	}

	 
}