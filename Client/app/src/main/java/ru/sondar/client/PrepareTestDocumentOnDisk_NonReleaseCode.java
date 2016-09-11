package ru.sondar.client;

import android.content.Context;
import android.os.Environment;
import ru.sondar.client.filemodule.android.FileModule;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;

public class PrepareTestDocumentOnDisk_NonReleaseCode {
	public PrepareTestDocumentOnDisk_NonReleaseCode(Context context){
		FileModuleInterface fileModule = new FileModule(context);

		FileModuleWriteThreadInterface writeThreadConfig = fileModule.getWriteThread(Environment.getExternalStorageDirectory()+"/sondar/example/config.txt");
		writeThreadConfig.write("<configFile>\n"
				+ "<fileName>Example1.xml</fileName>\n"
				+ "<fileName>Example2.xml</fileName>\n"
				+ "<fileName>Example3.xml</fileName>\n"
				+ "</configFile>" );
		writeThreadConfig.close();

		FileModuleWriteThreadInterface writeThread = fileModule.getWriteThread(Environment.getExternalStorageDirectory()+"/sondar/example/" + "Example1.xml");
		writeThread.write("<Document>\n"
				+ "<head>\n"
				+ "<documentUUID>00000000-0000-0000-0000-000000000000</documentUUID>\n"
				+ "<pluginUUID>00000000-0000-0000-0000-000000000000</pluginUUID>\n"
				+ "<creationTime>45780364596876</creationTime>\n"
				+ "<lastModificationTime>248657685765234</lastModificationTime>\n"
				+ "</head>\n"
				+ "<WordsBase>\n"
				+ "</WordsBase>\n"
				+ "<XMLSequence>\n"
				+ "<object type=\"CheckBox\" id=\"0\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "<default>false</default>\n"
				+ "</object>\n"
				+ "<object type=\"Date\" id=\"1\">\n"
				+ "<date>531651515</date>\n"
				+ "</object>\n"
				+ "<object type=\"EditText\" id=\"2\">\n"
				+ "<textEdit>hello bro</textEdit>\n"
				+ "<textLength>16</textLength>\n"
				+ "</object>\n"
				+ "<object type=\"EndLn\" id=\"3\">\n"
				+ "</object>\n"
				+ "<object type=\"Spinner\" id=\"4\">\n"
				+ "<dataList><item>test</item><item>test2</item><item>test3</item></dataList>\n"
				+ "<ItemSelected>0</ItemSelected>\n"
				+ "</object>\n"
				+ "<object type=\"Text\" id=\"5\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "</object>\n"
				+ "</XMLSequence>\n" +
				"<DependencyPart>\n" +
				"</DependencyPart>\n"
				+ "<Log>\n"
				+ "</Log>\n"
				+ "</Document>\n");
		writeThread.close();

		writeThread = fileModule.getWriteThread(Environment.getExternalStorageDirectory()+"/sondar/example/" + "Example2.xml");
		writeThread.write("<Document>\n"
				+ "<head>\n"
				+ "<documentUUID>00000000-0000-0000-0000-000000000000</documentUUID>\n"
				+ "<pluginUUID>00000000-0000-0000-0000-000000000000</pluginUUID>\n"
				+ "<creationTime>45780364596876</creationTime>\n"
				+ "<lastModificationTime>248657685765234</lastModificationTime>\n"
				+ "</head>\n"
				+ "<WordsBase>\n"
				+ "</WordsBase>\n"
				+ "<XMLSequence>\n"
				+ "<object type=\"Text\" id=\"0\">\n"
				+ "<text>Test Data Xoxoxo</text>\n"
				+ "</object>\n"
				+ "</XMLSequence>\n" +
				"<DependencyPart>\n" +
				"</DependencyPart>\n"
				+ "<Log>\n"
				+ "</Log>\n"
				+ "</Document>\n");
		writeThread.close();

		writeThread = fileModule.getWriteThread(Environment.getExternalStorageDirectory()+"/sondar/example/" + "Example3.xml");
		writeThread.write("<Document>\n" +
				"<head>\n" +
				"<documentUUID>00000000-0000-0000-0000-000000000000</documentUUID>\n" +
				"<pluginUUID>7524ee84-4dcb-4dd5-a0ca-1fa7687e5e6b</pluginUUID>\n" +
				"<creationTime>1466359747967</creationTime>\n" +
				"<lastModificationTime>1466359747967</lastModificationTime>\n" +
				"</head>\n" +
				"<WordsBase>\n" +
					"<dataList name=\"test\">\n" +
						"<item>01.03.02</item>\n" +
						"<item>01.04.02</item>\n" +
						"<item>09.03.01</item>\n" +
						"<item>09.03.02</item>\n" +
						"<item>01.04.01</item>\n" +
						"<item>01.04.02</item>\n" +
					"</dataList>" +
					"<dataList name=\"test2\">\n" +
						"<item>Бакалавр</item>\n" +
						"<item>Магистр</item>\n" +
						"<item>Специалист</item>\n" +
					"</dataList>" +
				"</WordsBase>\n" +
				"<XMLSequence>\n" +
				"<object type=\"Text\" id=\"0\">\n" +
				"<text>Выписка из протокола № </text>\n" +
				"</object>\n" +
				"<object type=\"EditText\" id=\"1\" name=\"protocolNumber\">\n" +
				"<textEdit></textEdit>\n" +
				"<textLength>3</textLength>\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"2\">\n" +
				"<text> от </text>\n" +
				"</object>\n" + "" +
				"<object type=\"Date\" id=\"3\" name=\"protocolDate\">\n" +
				"<date>1466359747997</date>\n" +
				"</object>\n" +
				"<object type=\"EndLn\" id=\"4\">\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"5\">\n" +
				"<text>Государственной экзаменационной комиссии </text>\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"6\">\n" +
				"<text>по специальности(направлению) :</text>\n" +
				"</object>\n" +
				"<object type=\"Spinner\" id=\"7\" name=\"specialtiesCode\">\n" +
				"<dataList baseName=\"test\">" +
				"</dataList>\n" +
				"<ItemSelected>0</ItemSelected>\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"8\">\n" +
				"<text>\"Прикладная математика и информатика\"</text>\n" +
				"</object>\n" +
				"<object type=\"EndLn\" id=\"9\">\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"10\">\n" +
				"<text>На основании результатов учебной </text>\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"11\">\n" +
				"<text>успеваемости, выполнения и защиты </text>\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"12\">\n" +
				"<text>выпускной квалификационной работы </text>\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"13\">\n" +
				"<text>Государтсвенная экзаменационная </text>\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"14\">\n" +
				"<text>комиссия ПОСТАНОВЛЯЕТ :</text>\n" +
				"</object>\n" +
				"<object type=\"EndLn\" id=\"15\">\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"16\">\n" +
				"<text>1. Присвоить студенту </text>\n" +
				"</object>\n" +
				"<object type=\"EditText\" id=\"17\" name=\"StudentName\">\n" +
				"<textEdit></textEdit>\n" +
				"<textLength>20</textLength>\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"18\">\n" +
				"<text>степень </text>\n" +
				"</object>\n" +
				"<object type=\"Spinner\" id=\"19\" name=\"educationRank\">\n" +
				"<dataList baseName=\"test2\">" +
				"</dataList>\n" +
				"<ItemSelected>0</ItemSelected>\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"20\">\n" +
				"<text>\"Прикладная математика и информатика\"</text>\n" +
				"</object>\n" +
				"<object type=\"EndLn\" id=\"21\">\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"22\">\n" +
				"<text>2. Выдать студенту </text>\n" +
				"</object>\n" +
				"<object type=\"EditText\" id=\"23\" name=\"StudentName2\">\n" +
				"<textEdit></textEdit>\n" +
				"<textLength>20</textLength>\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"24\">\n" +
				"<text>диплом </text>\n" +
				"</object>\n" +
				"<object type=\"CheckBox\" id=\"25\" name=\"AdditionalBun\">\n" +
				"<text>с отличием</text>\n" +
				"<default>false</default>\n" +
				"</object>\n" +
				"<object type=\"EndLn\" id=\"26\">\n" +
				"</object>\n" +
				"<object type=\"Text\" id=\"27\">\n" +
				"<text>3.</text>\n" +
				"</object>\n" +
				"<object type=\"CheckBox\" id=\"28\" name=\"MastersRecommend\">\n" +
				"<text>Рекомендовать в магистратуру</text>\n" +
				"<default>false</default>\n" +
				"</object>\n" +
				"</XMLSequence>\n" +
				"<DependencyPart>\n" +
				"<link objectName=\"protocolNumber\" cellId=\"1\"></link>\n" +
				"<link objectName=\"protocolDate\" cellId=\"2\"></link>\n" +
				"<link objectName=\"specialtiesCode\" cellId=\"3\"></link>\n" +
				"<link objectName=\"StudentName\" cellId=\"4\"></link>\n" +
				"<link objectName=\"educationRank\" cellId=\"5\"></link>\n" +
				"<link objectName=\"StudentName2\" cellId=\"6\"></link>\n" +
				"<link objectName=\"AdditionalBun\" cellId=\"7\"></link>\n" +
				"<link objectName=\"MastersRecommend\" cellId=\"8\"></link>\n" +
				"</DependencyPart>\n" +
				"<Log>\n" +
				"</Log>\n" +
				"</Document>\n");
		writeThread.close();
	}

}
