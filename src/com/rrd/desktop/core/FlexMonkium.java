package com.rrd.desktop.core;

import com.thoughtworks.selenium.HttpCommandProcessor;

/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 1/7/13
 * Time: 2:58 PM
 */
public class FlexMonkium {
    private HttpCommandProcessor proc;

    public FlexMonkium(final HttpCommandProcessor proc) {
       this.proc = proc;
    }

    //"<UIEvent command=\"SelectText\" value=\"userNameTextInput\"></UIEvent>"
    // where value is the automation ID
    public boolean selectTextBox(String automationId){
        return proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"SelectText\" value=\""+ automationId +"\"></UIEvent>"});
    }

    //{"<UIEvent command=\"Input\" value=\"userNameTextInput\"><arg value=\"admin\"/></UIEvent>"}
    public boolean enterText(String automationId, String text){
        return proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"Input\" value=\""+ automationId +"\"><arg value=\""+ text +"\"/></UIEvent>"});
    }

    // value=automation ID (i.e. submitButton)
    public boolean click(String automationId){
        return proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"Click\" value=\""+ automationId +"\"/>"});
    }
}
