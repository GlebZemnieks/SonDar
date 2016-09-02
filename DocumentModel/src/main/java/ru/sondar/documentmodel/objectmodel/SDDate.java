package ru.sondar.documentmodel.objectmodel;

import java.util.Date;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.documentmodel.dependencymodel.SupportDependencyInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.exception.parser.NoFieldException;
import ru.sondar.core.exception.parser.ObjectStructureException;

/**
 * SDDate object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SDDate extends SDMainObject implements SupportDependencyInterface {

    /**
     * Tag to print and parse date field
     */
    public static String Date_DateFieldTag = "date";

    /**
     * Date field
     */
    private Date date;

    /**
     * Constructor
     */
    public SDDate() {
        this.objectType = SDMainObjectType.Date;
    }

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

    // Start SupportDependency Interface
    @Override
    public Object getValue() {
        return this.date.getTime();
    }

    @Override
    public void setValue(Object object) {
        this.date = new Date(Long.parseLong((String) object));
    }
    // End SupportDependency Interface

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

    @Override
    public String toString() {
        return super.toString() + " : date : " + this.date.toString();
    }

}
