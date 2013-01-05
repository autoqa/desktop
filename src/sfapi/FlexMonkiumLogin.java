
package sfapi;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;
import com.thoughtworks.selenium.Selenium;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.fail;

public class FlexMonkiumLogin {
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
        selenium.open("/desktop//desktop.html#");
        for (int t = 0;; t++) {
            if (t >= 60) fail("timeout");
            try {
                if (proc.getBoolean("isFlexMonkey", new String[] {""})) break;
            } catch (Exception e) { }
            Thread.sleep(500);
        }

        for (int t = 0;; t++) {
            if (t >= 60) fail("timeout");
            try {
                if (proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"SelectText\" value=\"userNameTextInput\">   <arg value=\"0\"/>   <arg value=\"0\"/> </UIEvent>"})) break;
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
                if (proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"SelectText\" value=\"passwordTextInput\"><arg value=\"0\"/><arg value=\"0\"/></UIEvent>"})) break;
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

    }
}
