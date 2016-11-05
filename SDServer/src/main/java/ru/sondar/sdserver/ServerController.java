package ru.sondar.sdserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.string.FileModule;
import ru.sondar.documentmodel.SDDocument;

/**
 *
 * @author GlebZemnieks
 */
@RestController
public class ServerController {

    public static SonDarServer server;

    @RequestMapping("/getExportDocumentListById")
    public String getExportDocumentListById(@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
        return getDocumentXML(server.documentManager.exportFileList.get(id));
    }

    private String getDocumentXML(SDDocument document) {
        FileModuleInterface fileModule = new FileModule();
        document.saveDocument(fileModule.getWriteThread(""));
        return fileModule.getReadThread("").read();
    }

}
