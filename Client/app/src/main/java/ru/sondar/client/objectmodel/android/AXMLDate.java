package ru.sondar.client.objectmodel.android;

import java.util.Calendar;
import java.util.Date;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import ru.sondar.core.objectmodel.SDDate;
import ru.sondar.core.objectmodel.SDMainObject;
import ru.sondar.core.objectmodel.SDMainObjectType;

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
    protected View prepareView(Context context) {
        DatePicker view = new DatePicker(context);
        Calendar today = Calendar.getInstance();
        today.setTime(((SDDate) this.sdMainObject).getDate());
        view.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH), null);
        view.setCalendarViewShown(false);
        return view;
    }

    @Override
    public void updateState() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, ((DatePicker) this.getView()).getYear());
        cal.set(Calendar.MONTH, ((DatePicker) this.getView()).getMonth());
        cal.set(Calendar.DAY_OF_MONTH, ((DatePicker) this.getView()).getDayOfMonth());
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 1);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        ((SDDate) this.sdMainObject).setDate(new Date(cal.getTimeInMillis()));
    }

}
