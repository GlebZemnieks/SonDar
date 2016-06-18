package ru.sondar.documentmodel.internalfunction;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import static ru.sondar.documentmodel.internalfunction.Action.actionTag;
import ru.sondar.documentmodel.internalfunction.action.CheckAction;
import ru.sondar.documentmodel.internalfunction.action.SetAction;
import ru.sondar.documentmodel.internalfunction.interfaces.NavigatorInterface;

/**
 *
 * @author GlebZemnieks
 */
public abstract class Function {

    /**
     * Type of trigger witch can active this function. Default
     * TriggerType.allAction;
     */
    TriggerType triggerType = TriggerType.allAction;

    /**
     * return String in format 'triggerType="%triggerType%"' or '' if
     * triggerType is null
     *
     * @return
     */
    public String getFunctionTriggerTypeTag() {
        if (this.triggerType != null) {
            return " triggerType=\"" + this.triggerType + "\"";
        }
        return "";
    }

    /**
     * Tag to parse and print function object in XML.
     */
    public static final String functionTag = "Function";

    /**
     * Type of functions
     */
    public FunctionType functionType;

    /**
     * return String in format 'functionType="%functionType%"' or '' if
     * functionType is null
     *
     * @return
     */
    public String getFunctionTypeTag() {
        if (this.functionType != null) {
            return " functionType=\"" + this.functionType + "\"";
        }
        return "";
    }

    /**
     * List of action
     */
    public ArrayList<Action> actionList = new ArrayList<>();

    /**
     * Add new action to array
     *
     * @param newObject
     */
    public void AddAction(Action newObject) {
        this.actionList.add(newObject);
    }

    public void makeFunction(TriggerType trigger) {
        //If trigger is active trigger calling all actions in function
        if (trigger == triggerType) {
            for (int i = 0; i < this.actionList.size(); i++) {
                this.actionList.get(i).makeAction();
            }
        }
    }

    public void setNavigator(NavigatorInterface navigatorInterface) {
        for (int i = 0; i < this.actionList.size(); i++) {
            this.actionList.get(i).setNavigator(navigatorInterface);
        }
    }

    /**
     * Parse attribute of function
     *
     * @param element
     */
    public void parseObjectAttribute(Element element) {
        this.functionType = chooseFunctionType(element.getAttribute("functionType"));
        this.triggerType = chooseTriggerType(element.getAttribute("triggerType"));
    }

    /**
     * Parse all function by InternalFunction DOM element
     *
     * @param element
     */
    public void parseXMLString(Element element) {
        this.parseObjectAttribute(element);
        NodeList tempList = element.getElementsByTagName(actionTag);
        for (int count = 0; count < tempList.getLength(); count++) {
            Element tempElement = (Element) tempList.item(count);
            ActionType newObjectType = Action.chooseActionType(tempElement.getAttribute("actionType"));
            Action tempObject = getActionByType(newObjectType);
            tempObject.parseXMLString(tempElement);
            AddAction(tempObject);
        }
    }

    /**
     * Print all function from array to file in XML format
     *
     * @param fileModule
     */
    public void printXMLString(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + functionTag
                + this.getFunctionTypeTag()
                + this.getFunctionTriggerTypeTag() + ">\n");
        for (int i = 0; i < this.actionList.size(); i++) {
            actionList.get(i).printXMLString(fileModule);
        }
        fileModule.write("</" + functionTag + ">\n");
    }

    /**
     * Return function type by String object
     *
     * @param XMLTag
     * @return
     */
    public static FunctionType chooseFunctionType(String XMLTag) {
        if (XMLTag.contains(FunctionType.AutoFill.toString())) {
            return FunctionType.AutoFill;
        }
        if (XMLTag.contains(FunctionType.SearchFilter.toString())) {
            return FunctionType.SearchFilter;
        }
        if (XMLTag.contains(FunctionType.CustomFunction.toString())) {
            return FunctionType.CustomFunction;
        }
        throw new UnsupportedClassVersionError("Function : " + XMLTag + " : not supported yet");
    }

    /**
     * Return trigger type by String object
     *
     * @param XMLTag
     * @return
     */
    public static TriggerType chooseTriggerType(String XMLTag) {
        if (XMLTag.contains(TriggerType.allAction.toString())) {
            return TriggerType.allAction;
        }
        if (XMLTag.contains(TriggerType.isChange.toString())) {
            return TriggerType.isChange;
        }
        if (XMLTag.contains(TriggerType.isUpdate.toString())) {
            return TriggerType.isUpdate;
        }
        if (XMLTag.contains(TriggerType.onView.toString())) {
            return TriggerType.onView;
        }
        throw new UnsupportedClassVersionError("Trigger : " + XMLTag + " : not supported yet");
    }

    /**
     * Get action object by Action type
     *
     * @param type
     * @return
     */
    public Action getActionByType(ActionType type) {
        if ((type.toString()).equals(ActionType.setAction.toString())) {
            return new SetAction(null);
        }
        if ((type.toString()).equals(ActionType.checkAction.toString())) {
            return new CheckAction(null);
        }
        throw new UnsupportedClassVersionError("Action : " + type.toString() + " : not supported yet");
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < this.actionList.size(); i++) {
            temp += this.actionList.get(i).toString();
        }
        return temp;
    }
}
