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
import ru.sondar.documentmodel.objectmodel.SDCheckBox;
import ru.sondar.documentmodel.objectmodel.SDDate;
import ru.sondar.documentmodel.objectmodel.SDEditText;
import ru.sondar.documentmodel.serializer.XMLSerializer;

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
        (new XMLSerializer()).parseSequence(sequence, TestVariables.getRootElementByFile("SequenceTest", "sequence_1.txt"));
        sequence.enumirateSequence(0);
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
        documentTemp.loadDocument(testFolder + "SequenceTest\\document_1.txt");
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

    /**
     * Test of saveDocument method, of class SDDocument.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSaveDocument2() throws Exception {
        SDDocument documentTemp = new SDDocument();
        documentTemp.loadDocument(testFolder + "SequenceTest\\document_2.txt");
        assertEquals(documentTemp.sequence.getSequenceSize(), 4);
        assertEquals(((SDDate)documentTemp.sequence.getXMLObject(1)).getCalendar().getTime().getTime(), 123456789);
        assertEquals(((SDCheckBox)documentTemp.sequence.getXMLObject(2)).getChecked(), true);
        assertEquals(((SDEditText)documentTemp.sequence.getXMLObject(3)).getText(), "test");
        assertEquals(((SDEditText)documentTemp.sequence.getXMLObject(3)).getTextLength(), 10);
        ((SDCheckBox)documentTemp.sequence.getXMLObject(2)).setChecked(false);
        ((SDEditText)documentTemp.sequence.getXMLObject(3)).setTextLength(20);
        ((SDEditText)documentTemp.sequence.getXMLObject(3)).setText("Hello");
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "SequenceTest\\document_temp2.txt", false);
        documentTemp.saveDocument(fileModule);
        fileModule.close();
        SDDocument documentTemp_check = new SDDocument();
        documentTemp_check.loadDocument(testFolder + "SequenceTest\\document_temp2.txt");
        assertEquals(((SDDate)documentTemp_check.sequence.getXMLObject(1)).getCalendar().getTime().getTime(), 123456789);
        assertEquals(((SDCheckBox)documentTemp_check.sequence.getXMLObject(2)).getChecked(), false);
        assertEquals(((SDEditText)documentTemp_check.sequence.getXMLObject(3)).getText(), "Hello");
        assertEquals(((SDEditText)documentTemp_check.sequence.getXMLObject(3)).getTextLength(), 20);
    }

}
