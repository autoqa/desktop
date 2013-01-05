package poc.desktop;

/**
 * Created with IntelliJ IDEA.
 * User: robertc
 * Date: 12/20/12
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;
import com.thoughtworks.selenium.Selenium;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.junit.Assert.fail;

public class Login {
    private Selenium selenium;
    private HttpCommandProcessor proc;

    @BeforeClass
    public void setUp() throws Exception {
        proc = new HttpCommandProcessor("localhost", 4444, "*chrome", "https://app.devlinux.nimblefish.com/desktop//desktop.html");
        selenium = new DefaultSelenium(proc);
        selenium.start();
    }

    @AfterClass
    public void tearDown() throws Exception {
        if (selenium != null) {
            selenium.stop();
            selenium = null;
        }
    }

    @Test
    public void myTestMethod() throws Exception {
        selenium.open("/desktop//desktop.html");
        for (int t = 0;; t++) {
            if (t >= 60) fail("timeout");
            try {
                if (proc.getBoolean("isFlexMonkey", new String[] {"<VerifyProperty value=\"userNameTextInput\" propertyString=\"automationName\" expectedValue=\"userNameTextInput\" propertyType=\"equals\"/>"})) break;
            } catch (Exception e) { }
            Thread.sleep(500);
        }

        for (int t = 0;; t++) {
            if (t >= 60) fail("timeout");
            try {
                if (proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"Input\" value=\"userNameTextInput\"><arg value=\"admin\"/></UIEvent>"})) break;
            } catch (Exception e) { }
            Thread.sleep(500);
        }

        for (int t = 0;; t++) {
            if (t >= 60) fail("timeout");
            try {
                if (proc.getBoolean("isFlexMonkey", new String[] {"<VerifyProperty value=\"passwordTextInput\" propertyString=\"automationName\" expectedValue=\"passwordTextInput\" propertyType=\"equals\"/>"})) break;
            } catch (Exception e) { }
            Thread.sleep(500);
        }

        for (int t = 0;; t++) {
            if (t >= 60) fail("timeout");
            try {
                if (proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"Input\" value=\"passwordTextInput\"><arg value=\"admin\"/></UIEvent>"})) break;
            } catch (Exception e) { }
            Thread.sleep(500);
        }

        for (int t = 0;; t++) {
            if (t >= 60) fail("timeout");
            try {
                if (proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"Click\" value=\"submitButton\"/>"})) break;
            } catch (Exception e) { }
            Thread.sleep(500);
        }

        for (int t = 0;; t++) {
            if (t >= 60) fail("timeout");
            try {
                if (proc.getBoolean("isFlexMonkey", new String[] {"<VerifyProperty value=\"logout\" propertyString=\"automationName\" expectedValue=\"logout\" propertyType=\"equals\"/>"})) break;
            } catch (Exception e) { }
            Thread.sleep(500);
        }

    }
}
