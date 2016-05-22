package ru.sondar.client.UI;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import ru.sondar.client.ALogging;
import ru.sondar.client.documentmodel.android.AXMLDocument;
import ru.sondar.client.documentmodel.android.AXMLSequenceObject;
import ru.sondar.client.filemodule.android.FileModule;
import ru.sondar.core.documentmodel.SDDOMParser;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.filesystem.SonDarFileSystem;
import ru.sondar.core.logging.LoggerInterface;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

public class DocumentSessionActivity extends Activity {

	public String logTag = "SDActivity:DocumentSessionActivity";

	// TODO Redesign
	public static final int buttonHeight = 96;
	private static final int buttonWidth = 72;
	private static final int footer = 100;

	LoggerInterface Logging = new ALogging();
	
	private Button getBackButton(final Context context, final AXMLDocument sequence) {
		OnClickListener backButtonOnCliclListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout tempLayout = sequence.getBackLayout();
				tempLayout.addView(getButtonLayout(context, sequence));
				setContentView(tempLayout);
			}
		};
		Button backButton = getButton("Back", backButtonOnCliclListener,
				Gravity.LEFT);
		return backButton;
	}

	private Button getButton(String buttonName,
			OnClickListener buttonOnCliclListener, int gravityValue) {
		Button tempButton = new Button(this);
		tempButton.setText(buttonName);
		tempButton.setWidth(buttonWidth);
		tempButton.setHeight(buttonHeight);
		tempButton.setOnClickListener(buttonOnCliclListener);
		tempButton.setGravity(Gravity.BOTTOM);
		tempButton.setGravity(gravityValue);
		return tempButton;
	}

	private LinearLayout getButtonLayout(final Context context,
			final AXMLDocument sequence) {
		LinearLayout buttonLayout = new LinearLayout(this);
		buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
		buttonLayout.addView(getBackButton(context, sequence));
		buttonLayout.addView(getNextButton(context, sequence));
		return buttonLayout;
	}

	private Button getNextButton(final Context context,
			final AXMLDocument sequence) {
		OnClickListener nextButtonOnCliclListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("TEST", "Next button press");
				LinearLayout tempLayout = sequence.getNextLayout();
				tempLayout.addView(getButtonLayout(context, sequence));
				setContentView(tempLayout);
			}
		};
		Button nextButton = getButton("Next", nextButtonOnCliclListener,
				Gravity.RIGHT);
		return nextButton;
	}
	
	AXMLDocument document;
	SonDarFileSystem fileSystem;
	FileModuleInterface fileModule;
	String fileName;
	String folderName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fileName = (String) getIntent().getSerializableExtra("fileName");
		folderName = (String) getIntent().getSerializableExtra("folderName");

		fileModule = new FileModule(this);
		//this.Logging =  new FileLogging(fileModule, Environment.getExternalStorageDirectory()+"/sondar/log/log_" + UUID.fromString((String) getIntent().getSerializableExtra("logUUID")) + ".txt");
		Logging.Log(logTag, "MainActivity.onCreate start");

		fileSystem = new SonDarFileSystem(Environment.getExternalStorageDirectory()+"/sondar", Logging);
		fileSystem.addFolder(Folder.temp.toString());
		fileSystem.addFolder(Folder.work.toString());
		fileSystem.addFolder(Folder.done.toString());
		fileSystem.init(fileModule);

		document = new AXMLDocument(this);
		Logging.Log(logTag, "Load Document start : " + fileName);
		try {
			document.loadDocument(
					new SDDOMParser(fileSystem.getFolderByName(Folder.temp.toString()).getGlobalFileName(fileName)),
					new AXMLSequenceObject()
			);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjectStructureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Logging.Log(logTag, "LoadDocument done : " + fileName);
		Logging.Log(logTag, "Create start layout");
		LinearLayout layout = document.getStartLayout();

		Logging.Log(logTag, "prepare buttonLayout");
		layout.addView(getButtonLayout(this, document));
		Logging.Log(logTag, "prepare buttonLayout");

		Logging.Log(logTag, "set Layout");
		setContentView(layout);
			

		/*
		 * connection = new Connection(this,Logging);
		 * //connection.sendData("Hello");
		 * 
		 * connection.sendFile("TEST\\EXAMPLE\\Example1.xml");
		 */
	}
	
	public void onPause(){
		try{
			Logging.Log(logTag, "onPause();");
			FileModuleWriteThread temp = (FileModuleWriteThread)fileSystem.getFolderByName(Folder.temp.toString()).saveFile(fileModule);
			Logging.Log(logTag, "SaveDocument");
			document.saveDocument(temp);
			temp.close();
			fileSystem.moveFile(fileModule, Folder.temp.toString(), fileName, Folder.work.toString(), fileName);
			Intent intent = new Intent(this, FileSystemActivity.class); 
		    startActivity(intent);
			super.onPause();
			finish();
		}catch(Exception error){
			Logging.Log(logTag, "Error", error); 
			super.onPause();
		}
		
	}
	/*
	@Override
	public void onBackPressed() {
		openQuitDialog();
	}
	private void openQuitDialog() {
		AlertDialog.Builder quitDialog = new AlertDialog.Builder(DocumentSessionActivity.this);
		quitDialog.setTitle("Закрыть документ");
		quitDialog.setMessage("hello");
		quitDialog.setCancelable(true);

		quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				FileModuleWriteThreadInterface temp = fileSystem.getFolderByName(Folder.temp.toString()).saveFile(fileModule);
				document.saveDocument(temp);
				temp.close();
				fileSystem.moveFile(fileModule, Folder.temp.toString(), fileName, Folder.done.toString(), fileName);
				finish();
			}
		});

		quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				FileModuleWriteThreadInterface temp = fileSystem.getFolderByName(Folder.temp.toString()).saveFile(fileModule);
				document.saveDocument(temp);
				temp.close();
				fileSystem.moveFile(fileModule, Folder.temp.toString(), fileName, Folder.work.toString(), fileName);
				finish();
			}
		});
		quitDialog.setNeutralButton("Продолжить работу", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		quitDialog.show();
	}
	*/

	public void setLogger(LoggerInterface logger) {
		this.Logging = logger;
	}

	public static int getFooter() {
		return footer;
	}
}
