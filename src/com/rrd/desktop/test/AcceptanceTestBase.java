package com.rrd.desktop.test;

/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 12/27/12
 * Time: 2:46 PM
 */

import com.rrd.desktop.FlexSelenium;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.Properties;

public abstract class AcceptanceTestBase  {

    static Properties properties;
    static Selenium selenium;
    private FlexSelenium flexSelenium;

    /**
     * Perform necessary initialisation prior to any test being executed.
     *
     * @throws Exception if initialisation fails
     */
    @BeforeClass
    public static void setUpClass()
            throws Exception
    {
        properties = new Properties();
        //properties.load(new FileInputStream("test.properties"));
        /*
        selenium = new DefaultSelenium(
                properties.getProperty("selenium.serverHost"),
                Integer.valueOf(properties.getProperty("selenium.serverPort")),
                properties.getProperty("selenium.browserStartCommand"),
                properties.getProperty("selenium.browserUrl"));
        */

        selenium = new DefaultSelenium("localhost", 4444, "*firefox" ,"https://app.devlinux.nimblefish.com");

        getContext().start();
    }

    @Before
    public void setUp() throws Exception {
        flexSelenium = new FlexSelenium(getContext(), "desktop");
        getContext().open("/desktop//desktop.html");
        //flexSelenium = new FlexSelenium(getContext(), properties.getProperty("application.name"));
        //getContext().open(properties.getProperty("selenium.browserUrl"));
    }

    /**
     * Tidy up resources after every test has been executed.
     *
     * @throws Exception if the tidy up fails
     */
    @AfterClass
    public static void tearDownClass()
            throws Exception
    {
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

    protected FlexSelenium getFlashApp() {
        return flexSelenium;
    }
}
