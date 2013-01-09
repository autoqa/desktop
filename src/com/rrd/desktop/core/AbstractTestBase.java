package com.rrd.desktop.core;

/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 1/8/13
 * Time: 6:47 PM
 */

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;
import com.thoughtworks.selenium.Selenium;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.util.Properties;

public class AbstractTestBase {
    static Properties properties;
    static Selenium selenium;
    static HttpCommandProcessor proc;
    static FlexCommandProcessor flex;

    /**
     * Perform necessary initialisation prior to any test being executed.
     *
     * @throws Exception if initialisation fails
     */
    @BeforeTest
    public static void setUpTest() throws Exception
    {
        System.out.println("AcceptanceTestBase.setUpClass BeforeTest");
        properties = new Properties();
        properties.load(new FileInputStream("test.properties"));

        proc = new HttpCommandProcessor(
                properties.getProperty("selenium.serverHost"),
                Integer.valueOf(properties.getProperty("selenium.serverPort")),
                properties.getProperty("selenium.browserStartCommand"),
                properties.getProperty("selenium.browserUrl"));

        selenium = new DefaultSelenium(proc);

        //selenium = new DefaultSelenium("localhost", 4444, "*firefox" ,"https://app.devlinux.nimblefish.com");

        getContext().start();
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        //flexSelenium = new FlexSelenium(getContext(), "desktop");
        //getContext().open("/desktop//desktop.html");
        System.out.println("AcceptanceTestBase.setUp BeforeClass");
        flex = new FlexCommandProcessor(proc);
        getContext().open(properties.getProperty("selenium.browserUrl"));
    }

    /**
     * Tidy up resources after every test has been executed.
     *
     * @throws Exception if the tidy up fails
     */
    @AfterTest
    public static void tearDownTest()
            throws Exception
    {
        System.out.println("AcceptanceTestBase.tearDownTest AfterClass");
        getContext().stop();
    }

    /**
     * Retrieve the context for accessing Selenium.
     *
     * @return the Selenium context
     */
    protected static Selenium getContext()
    {
        return selenium;
    }
}
