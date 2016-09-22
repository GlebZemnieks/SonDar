package ru.sondar.client.documentmodel.android;

import ru.sondar.client.objectmodel.android.*;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.documentmodel.objectmodel.SDMainObjectType;

/**
 * Overwriting sequence object for supporting android objects
 */
public class AXMLSequenceObject extends SDSequenceObject {

	private int lastIdInDomain = 0;
	private int firstIdInDomain = 0;

	public int getFirstIdInDomain() {
		return firstIdInDomain;
	}
	public int getLastIdInDomain() {
		return lastIdInDomain;
	}
    public void setFirstIdInDomain(int firstIdInDomain) {
        this.firstIdInDomain = firstIdInDomain;
    }
    public void setLastIdInDomain(int lastIdInDomain) {
        this.lastIdInDomain = lastIdInDomain;
    }

    /**
     * Remove view object from domain layout for all object in sequence
     */
    public void resetView(){
        for(int count=0;count<this.sequenceArrayLength;count++){
            if(!(this.getXMLObject(count) instanceof AXMLSequenceObject)) {
                ((AXMLMainObject) this.getXMLObject(count)).resetView();
            }
        }
    }

    /**
     * Update fields from view for all objects
     */
	public void updateState(){
		for(int count=firstIdInDomain;count<lastIdInDomain+1;count++){
            if(!(this.getXMLObject(count) instanceof AXMLSequenceObject)) {
                ((AXMLMainObject) this.getXMLObject(count)).updateState();
            }
		}
	}

    public void AddXMLObject(SDMainObject newObject) {
        super.AddXMLObject(newObject);
    }

    public SDMainObject getXMLObject(int id) {
        return super.getXMLObject(id);
    }

    /**
     * Overwriting factory for android objects
     * @param type
     * @return
     */
    public SDMainObject getObjectByType(SDMainObjectType type) {
        if (type == SDMainObjectType.Text) {
            return new AXMLText();
        }
        if (type == SDMainObjectType.Spinner) {
            return new AXMLSpinner();
        }
        if (type == SDMainObjectType.CheckBox) {
            return new AXMLCheckBox();
        }
        if (type == SDMainObjectType.EndLn) {
            return new AXMLEndln();
        }
        if (type == SDMainObjectType.Date) {
            return new AXMLDate();
        }
        if (type == SDMainObjectType.EditText) {
            return new AXMLEditText();
        }
        if (type == SDMainObjectType.DivContainer) {
            return new AXMLSequenceObject();
        }
        return null;
    }
}
