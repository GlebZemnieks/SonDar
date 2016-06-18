package ru.sondar.documentmodel.internalfunction;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import static ru.sondar.documentmodel.internalfunction.Function.functionTag;
import ru.sondar.documentmodel.internalfunction.function.CustomFunction;

/**
 * Class witch give you access to internal function list.
 *
 * @author GlebZemnieks
 */
public class InternalFunction {

    /**
     * List of internal function
     */
    public ArrayList<Function> functionList = new ArrayList<>();

    /**
     * Add new function to array
     *
     * @param newObject
     */
    public void AddXMLObject(Function newObject) {
        this.functionList.add(newObject);
    }

    public void makeFunction(TriggerType trigger) {
        for (int i = 0; i < this.functionList.size(); i++) {
            this.functionList.get(i).makeFunction(trigger);
        }
    }

    /**
     * Tag to parse and print internal function object in XML.
     */
    public static final String internalFunctionTag = "InternalFunction";

    /**
     * Parse all function by InternalFunction DOM element
     *
     * @param element
     */
    public void parseXMLString(Element element) {
        NodeList tempList = element.getElementsByTagName(functionTag);
        for (int count = 0; count < tempList.getLength(); count++) {
            Element tempElement = (Element) tempList.item(count);
            FunctionType newObjectType = Function.chooseFunctionType(tempElement.getAttribute("functionType"));
            Function tempObject = getFunctionByType(newObjectType);
            tempObject.parseObjectAttribute(tempElement);
            tempObject.parseXMLString(tempElement);
            AddXMLObject(tempObject);
        }
    }

    /**
     * Print all function from array to file in XML format
     *
     * @param fileModule
     */
    public void printXMLString(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + internalFunctionTag + ">\n");
        for (int i = 0; i < this.functionList.size(); i++) {
            this.functionList.get(i).printXMLString(fileModule);
        }
        fileModule.write("</" + internalFunctionTag + ">\n");
    }

    public Function getFunctionByType(FunctionType type) {
        if (type.toString().equals(FunctionType.CustomFunction.toString())) {
            return new CustomFunction();
        }
        throw new UnsupportedClassVersionError("Function : " + type.toString() + " : not supported yet");
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < this.functionList.size(); i++) {
            temp += this.functionList.get(i).toString();
        }
        return temp;
    }

}
