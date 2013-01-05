/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 12/27/12
 * Time: 3:23 PM
 */

package com.rrd.desktop.test;

import com.rrd.desktop.page.LoginPageDriver;
//import org.testng.annotations.Test;
import org.junit.Test;

public class LoginPageAcceptanceTest extends AcceptanceTestBase{
    private LoginPageDriver login;

    @Test
    public void loginTest() {
        login.enterUsername("admin");
        login.enterPassword("admin");
    }
}
