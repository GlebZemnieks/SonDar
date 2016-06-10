/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.core.documentmodel;

import com.sun.jndi.toolkit.ctx.HeadTail;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sondar.core.dependencymodel.DependencyPart;
import static ru.sondar.core.documentmodel.TestVariables.testFolder;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.objectmodel.SDHeadPart;
import ru.sondar.core.objectmodel.SDLogPart;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

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
        document.setSequence(sequence);
        DependencyPart dependency = new DependencyPart();
        dependency.addDependencyItem("test", 0);
        dependency.addDependencyItem("test2", 1);
        document.setDependencyPart(dependency);
        document.setLogPart(new SDLogPart());
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
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "SequenceTest\\document_temp2.txt");
        documentTemp.saveDocument(fileModule);
        fileModule.close();
    }

    /**
     * Test of saveDocument method, of class SDDocument.
     */
    @Test
    public void testSaveDocument() {
        FileModuleWriteThread fileModule = new FileModuleWriteThread(testFolder + "SequenceTest\\document_temp.txt");
        this.document.saveDocument(fileModule);
        fileModule.close();
    }

}
