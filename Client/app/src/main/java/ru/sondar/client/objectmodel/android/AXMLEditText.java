package ru.sondar.client.objectmodel.android;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import ru.sondar.documentmodel.objectmodel.SDEditText;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.documentmodel.objectmodel.SDMainObjectType;

public class AXMLEditText extends AXMLMainObject {

    public AXMLEditText() {
        super(new SDEditText());
        this.objectType = SDMainObjectType.EditText;
    }

    public AXMLEditText(SDMainObject sdmainObject) {
        super((SDEditText)sdmainObject);
        this.objectType = SDMainObjectType.EditText;
    }

    @Override
    protected View prepareView(Context context) {
        View view = new EditText(context);
        String temp = ((SDEditText) this.sdMainObject).getText();
        for (int i = temp.length(); i < ((SDEditText) this.sdMainObject).getTextLength(); i++) {
            temp += " ";
        }
        ((EditText) view).setText(temp);
        return view;
    }

    @Override
    public void updateState() {
        EditText view = (EditText) this.getView();
        String temp = view.getText().toString();
        ((SDEditText) this.sdMainObject).setText(temp.trim());
    }

}
