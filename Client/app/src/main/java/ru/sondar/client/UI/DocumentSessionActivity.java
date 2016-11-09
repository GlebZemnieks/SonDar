package ru.sondar.client.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import ru.sondar.client.documentmodel.android.AXMLDocument;
import ru.sondar.client.documentmodel.android.AXMLSequenceObject;
import ru.sondar.client.documentmodel.android.XMLSequenceIndexOverflowException;
import ru.sondar.client.filemodule.android.FileModule;
import ru.sondar.client.objectmodel.android.AXMLMainObject;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filesystem.SonDarFileSystem;
import ru.sondar.core.logger.Logger;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.loader.SDDOMParser;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.documentmodel.objectmodel.SDMainObjectType;
import ru.sondar.documentmodel.serializer.DocumentSerializer;
import ru.sondar.documentmodel.serializer.XMLSerializer;

import static ru.sondar.documentmodel.SDSequenceObject.Sequence_MainTag;

public class DocumentSessionActivity extends ActionBarActivity {

    // TODO Redesign
    public static final int buttonHeight = 96;
    private static final int buttonWidth = 72;
    private static final int footer = 100;
    public String logTag = "SDActivity:DocumentSessionActivity";
    AXMLDocument document;
    SonDarFileSystem fileSystem;
    FileModuleInterface fileModule;
    String fileName;
    String folderName;

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
                } catch (XMLSequenceIndexOverflowException error) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            fileName = (String) getIntent().getSerializableExtra("fileName");
            folderName = (String) getIntent().getSerializableExtra("folderName");

            fileModule = new FileModule(this);
            Logger.Log(logTag, "MainActivity.onCreate start");

            fileSystem = new SonDarFileSystem(Environment.getExternalStorageDirectory() + "/sondar");
            fileSystem.addFolder(Folder.temp.toString());
            fileSystem.addFolder(Folder.work.toString());
            fileSystem.addFolder(Folder.done.toString());
            fileSystem.init(fileModule);

            document = new AXMLDocument(this);
            Logger.Log(logTag, "Load Document start : " + fileName);
            try {
                document.loadDocument(
                        new androidSerializer(),
                        new SDDOMParser(fileSystem.getFolderByName(Folder.temp.toString()).getGlobalFileName(fileName)),
                        new AXMLSequenceObject()
                );
            } catch (SAXException | IOException | ParserConfigurationException | ObjectStructureException e) {
                Logger.Log("DocumentSessionActivity", "Trouble with document loading", e);
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
        } catch (Exception error) {
            Logger.Log("DocumentSessionActivity", "onCreate -> Exception", error);
            throw error;
        }
    }

    public void onPause() {
        try {
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
        } catch (Exception error) {
            Logger.Log("DocumentSessionActivity", "onPause -> Exception", error);
            throw error;
        }
    }

    class androidSerializer extends XMLSerializer
            implements DocumentSerializer {
        @Override
        public void parseSequence(SDSequenceObject sequence, Object... params) throws ObjectStructureException {
            Logger.Log("SDSequenceObject::parseSequence", "Parsing start");
            sequence.sequenceArray = new ArrayList<>();
            sequence.sequenceArrayLength = 0;
            NodeList tempList = ((Element) params[0]).getChildNodes();
            for (int count = 0; count < tempList.getLength(); count++) {
                if (tempList.item(count).getNodeName().equals("object")) {
                    Element tempElement = (Element) tempList.item(count);
                    SDMainObjectType newObjectType = SDMainObject.chooseXMLType(tempElement.getAttribute("type"));
                    SDMainObject tempObject = sequence.getObjectByType(newObjectType);
                    sequence.addXMLObject(tempObject);
                    this.parseObject(((AXMLMainObject) tempObject).getParentObject(), tempElement);
                    Logger.Log("SDSequenceObject::parseSequence", "Parsed object : " + tempObject.toString());
                }
            }
            Logger.Log("SDSequenceObject::parseSequence", "Enumirate sequence");
            sequence.enumirateSequence(0);
            Logger.Log("SDSequenceObject::parseSequence", "Parsing finish");
            Logger.Log("SDSequenceObject::parseSequence", "Result : \n" + sequence.toString());
        }

        @Override
        public void printSequence(SDSequenceObject sequence, FileModuleWriteThreadInterface fileModule) {
            fileModule.write("<" + Sequence_MainTag + ">\n");
            for (SDMainObject sDMainObject : sequence.sequenceArray) {
                this.printObject(((AXMLMainObject) sDMainObject).getParentObject(), fileModule);
            }
            fileModule.write("</" + Sequence_MainTag + ">\n");
        }

    }
}
