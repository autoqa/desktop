package com.rrd.desktop.call;

/**
 * RR Donnelley - Nimblefish
 * User: robertc
 * Date: 12/27/12
 * Time: 11:44 AM
 */
public interface FlashCall {

    boolean attemptCall();

    String getErrorMessage();

}
