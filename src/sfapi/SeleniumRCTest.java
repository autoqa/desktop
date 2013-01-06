/**
 * Created with IntelliJ IDEA.
 * User: robertc
 * Date: 12/18/12
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
package sfapi;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.Selenium;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SeleniumRCTest {

    private final static String BASE_URL = "https://app.devlinux.nimblefish.com/";
    private final static String PAGE = "desktop//desktop.html";
    private Selenium selenium;
    //private FlashSelenium flashApp;
    private FlexUISelenium flexUITester;

    @BeforeClass
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*chrome",BASE_URL);
        selenium.start();
        //flashApp = new FlashSelenium(selenium, "desktop");
        selenium.open(PAGE);
        flexUITester = new FlexUISelenium(selenium, "desktop");
        flexUITester.waitUntilLoaded();
    }

    @AfterClass
    public void tearDown() throws Exception {
        selenium.stop();
    }

    @Test
    public void verifyPageTitle() throws Exception {
        //assertEquals("Nimblefish N7 Desktop", selenium.getTitle());
        //assertEquals(100, flashApp.PercentLoaded());
        //System.out.println(flashApp.getClass());
        //System.out.println(flashApp.toString());


        Thread.sleep(7000);
        flexUITester.type("admin").at("userNameTextInput");
        flexUITester.type("admin").at("passwordTextInput");
        flexUITester.click("submitButton");
        //flexUITester.readFrom("")
        //flexUITester.
        //assertEquals("x", flexUITester.toString());


        //flexUITester.type("userNameTextInput", "admin");
        //flexUITester.type("passwordTextInput", "admin");
        //flexUITester.click("submitButton");
    }

}
