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

import java.text.DateFormatSymbols;
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
        super((SDDate) sdmainObject);
        this.objectType = SDMainObjectType.Date;
    }

    @Override
    protected View prepareView(final Context context) {
        final TextView viewTemp = new TextView(context);
        viewTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.Log("AXMLDate::", "OnClick on date object : " + sdMainObject.toString());
                DialogFragment dateDialog = (new CustomDatePickerFragment()).setDate((SDDate) sdMainObject).setTextView(viewTemp);
                dateDialog.show(((ActionBarActivity) context).getSupportFragmentManager(), "datePicker");
            }
        });
        viewTemp.setText(((SDDate) this.sdMainObject).getCalendar().get(Calendar.DAY_OF_MONTH)
                + "-" + ((new DateFormatSymbols()).getMonths())[(((SDDate) this.sdMainObject).getCalendar().get(Calendar.MONTH))]
                + "-" + ((SDDate) this.sdMainObject).getCalendar().get(Calendar.YEAR));
        return viewTemp;
    }

    @Override
    public void updateState() {
        //Empty
    }

}

class CustomDatePickerDialog extends DatePickerDialog {

    private String title = "";

    public CustomDatePickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }

    public void setPermanentTitle(String title) {
        this.title = title;
        this.setTitle(title);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);
        setTitle(title);
    }
}

class CustomDatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    SDDate protectedDate;
    TextView protectedView;

    public CustomDatePickerFragment setDate(SDDate date) {
        this.protectedDate = date;
        Logger.Log("AXMLDate::", "Set date object : " + protectedDate.toString());
        return this;
    }

    public CustomDatePickerFragment setTextView(TextView view) {
        this.protectedView = view;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog picker = new CustomDatePickerDialog(getActivity(), this,
                protectedDate.getCalendar().get(Calendar.YEAR),
                protectedDate.getCalendar().get(Calendar.MONTH),
                protectedDate.getCalendar().get(Calendar.DAY_OF_MONTH));
        ((CustomDatePickerDialog) picker).setPermanentTitle(protectedDate.getText());
        Logger.Log("AXMLDate::", "Dialog created with date : " + protectedDate.toString());
        ((CustomDatePickerDialog) picker).getDatePicker().setCalendarViewShown(false);
        return picker;
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year,
                          int month, int day) {
        protectedDate.getCalendar().set(Calendar.DAY_OF_MONTH, day);
        protectedDate.getCalendar().set(Calendar.MONTH, month);
        protectedDate.getCalendar().set(Calendar.YEAR, year);
        protectedView.setText(((SDDate) this.protectedDate).getCalendar().get(Calendar.DAY_OF_MONTH)
                + "-" + ((new DateFormatSymbols()).getMonths())[(((SDDate) this.protectedDate).getCalendar().get(Calendar.MONTH))]
                + "-" + ((SDDate) this.protectedDate).getCalendar().get(Calendar.YEAR));
        Logger.Log("AXMLDate::", "onDateSet work:: new value " + protectedDate.toString());
    }
}
