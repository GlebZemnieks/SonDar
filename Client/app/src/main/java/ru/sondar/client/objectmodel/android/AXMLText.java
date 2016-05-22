package ru.sondar.client.objectmodel.android;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import ru.sondar.core.objectmodel.SDMainObject;
import ru.sondar.core.objectmodel.SDText;

public class AXMLText extends AXMLMainObject {

    public AXMLText() {
        super(new SDText());
    }

    public AXMLText(SDMainObject sdmainobject) {
        super((SDText)sdmainobject);
    }

    @Override
    protected View prepareView(Context context) {
        TextView temp = new TextView(context);
        temp.setText(((SDText) this.sdMainObject).getText());
        return temp;
    }

    @Override
    public void updateState() {
        //No active field
    }

}
