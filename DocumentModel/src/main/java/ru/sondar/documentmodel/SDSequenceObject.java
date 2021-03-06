package ru.sondar.documentmodel;

import java.util.ArrayList;
import ru.sondar.documentmodel.objectmodel.*;
import ru.sondar.documentmodel.exception.ObjectNotFountException;

/**
 * Objects sequence object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
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
     * Domain document object
     */
    public SDDocument document;

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
     * @return Just sequence by current object
     */
    public int getSequenceSize() {
        return this.sequenceArrayLength;
    }

    /**
     * Find object with %name% and return it. If it not exist return null.
     *
     * @param name name of object
     * @return object with name or null if object not exist
     */
    public SDMainObject getXMLObjectByName(String name) {
        for (int i = 0; i < this.sequenceArray.size(); i++) {
            SDMainObject temp = this.sequenceArray.get(i);
            if (name.equals(temp.getObjectName())) {
                return temp;
            }
            if (temp.getObjectType() == SDMainObjectType.DivContainer) {
                try {
                    return ((SDSequenceObject) temp).getXMLObjectByName(name);
                } catch (ObjectNotFountException error) {
                    //Do nothing and go to next iteration
                }
            }
        }
        throw new ObjectNotFountException("Object with name \"" + name + "\" not found in sequence");
    }

    public boolean isObjectWithNameExist(String name) {
        try {
            getXMLObjectByName(name);
        } catch (ObjectNotFountException error) {
            return false;
        }
        return true;
    }

    public boolean isObjectWithIdExist(int id) {
        return !((id < 0) || (id >= this.sequenceArrayLength));
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
        if (cursor < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (cursor > (this.sequenceArray.size() - 1)) {
            cursor = this.sequenceArray.size() - 1;
        }
        if (this.sequenceArray.get(cursor).getID() == id) {
            return this.sequenceArray.get(cursor);
        }
        if (id > this.sequenceArray.get(cursor).getID()) {
            if (this.sequenceArray.get(cursor).getObjectType() == SDMainObjectType.DivContainer) {
                return ((SDSequenceObject) this.sequenceArray.get(cursor)).getXMLObject(id);
            } else {
                throw new IndexOutOfBoundsException("Try to get object '" + cursor + "' from sequence with max '" + this.sequenceArray.size() + "(" + this.sequenceArray.get(this.sequenceArray.size() - 1).getObjectType() + ")");
            }
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
            if (SDMainObjectType.DivContainer == this.sequenceArray.get(count).getObjectType()) {
                ((SDSequenceObject) this.sequenceArray.get(count)).enumirateSequence(tempId);
                tempId += ((SDSequenceObject) this.sequenceArray.get(count)).sequenceArrayLength;
            }
        }
    }

    /**
     * Add new object to sequence
     *
     * @param newObject
     */
    public void addXMLObject(SDMainObject newObject) {
        if (newObject.getObjectType() == SDMainObjectType.DivContainer) {
            ((SDSequenceObject) newObject).document = this.document;
            this.sequenceArrayLength += ((SDSequenceObject) newObject).sequenceArrayLength;
        }
        this.sequenceArrayLength++;
        newObject.setSequence(this);
        this.sequenceArray.add(newObject);
    }

    public SDMainObject getObjectByType(SDMainObjectType type) {
        return type.getObjectByType();
    }

    @Override
    public String toString() {
        String temp = "Obj Count : " + this.sequenceArrayLength + " : \n";
        for (SDMainObject sDMainObject : sequenceArray) {
            temp += sDMainObject.toString() + "\n";
        }
        return temp;
    }
}
