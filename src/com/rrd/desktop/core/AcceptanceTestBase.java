package com.rrd.desktop.core;

/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 12/27/12
 * Time: 2:46 PM
 */

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
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
    @BeforeTest
    public static void setUpTest()
            throws Exception
    {
        System.out.println("AcceptanceTestBase.setUpClass BeforeTest");
        properties = new Properties();
        properties.load(new FileInputStream("test.properties"));

        selenium = new DefaultSelenium(
                properties.getProperty("selenium.serverHost"),
                Integer.valueOf(properties.getProperty("selenium.serverPort")),
                properties.getProperty("selenium.browserStartCommand"),
                properties.getProperty("selenium.browserUrl"));


        //selenium = new DefaultSelenium("localhost", 4444, "*firefox" ,"https://app.devlinux.nimblefish.com");

        getContext().start();
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        //flexSelenium = new FlexSelenium(getContext(), "desktop");
        //getContext().open("/desktop//desktop.html");
        System.out.println("AcceptanceTestBase.setUp BeforeClass");
        flexSelenium = new FlexSelenium(getContext(), properties.getProperty("application.name"));
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

    protected FlexSelenium getFlashApp() {
        return flexSelenium;
    }
}
