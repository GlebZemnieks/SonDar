package ru.sondar.documentmodel;

import ru.sondar.documentmodel.serializer.file.LoaderForFile;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.logger.Logger;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.exception.*;
import ru.sondar.documentmodel.serializer.DocumentLoader;
import ru.sondar.documentmodel.objectmodel.*;
import ru.sondar.documentmodel.serializer.DocumentSaver;
import ru.sondar.documentmodel.serializer.DocumentSerializer;
import ru.sondar.documentmodel.serializer.XMLSerializer;
import ru.sondar.documentmodel.serializer.file.SaverForFile;

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
    protected SDWordsBasePart wordsBase;

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
        this.sequence.document = this;
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
     * Getter for dependency part object. If dependency is null - return
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
    public SDWordsBasePart getWordsBasePart() {
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
    public void setWordsBasePart(SDWordsBasePart wordsBase) {
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
    public void loadDocument(String fileName) throws SAXException, IOException,
            ParserConfigurationException, ObjectStructureException {
        this.loadDocument(new LoaderForFile(fileName));
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
    public void loadDocument(LoaderForFile parser) throws ObjectStructureException {
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
    public void loadDocument(LoaderForFile parser, SDSequenceObject sequence) throws ObjectStructureException {
        this.loadDocument(new XMLSerializer(), parser, sequence);
    }

    /**
     * Load document from file by parser object with customer sequence format.
     *
     * @param serializer
     * @param loader
     * @param sequence
     * @throws ObjectStructureException Throw when document structure cant be
     * read
     * @throws DocumentAlreadyInitException Throw when some of part document
     * already initializing.
     */
    public void loadDocument(DocumentSerializer serializer, DocumentLoader loader, SDSequenceObject sequence) throws ObjectStructureException {
        if (this.sequence != null || this.headPart != null
                || this.logPart != null || this.dependency != null
                || this.wordsBase != null) {
            throw new DocumentAlreadyInitException("Trying to reload document. Denied!");
        }
        Logger.Log("SDDocument::loadDocument", "Start");
        headPart = new SDHeadPart();
        loader.getHeadPart(serializer, headPart);
        wordsBase = new SDWordsBasePart();
        loader.getWordsBasePart(serializer, wordsBase);
        this.sequence = sequence;
        this.sequence.document = this;
        loader.getSequence(serializer, sequence);
        dependency = new DependencyPart();
        loader.getDependencyPart(serializer, dependency);
        logPart = new SDLogPart();
        loader.getLogPart(serializer, logPart);
        Logger.Log("SDDocument::loadDocument", "Finish");
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
        this.saveDocument(new XMLSerializer(), new SaverForFile(fileModule));
    }

    public void saveDocument(DocumentSerializer serializer, FileModuleWriteThreadInterface fileModule) {
        this.saveDocument(serializer, new SaverForFile(fileModule));
    }

    public void saveDocument(DocumentSerializer serializer, DocumentSaver saver) {
        if (this.sequence == null || this.headPart == null
                || this.logPart == null || this.dependency == null
                || this.wordsBase == null) {
            throw new DocumentNotInitException("head : " + this.headPart
                    + " : sequence : " + this.sequence
                    + " : dependency : " + this.dependency
                    + " : log : " + this.logPart
                    + " : wordsBase : " + this.wordsBase + " ;");
        }
        Logger.Log("SDDocument::saveDocument", "Document ready to writing");
        saver.getThreadForComment().write("<Document>\n");
        serializer.printHeadPart(headPart, saver.getThreadForHeadPart());
        serializer.printWordsBasePart(wordsBase, saver.getThreadForWordsBasePart());
        serializer.printSequence(sequence, saver.getThreadSequence());
        serializer.printDependencyPart(dependency, saver.getThreadForDependencyPart());
        serializer.printLogPart(logPart, saver.getThreadForLogPart());
        saver.getThreadForComment().write("</Document>\n");
        Logger.Log("SDDocument::saveDocument", "Document writted");
    }

    public static boolean isInFormat(String fileName) {
        //Now - validation using read document. No exception - Valid!
        //Later - after XMLScheme integration use it!
        try {
            SDDocument document = new SDDocument();
            document.loadDocument(fileName);
        } catch (SAXException | IOException | ParserConfigurationException | ObjectStructureException ex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String temp = "Document : " + this.headPart.toString();
        temp += "Sequence : " + this.sequence.toString();
        return temp;
    }
}
