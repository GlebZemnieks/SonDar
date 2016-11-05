package ru.sondar.sdserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.string.FileModule;
import ru.sondar.documentmodel.SDDocument;

/**
 * Server controller. All method for view and manager clients. Default bind on
 * (/)
 *
 * @author GlebZemnieks
 */
@RestController
public class ServerController {

    public static SonDarServer server;

    //!!!  Client View part start
    /**
     * Get document list of client (Work, Done, Example directories)
     *
     * @param value
     * @return
     */
    @RequestMapping(value = "/sendDocumentList", method = RequestMethod.POST)
    public String getDocumentListByClient(@RequestParam(value = "headPartXML12List", required = false, defaultValue = "") String value) {
        return "OK";
    }

    /**
     * Send document in xml by id
     *
     * @param documentId
     * @return
     */
    @RequestMapping(value = "/getDocument", method = RequestMethod.GET)
    public String sendDocumentToClient(@RequestParam(value = "documentId", required = false, defaultValue = "") String documentId) {
        return "OK";
    }

    /**
     * Get document by client
     *
     * @param documentXML
     * @return
     */
    @RequestMapping(value = "/sendDocument", method = RequestMethod.POST)
    public String getDocumentFromClient(@RequestParam(value = "documentXML", required = false, defaultValue = "") String documentXML) {
        return "OK";
    }

    /**
     * Send client status and list of id which waiting synchronization
     *
     * @return
     */
    @RequestMapping(value = "/synchronizingDocumentList", method = RequestMethod.GET)
    public String synchronizingDocumentList() {
        return "OK";
    }

    //!!! Client View part done
    //!!! Client manager part start
    /**
     * Get document list in export directory
     *
     * @return
     */
    @RequestMapping(value = "/getExportDocumentList", method = RequestMethod.GET)
    public String getExportDocumentList() {
        return "OK";
    }

    /**
     * Get document list in import directory
     *
     * @return
     */
    @RequestMapping(value = "/getImportDocumentList", method = RequestMethod.GET)
    public String getImportDocumentList() {
        return "OK";
    }

    /**
     * Get plug-in list
     *
     * @return
     */
    @RequestMapping(value = "/getPluginList", method = RequestMethod.GET)
    public String getPluginList() {
        return "OK";
    }

    /**
     * Get Client list
     *
     * @return
     */
    @RequestMapping(value = "/getClientList", method = RequestMethod.GET)
    public String getClientList() {
        return "OK";
    }

    /**
     * Get key list from plug-in db file
     *
     * @param pluginId
     * @return
     */
    @RequestMapping(value = "/getKeyList", method = RequestMethod.GET)
    public String getKeyList(@RequestParam(value = "pluginId", required = false, defaultValue = "") String pluginId) {
        return "OK";
    }

    /**
     * Assign document for sending on client in next synchronization.
     *
     * @param clientId
     * @param documentId
     * @return
     */
    @RequestMapping(value = "/assignDocumentToClient", method = RequestMethod.POST)
    public String assignDocumentToClient(@RequestParam(value = "clientId", required = false, defaultValue = "") String clientId,
            @RequestParam(value = "documentId", required = false, defaultValue = "") String documentId) {
        return "OK";
    }

    /**
     * Assign document for sending in plug-in data base now.
     *
     * @param pluginId
     * @param documentId
     * @return
     */
    @RequestMapping(value = "/assignDocumentToPugin", method = RequestMethod.POST)
    public String assignDocumentToPlugin(@RequestParam(value = "pluginId", required = false, defaultValue = "") String pluginId,
            @RequestParam(value = "documentId", required = false, defaultValue = "") String documentId) {
        return "OK";
    }

    /**
     * Get document from plug-in db by key value
     *
     * @param pluginId
     * @param keyValue
     * @return
     */
    @RequestMapping(value = "/getDocumentFromPlugin", method = RequestMethod.POST)
    public String getDocumentFromPlugin(@RequestParam(value = "pluginId", required = false, defaultValue = "") String pluginId,
            @RequestParam(value = "keyValue", required = false, defaultValue = "") String keyValue) {
        return "OK";
    }

    private String getDocumentXML(SDDocument document) {
        FileModuleInterface fileModule = new FileModule();
        document.saveDocument(fileModule.getWriteThread(""));
        return fileModule.getReadThread("").read();
    }

}
