package ru.sondar.client.objectmodel.android;

import android.content.Context;
import android.view.View;
import ru.sondar.core.objectmodel.SDEndln;
import ru.sondar.core.objectmodel.SDMainObject;

public class AXMLEndln extends AXMLMainObject {

    public AXMLEndln() {
        super(new SDEndln());
    }

    public AXMLEndln(SDMainObject sdmainobject) {
        super((SDEndln)sdmainobject);
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
