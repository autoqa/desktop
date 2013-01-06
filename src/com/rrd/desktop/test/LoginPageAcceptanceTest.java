/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 12/27/12
 * Time: 3:23 PM
 */

package com.rrd.desktop.test;

import com.rrd.desktop.core.AcceptanceTestBase;
import com.rrd.desktop.page.LoginPageDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageAcceptanceTest extends AcceptanceTestBase {
    private LoginPageDriver loginPage;

    @BeforeClass
    public void setUp() {
        System.out.println("LoginPageAcceptanceTest.setUpPageDriver BeforeClass");
        loginPage = new LoginPageDriver(getFlashApp());
        loginPage.navigateToPage();
    }

    @Test
    public void loginTest() {
        loginPage.enterUsername("admin");
        loginPage.enterPassword("admin");
        loginPage.clickSubmit();
    }


}
