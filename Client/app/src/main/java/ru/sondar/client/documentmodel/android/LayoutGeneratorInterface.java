package ru.sondar.client.documentmodel.android;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Interface to layout generator of main screens. Only two methods for all your needs. 
 * Get main screen with straight logic and get index of first item using reverse logic.
 */
public interface LayoutGeneratorInterface {

	/**
	 * Realization straight logic to generate main layout
	 * @param sequenceObject - XMLMainObject[] to generate
	 * @param startObjectId  - first item to generate
	 * @param footer		 - quantity pixel to make indent
	 * @return LinearLayout object - main screen
	*/
	public LinearLayout generateLayout(Context context, AXMLSequenceObject sequenceObject, int startObjectId, int footer);

	/**
	 * Realization reverse logic to get index of first item
	 * @param sequenceObject - XMLMainObject[] to generate
	 * @param startObjectId  - first item to generate
	 * @param footer		 - quantity pixel to make indent
	 * @return Integer - index of sequenceObject item
	 */
	public int reverseGenerating(Context context, AXMLSequenceObject sequenceObject, int startObjectId, int footer);

}