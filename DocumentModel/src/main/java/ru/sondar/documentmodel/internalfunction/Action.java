package ru.sondar.documentmodel.internalfunction;

import org.w3c.dom.Element;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.internalfunction.interfaces.NavigatorInterface;

/**
 *
 * @author GlebZemnieks
 */
public abstract class Action {

    /**
     * Active action make something(True). Passive action should be calls only
     * from another action(False)
     */
    public boolean isActive;

    /**
     * Action type enumeration object
     */
    public ActionType actionType = null;

    /**
     * return String in format 'name="%name%"' or '' if name equals null
     *
     * @return
     */
    public String getActionTypeTag() {
        if (this.actionType != null) {
            return " actionType=\"" + this.actionType + "\"";
        }
        return "";
    }
    /**
     * Call this object to get target object to make action
     */
    protected NavigatorInterface navigator = null;

    /**
     * Setter for navigation object
     *
     * @param navigatorInterface
     */
    public void setNavigator(NavigatorInterface navigatorInterface) {
        this.navigator = navigatorInterface;
    }

    /**
     * Tag to parse and print action object in XML.
     */
    public static final String actionTag = "Action";

    /**
     * Object Id. Set it to navigator and get really object to make action
     */
    protected Object targetId = null;

    /**
     * Setter for target Id field
     *
     * @param targetObject
     */
    public void setTargetId(Object targetObject) {
        this.targetId = targetObject;
    }

    /**
     * return String in format 'targetId="%targetId%"' or '' if targetId null
     *
     * @return
     */
    public String getActionTargetIdTag() {
        if (this.targetId != null) {
            return " targetId=\"" + this.targetId + "\"";
        }
        return "";
    }
    /**
     * Action's name
     */
    protected String actionName = null;

    /**
     * Setter for object name. Return current object for usability
     *
     * @param name
     * @return
     */
    public Action setActionName(String name) {
        this.actionName = name;
        return this;
    }

    /**
     * Getter for action name field
     *
     * @return
     */
    public String getActionName() {
        return this.actionName;
    }

    /**
     * return String in format 'name="%name%"' or '' if name null
     *
     * @return
     */
    public String getActionNameTag() {
        if (this.actionName != null && this.actionName != "") {
            return " name=\"" + this.actionName + "\"";
        }
        return "";
    }
    /**
     * Value to set in targetObject
     */
    protected Object value = null;

    /**
     * Setter for value field
     *
     * @param object
     */
    public void setValue(Object object) {
        this.value = object;
    }

    /**
     * return String in format 'value="%value%"' or '' if value null
     *
     * @return
     */
    public String getActionValueTag() {
        if (this.value != null) {
            return " value=\"" + this.value + "\"";
        }
        return "";
    }

    /**
     * Method to overloading in child class
     *
     * @return
     */
    public abstract Object makeAction();

    /**
     * Parse attribute of action
     *
     * @param element
     */
    public void parseObjectAttribute(Element element) {
        if (!"".equals(element.getAttribute("name"))) {
            this.setActionName(element.getAttribute("name"));
        }
        this.targetId = element.getAttribute("targetId");
        this.value = element.getAttribute("value");
    }

    /**
     * Parse all function by InternalFunction DOM element
     *
     * @param element
     */
    public void parseXMLString(Element element) {
        this.parseObjectAttribute(element);
    }

    /**
     * Print action to file in XML format
     *
     * @param fileModule
     */
    public void printXMLString(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + actionTag
                + this.getActionTypeTag()
                + this.getActionNameTag()
                + this.getActionTargetIdTag()
                + this.getActionValueTag()
                + "></" + actionTag + ">\n");
    }

    public static ActionType chooseActionType(String XMLTag) {
        if (XMLTag.contains(ActionType.setAction.toString())) {
            return ActionType.setAction;
        }
        if (XMLTag.contains(ActionType.checkAction.toString())) {
            return ActionType.checkAction;
        }
        throw new UnsupportedClassVersionError("Action : " + XMLTag + " : not supported yet");
    }

    @Override
    public String toString() {
        return "Action : type " + this.actionType.toString()
                + " : value : " + this.value
                + " : target : " + this.targetId + "\n";
    }
}
