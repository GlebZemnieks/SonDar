package ru.sondar.client.objectmodel.android;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.w3c.dom.Element;

import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.documentmodel.objectmodel.SDMainObjectType;
import ru.sondar.documentmodel.objectmodel.SDSpinner;

public class AXMLSpinner extends AXMLMainObject {

    public AXMLSpinner() {
        super(new SDSpinner());
        this.objectType = SDMainObjectType.Spinner;
    }

    public AXMLSpinner(SDMainObject sdmainobject) {
        super((SDSpinner)sdmainobject);
        this.objectType = SDMainObjectType.Spinner;
    }

    @Override
    protected View prepareView(Context context) {
        Spinner temp = new Spinner(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, ((SDSpinner) this.sdMainObject).getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        temp.setAdapter(adapter);
        temp.setSelection(((SDSpinner) this.sdMainObject).getDefaultItemSelected());
        return temp;
    }

    @Override
    public void updateState() {
        ((SDSpinner) this.sdMainObject).setDefaultItemSelected(((Spinner) this.getView()).getSelectedItemPosition());
    }
}
