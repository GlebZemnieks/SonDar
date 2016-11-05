package ru.sondar.documentmodel.objectmodel;

import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.objectmodel.exception.*;

/**
 * Abstract class to association main SDobject's parameters
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public abstract class SDMainObject {

    /**
     * Tag to search activity this object in logging file
     */
    protected static String logTag = "Parser";

    /**
     * Main tag to writing all object of this type
     */
    public static String Object_MainTag = "object";

    /**
     * Object ID. The sequentially numbering. Set in the XML parsing.
     */
    protected int ID;

    /**
     * Domain sequence
     */
    private SDSequenceObject sequence;

    public void setSequence(SDSequenceObject sequence) {
        this.sequence = sequence;
    }

    public SDSequenceObject getSequence() {
        return this.sequence;
    }

    /**
     * Object type to access personal and overloaded methods. Set once when
     * calling child's construction.
     */
    protected SDMainObjectType objectType;

    /**
     * Object name. Use for relative calls from another object and to access
     * from plugin whose answering of this document
     */
    protected String objectName = null;

    /**
     * Method for access to objectType parameter
     *
     * @return SDMainObjectType
     */
    public SDMainObjectType getObjectType() {
        return this.objectType;
    }

    /**
     * Method to check of tag of object and send current SDMainObjectType object
     *
     * @param XMLTag
     * @return
     */
    public static SDMainObjectType chooseXMLType(String XMLTag) {
        try {
            return SDMainObjectType.valueOf(XMLTag);
        } catch (IllegalArgumentException exception) {
            return SDMainObjectType.ErrorObject;
        }
    }

    /**
     * Getter for ID field
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter for ID field
     *
     * @param iD
     */
    public void setID(int iD) {
        ID = iD;
    }

    /**
     * Method to access object name. If name is null return null;
     *
     * @return
     */
    public String getObjectName() {
        return this.objectName;
    }

    /**
     * Method to set object name. Object name sets only once. Another calls
     * catch exception ObjectAlreadyHaveNameException;
     *
     * @param name
     * @return this object for usability
     */
    public SDMainObject setObjectName(String name) {
        if (this.objectName != null) {
            throw new ObjectAlreadyHaveNameException("Object:" + this.toString() + ". Old name:" + this.objectName + ". Try to set new name \"" + name + "\" denied");
        }
        this.objectName = name;
        return this;
    }

    /**
     * Method for printXMLString. Return name="%objectName%" if
     * %objectName%!=null or empty string if %objectName% is null;
     *
     * @return
     */
    public String getObjectNameTag() {
        if (this.objectName != null && !this.objectName.equals("")) {
            return " name=\"" + this.objectName + "\"";
        }
        return "";
    }

    @Override
    public String toString() {
        return "ObjectType : " + this.objectType + " : id : " + this.ID
                + " : name : " + this.objectName;
    }

}
