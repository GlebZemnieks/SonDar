package ru.sondar.client;

import android.content.Context;
import android.os.Environment;

import ru.sondar.client.filemodule.android.FileModule;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.logging.LoggerInterface;

public class PrepareTestDocumentOnDisk_NonReleaseCode {
	LoggerInterface Logging = null;
	public PrepareTestDocumentOnDisk_NonReleaseCode(Context context,LoggerInterface logger){
		this.Logging = logger;
		FileModuleInterface fileModule = new FileModule(context);

		FileModuleWriteThreadInterface writeThreadConfig = fileModule.getWriteThread(Environment.getExternalStorageDirectory()+"/sondar/example/config.txt");
		writeThreadConfig.write("<configFile>\n" 
		+ "<fileName>Example1.xml</fileName>\n"
		+ "</configFile>" );
		writeThreadConfig.close();
		
		Logging.Log("TEST","fileModule : " + fileModule);
		FileModuleWriteThreadInterface writeThread = fileModule.getWriteThread(Environment.getExternalStorageDirectory()+"/sondar/example/" + "Example1.xml");
		writeThread.write("<Document>\n"
				+ "<head>\n"
				+ "<documentUUID>00000000-0000-0000-0000-000000000000</documentUUID>\n"
				+ "<pluginUUID>00000000-0000-0000-0000-000000000000</pluginUUID>\n"
				+ "<creationTime>45780364596876</creationTime>\n"
				+ "<lastModificationTime>248657685765234</lastModificationTime>\n"
				+ "</head>\n"
				+ "<XMLSequence>\n"
				+ "<object type=\"Text\" id=\"0\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "</object>\n"
				+ "<object type=\"Text\" id=\"1\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "</object>\n"
				+ "<object type=\"Text\" id=\"2\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "</object>\n"
				+ "<object type=\"Text\" id=\"3\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "</object>\n"
				+ "<object type=\"Text\" id=\"4\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "</object>\n"
				+ "<object type=\"Text\" id=\"5\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "</object>\n"
				+ "<object type=\"Text\" id=\"6\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "</object>\n"
				+ "<object type=\"Text\" id=\"7\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "</object>\n"
				+ "<object type=\"Text\" id=\"8\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "</object>\n"
				+ "</XMLSequence>\n"
				+ "<Log>\n"
				+ "</Log>\n"
				+ "</Document>\n");
		writeThread.close();
	}

	public void setLogger(LoggerInterface logger){
	    this.Logging = logger;
	}
}
