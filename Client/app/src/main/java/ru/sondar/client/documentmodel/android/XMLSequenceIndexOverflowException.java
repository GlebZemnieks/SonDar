package ru.sondar.client.documentmodel.android;

/**
 * Throw when called object ID incorrect
 * @author GlebZemnieks
 */
public class XMLSequenceIndexOverflowException extends RuntimeException {
    public XMLSequenceIndexOverflowException(){
    }
    public XMLSequenceIndexOverflowException(String msg){
        super(msg);
    }
}
