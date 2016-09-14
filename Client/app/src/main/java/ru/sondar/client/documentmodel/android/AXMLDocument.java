package ru.sondar.client.documentmodel.android;

import android.content.Context;
import android.widget.LinearLayout;

import ru.sondar.core.logger.Logger;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.SDDOMParser;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.documentmodel.SDSequenceObject;

public class AXMLDocument extends SDDocument {

	/**
	 * Layout generator object
	 */
	private LayoutGeneratorInterface generator;
	/**
	 * Activity context
	 */
	private Context context;
	/**
	 * Footer for layout
	 */
	private int footer = 100;

	/**
	 * Setter for footer field
	 * @param footer
     */
	public void setFooter(int footer){
		this.footer = footer;
	}

	/**
	 * Constructor
	 *
	 * @param context
     */
	public AXMLDocument(Context context){
		this.context = context;
		this.generator = new LayoutGenerator();
	}

	/**
	 * Get start layout with start id 0
	 * @return
     */
	public LinearLayout getStartLayout(){
		getSequense().resetView();
		try {
			return generator.generateLayout(this.context, getSequense(), 0, footer);
		} catch (XMLSequenceIndexOverflowException error){
			Logger.Log("getStartLayout","Try to open empty document(No object defined). Something wrong, but 'okay' - return empty layout. If this problem in log unexpected -> GLOBAL WARNING, suspected dataloss");
			return new LinearLayout(this.context);
		}
	}

	/**
	 * Get layout with next id start
	 * @return
     */
	public LinearLayout getNextLayout(){
		((AXMLSequenceObject) sequence).updateState();
		((AXMLSequenceObject) sequence).resetView();
		return generator.generateLayout(this.context, ((AXMLSequenceObject) sequence), ((AXMLSequenceObject) sequence).getLastIdInDomain() + 1, footer);
	}

	/**
	 * Get layout with old start id - 1
	 * @return
     */
	public LinearLayout getBackLayout(){
		int startObjectId = 0;
        ((AXMLSequenceObject) sequence).updateState();
	   	((AXMLSequenceObject) sequence).resetView();
		startObjectId = generator.reverseGenerating(this.context, ((AXMLSequenceObject) sequence), ((AXMLSequenceObject) sequence).getFirstIdInDomain()-1, footer);
	   	((AXMLSequenceObject) sequence).resetView();
		return generator.generateLayout(context,((AXMLSequenceObject) sequence), startObjectId, footer);
	}

	@Override
	public void saveDocument(FileModuleWriteThreadInterface fileModule) {
		((AXMLSequenceObject) sequence).updateState();
		super.saveDocument(fileModule);
    }

	@Override
    public AXMLSequenceObject getSequense() {
        return (AXMLSequenceObject) sequence;
    }
	
	
}
