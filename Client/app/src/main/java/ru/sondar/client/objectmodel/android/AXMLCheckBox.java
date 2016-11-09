package ru.sondar.client.objectmodel.android;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import ru.sondar.documentmodel.objectmodel.SDCheckBox;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.documentmodel.objectmodel.SDMainObjectType;

public class AXMLCheckBox extends AXMLMainObject {

    public AXMLCheckBox() {
        super(new SDCheckBox());
        this.objectType = SDMainObjectType.CheckBox;
    }

    public AXMLCheckBox(SDMainObject sdmainobject) {
        super((SDCheckBox) sdmainobject);
        this.objectType = SDMainObjectType.CheckBox;
    }

    @Override
    protected View prepareView(Context context) {
        CheckBox temp = new CheckBox(context);
        temp.setEnabled(true);
        temp.setChecked(((SDCheckBox) this.sdMainObject).getChecked());
        temp.setText(((SDCheckBox) this.sdMainObject).getText());
        return temp;
    }

    @Override
    public void updateState() {
        ((SDCheckBox) this.sdMainObject).setChecked(((CheckBox) this.getView()).isChecked());
    }

}
