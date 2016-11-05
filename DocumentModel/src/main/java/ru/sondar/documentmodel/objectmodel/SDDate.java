package ru.sondar.documentmodel.objectmodel;

import java.util.Calendar;
import java.util.Date;
import ru.sondar.documentmodel.dependencymodel.SupportDependencyInterface;

/**
 * SDDate object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SDDate extends SDMainObject implements SupportDependencyInterface {

    /**
     * Tag to print and parse day field
     */
    public static String DATE_DATE_FIELD_TAG = "date";
    /**
     * Tag to print and parse text field
     */
    public static String DATE_TEXT_FIELD_TAG = "text";

    /**
     * Constructor
     */
    public SDDate() {
        this.objectType = SDMainObjectType.Date;
    }

    /**
     * Date field
     */
    private Calendar calendar = Calendar.getInstance();

    /**
     * Getter for date field
     *
     * @return
     */
    public Calendar getCalendar() {
        return this.calendar;
    }

    /**
     * Setter for date field
     *
     * @param calendar
     */
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * Setter for date field
     *
     * @param date
     */
    public void setCalendar(Date date) {
        this.calendar.setTime(date);
    }

    /**
     * Set current date
     */
    public void setCurrentDate() {
        this.calendar = Calendar.getInstance();
    }

    private String text = "";

    public String getText() {
        return this.text;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    // Start SupportDependency Interface
    @Override
    public Object getValue() {
        return this.calendar.getTime().getTime();
    }

    @Override
    public void setValue(Object object) {
        this.calendar.setTime(new Date(Long.parseLong((String) object)));
    }
    // End SupportDependency Interface

    @Override
    public String toString() {
        return super.toString() + " : date : " + this.calendar.getTime() + " : text : " + this.text;
    }

}
