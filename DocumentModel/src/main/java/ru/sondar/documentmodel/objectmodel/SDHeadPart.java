package ru.sondar.documentmodel.objectmodel;

import java.util.Date;
import java.util.UUID;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.logger.Logger;
import ru.sondar.core.parser.exception.ObjectStructureException;

/**
 * SDHeadPart object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SDHeadPart extends SDMainObject {

    /**
     * Tag for print and parse dataList field
     */
    public static String Tag_MainObject = "head";

    /**
     * UUID for document. Set position in DataBase. Set
     * 00000000-0000-0000-0000-000000000000 if document new and have not
     * protection in DataBase.
     */
    private UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");

    /**
     * Plug-in UUID. Used in DocumentManager for plug-in identification.
     */
    private UUID plugin = UUID.fromString("00000000-0000-0000-0000-000000000000");

    /**
     * User Friendly document name. Use for display document for user
     */
    private String documentName = uuid.toString();

    /**
     * Time and Date of document creation. Using if you wont to know if this
     * document are equals;
     */
    private Date creationTime = new Date();

    /**
     * Time and Date of document last modification. Use it, if you have two
     * document with equal time creation but wrong context, to choice last
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
     * Getter for plug-in UUID
     *
     * @return
     */
    public UUID getPluginUUID() {
        return this.plugin;
    }

    /**
     * Setter for plug-in UUID
     *
     * @param pluginName
     */
    public void setPluginUUID(UUID pluginName) {
        this.plugin = pluginName;
    }

    /**
     * Getter for documentName field
     *
     * @return
     */
    public String getDocumentName() {
        return this.documentName;
    }

    /**
     * Setter for documentName field
     *
     * @param newName
     */
    public void setDocumentName(String newName) {
        this.documentName = newName;
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
    public void parseCurrentObjectField(Element element) throws ObjectStructureException {
        NodeList list = element.getElementsByTagName("documentUUID");
        if (list.item(0) != null) {
            this.setUUID(UUID.fromString(list.item(0).getTextContent()));
        } else {
            Logger.Log("parser", "Missing \"documentUUID\" field in head object. Set default value \"00000000-0000-...\"");
            this.setUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        }
        list = element.getElementsByTagName("pluginUUID");
        if (list.item(0) != null) {
            this.setPluginUUID(UUID.fromString(list.item(0).getTextContent()));
        } else {
            Logger.Log("parser", "Missing \"pluginUUID\" field in head object. Set default value \"00000000-0000-...\"");
            this.setPluginUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        }
        list = element.getElementsByTagName("documentName");
        if (list.item(0) != null) {
            this.setDocumentName(list.item(0).getTextContent());
        } else {
            Logger.Log("parser", "Missing \"documentName\" field in head object. Set default value \"" + this.uuid.toString() + "\"");
            this.setDocumentName(this.uuid.toString());
        }
        list = element.getElementsByTagName("creationTime");
        if (list.item(0) != null) {
            this.setCreationTime(new Date(Long.parseLong(list.item(0).getTextContent())));

        } else {
            Logger.Log("parser", "Missing \"creationTime\" field in head object. Set current date.");
            this.setCreationTime(new Date());
        }
        list = element.getElementsByTagName("lastModificationTime");
        if (list.item(0) != null) {
            this.setLastModificationTime(new Date(Long.parseLong(list.item(0).getTextContent())));
        } else {
            Logger.Log("parser", "Missing \"lastModificationTime\" field in head object. Set current date.");
            this.setLastModificationTime(new Date());
        }
    }

    @Override
    public void printObjectToXML(FileModuleWriteThreadInterface fileModule) {
        Logger.Log("SDHeadPart::printObjectToXML", "Write headPart : " + this.toString());
        this.printCurrentObjectField(fileModule);
    }

    @Override
    public void printCurrentObjectField(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + Tag_MainObject + ">\n"
                + "<documentUUID>" + this.uuid.toString() + "</documentUUID>\n"
                + "<pluginUUID>" + this.plugin.toString() + "</pluginUUID>\n"
                + "<documentName>" + this.documentName + "</documentName>\n"
                + "<creationTime>" + this.creationTime.getTime() + "</creationTime>\n"
                + "<lastModificationTime>" + this.lastModificationTime.getTime() + "</lastModificationTime>\n"
                + "</" + Tag_MainObject + ">\n");
    }

    @Override
    public String toString() {
        return "Id : " + this.getUUID().toString()
                + " : Name : " + this.documentName
                + " : plugin : " + this.getPluginUUID().toString()
                + "\n";
    }

}
