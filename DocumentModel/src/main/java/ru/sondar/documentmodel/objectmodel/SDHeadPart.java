package ru.sondar.documentmodel.objectmodel;

import java.util.Date;
import java.util.UUID;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

/**
 * SDHeadPart object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SDHeadPart extends SDMainObject {

    /**
     * UUID for document. Set position in DataBase. Set
     * 00000000-0000-0000-0000-000000000000 if document new and have not
     * protection in DataBase.
     */
    private UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");

    /**
     * Plugin UUID. Used in DocumentManager for plugin indentefication.
     */
    private UUID plugin = UUID.fromString("00000000-0000-0000-0000-000000000000");

    /**
     * Time and Date of document creation. Using if you wont to know if this
     * document are equals;
     */
    private Date creationTime = new Date();

    /**
     * Time and Date of document last modification. Use it, if you have two
     * document with equal time creation but wrong context, to choise last
     * document version.
     */
    private Date lastModificationTime = new Date();

    /**
     * Getter for UUID
     *
     * @return
     */
    public UUID getUUID() {
        return this.uuid;
    }

    /**
     * Setter for UUID
     *
     * @param uuid
     */
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Getter for plugin UUID
     *
     * @return
     */
    public UUID getPluginUUID() {
        return this.plugin;
    }

    /**
     * Setter for plugin UUID
     *
     * @param pluginName
     */
    public void setPluginUUID(UUID pluginName) {
        this.plugin = pluginName;
    }

    /**
     * Getter for creation time
     *
     * @return
     */
    public Date getCreationTime() {
        return this.creationTime;
    }

    /**
     * Setter for creation time
     *
     * @param date
     */
    public void setCreationTime(Date date) {
        this.creationTime = date;
    }

    /**
     * Getter for last modification time
     *
     * @return
     */
    public Date getLastModificationTime() {
        return this.lastModificationTime;
    }

    /**
     * Setter for last modification time
     *
     * @param date
     */
    public void setLastModificationTime(Date date) {
        this.lastModificationTime = date;
    }

    @Override
    public void parseObjectFromXML(Element element) throws ObjectStructureException {
        this.parseCurrentObjectField(element);
    }

    @Override
    protected void parseCurrentObjectField(Element element) throws ObjectStructureException {
        NodeList list = element.getElementsByTagName("documentUUID");
        if (list.item(0) != null) {
            this.setUUID(UUID.fromString(list.item(0).getTextContent()));
        } else {
            this.setUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        }
        list = element.getElementsByTagName("pluginUUID");
        if (list.item(0) != null) {
            this.setPluginUUID(UUID.fromString(list.item(0).getTextContent()));
        } else {
            this.setPluginUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        }
        list = element.getElementsByTagName("creationTime");
        if (list.item(0) != null) {
            this.setCreationTime(new Date(Long.parseLong(list.item(0).getTextContent())));

        } else {
            this.setCreationTime(new Date());
        }
        list = element.getElementsByTagName("lastModificationTime");
        if (list.item(0) != null) {
            this.setLastModificationTime(new Date(Long.parseLong(list.item(0).getTextContent())));
        } else {
            this.setLastModificationTime(new Date());
        }
    }

    @Override
    public void printObjectToXML(FileModuleWriteThreadInterface fileModule) {
        this.printCurrentObjectField(fileModule);
    }

    @Override
    protected void printCurrentObjectField(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<head>\n"
                + "<documentUUID>" + this.uuid.toString() + "</documentUUID>\n"
                + "<pluginUUID>" + this.plugin.toString() + "</pluginUUID>\n"
                + "<creationTime>" + this.creationTime.getTime() + "</creationTime>\n"
                + "<lastModificationTime>" + this.lastModificationTime.getTime() + "</lastModificationTime>\n"
                + "</head>\n");
    }

}
