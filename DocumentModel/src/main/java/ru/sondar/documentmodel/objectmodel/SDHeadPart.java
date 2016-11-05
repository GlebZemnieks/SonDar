package ru.sondar.documentmodel.objectmodel;

import java.util.Date;
import java.util.UUID;

/**
 * SDHeadPart object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SDHeadPart {

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
    public String toString() {
        return "Id : " + this.getUUID().toString()
                + " : Name : " + this.documentName
                + " : plugin : " + this.getPluginUUID().toString()
                + "\n";
    }

}
