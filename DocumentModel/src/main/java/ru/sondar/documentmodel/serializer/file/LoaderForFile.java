package ru.sondar.documentmodel.serializer.file;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sondar.core.parser.DOMParser;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.objectmodel.SDHeadPart;
import ru.sondar.documentmodel.objectmodel.SDLogPart;
import ru.sondar.documentmodel.objectmodel.SDWordsBasePart;
import ru.sondar.documentmodel.serializer.DocumentLoader;
import ru.sondar.documentmodel.serializer.DocumentSerializer;

/**
 * Default realization for DocumentLoader. Read all part of document from one
 * file. For read used DOMParser class as parent.
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class LoaderForFile extends DOMParser
        implements DocumentLoader {

    public LoaderForFile() {
        super();
    }

    public LoaderForFile(String fileName) throws SAXException, IOException, ParserConfigurationException {
        super(fileName);
    }

    @Override
    public void getSequence(DocumentSerializer serializer, SDSequenceObject xmlSequence) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName(SDSequenceObject.Sequence_MainTag);
        Element sequence = ((Element) nList.item(0));
        serializer.parseSequence(xmlSequence, sequence);
    }

    @Override
    public void getHeadPart(DocumentSerializer serializer, SDHeadPart headPart) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName(SDHeadPart.Tag_MainObject);
        Element head = ((Element) nList.item(0));
        serializer.parseHeadPart(headPart, head);
    }

    @Override
    public void getWordsBasePart(DocumentSerializer serializer, SDWordsBasePart wordsBase) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName(SDWordsBasePart.Tag_MainObject);
        Element words = ((Element) nList.item(0));
        serializer.parseWordsBasePart(wordsBase, words);
    }

    @Override
    public void getLogPart(DocumentSerializer serializer, SDLogPart logPart) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName(SDLogPart.Log_MainTag);
        Element log = ((Element) nList.item(0));
        serializer.parseLogPart(logPart, log);
    }

    @Override
    public void getDependencyPart(DocumentSerializer serializer, DependencyPart dependencyPart) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName(DependencyPart.Dependency_MainTag);
        Element dependency = ((Element) nList.item(0));
        serializer.parseDependencyPart(dependencyPart, dependency);
    }

}
