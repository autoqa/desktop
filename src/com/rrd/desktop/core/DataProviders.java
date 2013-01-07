package com.rrd.desktop.core;

import org.testng.annotations.DataProvider;

/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 1/6/13
 * Time: 8:56 PM
 */
public class DataProviders {

    @DataProvider
    public static final Object[][] getLoginCredentials() {
        return new Object[][] {{"admin", "admin", "Admin Nimblefish"}};
        //return new Object[][] {{"admin", "admin"}, {"rob", "rob"}};
    }

    // more data providers here
}
