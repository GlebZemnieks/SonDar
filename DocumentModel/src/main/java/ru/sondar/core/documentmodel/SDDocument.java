package ru.sondar.core.documentmodel;

import java.io.IOException;
import java.util.UUID;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.core.dependencymodel.DependencyPart;
import ru.sondar.core.documentmodel.exception.DocumentAlreadyInitException;
import ru.sondar.core.documentmodel.exception.DocumentNotInitException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
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
     * Sequence object of this document
     */
    protected SDSequenceObject sequence;
    /**
     * Head object of this document
     */
    protected SDHeadPart headPart;
    /**
     * Log object of this document
     */
    protected SDLogPart logPart;
    /**
     * Dependency object of this document
     */
    protected DependencyPart dependency;

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
     * Getter for head object
     *
     * @return
     */
    public SDHeadPart getHeadPart() {
        return this.headPart;
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
     * Setter for dependency object. Throw DocumentAlreadyInitException if log
     * already exist
     *
     * @param dependency
     */
    public void setDependencyPart(DependencyPart dependency) {
        if (this.dependency != null) {
            throw new DocumentAlreadyInitException("Trying to reinitialize document part \"dependency\". Denied!");
        }
        this.dependency = dependency;
    }

    public DependencyPart getDependencyPart() {
        return this.dependency;
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
        if (this.sequence != null || this.headPart != null || this.logPart != null || this.dependency != null) {
            throw new DocumentAlreadyInitException("Trying to reload document. Denied!");
        }
        headPart = new SDHeadPart();
        parser.getHeadPart(headPart);
        this.sequence = sequence;
        parser.getSequence(sequence);
        dependency = new DependencyPart();
        parser.getDependencyPart(dependency);
        logPart = new SDLogPart();
        parser.getLogPart(logPart);
    }

    public void saveDocument(FileModuleWriteThreadInterface fileModule) {
        if (this.sequence == null || this.headPart == null || this.logPart == null || this.dependency == null) {
            throw new DocumentNotInitException("head : " + this.headPart + " : sequence : " + this.sequence + " : dependency : " + this.dependency + " : log : " + this.logPart + " ;");
        }
        fileModule.write("<Document>\n");
        this.headPart.printObjectToXML(fileModule);
        this.sequence.printSequence(fileModule);
        this.dependency.printObjectToXML(fileModule);
        this.logPart.printObjectToXML(fileModule);
        fileModule.write("</Document>\n");
    }

}
