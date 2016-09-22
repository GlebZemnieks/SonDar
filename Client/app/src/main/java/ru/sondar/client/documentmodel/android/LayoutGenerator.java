package ru.sondar.client.documentmodel.android;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import ru.sondar.client.objectmodel.android.AXMLMainObject;
import ru.sondar.core.logger.Logger;
import ru.sondar.documentmodel.objectmodel.SDMainObjectType;

/**
 * TODO Need refactoring!! This is puzdes!
 * 
 */
public class LayoutGenerator implements LayoutGeneratorInterface {

	private String logTag = "LayoutGenerator";

	private LinearLayout domainLayout;
	private LinearLayout nowLayout;
	private int screenWidth;
	private int screenHeight;
	private int heightNow;
	private int widthNow;
	private int lastIdInString;
	private int heightNewView;
	private int widthNewView;

	@Override
	public LinearLayout generateLayout(Context context, AXMLSequenceObject sequenceObject, int startObjectId, int footer){
		Logger.Log(logTag, "Start LayoutGenerate with parameter:\n   startObjectId:"+startObjectId+"\n   footer:" + footer);
		isEntriesCorrect(sequenceObject, startObjectId);
		Logger.Log(logTag, "XMLSequence:\n" + sequenceObject.toString());
		prepareToGenerate(context, startObjectId, footer, sequenceObject);
		for(int count = startObjectId;;count++){			
			if(checkSequenceOverflow(sequenceObject, count)){
				if(count == startObjectId){
					Logger.Log(logTag, "Last page. Generation will skip. Throw XMLSequenceIndexOverflowException");
					throw new XMLSequenceIndexOverflowException("Layout no update");
				}
				Logger.Log(logTag, "checkSequenceOverflow = True --> break");
				domainLayout.addView(nowLayout);
				sequenceObject.setFirstIdInDomain(startObjectId);
				sequenceObject.setLastIdInDomain(sequenceObject.getXMLObject(count-1).getID());
				Logger.Log(logTag, "return domainLayout --> lastIdInDomain = " + sequenceObject.getLastIdInDomain());
				return domainLayout;
			}
			if(ifEndln(sequenceObject, count)){
				Logger.Log(logTag, "ifEndln = True --> continue");
				prepareNewLayout(context, sequenceObject, count);
				continue;
			}
			if(sequenceObject.getXMLObject(count).getObjectType() == SDMainObjectType.DivContainer){
				Logger.Log(logTag, "ifDivContainer = True --> continue");
				continue;
			}
			Logger.Log(logTag, "prepare object:\n" + count + "-->" + sequenceObject.getXMLObject(count).toString());
			View newView = prepareNewView(context, sequenceObject, count);
			Logger.Log(logTag, "objectWidth = " + widthNewView + ";objectHeight = " + heightNewView);
			if(domainCheck(footer)){
				Logger.Log(logTag, "domainLayout overflow --> break");
				sequenceObject.setLastIdInDomain(lastIdInString);
				sequenceObject.setFirstIdInDomain(startObjectId);
				Logger.Log(logTag, "return domainLayout --> lastIdInDomain = " + sequenceObject.getLastIdInDomain());
				return domainLayout;
			}
			if(nowLayoutCheck()){
				Logger.Log(logTag, "nowLayout overflow --> continue with decrement count");
				prepareNewLayout(context, sequenceObject, count);
				Logger.Log(logTag, "left height = " + screenHeight);
				//To the next time the cycle has continued to work with the current item. Do decrement the iterator
				count--;
				continue;
			}
			Logger.Log(logTag, "addNewView");
			addNewView(sequenceObject, count, newView);
			Logger.Log(logTag, "left width = " + widthNow);
		}		
	}


	@Override
	public int reverseGenerating(Context context, AXMLSequenceObject sequenceObject, int startObjectId, int footer){
		Logger.Log(logTag, "Start reverse Generate with parameter:\n   startObjectId:"+startObjectId+"\n   footer:" + footer);
		isEntriesCorrect(sequenceObject, startObjectId);
		Logger.Log(logTag, "XMLSequence:\n" + sequenceObject.toString());
		prepareToGenerate(context, startObjectId, footer, sequenceObject);
		for(int count = startObjectId;;count--){
			if(checkSequenceOverflow(sequenceObject, count)){
				Logger.Log(logTag, "checkSequenceOverflow = True --> break");
				domainLayout.addView(nowLayout);
				Logger.Log(logTag, "return domainLayout --> lastIdInDomain = " + 0);
				return 0;
			}
			if(ifEndln(sequenceObject, count)){
				Logger.Log(logTag, "ifEndln = True --> continue");
				prepareNewLayout(context, sequenceObject, count);
				continue;
			}
			Logger.Log(logTag, "prepare object:\n" + count + "-->" + sequenceObject.getXMLObject(count).toString());
			View newView = prepareNewView(context, sequenceObject, count);
			Logger.Log(logTag, "objectWidth = " + widthNewView + ";objectHeight = " + heightNewView);
			if(domainCheck(footer)){
				Logger.Log(logTag, "domainLayout overflow --> break");
				Logger.Log(logTag, "return domainLayout --> lastIdInDomain = " + lastIdInString);
				return lastIdInString;
			}
			if(nowLayoutCheck()){
				Logger.Log(logTag, "nowLayout overflow --> continue with decrement count");
				prepareNewLayout(context, sequenceObject, count);
				Logger.Log(logTag, "left height = " + screenHeight);
				//To the next time the cycle has continued to work with the current item. Do increment the iterator
				count++;
				continue;
			}
			Logger.Log(logTag, "addNewView");
			addNewView(sequenceObject, count, newView);
			Logger.Log(logTag, "left width = " + widthNow);
		}
	}

	
	/**
	 * Is startObjectId correctly? Else throw OverflowException.
	 * @param sequenceObject
	 * @param startObjectId
	 * @throws XMLSequenceIndexOverflowException
	 */
	private void isEntriesCorrect(AXMLSequenceObject sequenceObject, int startObjectId) throws XMLSequenceIndexOverflowException {
		if(startObjectId>=sequenceObject.sequenceArray.size()&&startObjectId<0){
			Logger.Log(logTag, "!!!!throw XMLSequenceIndexOverflowException!!!!");
			throw new XMLSequenceIndexOverflowException();
		}
	}
	/**
	 * Set default value to all generator parameter.
	 * Set startObjectId as FirstIdInDomain for sequence
	 * @param context
	 * @param startObjectId
	 * @param footer
	 * @param sequence
	 */
	private void prepareToGenerate(Context context, int startObjectId, int footer, AXMLSequenceObject sequence) {
		sequence.setFirstIdInDomain(startObjectId);
		domainLayout = getDomainLayout(context);
		nowLayout = getNowLayout(context);
		screenWidth = getScreenWidth(context);
		screenHeight = getScreenHeight(context) - footer;
		Logger.Log(logTag, "Start screenWidth = " + screenWidth + ";screenHeight = " + screenHeight);
		heightNow = 0;
		widthNow = screenWidth;
		lastIdInString = 0;
	}
	/**
	 * Check is sequence overflow. 
	 * @param Array
	 * @param count
	 * @return
	 */
	private boolean checkSequenceOverflow(AXMLSequenceObject Array, int count){
		try {
			Array.getXMLObject(count).toString();
		}
		catch(IndexOutOfBoundsException e){
			return true;
		}	
		return false;
	}
	/**
	 * Add to vertical current horizontal layout. And create new.
	 * @param context
	 * @param sequenceObject
	 * @param count
	 */
	private void prepareNewLayout(Context context, AXMLSequenceObject sequenceObject, int count) {
		domainLayout.addView(nowLayout);
		sequenceObject.setLastIdInDomain(sequenceObject.getXMLObject(count-1).getID());
		nowLayout = getNowLayout(context);
		screenHeight = screenHeight - heightNow;
		heightNow = 0;
		widthNow = screenWidth;
	}
	/**
	 * Get from MainObject factory current object and call overloading method getView to get View height and width.
	 * @param context
	 * @param sequenceObject
	 * @param count
	 * @return
	 */
	private View prepareNewView(Context context, AXMLSequenceObject sequenceObject,int count) {
		View newView = null;
		heightNewView = 0;
		widthNewView = 0;
		newView = ((AXMLMainObject) sequenceObject.getXMLObject(count)).getView(context);
		heightNewView = ((AXMLMainObject) sequenceObject.getXMLObject(count)).getViewHeight(context);
		widthNewView = ((AXMLMainObject) sequenceObject.getXMLObject(count)).getViewWidth(context);
		return newView;
	}
	/**
	 * Is current object can fit in this screen by height.
	 * @param footer
	 * @return
	 */
	private boolean domainCheck(int footer) {
		heightNow = updateHeightNow(heightNow, heightNewView);
		return heightNow + footer >= screenHeight;
	}
	/**
	 * Is current object can fit in this line by width.
	 * @return
	 */
	private boolean nowLayoutCheck() {
		return widthNow < widthNewView;
	}
	
	/**
	 * Add view of current object to sequence.
	 * @param sequenceObject
	 * @param count
	 * @param newView
	 */
	private void addNewView(AXMLSequenceObject sequenceObject, int count, View newView) {
		((AXMLMainObject)sequenceObject.getXMLObject(count)).setLinearLayout(nowLayout);
		nowLayout.addView(newView);
		widthNow = widthNow - widthNewView;
		lastIdInString = sequenceObject.getXMLObject(count).getID();
	}
	
	/**
	 * Prepare LinearLayout with VERTICAL Orientation
	 * @param context
	 * @return
	 */
	private LinearLayout getDomainLayout(Context context) {
		LinearLayout domainLayout = new LinearLayout(context);
		domainLayout.setOrientation(LinearLayout.VERTICAL);
		return domainLayout;
	}

	/**
	 * Prepare LinearLayout with HORIZONTAL Orientation
	 * @param context
	 * @return
	 */
	private LinearLayout getNowLayout(Context context) {
		LinearLayout nowLayout;
		nowLayout = new LinearLayout(context);
		nowLayout.setOrientation(LinearLayout.HORIZONTAL);
		return nowLayout;
	}
	/**
	 * Get DisplayMetrics
	 * @param context
	 * @return
	 */
	private int getScreenHeight(Context context){
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return displayMetrics.heightPixels;
	}

	/**
	 * Get DisplayMetrics
	 * @param context
	 * @return
	 */
	private int getScreenWidth(Context context){
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return displayMetrics.widthPixels;
	}
	
	/**
	 * If current object is Endln symbol
	 * @param sequenceObject
	 * @param count
	 * @return
	 */
	private boolean ifEndln(AXMLSequenceObject sequenceObject, int count) {
		return sequenceObject.getXMLObject(count).getObjectType() == SDMainObjectType.EndLn;
	}
		
	private int updateHeightNow(int heightNow, int heightNewView) {
		if(heightNow < heightNewView) {
			return heightNewView;
		}
		return heightNow;
	}


	
	
}
