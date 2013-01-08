package sfapi;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class LoginLogoutWebDriver {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors;
    JavascriptExecutor js;
    FirefoxProfile profile;

    @Before
    public void setUp() throws Exception {
        File file = new File("/Users/robertc/_dev/FirefoxDefaultProfile");
        profile = new FirefoxProfile(file);
        driver = new FirefoxDriver(profile);  // driver = new ChromeDriver();  //
        baseUrl = "https://devlinux.nimblefish.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        verificationErrors = new StringBuffer();
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testLoginLogoutWebdriver() throws Exception {
        driver.get(baseUrl + "/desktop/desktop.html");
        js.executeScript("return window.document['desktop'].playFromSelenium('<UIEvent command=\"SelectText\" value=\"userNameTextInput\">   <arg value=\"0\"/>   <arg value=\"0\"/> </UIEvent>')\"");
        js.executeScript("return window.document['desktop'].playFromSelenium('<UIEvent command=\"Input\" value=\"userNameTextInput\"><arg value=\"admin\"/></UIEvent>')\"");
        // ERROR: Caught exception [unknown command [waitForFlexMonkey]]
        // ERROR: Caught exception [unknown command [waitForFlexMonkey]]
        // ERROR: Caught exception [unknown command [waitForFlexMonkey]]
        // ERROR: Caught exception [unknown command [waitForFlexMonkey]]
        // ERROR: Caught exception [unknown command [waitForFlexMonkey]]
        // ERROR: Caught exception [unknown command [waitForFlexMonkey]]
        // ERROR: Caught exception [unknown command [waitForFlexMonkey]]

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alert.getText();
        } finally {
            acceptNextAlert = true;
        }
    }
}




/*

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginLogoutWebDriver {
    private final WebDriver driver;

    public LoginLogoutWebDriver(WebDriver driver) {
        this.driver = driver;

        // Check that we're on the right page.
        if (!"Login".equals(driver.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the login page");
        }
    }

    public void click(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            Thread.sleep(500);
            js.executeScript("return window.document['desktop'].playFromSelenium('<UIEvent command=\"Click\" value=\""
                    + locator + "\"></UIEvent>', '')");
        } catch (WebDriverException e) {
            System.out.print(e);
        } catch (InterruptedException e) {
            System.out.print(e);
        }
    }

    public static void main(String args[]) {
        LoginLogoutWebDriver loginPage;
        WebDriver webDriver = new FirefoxDriver();

    }
}
*/