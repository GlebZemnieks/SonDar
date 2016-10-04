package ru.sondar.client.documentmodel.android;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when called object ID incorrect
 * @author GlebZemnieks
 */
public class XMLSequenceIndexOverflowException extends SonDarRuntimeException {
    public XMLSequenceIndexOverflowException(){
    }
    public XMLSequenceIndexOverflowException(String msg){
        super(msg);
    }
}
