package com.rrd.desktop.test;

/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 12/27/12
 * Time: 3:23 PM
 */

import com.rrd.desktop.core.AcceptanceTestBase;
import com.rrd.desktop.core.DataProviders;
import com.rrd.desktop.page.LoginPageDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageAcceptanceTest extends AcceptanceTestBase {
    private LoginPageDriver loginPage;
    //private MainPageDriver mainPage;

    @BeforeClass
    public void setUp() {
        System.out.println("LoginPageAcceptanceTest.setUpPageDriver BeforeClass");
        loginPage = new LoginPageDriver(getFlashApp());
        //mainPage = new MainPageDriver(getFlashApp());

        loginPage.navigateToPage();
    }

    @Test (dataProvider = "getLoginCredentials", dataProviderClass = DataProviders.class)
    public void loginTest(String userName, String password, String firstAndLastName) {
        loginPage.enterUsername(userName);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();
        loginPage.checkLoggedInUser(firstAndLastName);
    }
}
