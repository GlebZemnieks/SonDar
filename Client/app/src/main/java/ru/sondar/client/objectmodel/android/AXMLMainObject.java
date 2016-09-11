package ru.sondar.client.objectmodel.android;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import org.w3c.dom.Element;

import ru.sondar.core.exception.parser.ObjectStructureException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.objectmodel.SDMainObject;

/**
 * Main class for association android parameter for object
 *
 * @author GlebZemnieks
 */
public abstract class AXMLMainObject extends SDMainObject {

    /**
     * Layout on which object placement
     */
    private LinearLayout domainLayout = null;

    /**
     * Setter for layout
     *
     * @param layout
     */
    public void setLinearLayout(LinearLayout layout) {
        this.domainLayout = layout;
    }
    /**
     * View object
     */
    private View view;

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

    /**
     * Parent main object
     */
    protected SDMainObject sdMainObject;

    /**
     * Constructor
     *
     * @param sdmainobject
     */
    public AXMLMainObject(SDMainObject sdmainobject) {
        this.sdMainObject = sdmainobject;
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

    public void setSequence(SDSequenceObject sequence){
        super.setSequence(sequence);
        this.sdMainObject.setSequence(sequence);
    }

    public void parseAttribute(Element element) throws ObjectStructureException {
        this.sdMainObject.parseAttribute(element);
    }

    public void parseObjectFromXML(Element element) throws ObjectStructureException {
        this.sdMainObject.parseObjectFromXML(element);
    }
    @Override
    public void parseCurrentObjectField(Element element) throws ObjectStructureException {
        this.sdMainObject.parseCurrentObjectField(element);
    }

    public void printAttrivute(FileModuleWriteThreadInterface fileModule) {
        this.sdMainObject.printAttrivute(fileModule);
    }

    @Override
    public void printObjectToXML(FileModuleWriteThreadInterface fileModule) {
        this.sdMainObject.printObjectToXML(fileModule);
    }

    @Override
    public void printCurrentObjectField(FileModuleWriteThreadInterface fileModule) {
        this.sdMainObject.printCurrentObjectField(fileModule);
    }

    @Override
    public String toString() {
        return sdMainObject.toString();
    }

}
