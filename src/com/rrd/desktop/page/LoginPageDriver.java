/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 12/27/12
 * Time: 2:49 PM
 */

package com.rrd.desktop.page;

import com.rrd.desktop.core.FlexSelenium;
import com.rrd.desktop.core.AbstractPageDriver;
import org.testng.Assert;

public class LoginPageDriver extends AbstractPageDriver {

    private static final String USERNAME_FIELD_ID = "userNameTextInput";
    private static final String PASSWORD_FIELD_ID = "passwordTextInput";
    private static final String SUBMIT_BUTTON_ID = "submitButton";
    private static final int APP_LOAD_TIMEOUT_MS = 120000;

    public LoginPageDriver(FlexSelenium flexSelenium) {
        super(flexSelenium);
    }

    public void enterUsername(final String username){
        enterText(USERNAME_FIELD_ID, username);
    }

    public void enterPassword(final String username){
        enterText(PASSWORD_FIELD_ID, username);
    }

    public void clickSubmit() {
        clickButton(SUBMIT_BUTTON_ID);
    }

    public void navigateToPage() {
        final long timeoutPoint = System.currentTimeMillis() + APP_LOAD_TIMEOUT_MS;
        while (timeoutPoint > System.currentTimeMillis()) {
            try {
                if (getFlexSelenium().isVisible(USERNAME_FIELD_ID)) {
                    return;
                }
                Thread.sleep(250);
            } catch (Exception e) {
                // Ignore this, we are just waiting for the app to load
            }
        }
        Assert.fail("Application did not load");
    }
}

