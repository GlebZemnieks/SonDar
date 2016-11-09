package ru.sondar.client.objectmodel.android;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.objectmodel.SDMainObject;

/**
 * Main class for association android parameter for object
 *
 * @author GlebZemnieks
 */
public abstract class AXMLMainObject extends SDMainObject {

    /**
     * Parent main object
     */
    protected SDMainObject sdMainObject;
    /**
     * Layout on which object placement
     */
    private LinearLayout domainLayout = null;
    /**
     * View object
     */
    private View view;

    /**
     * Constructor
     *
     * @param sdmainobject
     */
    public AXMLMainObject(SDMainObject sdmainobject) {
        this.sdMainObject = sdmainobject;
    }

    /**
     * Setter for layout
     *
     * @param layout
     */
    public void setLinearLayout(LinearLayout layout) {
        this.domainLayout = layout;
    }

    /**
     * Internal getter for view field
     *
     * @return
     */
    protected View getView() {
        return view;
    }

    /**
     * Getter for view object
     *
     * @param context
     * @return
     */
    public View getView(Context context) {
        if (view == null) {
            view = this.prepareView(context);
        }
        return view;
    }

    /**
     * Remove this view object from domain layout
     */
    public void resetView() {
        if (this.domainLayout != null) {
            this.domainLayout.removeView(this.view);
        }
    }

    public SDMainObject getParentObject() {
        return this.sdMainObject;
    }

    /**
     * Getter for this view object height
     *
     * @param context
     * @return
     */
    public int getViewHeight(Context context) {
        this.getView(context);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return view.getMeasuredHeight();
    }

    /**
     * Getter for this view object width
     *
     * @param context
     * @return
     */
    public int getViewWidth(Context context) {
        this.getView(context);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return view.getMeasuredWidth();
    }

    /**
     * Abstract method for overloading. Create new view object and return it.
     *
     * @param context
     * @return
     */
    protected abstract View prepareView(Context context);

    /**
     * Update object fields from view fields
     */
    public abstract void updateState();

    public void setSequence(SDSequenceObject sequence) {
        super.setSequence(sequence);
        this.sdMainObject.setSequence(sequence);
    }

    @Override
    public String toString() {
        return sdMainObject.toString();
    }

}
