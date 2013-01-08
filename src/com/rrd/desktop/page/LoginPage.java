package com.rrd.desktop.page;

import com.rrd.desktop.core.AbstractPage;
import com.thoughtworks.selenium.HttpCommandProcessor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 1/8/13
 * Time: 2:12 AM
 */

public class LoginPage extends AbstractPage {
    @FindBy(how = How.NAME, using = "login")
    private WebElement loginEdit;
    @FindBy(how = How.NAME, using = "pass")
    private WebElement passwordEdit;
    @FindBy(how = How.NAME, using = "button")
    private WebElement loginButton;

    public LoginPage(HttpCommandProcessor proc) {
        super(proc);
    }
    public void enterLogin(String login) {
        loginEdit.clear();
        loginEdit.sendKeys(login);
    }
    public void enterPassword(String password) {
        loginEdit.clear();
        passwordEdit.sendKeys(password);
    }
    public void clickLoginButton() {
        loginButton.click();
    }
    public Object login(String login, String password) {
        enterLogin(login);
        enterPassword(password);
        clickLoginButton();
        return new Object();
    }
}