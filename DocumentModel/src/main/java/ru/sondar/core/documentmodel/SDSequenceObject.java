package ru.sondar.core.documentmodel;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.objectmodel.*;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

/**
 *
 * @author GlebZemnieks
 */
public class SDSequenceObject extends SDMainObject {

    /**
     * Tag for print and parse Sequence object
     */
    public static String Sequence_MainTag = "XMLSequence";
    /**
     * Tag for print and parse Container object
     */
    public static String DivContainer_MainTag = "DivContainer";

    /**
     * Count of objectS in sequence considering nested sequences
     */
    public int sequenceArrayLength = 0;
    /**
     * Sequence of objects
     */
    public ArrayList<SDMainObject> sequenceArray = new ArrayList<>();

    /**
     * Constructor
     */
    public SDSequenceObject() {
        this.objectType = SDMainObjectType.DivContainer;
    }

    /**
     * Getter for sequenceArrayLength field
     *
     * @return
     */
    public int getSequenceSize() {
        return this.sequenceArrayLength;
    }

    /**
     * Find object with %name% and return it. If it not exist return null
     *
     * @param name
     * @return
     */
    public SDMainObject getXMLObjectByName(String name) {
        for (int i = 0; i < this.sequenceArray.size(); i++) {
            SDMainObject temp = this.sequenceArray.get(i);
            if (name.equals(temp.getObjectName())) {
                return temp;
            }
            if (temp.getObjectType() == SDMainObjectType.DivContainer) {
                SDMainObject temp2 = ((SDSequenceObject) temp).getXMLObjectByName(name);
                if (temp2 != null) {
                    return temp2;
                }
            }
        }
        return null;
    }

    /**
     * Get object by id. Enumeration by id is global. So index of element in
     * sequence can be another, so need search in considering nested sequences
     *
     * @param id
     * @return
     */
    public SDMainObject getXMLObject(int id) {
        return getInterval(id);
    }

    /**
     * Find interval for object position.
     *
     * @param id
     * @return
     */
    private SDMainObject getInterval(int id) {
        int cursor = id;
        if (cursor > (this.sequenceArray.size() - 1)) {
            cursor = this.sequenceArray.size() - 1;
        }
        if (this.sequenceArray.get(cursor).getID() == id) {
            return this.sequenceArray.get(cursor);
        }
        if (id > this.sequenceArray.get(cursor).getID()) {
            return ((SDSequenceObject) this.sequenceArray.get(cursor)).getXMLObject(id);
        }
        int max = cursor;
        cursor = 2 * cursor - this.sequenceArray.get(cursor).getID();
        if (cursor < 0) {
            cursor = 0;
        }
        if (this.sequenceArray.get(cursor).getID() == id) {
            return this.sequenceArray.get(cursor);
        }
        int min = cursor;
        return getConstriction(id, max, min);
    }

    /**
     * Constriction interval for object position
     *
     * @param id
     * @param max
     * @param min
     * @return
     */
    private SDMainObject getConstriction(int id, int max, int min) {
        int tempMin = min;
        int tempMax = max;
        while ((tempMax - tempMin) > 1) {
            int tempAverage = (int) ((tempMax + tempMin) / 2);
            if (this.sequenceArray.get(tempAverage).getID() == id) {
                return this.sequenceArray.get(tempAverage);
            }
            if (this.sequenceArray.get(tempAverage).getID() > id) {
                tempMax = tempAverage;
            } else {
                tempMin = tempAverage;
            }
        }
        return ((SDSequenceObject) this.sequenceArray.get(tempMin)).getXMLObject(id);
    }

    /**
     * Enumerate new objects successively
     *
     * @param startId
     */
    public void enumirateSequence(int startId) {
        int tempId = startId;
        for (int count = 0; count < this.sequenceArray.size(); count++) {
            this.sequenceArray.get(count).setID(tempId++);
            if (SDMainObjectType.DivContainer.toString().equals(this.sequenceArray.get(count).getObjectType().toString())) {
                ((SDSequenceObject) this.sequenceArray.get(count)).enumirateSequence(tempId);
            }
        }
    }

    /**
     * Add new object to sequence
     *
     * @param newObject
     */
    public void AddXMLObject(SDMainObject newObject) {
        if (newObject.getObjectType().toString().equals(SDMainObjectType.DivContainer.toString())) {
            this.sequenceArrayLength += ((SDSequenceObject) newObject).sequenceArrayLength;
        }
        this.sequenceArrayLength++;
        this.sequenceArray.add(newObject);
    }

    /**
     * Method for parse sequence.
     *
     * @param element
     * @throws ru.sondar.core.objectmodel.exception.ObjectStructureException
     */
    public void parseSequence(Element element) throws ObjectStructureException {
        this.sequenceArray = new ArrayList<>();
        this.sequenceArrayLength = 0;
        NodeList tempList = element.getChildNodes();
        for (int count = 0; count < tempList.getLength(); count++) {
            if (tempList.item(count).getNodeName().equals("object")) {
                Element tempElement = (Element) tempList.item(count);
                SDMainObjectType newObjectType = SDMainObject.chooseXMLType(tempElement.getAttribute("type"));
                SDMainObject tempObject = getObjectByType(newObjectType);
                tempObject.parseObjectFromXML(tempElement);
                AddXMLObject(tempObject);
            }
        }
        this.enumirateSequence(0);
    }

    /**
     * Method for print sequence
     *
     * @param fileModule
     */
    public void printSequence(FileModuleWriteThread fileModule) {
        fileModule.write("<" + Sequence_MainTag + ">\n");
        for (int count = 0; count < this.sequenceArray.size(); count++) {
            this.getXMLObject(count).printObjectToXML(fileModule);
        }
        fileModule.write("</" + Sequence_MainTag + ">\n");
    }

    @Override
    protected void parseCurrentObjectField(Element element) throws ObjectStructureException {
        this.parseSequence(element);
    }

    @Override
    public void printCurrentObjectField(FileModuleWriteThread fileModule) {
        for (int count = 0; count < this.sequenceArray.size(); count++) {
            this.getXMLObject(count + this.ID + 1).printObjectToXML(fileModule);
        }
    }

    public SDMainObject getObjectByType(SDMainObjectType type) {
        if (type == SDMainObjectType.Text) {
            return new SDText();
        }
        if (type == SDMainObjectType.Spinner) {
            return new SDSpinner();
        }
        if (type == SDMainObjectType.CheckBox) {
            return new SDCheckBox();
        }
        if (type == SDMainObjectType.EndLn) {
            return new SDEndln();
        }
        if (type == SDMainObjectType.Date) {
            return new SDDate();
        }
        if (type == SDMainObjectType.EditText) {
            return new SDEditText();
        }
        if (type == SDMainObjectType.DivContainer) {
            return new SDSequenceObject();
        }
        return null;
    }

}
