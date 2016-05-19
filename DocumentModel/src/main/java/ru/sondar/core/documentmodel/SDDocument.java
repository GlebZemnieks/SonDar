package ru.sondar.core.documentmodel;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.core.documentmodel.exception.DocumentAlreadyInitException;
import ru.sondar.core.documentmodel.exception.DocumentNotInitException;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.objectmodel.SDHeadPart;
import ru.sondar.core.objectmodel.SDLogPart;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

/**
 * Document model
 *
 * @author GlebZemnieks
 */
public class SDDocument {

    /**
     * Sequence object of with document
     */
    protected SDSequenceObject sequence;
    /**
     * Head object of with document
     */
    protected SDHeadPart headPart;
    /**
     * Log object of with document
     */
    protected SDLogPart logPart;

    /**
     * Getter for sequence object
     *
     * @return
     */
    public SDSequenceObject getSequense() {
        return sequence;
    }

    /**
     * Setter for sequence object.Throw DocumentAlreadyInitException if sequence
     * already exist
     *
     * @param sequence
     * @throws DocumentAlreadyInitException
     */
    public void setSequence(SDSequenceObject sequence) {
        if (this.sequence != null) {
            throw new DocumentAlreadyInitException("Trying to reinitialize document part \"sequence\". Denied!");
        }
        this.sequence = sequence;
    }

    /**
     * Setter for head object. Throw DocumentAlreadyInitException if head
     * already exist
     *
     * @param head
     */
    public void setHeadPart(SDHeadPart head) {
        if (this.headPart != null) {
            throw new DocumentAlreadyInitException("Trying to reinitialize document part \"head\". Denied!");
        }
        this.headPart = head;
    }

    /**
     * Setter for log object. Throw DocumentAlreadyInitException if log already
     * exist
     *
     * @param log
     */
    public void setLogPart(SDLogPart log) {
        if (this.logPart != null) {
            throw new DocumentAlreadyInitException("Trying to reinitialize document part \"log\". Denied!");
        }
        this.logPart = log;
    }

    /**
     * Load document from file by name
     *
     * @param fileName
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ObjectStructureException
     */
    public void loadDocument(String fileName) throws SAXException, IOException, ParserConfigurationException, ObjectStructureException {
        this.loadDocument(new SDDOMParser(fileName));
    }

    /**
     * Load document from file by parser object
     *
     * @param parser
     * @throws ObjectStructureException
     */
    public void loadDocument(SDDOMParser parser) throws ObjectStructureException {
        this.loadDocument(parser, new SDSequenceObject());
    }

    /**
     * Load document from file by parser object with customer sequence format
     *
     * @param parser
     * @param sequence
     * @throws ObjectStructureException
     */
    public void loadDocument(SDDOMParser parser, SDSequenceObject sequence) throws ObjectStructureException {
        if (this.sequence != null || this.headPart != null || this.logPart != null) {
            throw new DocumentAlreadyInitException("Trying to reload document. Denied!");
        }
        headPart = new SDHeadPart();
        parser.getHeadPart(headPart);
        this.sequence = sequence;
        parser.getSequence(sequence);
        logPart = new SDLogPart();
        parser.getLogPart(logPart);
    }

    public void saveDocument(FileModuleWriteThread fileModule) {
        if (this.sequence == null || this.headPart == null || this.logPart == null) {
            throw new DocumentNotInitException("");
        }
        fileModule.write("<Document>\n");
        this.headPart.printObjectToXML(fileModule);
        this.sequence.printSequence(fileModule);
        this.logPart.printObjectToXML(fileModule);
        fileModule.write("</Document>\n");
    }

}
