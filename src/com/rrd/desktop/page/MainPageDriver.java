package com.rrd.desktop.page;

/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 1/6/13
 * Time: 9:48 PM
 */

import com.rrd.desktop.core.AbstractPageDriver;
import com.rrd.desktop.core.FlexSelenium;

public class MainPageDriver extends AbstractPageDriver {
    private static final String LOGGED_IN_USER_FIELD_ID = "_MenuBar_Main_Label1";   //loggedInUserName
    private static final String LOGOUT_LINK_ID = "logout";

    public MainPageDriver(FlexSelenium flexSelenium) {
        super(flexSelenium);
    }

    public void checkLoggedInUser(final String firstAndLastName){
        ensureWidgetHasText(LOGGED_IN_USER_FIELD_ID, "Hi, " + firstAndLastName + "!");
    }
}
