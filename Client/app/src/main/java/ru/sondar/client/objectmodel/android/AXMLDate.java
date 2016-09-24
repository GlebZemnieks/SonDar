package ru.sondar.client.objectmodel.android;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import ru.sondar.core.logger.Logger;
import ru.sondar.documentmodel.objectmodel.SDDate;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.documentmodel.objectmodel.SDMainObjectType;

public class AXMLDate extends AXMLMainObject {

    public AXMLDate() {
        super(new SDDate());
        this.objectType = SDMainObjectType.Date;
    }

    public AXMLDate(SDMainObject sdmainObject) {
        super((SDDate)sdmainObject);
        this.objectType = SDMainObjectType.Date;
    }

    @Override
    protected View prepareView(final Context context) {
        DatePicker view = new DatePicker(context);
        Calendar today = Calendar.getInstance();
        today.setTime(((SDDate) this.sdMainObject).getDate());
        view.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH), null);
        view.setCalendarViewShown(false);

        TextView viewTemp = new TextView(context);
        viewTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.Log("AXMLDate::","OnClick on date object : " + sdMainObject.toString());
                DialogFragment dateDialog = (new CustomDatePickerDialog()).setDate((SDDate) sdMainObject);
                dateDialog.show(((ActionBarActivity)context).getSupportFragmentManager(), "datePicker");
            }
        });
        viewTemp.setText(((SDDate) this.sdMainObject).getDate().getDay()
                + "-" + ((SDDate) this.sdMainObject).getDate().getMonth()
                + "-" + ((SDDate) this.sdMainObject).getDate().getYear());

        return viewTemp;

    }

    @Override
    public void updateState() {
        //Empty
    }

}

class CustomDatePickerDialog extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    SDDate protectedDate;

    public CustomDatePickerDialog setDate(SDDate date){
        this.protectedDate = date;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog picker = new DatePickerDialog(getActivity(), this,
                protectedDate.getDate().getYear(),
                protectedDate.getDate().getMonth(),
                protectedDate.getDate().getDay());
        picker.setTitle(protectedDate.getText());
        picker.

        return picker;
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year,
                          int month, int day) {
        protectedDate.getDate().setDate(day);
        protectedDate.getDate().setMonth(month);
        protectedDate.getDate().setYear(year);
    }
}