package ru.sondar.documentmodel.documentfactory;

import ru.sondar.documentmodel.SDDocument;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.documentfactory.structure.*;
import ru.sondar.documentmodel.objectmodel.*;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import static ru.sondar.documentmodel.documentfactory.structure.SDStructureFactory.addCompositeObjectList;
import static ru.sondar.documentmodel.documentfactory.structure.SDStructureFactory.enter;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;

/**
 *
 * @author GlebZemnieks
 */
public class SpaceForYourCreativity {

    public static String pathToFileCreate = "E:\\Development\\SonDar\\SDServer\\JUnitTest\\testserver1\\test1\\Demo.xml";

    public static String documentUUID = "00000000-0000-0000-0000-000000000003";
    public static String PluginUuid = "9cc11062-94cd-4432-8354-350754f52270";

    public static void main(String... args) {
        SDDocument document = new SDDocument();
        SDHeadPart head = SDObjectFactory.getHeadPart(PluginUuid, documentUUID);
        document.setHeadPart(head);
        SDWordsBasePart baseList = getWordsBase();
        document.setWordsBasePart(baseList);
        SDSequenceObject sequenceObject = getSequence(baseList);
        document.setSequence(sequenceObject);

        DependencyPart dependency = getDependencyPart(sequenceObject);
        document.setDependencyPart(dependency);

        SDLogPart log = new SDLogPart();
        document.setLogPart(log);
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(pathToFileCreate, false);
        document.saveDocument(fileModule);
        fileModule.close();
    }

    public static SDWordsBasePart getWordsBase() {
        SDWordsBasePart baseList = new SDWordsBasePart();
        return baseList;
    }

    public static DependencyPart getDependencyPart(SDSequenceObject sequenceObject) {
        DependencyPart dependency = new DependencyPart();
        dependency.addDependencyItemWithValidation(sequenceObject, "test", 0);
        dependency.addDependencyItemWithValidation(sequenceObject, "test2", 1);
        dependency.addDependencyItemWithValidation(sequenceObject, "test3", 2);
        return dependency;
    }

    public static SDSequenceObject getSequence(SDWordsBasePart baseList) {
        SDSequenceObject sequence = new SDSequenceObject();
        sequence.AddXMLObject(SDObjectFactory.getCheckBox("1 : ", true).setObjectName("test"));
        sequence.AddXMLObject(SDObjectFactory.getCheckBox("2 : ", true).setObjectName("test2"));
        sequence.AddXMLObject(SDObjectFactory.getCheckBox("3 : ", false).setObjectName("test3"));
        sequence.enumirateSequence(0);
        return sequence;
    }
}
