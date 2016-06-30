package ru.sondar.documentmodel;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.exception.DocumentAlreadyInitException;
import ru.sondar.documentmodel.exception.DocumentNotInitException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.objectmodel.SDHeadPart;
import ru.sondar.documentmodel.objectmodel.SDLogPart;
import ru.sondar.documentmodel.objectmodel.WordsBase;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

/**
 * Document model
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
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
     * WordsBase object of this document
     */
    protected WordsBase wordsBase;

    /**
     * Getter for sequence object
     *
     * @return
     */
    public SDSequenceObject getSequense() {
        return sequence;
    }

    /**
     * Setter for sequence object. Throw
     * {@link ru.sondar.documentmodel.exception.DocumentAlreadyInitException}
     * (RunTime) if sequence already exist
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
     * Setter for head object. Throw
     * {@link ru.sondar.documentmodel.exception.DocumentAlreadyInitException}
     * (RunTime) if head already exist
     *
     * @param head
     * @throws DocumentAlreadyInitException
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
     * @return head object of current document
     */
    public SDHeadPart getHeadPart() {
        return this.headPart;
    }

    /**
     * Setter for log object. Throw
     * {@link ru.sondar.documentmodel.exception.DocumentAlreadyInitException}
     * (RunTime) if log already exist
     *
     * @param log
     * @throws DocumentAlreadyInitException
     */
    public void setLogPart(SDLogPart log) {
        if (this.logPart != null) {
            throw new DocumentAlreadyInitException("Trying to reinitialize document part \"log\". Denied!");
        }
        this.logPart = log;
    }

    /**
     * Setter for dependency object. Throw
     * {@link ru.sondar.documentmodel.exception.DocumentAlreadyInitException}
     * (RunTime) if dependency already exist
     *
     * @param dependency
     * @throws DocumentAlreadyInitException
     */
    public void setDependencyPart(DependencyPart dependency) {
        if (this.dependency != null) {
            throw new DocumentAlreadyInitException("Trying to reinitialize document part \"dependency\". Denied!");
        }
        this.dependency = dependency;
    }

    /**
     * Getter for dependency part object. If head is null - return
     * <code>null</code>
     *
     * @return dependency object of current document
     */
    public DependencyPart getDependencyPart() {
        return this.dependency;
    }

    /**
     * Getter for words base part object. If words base is null - return
     * <code>null</code>
     *
     * @return words base object of current document
     */
    public WordsBase getWordsBasePart() {
        return this.wordsBase;
    }

    /**
     * Setter for words base object. Throw
     * {@link ru.sondar.documentmodel.exception.DocumentAlreadyInitException}
     * (RunTime) if words base already exist
     *
     * @param wordsBase
     * @throws DocumentAlreadyInitException
     */
    public void setWordsBasePart(WordsBase wordsBase) {
        if (this.wordsBase != null) {
            throw new DocumentAlreadyInitException("Trying to reinitialize document part \"wordsBase\". Denied!");
        }
        this.wordsBase = wordsBase;
    }

    /**
     * Load document from file by name
     *
     * @param fileName
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ObjectStructureException
     * @throws DocumentAlreadyInitException Throw when some of part document
     * already initializing.
     */
    public void loadDocument(String fileName) throws SAXException, IOException, ParserConfigurationException, ObjectStructureException {
        this.loadDocument(new SDDOMParser(fileName));
    }

    /**
     * Load document from file by parser object. Used default parser
     * {@link ru.sondar.documentmodel.SDDOMParser}
     *
     * @param parser
     * @throws ObjectStructureException
     * @throws DocumentAlreadyInitException Throw when some of part document
     * already initializing.
     */
    public void loadDocument(SDDOMParser parser) throws ObjectStructureException {
        this.loadDocument(parser, new SDSequenceObject());
    }

    /**
     * Load document from file by parser object with customer sequence format.
     *
     * @param parser
     * @param sequence
     * @throws ObjectStructureException Throw when document structure cant be
     * read
     * @throws DocumentAlreadyInitException Throw when some of part document
     * already initializing.
     */
    public void loadDocument(SDDOMParser parser, SDSequenceObject sequence) throws ObjectStructureException {
        if (this.sequence != null || this.headPart != null
                || this.logPart != null || this.dependency != null
                || this.wordsBase != null) {
            throw new DocumentAlreadyInitException("Trying to reload document. Denied!");
        }
        headPart = new SDHeadPart();
        parser.getHeadPart(headPart);
        wordsBase = new WordsBase();
        parser.getWordsBasePart(wordsBase);
        this.sequence = sequence;
        parser.getSequence(sequence);
        dependency = new DependencyPart();
        parser.getDependencyPart(dependency);
        logPart = new SDLogPart();
        parser.getLogPart(logPart);
    }

    /**
     * Save document to thread in format
     * {@link ru.sondar.core.filemodule.FileModuleWriteThreadInterface}. Throw
     * {@link ru.sondar.documentmodel.exception.DocumentNotInitException} if
     * some part of document not initialized.
     *
     * @param fileModule
     */
    public void saveDocument(FileModuleWriteThreadInterface fileModule) {
        if (this.sequence == null || this.headPart == null
                || this.logPart == null || this.dependency == null
                || this.wordsBase == null) {
            throw new DocumentNotInitException("head : " + this.headPart + " : sequence : " + this.sequence + " : dependency : " + this.dependency + " : log : " + this.logPart + " : wordsBase : " + this.wordsBase + " ;");
        }
        fileModule.write("<Document>\n");
        this.headPart.printObjectToXML(fileModule);
        this.wordsBase.printObjectToXML(fileModule);
        this.sequence.printSequence(fileModule);
        this.dependency.printObjectToXML(fileModule);
        this.logPart.printObjectToXML(fileModule);
        fileModule.write("</Document>\n");
    }

}
