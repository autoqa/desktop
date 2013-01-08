package com.rrd.desktop.core;

/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 1/8/13
 * Time: 10:36 AM
 */
public abstract class AbstractPage {
    private static final String ACTION_SUCCESS = "true";
    private static final long TIMEOUT_MILLIS = 10000L;

    private final FlexCommandProcessor flex;

    protected AbstractPage(final FlexCommandProcessor flex) {
        this.flex = flex;
    }

    protected FlexCommandProcessor getFlexCommandProcessor() {
        return flex;
    }
}
