package ru.sondar.documentmodel.serializer.folder;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
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
 *
 * @author GlebZemnieks
 */
public class LoaderForFolder implements DocumentLoader {

    /**
     * Path to folder with collection of document part
     */
    private final String folderPath;

    public LoaderForFolder(String folderPath) {
        this.folderPath = folderPath;
    }

    @Override
    public void getHeadPart(DocumentSerializer serializer, SDHeadPart headPart) throws ObjectStructureException {
        DOMParser headParser = createDOMParser(folderPath, "head.xml");
        serializer.parseHeadPart(headPart, headParser.getRootElement());
    }

    @Override
    public void getWordsBasePart(DocumentSerializer serializer, SDWordsBasePart wordsBase) throws ObjectStructureException {
        DOMParser wordsBaseParser = createDOMParser(folderPath, "wordsbase.xml");
        serializer.parseWordsBasePart(wordsBase, wordsBaseParser.getRootElement());
    }

    @Override
    public void getSequence(DocumentSerializer serializer, SDSequenceObject xmlSequence) throws ObjectStructureException {
        DOMParser sequenceParser = createDOMParser(folderPath, "sequence.xml");
        serializer.parseSequence(xmlSequence, sequenceParser.getRootElement());
    }

    @Override
    public void getLogPart(DocumentSerializer serializer, SDLogPart logPart) throws ObjectStructureException {
        DOMParser logPartParser = createDOMParser(folderPath, "log.xml");
        serializer.parseLogPart(logPart, logPartParser.getRootElement());
    }

    @Override
    public void getDependencyPart(DocumentSerializer serializer, DependencyPart dependencyPart) throws ObjectStructureException {
        DOMParser dependencyPartParser = createDOMParser(folderPath, "dependency.xml");
        serializer.parseDependencyPart(dependencyPart, dependencyPartParser.getRootElement());

    }

    private DOMParser createDOMParser(String folderName, String fileName) throws ObjectStructureException {
        try {
            DOMParser parser = new DOMParser(folderPath + "/" + fileName);
            return parser;
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new ObjectStructureException("Troble with content \"" + fileName + "\" in \"" + folderPath + "\"", ex);
        }
    }

}
