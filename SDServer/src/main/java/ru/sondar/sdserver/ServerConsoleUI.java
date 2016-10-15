package ru.sondar.sdserver;

import java.io.File;

/**
 *
 * @author GlebZemnieks
 */
public class ServerConsoleUI {

    public static SonDarServer server;

    public static void main(String... args) {
        String globalPath = (new File("")).getAbsolutePath();
        server = new SonDarServer(globalPath);
        System.out.println(server.toString());
        /*
        //System.out.println("Plugin : " + .toString());
        System.out.println("START");
        printDB("9cc11062-94cd-4432-8354-350754f52270");
        System.out.println("Export list : \n");
        for (SDDocument document : server.importFileList) {
            System.out.println(document.getHeadPart().getUUID().toString() + "\n");
        }
        System.out.println("My plugin list : \n");
        for (SDDocument document : server.getDocumentListByPluginFilterFromImportList("9cc11062-94cd-4432-8354-350754f52270")) {
            System.out.println(document.getHeadPart().getUUID().toString() + "\n");
            server.exportDocument(document, DriverName.ExcelDriver);
        }
        System.out.println("DONE!");
        printDB("9cc11062-94cd-4432-8354-350754f52270");
         */
    }

    /*
    public static void printDB(String PluginUUID) throws DataBaseFileNotFoundException, RowNotFoundException {
        Plugin plugin = server.configuration.getPluginByUUID(UUID.fromString(PluginUUID));
        ArrayList<Object> keyList = plugin.getKeyList(plugin.getDriversList().get(0));
        System.out.println("list : \n " + keyList);
        ArrayList<SDDocument> documents = new ArrayList<>();
        int i = 0;
        for (Object key : keyList) {
            documents.add(plugin.importDocumentFromDB(DriverName.ExcelDriver, (String) key));
            System.out.println("Doc by key : " + key + "\n "
                    + documents.get(i));
            i++;
        }
    }
     */
}
