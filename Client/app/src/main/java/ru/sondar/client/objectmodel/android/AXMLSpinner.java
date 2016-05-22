package ru.sondar.client.objectmodel.android;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import ru.sondar.core.objectmodel.SDMainObject;
import ru.sondar.core.objectmodel.SDMainObjectType;
import ru.sondar.core.objectmodel.SDSpinner;

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
        temp.setSelection(((SDSpinner) this.sdMainObject).getDefaultItemSelected() - 1);
        return temp;
    }

    @Override
    public void updateState() {
        ((SDSpinner) this.sdMainObject).setDefaultItemSelected(((Spinner) this.getView()).getSelectedItemPosition() + 1);
    }
}
