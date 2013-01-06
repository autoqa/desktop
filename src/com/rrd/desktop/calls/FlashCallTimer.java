/**
 * RR Donnelley - Nimblefish
 * User: Rob Cruz (robertc)
 * Date: 12/27/12
 * Time: 11:53 AM
 */
package com.rrd.desktop.calls;

import org.testng.Assert;

public class FlashCallTimer {
    private static final long INCREMENT = 500L;

    private FlashCallTimer() {
        // static helper
    }

    public static void waitForCall(final long timeoutMillis, final FlashCall call) {
        final long endTime = System.currentTimeMillis() + timeoutMillis;
        do {
            if (call.attemptCall()) {
                return;
            }

            try {
                Thread.sleep(INCREMENT);
            }
            catch (InterruptedException e) {
                // We can ignore this.  The worst that can happen is that we
                // try the call more often than the defined interval
            }
        }
        while (System.currentTimeMillis() <= endTime);

        Assert.fail(String.format("Timed out after %dms: %s", timeoutMillis, call.getErrorMessage()));
    }

    public static void waitForNegatedCall(final long timeout, final FlashCall call) {
        waitForCall(timeout, new FlashCall() {
            @Override
            public boolean attemptCall() {
                return ! call.attemptCall();
            }

            @Override
            public String getErrorMessage() {
                return "NOT " + call.getErrorMessage();
            }
        });
    }
}
