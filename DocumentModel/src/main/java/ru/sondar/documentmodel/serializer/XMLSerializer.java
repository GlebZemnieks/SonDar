package ru.sondar.documentmodel.serializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.logger.Logger;
import ru.sondar.core.parser.exception.NoAttributeException;
import ru.sondar.core.parser.exception.NoFieldException;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.SDSequenceObject;
import static ru.sondar.documentmodel.SDSequenceObject.Sequence_MainTag;
import ru.sondar.documentmodel.dependencymodel.DependencyItem;
import static ru.sondar.documentmodel.dependencymodel.DependencyItem.DependencyItem_xmlTag;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import static ru.sondar.documentmodel.dependencymodel.DependencyPart.Dependency_MainTag;
import ru.sondar.documentmodel.dependencymodel.exception.MissingDependencyAttributeException;
import ru.sondar.documentmodel.objectmodel.SDCheckBox;
import static ru.sondar.documentmodel.objectmodel.SDCheckBox.CheckBox_defaultCheck;
import static ru.sondar.documentmodel.objectmodel.SDCheckBox.CheckBox_textFieldTag;
import ru.sondar.documentmodel.objectmodel.*;
import static ru.sondar.documentmodel.objectmodel.SDDate.DATE_DATE_FIELD_TAG;
import static ru.sondar.documentmodel.objectmodel.SDDate.DATE_TEXT_FIELD_TAG;
import static ru.sondar.documentmodel.objectmodel.SDEditText.EditText_contentTypeTag;
import static ru.sondar.documentmodel.objectmodel.SDEditText.EditText_textFieldTag;
import static ru.sondar.documentmodel.objectmodel.SDEditText.EditText_textLengthTag;
import static ru.sondar.documentmodel.objectmodel.SDHeadPart.Tag_MainObject;
import static ru.sondar.documentmodel.objectmodel.SDLogPart.Log_MainTag;
import static ru.sondar.documentmodel.objectmodel.SDMainObject.Object_MainTag;
import static ru.sondar.documentmodel.objectmodel.SDSpinner.Spinner_DataList;
import static ru.sondar.documentmodel.objectmodel.SDSpinner.Spinner_defaultItemSelected;
import static ru.sondar.documentmodel.objectmodel.SDText.Text_textFieldTag;
import static ru.sondar.documentmodel.objectmodel.SDWordsBasePart.Tag_DataList;
import static ru.sondar.documentmodel.objectmodel.WordBase.Tag_Filter;
import static ru.sondar.documentmodel.objectmodel.WordBase.Tag_Item;

/**
 *
 * @author GlebZemnieks
 */
public class XMLSerializer implements DocumentSerializer {

    @Override
    public void parseObjectAttribute(SDMainObject object, Object... params) throws ObjectStructureException {
        Element element = (Element) params[0];
        if (!"".equals(element.getAttribute("id"))) {
            object.setID(Integer.valueOf(element.getAttribute("id")));
        } else {
            throw new NoAttributeException("Attribute \"id\"");
        }
        if (!"".equals(element.getAttribute("name"))) {
            object.setObjectName(element.getAttribute("name"));
        }
    }

    @Override
    public void parseObjectFromXML(SDMainObject object, Object... params) throws ObjectStructureException {
        this.parseObjectAttribute(object, params);
        this.parseCurrentObjectField(object, params);
    }

    @Override
    public void parseCurrentObjectField(SDMainObject object, Object... params) throws ObjectStructureException {
        NodeList list;
        Element element = (Element) params[0];
        if (object instanceof SDText) {
            list = element.getElementsByTagName(Text_textFieldTag);
            if (list.item(0) != null) {
                ((SDText) object).setText(list.item(0).getTextContent());
            } else {
                throw new NoFieldException("Missing \"text\" field");
            }
        }
        if (object instanceof SDCheckBox) {
            list = element.getElementsByTagName(CheckBox_textFieldTag);
            if (list.item(0) != null) {
                ((SDCheckBox) object).setText(list.item(0).getTextContent());
            } else {
                throw new NoFieldException("Missing \"text\" field");
            }
            list = element.getElementsByTagName(CheckBox_defaultCheck);
            if (list.item(0) != null) {
                ((SDCheckBox) object).setChecked(Boolean.valueOf(list.item(0).getTextContent()));
            } else {
                throw new NoFieldException("Missing \"default\" field");
            }
        }
        if (object instanceof SDDate) {
            list = element.getElementsByTagName(DATE_DATE_FIELD_TAG);
            if (list.item(0) != null) {
                ((SDDate) object).setCalendar(new Date(Long.parseLong(list.item(0).getTextContent())));
            } else {
                throw new NoFieldException("Missing \"date\" field");
            }
            list = element.getElementsByTagName(DATE_TEXT_FIELD_TAG);
            if (list.item(0) != null) {
                ((SDDate) object).setText(list.item(0).getTextContent());
            } else {
                //Do nothing - default value ""(Empty string)
                //throw new NoFieldException("Missing \"text\" field");
            }

        }
        if (object instanceof SDSpinner) {
            list = element.getElementsByTagName(Spinner_DataList);
            if (list.item(0) == null) {
                throw new NoFieldException("Missing \"dataList\" field");
            }
            if (((Element) list.item(0)).getAttribute("baseName").equals("")) {
                throw new NoFieldException("Missing \"baseName\" attribute");
            }
            ((SDSpinner) object).setWordsBaseName(((Element) list.item(0)).getAttribute("baseName"));
            ((SDSpinner) object).setList(((SDSpinner) object).getSequence().document.getWordsBasePart().getList(((Element) list.item(0)).getAttribute("baseName")));
            if (!((Element) list.item(0)).getAttribute("activeFilter").equals("")) {
                ((SDSpinner) object).setActiveFilter(((Element) list.item(0)).getAttribute("activeFilter"));
            } else {
                ((SDSpinner) object).setActiveFilter(null);
            }
            list = element.getElementsByTagName(Spinner_defaultItemSelected);
            if (list.item(0) != null) {
                ((SDSpinner) object).setDefaultItemSelected(Integer.valueOf(list.item(0).getTextContent()));
            } else {
                throw new NoFieldException("Missing \"ItemSelected\" field");
            }
        }
        if (object instanceof SDEditText) {
            list = element.getElementsByTagName(EditText_textFieldTag);
            if (list.item(0) != null) {
                ((SDEditText) object).setText(list.item(0).getTextContent());
                ((SDEditText) object).setText(((SDEditText) object).getText().replaceAll("Zzz", "\n"));
            } else {
                throw new NoFieldException("Missing \"text\" field");
            }
            list = element.getElementsByTagName(EditText_textLengthTag);
            if (list.item(0) != null) {
                ((SDEditText) object).setTextLength(Integer.valueOf(list.item(0).getTextContent()));
            } else {
                // Default value - Text.length()
                ((SDEditText) object).setTextLength(((SDEditText) object).getText().length());
            }
            list = element.getElementsByTagName(EditText_contentTypeTag);
            if (list.item(0) != null) {
                ((SDEditText) object).setContentType(list.item(0).getTextContent());
            } else {
                //Default value "text"
            }
        }
        if (object instanceof SDEndln) {
            //Empty
        }
        if (object instanceof SDSequenceObject) {
            this.parseSequence((SDSequenceObject) object, params);
        }
    }

    @Override
    public void printObjectAttrivute(SDMainObject object, FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + Object_MainTag + " type=\"" + object.getObjectType().toString() + "\" id=\"" + object.getID() + "\"" + object.getObjectNameTag() + ">\n");
    }

    @Override
    public void printObjectToXML(SDMainObject object, FileModuleWriteThreadInterface fileModule) {
        this.printObjectAttrivute(object, fileModule);
        this.printCurrentObjectField(object, fileModule);
        fileModule.write("</" + Object_MainTag + ">\n");
    }

    @Override
    public void printCurrentObjectField(SDMainObject object, FileModuleWriteThreadInterface fileModule) {
        if (object instanceof SDText) {
            fileModule.write("<" + Text_textFieldTag + ">" + ((SDText) object).getText() + "</" + Text_textFieldTag + ">\n");
        }
        if (object instanceof SDCheckBox) {
            fileModule.write("<" + CheckBox_textFieldTag + ">" + ((SDCheckBox) object).getText() + "</" + CheckBox_textFieldTag + ">\n"
                    + "<" + CheckBox_defaultCheck + ">" + ((SDCheckBox) object).getChecked() + "</" + CheckBox_defaultCheck + ">\n");
        }
        if (object instanceof SDDate) {
            fileModule.write("<" + DATE_TEXT_FIELD_TAG + ">" + ((SDDate) object).getText() + "</" + DATE_TEXT_FIELD_TAG + ">\n");
            fileModule.write("<" + DATE_DATE_FIELD_TAG + ">" + ((SDDate) object).getCalendar().getTime().getTime() + "</" + DATE_DATE_FIELD_TAG + ">\n");
        }
        if (object instanceof SDSpinner) {
            fileModule.write("<" + Spinner_DataList + ((SDSpinner) object).getWordsBaseNameAttribute() + ((SDSpinner) object).getActiveFilterAttribute() + ">" + "</" + Spinner_DataList + ">\n");
            fileModule.write("<" + Spinner_defaultItemSelected + ">" + ((SDSpinner) object).getSelectedItem() + "</" + Spinner_defaultItemSelected + ">\n");
        }
        if (object instanceof SDEditText) {
            ((SDEditText) object).setText(((SDEditText) object).getText().replaceAll("\n", "Zzz"));
            fileModule.write("<" + EditText_textFieldTag + ">" + ((SDEditText) object).getText() + "</" + EditText_textFieldTag + ">\n");
            fileModule.write("<" + EditText_textLengthTag + ">" + ((SDEditText) object).getTextLength() + "</" + EditText_textLengthTag + ">\n");
            fileModule.write("<" + EditText_contentTypeTag + ">" + ((SDEditText) object).getContentType() + "</" + EditText_contentTypeTag + ">\n");
        }
        if (object instanceof SDEndln) {
            //Empty
        }
        if (object instanceof SDSequenceObject) {
            for (int count = 0; count < ((SDSequenceObject) object).sequenceArray.size(); count++) {
                this.printObjectToXML(((SDSequenceObject) object).getXMLObject(((SDSequenceObject) object).getID() + 1 + count), fileModule);
            }
        }
    }

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
                this.parseObjectFromXML(tempObject, tempElement);
                Logger.Log("SDSequenceObject::parseSequence", "Parsed object : " + tempObject.toString());
            }
        }
        Logger.Log("SDSequenceObject::parseSequence", "Enumirate sequence");
        sequence.enumirateSequence(0);
        Logger.Log("SDSequenceObject::parseSequence", "Parsing finish");
        Logger.Log("SDSequenceObject::parseSequence", "Result : \n" + this.toString());
    }

    @Override
    public void printSequence(SDSequenceObject sequence, FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + Sequence_MainTag + ">\n");
        for (SDMainObject sDMainObject : sequence.sequenceArray) {
            this.printObjectToXML(sDMainObject, fileModule);
        }
        fileModule.write("</" + Sequence_MainTag + ">\n");
    }

    @Override
    public void parseHeadPart(SDHeadPart headPart, Object... params) throws ObjectStructureException {
        NodeList list = ((Element) params[0]).getElementsByTagName("documentUUID");
        if (list.item(0) != null) {
            headPart.setUUID(UUID.fromString(list.item(0).getTextContent()));
        } else {
            Logger.Log("parser", "Missing \"documentUUID\" field in head object. Set default value \"00000000-0000-...\"");
            headPart.setUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        }
        list = ((Element) params[0]).getElementsByTagName("pluginUUID");
        if (list.item(0) != null) {
            headPart.setPluginUUID(UUID.fromString(list.item(0).getTextContent()));
        } else {
            Logger.Log("parser", "Missing \"pluginUUID\" field in head object. Set default value \"00000000-0000-...\"");
            headPart.setPluginUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        }
        list = ((Element) params[0]).getElementsByTagName("documentName");
        if (list.item(0) != null) {
            headPart.setDocumentName(list.item(0).getTextContent());
        } else {
            Logger.Log("parser", "Missing \"documentName\" field in head object. Set default value \"" + headPart.getUUID().toString() + "\"");
            headPart.setDocumentName(headPart.getUUID().toString());
        }
        list = ((Element) params[0]).getElementsByTagName("creationTime");
        if (list.item(0) != null) {
            headPart.setCreationTime(new Date(Long.parseLong(list.item(0).getTextContent())));

        } else {
            Logger.Log("parser", "Missing \"creationTime\" field in head object. Set current date.");
            headPart.setCreationTime(new Date());
        }
        list = ((Element) params[0]).getElementsByTagName("lastModificationTime");
        if (list.item(0) != null) {
            headPart.setLastModificationTime(new Date(Long.parseLong(list.item(0).getTextContent())));
        } else {
            Logger.Log("parser", "Missing \"lastModificationTime\" field in head object. Set current date.");
            headPart.setLastModificationTime(new Date());
        }
    }

    @Override
    public void printHeadPart(SDHeadPart headPart, FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + Tag_MainObject + ">\n"
                + "<documentUUID>" + headPart.getUUID().toString() + "</documentUUID>\n"
                + "<pluginUUID>" + headPart.getPluginUUID().toString() + "</pluginUUID>\n"
                + "<documentName>" + headPart.getDocumentName() + "</documentName>\n"
                + "<creationTime>" + headPart.getCreationTime().getTime() + "</creationTime>\n"
                + "<lastModificationTime>" + headPart.getLastModificationTime().getTime() + "</lastModificationTime>\n"
                + "</" + Tag_MainObject + ">\n");
    }

    @Override
    public void parseLogPart(SDLogPart logPart, Object... params) throws ObjectStructureException {
        NodeList tempList = ((Element) params[0]).getElementsByTagName("log");
        logPart.setLogList(new ArrayList<String>());
        for (int i = 0; i < tempList.getLength(); i++) {
            Element tempLog = ((Element) tempList.item(i));
            logPart.addLogFile(tempLog.getTextContent());
        }
    }

    @Override
    public void printLogPart(SDLogPart logPart, FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + Log_MainTag + ">\n");
        if (logPart.getLogList() != null) {
            for (String log : logPart.getLogList()) {
                fileModule.write("<log>" + log + "</log>\n");
            }
        }
        fileModule.write("</" + Log_MainTag + ">\n");
    }

    @Override
    public void parseWordsBasePart(SDWordsBasePart wordsBase, Object... params) {
        NodeList list = ((Element) params[0]).getElementsByTagName(Tag_DataList);
        for (int i = 0; i < list.getLength(); i++) {
            WordBase temp = new WordBase();
            this.parseWordsBase(temp, (Element) list.item(i));
            String name = ((Element) list.item(i)).getAttribute("name");
            wordsBase.addNewBase(name, temp);
        }
    }

    @Override
    public void printWordsBasePart(SDWordsBasePart wordsBase, FileModuleWriteThreadInterface fileModule) {
        Logger.Log("SDWordsBasePart::printObjectToXML", "Write wordsBase : " + this.toString());
        fileModule.write("<" + wordsBase.Tag_MainObject + ">\n");
        for (WordBase base : wordsBase.getBases()) {
            this.printWordsBase(base, fileModule);
        }
        fileModule.write("</" + wordsBase.Tag_MainObject + ">\n");
    }

    @Override
    public void parseWordsBase(WordBase wordsBase, Object... params) {
        wordsBase.setBaseName(((Element) params[0]).getAttribute("name"));
        NodeList list = ((Element) params[0]).getChildNodes();
        String filter = null;
        if (params.length > 1) {
            filter = (String) params[1];
        }
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals(Tag_Item)) {
                wordsBase.add(filter, list.item(i).getTextContent());
            }
            if (filter == null && list.item(i).getNodeName().equals(Tag_Filter)) {
                this.parseWordsBase(wordsBase, (Element) list.item(i), ((Element) list.item(i)).getAttribute("name"));
            }
        }
    }

    @Override
    public void printWordsBase(WordBase wordsBase, FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + Tag_DataList + " name=\"" + wordsBase.getBaseName() + "\">\n");
        for (String filter : wordsBase.getFilterNames()) {
            if (filter != null) {
                fileModule.write("<" + Tag_Filter + " name=\"" + filter + "\">\n");
            }
            for (String item : wordsBase.getList(filter)) {
                fileModule.write("<" + Tag_Item + ">" + item + "</" + Tag_Item + ">\n");
            }
            if (filter != null) {
                fileModule.write("</" + Tag_Filter + ">\n");
            }
        }
        fileModule.write("</" + Tag_DataList + ">\n");
    }

    @Override
    public void parseDependencyPart(DependencyPart dependency, Object... params) {
        dependency.setDependencyList(new ArrayList<DependencyItem>());
        NodeList list = ((Element) params[0]).getElementsByTagName(DependencyItem_xmlTag);
        for (int i = 0; i < list.getLength(); i++) {
            DependencyItem item = new DependencyItem();
            this.parseDependencyItem(item, (Element) list.item(i));
            dependency.getDependencyList().add(item);
        }
    }

    @Override
    public void printDependencyPart(DependencyPart dependency, FileModuleWriteThreadInterface fileModule) {
        Logger.Log("DependencyPart::printObjectToXML", "Write dependency : " + this.toString());
        fileModule.write("<" + Dependency_MainTag + ">\n");
        for (int i = 0; i < dependency.getDependencyList().size(); i++) {
            this.printDependencyItem(dependency.getDependencyList().get(i), fileModule);
        }
        fileModule.write("</" + Dependency_MainTag + ">\n");
    }

    @Override
    public void parseDependencyItem(DependencyItem dependency, Object... params) {
        if (!"".equals(((Element) params[0]).getAttribute("objectName"))) {
            dependency.objectName = (((Element) params[0]).getAttribute("objectName"));
        } else {
            throw new MissingDependencyAttributeException("Attribute \"objectName\"");
        }
        if (!"".equals(((Element) params[0]).getAttribute("cellId"))) {
            dependency.cellId = Integer.valueOf(((Element) params[0]).getAttribute("cellId"));
        } else {
            throw new MissingDependencyAttributeException("Attribute \"cellId\"");
        }
    }

    @Override
    public void printDependencyItem(DependencyItem dependency, FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + DependencyItem_xmlTag + " objectName=\"" + dependency.objectName + "\" cellId=\"" + dependency.cellId + "\"></" + DependencyItem_xmlTag + ">\n");
    }

}
