package ru.sondar.client.documentmodel.android;


import android.content.Context;
import android.widget.LinearLayout;

import ru.sondar.core.documentmodel.SDDOMParser;
import ru.sondar.core.documentmodel.SDDocument;
import ru.sondar.core.documentmodel.SDSequenceObject;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

public class AXMLDocument extends SDDocument {

	private LayoutGeneratorInterface generator;
	private Context context;
	private int footer = 100;
	
	public AXMLDocument(Context context){
		this.context = context;
		this.generator = new LayoutGenerator();
	}
	public LinearLayout getBackLayout(){
	int startObjectId = 0;
        ((AXMLSequenceObject)sequence).updateState();
	   	((AXMLSequenceObject) sequence).resetView();
		startObjectId = generator.reverseGenerating(this.context, ((AXMLSequenceObject) sequence), ((AXMLSequenceObject) sequence).getFirstIdInDomain()-1, footer);
		((AXMLSequenceObject) sequence).updateState();
	   	((AXMLSequenceObject) sequence).resetView();
		return generator.generateLayout(context,((AXMLSequenceObject) sequence), startObjectId, footer);
	}
	
	public LinearLayout getNextLayout(){
		((AXMLSequenceObject) sequence).updateState();
	   	((AXMLSequenceObject) sequence).resetView();
		return generator.generateLayout(this.context, ((AXMLSequenceObject) sequence), ((AXMLSequenceObject) sequence).getLastIdInDomain()+1, footer);
	}
	
	public LinearLayout getStartLayout(){
		getSequense().resetView();
		return generator.generateLayout(this.context, getSequense(), ((AXMLSequenceObject) sequence).getLastIdInDomain(), footer);	
	}
	
	public void loadDocument(SDDOMParser parser, SDSequenceObject sequence) throws ObjectStructureException {
		super.loadDocument(parser, sequence);
	}
	
	public void setFooter(int footer){
		this.footer = footer;
	}
	public void saveDocument(FileModuleWriteThread fileModule) {
		((AXMLSequenceObject) sequence).updateState();
		super.saveDocument(fileModule);
		
    }

    public AXMLSequenceObject getSequense() {
        return (AXMLSequenceObject)sequence;
    }
	
	
}
