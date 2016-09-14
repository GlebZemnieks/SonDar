package ru.sondar.documentmodel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import static ru.sondar.documentmodel.TestVariables.testFolder;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.objectmodel.SDHeadPart;
import ru.sondar.documentmodel.objectmodel.SDLogPart;
import ru.sondar.documentmodel.objectmodel.SDWordsBasePart;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.objectmodel.SDMainObject;

/**
 *
 * @author GlebZemnieks
 */
public class SDDocumentTest {

    SDDocument document = new SDDocument();

    @Before
    public void setUp() throws ObjectStructureException {
        document = new SDDocument();
        document.setHeadPart(new SDHeadPart());
        SDSequenceObject sequence = new SDSequenceObject();
        sequence.parseSequence(TestVariables.getRootElementByFile("SequenceTest", "sequence_1.txt"));
        for (int i = 0; i < 6; i++) {
            SDMainObject obj = sequence.getXMLObject(i);
            assertEquals(i, obj.getID());
        }
        document.setSequence(sequence);
        DependencyPart dependency = new DependencyPart();
        dependency.addDependencyItem("test", 0);
        dependency.addDependencyItem("test2", 1);
        document.setDependencyPart(dependency);
        document.setLogPart(new SDLogPart());
        document.wordsBase = new SDWordsBasePart();
    }

    /**
     * Test of loadDocument method, of class SDDocument.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadDocument_String() throws Exception {
        SDDocument documentTemp = new SDDocument();
        documentTemp.loadDocument("E:\\Development\\SonDar\\DocumentModel\\JUnitTest\\SequenceTest\\document_1.txt");
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "SequenceTest\\document_temp2.txt", false);
        documentTemp.saveDocument(fileModule);
        fileModule.close();
    }

    /**
     * Test of saveDocument method, of class SDDocument.
     */
    @Test
    public void testSaveDocument() {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "SequenceTest\\document_temp.txt", false);
        this.document.saveDocument(fileModule);
        fileModule.close();
    }

}
