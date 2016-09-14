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
import ru.sondar.client.documentmodel.android.AXMLDocument;
import ru.sondar.client.documentmodel.android.AXMLSequenceObject;
import ru.sondar.client.documentmodel.android.XMLSequenceIndexOverflowException;
import ru.sondar.client.filemodule.android.FileModule;
import ru.sondar.core.logger.Logger;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filesystem.SonDarFileSystem;
import ru.sondar.documentmodel.SDDOMParser;

public class DocumentSessionActivity extends Activity {

	public String logTag = "SDActivity:DocumentSessionActivity";

	// TODO Redesign
	public static final int buttonHeight = 96;
	private static final int buttonWidth = 72;
	private static final int footer = 100;

	private LinearLayout getButtonLayout(final Context context,
			final AXMLDocument sequence) {
		LinearLayout buttonLayout = new LinearLayout(this);
		buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
		buttonLayout.addView(getBackButton(context, sequence));
		buttonLayout.addView(getNextButton(context, sequence));
		return buttonLayout;
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

	private Button getNextButton(final Context context,
			final AXMLDocument document) {
		OnClickListener nextButtonOnClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					LinearLayout tempLayout = document.getNextLayout();
					tempLayout.addView(getButtonLayout(context, document));
					setContentView(tempLayout);
				} catch (XMLSequenceIndexOverflowException error){
					Logger.Log(logTag, "Last page. Generation was skipped");
				}
			}
		};
		Button nextButton = getButton("Next", nextButtonOnClickListener,
				Gravity.RIGHT);
		return nextButton;
	}

	private Button getBackButton(final Context context, final AXMLDocument document) {
		OnClickListener backButtonOnClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout tempLayout = document.getBackLayout();
				tempLayout.addView(getButtonLayout(context, document));
				setContentView(tempLayout);
			}
		};
		Button backButton = getButton("Back", backButtonOnClickListener,
				Gravity.LEFT);
		return backButton;
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
		Logger.Log(logTag, "MainActivity.onCreate start");

		fileSystem = new SonDarFileSystem(Environment.getExternalStorageDirectory()+"/sondar");
		fileSystem.addFolder(Folder.temp.toString());
		fileSystem.addFolder(Folder.work.toString());
		fileSystem.addFolder(Folder.done.toString());
		fileSystem.init(fileModule);

		document = new AXMLDocument(this);
		Logger.Log(logTag, "Load Document start : " + fileName);
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
		fileSystem.getFolderByName(Folder.temp.toString()).setOpenFile(fileName);
		Logger.Log(logTag, "LoadDocument done : " + fileName);
		Logger.Log(logTag, "Create start layout");
		LinearLayout layout = document.getStartLayout();

		Logger.Log(logTag, "prepare buttonLayout");
		layout.addView(getButtonLayout(this, document));
		Logger.Log(logTag, "prepare buttonLayout");

		Logger.Log(logTag, "set Layout");
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
			Logger.Log(logTag, "onPause();");
			FileModuleWriteThreadInterface temp = fileSystem.getFolderByName(Folder.temp.toString()).saveFile(fileModule);
			Logger.Log(logTag, "SaveDocument");
			document.saveDocument(temp);
			temp.close();
			fileSystem.moveFile(fileModule, Folder.temp.toString(), fileName, Folder.work.toString(), fileName);
			Intent intent = new Intent(this, FileSystemActivity.class); 
		    startActivity(intent);
			super.onPause();
			finish();
		}catch(Exception error){
			Logger.Log(logTag, "Error : " + error.getMessage());
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


	public static int getFooter() {
		return footer;
	}
}
