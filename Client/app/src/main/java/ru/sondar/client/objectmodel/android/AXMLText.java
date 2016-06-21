package ru.sondar.client.objectmodel.android;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.documentmodel.objectmodel.SDMainObjectType;
import ru.sondar.documentmodel.objectmodel.SDText;

public class AXMLText extends AXMLMainObject {

    public AXMLText() {
        super(new SDText());
        this.objectType = SDMainObjectType.Text;
    }

    public AXMLText(SDMainObject sdmainobject) {
        super((SDText)sdmainobject);
        this.objectType = SDMainObjectType.Text;
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
