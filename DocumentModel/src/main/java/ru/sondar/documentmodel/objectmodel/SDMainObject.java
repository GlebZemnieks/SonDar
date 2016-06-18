package ru.sondar.documentmodel.objectmodel;

import org.w3c.dom.Element;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.internalfunction.InternalFunction;
import ru.sondar.documentmodel.internalfunction.TriggerType;
import ru.sondar.documentmodel.objectmodel.exception.NoAttributeException;
import ru.sondar.documentmodel.objectmodel.exception.ObjectAlreadyHaveNameException;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

/**
 * Abstract class to association main SDobject's parameters
 *
 * @author GlebZemnieks
 */
public abstract class SDMainObject {

    /**
     * Tag to search activity this object in logging file
     */
    protected static String logTag = "Parser";

    /**
     * Main tag to writing all object of this type
     */
    protected static String Object_MainTag = "object";

    /**
     * Object type to access personal and overloaded methods. Set once when
     * calling child's construction.
     */
    protected SDMainObjectType objectType;

    /**
     * Method for access to objectType parameter
     *
     * @return SDMainObjectType
     */
    public SDMainObjectType getObjectType() {
        return this.objectType;
    }

    /**
     * InternalFunction object. This function make possible to change current or
     * another object by trigger of this function.
     */
    protected InternalFunction function = null;

    /**
     * Method to set InternalFunction for this object. Return current object for
     * usability
     *
     * @param function
     * @return
     */
    public SDMainObject setInternalFunction(InternalFunction function) {
        this.function = function;
        return this;
    }

    /**
     * Call of internal object
     *
     * @param trigger
     */
    public void startInternalFunction(TriggerType trigger) {
        //Do nothing if internal function not set in this object
        if (this.function != null) {
            this.function.makeFunction(trigger);
        }
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
     * Object name. Use for relative calls from another object and to access
     * from plugin whose answering of this document
     */
    protected String objectName = null;

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
     * Method to access object name. If name is null return null;
     *
     * @return
     */
    public String getObjectName() {
        return this.objectName;
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

    /**
     * Object ID. The sequentially numbering. Set in the XML parsing.
     */
    protected int ID;

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
     * Parse object attributes
     *
     * @param element
     * @throws NoAttributeException throw when some of attribute missed
     */
    protected void parseAttribute(Element element) throws ObjectStructureException {
        if (!"".equals(element.getAttribute("id"))) {
            this.setID(Integer.valueOf(element.getAttribute("id")));
        } else {
            throw new NoAttributeException("Attribute \"id\"");
        }
        if (!"".equals(element.getAttribute("name"))) {
            this.setObjectName(element.getAttribute("name"));
        }
    }

    /**
     * Public method for parser XML string
     *
     * @param element
     * @throws NoAttributeException throw when some of attribute missed
     */
    public void parseObjectFromXML(Element element) throws ObjectStructureException {
        this.parseAttribute(element);
        this.parseCurrentObjectField(element);
    }

    /**
     * An abstract method for parsing internal field in object
     *
     * @param element
     * @throws ObjectStructureException
     */
    protected abstract void parseCurrentObjectField(Element element) throws ObjectStructureException;

    protected void printAttrivute(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + Object_MainTag + " type=\"" + this.objectType.toString() + "\" id=\"" + this.getID() + "\"" + this.getObjectNameTag() + ">\n");
    }

    /**
     * An class print XML string in file
     *
     * @param fileModule
     */
    public void printObjectToXML(FileModuleWriteThreadInterface fileModule) {
        this.printAttrivute(fileModule);
        this.printCurrentObjectField(fileModule);
        fileModule.write("</" + Object_MainTag + ">\n");
    }

    /**
     * If function  was set, print it
     * @param fileModule 
     */
    public void printInternalFunction(FileModuleWriteThreadInterface fileModule) {
        if (this.function != null) {
            function.printXMLString(fileModule);
        }
    }

    /**
     * An abstract method for print XML string of current object
     *
     * @param fileModule
     */
    protected abstract void printCurrentObjectField(FileModuleWriteThreadInterface fileModule);

    /**
     * To string
     *
     * @return
     */
    @Override
    public String toString() {
        return "ObjectType:" + this.objectType + ":id:" + this.ID + ":name:" + this.objectName;
    }

}
