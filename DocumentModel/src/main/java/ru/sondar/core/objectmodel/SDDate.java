package ru.sondar.core.objectmodel;

import java.util.Date;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.objectmodel.exception.NoFieldException;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

/**
 * Date object
 *
 * @author GlebZemnieks
 */
public class SDDate extends SDMainObject {

    /**
     * Tag to print and parse date field
     */
    public static String Date_DateFieldTag = "date";

    /**
     * Date field
     */
    private Date date;

    /**
     * Getter for date field
     *
     * @return
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Setter for date field
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Set current date
     */
    public void setCurrentDate() {
        this.date = new Date();
    }

    /**
     * Constructor
     */
    public SDDate() {
        this.objectType = SDMainObjectType.Date;
    }

    @Override
    protected void parseCurrentObjectField(Element element) throws ObjectStructureException {
        NodeList list = element.getElementsByTagName(Date_DateFieldTag);
        if (list.item(0) != null) {
            this.setDate(new Date(Long.parseLong(list.item(0).getTextContent())));
        } else {
            throw new NoFieldException("Missing \"date\" field");
        }
    }

    @Override
    protected void printCurrentObjectField(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<date>" + this.date.getTime() + "</date>\n");
    }

}
