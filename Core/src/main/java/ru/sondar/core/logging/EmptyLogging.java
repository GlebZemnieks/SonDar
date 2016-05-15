/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.core.logging;

/**
 * Empty logger class to use by default on whatever system
 *
 * @author GlebZemnieks
 */
public class EmptyLogging implements LoggerInterface {

    @Override
    public void Log(String logTag, String logMsg) {
    }

    @Override
    public void Log(String logTag, String logMsg, Throwable errorStackTrace) {
    }

    @Override
    public void Log(int logingLevel, String logTag, String logMsg) {
    }

    @Override
    public void Log(int logingLevel, String logTag, String logMsg, Throwable errorStackTrace) {
    }
}
