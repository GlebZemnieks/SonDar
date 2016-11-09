package ru.sondar.client.objectmodel.android;

import android.content.Context;
import android.view.View;

import ru.sondar.documentmodel.objectmodel.SDEndln;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.documentmodel.objectmodel.SDMainObjectType;

public class AXMLEndln extends AXMLMainObject {

    public AXMLEndln() {
        super(new SDEndln());
        this.objectType = SDMainObjectType.EndLn;
    }

    public AXMLEndln(SDMainObject sdmainobject) {
        super((SDEndln) sdmainobject);
        this.objectType = SDMainObjectType.EndLn;
    }

    @Override
    public int getViewHeight(Context context) {
        return 0;
    }

    @Override
    public int getViewWidth(Context context) {
        return 0;
    }

    @Override
    protected View prepareView(Context context) {
        return new View(context);
    }

    @Override
    public void updateState() {
        //No active field
    }

}
