/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.documentmodel.documentfactory;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.objectmodel.SDLogPart;
import ru.sondar.documentmodel.objectmodel.SDWordsBasePart;
import ru.sondar.documentmodel.objectmodel.WordBase;

/**
 *
 * @author GlebZemnieks
 */
public class DocumentForClientTest {
    
    public static void main(String... args) throws SAXException, IOException, ParserConfigurationException, ObjectStructureException{
        generateTestSet1();
        generateTestSet2();
    }
    
    public static void generateTestSet1() throws SAXException, IOException, ParserConfigurationException, ObjectStructureException{
        String set1Folder = "E:\\set1\\";
        
        SDDocument document1 = new SDDocument();    

        document1.setHeadPart(SDObjectFactory.getHeadPart());

        SDWordsBasePart basePart = new SDWordsBasePart();
        WordBase base1 = new WordBase();
        base1.add("item1");
        base1.add("item2");
        basePart.addNewBase("test", base1);
        document1.setWordsBasePart(basePart);
        
        SDSequenceObject sequence = new SDSequenceObject();
        sequence.addXMLObject(SDObjectFactory.getCheckBox("CheckTEST1", true));
        sequence.addXMLObject(SDObjectFactory.getDate());
        sequence.addXMLObject(SDObjectFactory.getEndln());     
        SDSequenceObject sequence1 = new SDSequenceObject();
        sequence1.addXMLObject(SDObjectFactory.getCheckBox("CheckTEST2", false));
        sequence1.addXMLObject(SDObjectFactory.getEditText("test text(20)", 20));
        sequence.addXMLObject(sequence1);     
        sequence.addXMLObject(SDObjectFactory.getText("textTEST1 : "));
        SDSequenceObject sequence2 = new SDSequenceObject();
        sequence1.addXMLObject(SDObjectFactory.getEditText("test", 10));
        sequence2.addXMLObject(SDObjectFactory.getSpinner(basePart, "test", 0));
        sequence.addXMLObject(sequence2);     
        sequence.addXMLObject(SDObjectFactory.getText("testTEST2 : "));
        sequence.addXMLObject(SDObjectFactory.getCheckBox("checkTEST2", false));
        sequence.addXMLObject(SDObjectFactory.getSpinner(basePart, "test", 1));
        sequence.addXMLObject(SDObjectFactory.getEndln()); 
        sequence.addXMLObject(SDObjectFactory.getDate());
        sequence.enumirateSequence(0);
        document1.setSequence(sequence);
        
        document1.setDependencyPart(new DependencyPart());
        
        document1.setLogPart(new SDLogPart());
        
        FileModuleWriteThread fileModule = new FileModuleWriteThread(set1Folder + "doc1.xml", false);
        document1.saveDocument(fileModule);
        fileModule.close();
        
        //For check document is valid - loading it!
        SDDocument testDocument = new SDDocument();
        testDocument.loadDocument(set1Folder + "doc1.xml");
    }
    
    public static void generateTestSet2() throws SAXException, IOException, ParserConfigurationException, ObjectStructureException{
        String set1Folder = "E:\\set2\\";
        
        SDDocument document1 = new SDDocument();    

        document1.setHeadPart(SDObjectFactory.getHeadPart());

        SDWordsBasePart basePart = new SDWordsBasePart();
        WordBase base1 = new WordBase();
        base1.add("item1");
        base1.add("item2");
        basePart.addNewBase("test", base1);
        document1.setWordsBasePart(basePart);
        
        SDSequenceObject sequence = new SDSequenceObject();
        for (int i = 0; i < 200; i++) {
            sequence.addXMLObject(SDObjectFactory.getText("Text : " + i));
            sequence.addXMLObject(SDObjectFactory.getEndln());
        }
        sequence.enumirateSequence(0);
        document1.setSequence(sequence);
        
        document1.setDependencyPart(new DependencyPart());
        
        document1.setLogPart(new SDLogPart());
        
        FileModuleWriteThread fileModule = new FileModuleWriteThread(set1Folder + "doc1.xml", false);
        document1.saveDocument(fileModule);
        fileModule.close();
        
        //For check document is valid - loading it!
        SDDocument testDocument = new SDDocument();
        testDocument.loadDocument(set1Folder + "doc1.xml");
    }
}
