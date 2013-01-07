package com.rrd.desktop.calls;

import com.rrd.desktop.core.FlexSelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;

public class FlashTextHasValueCall implements FlashCall {

	private String objectId;
	private FlexSelenium flashApp;
	private String expectedValue;
	private String textValue;
    private HttpCommandProcessor proc;

	public FlashTextHasValueCall(FlexSelenium flashApp, String objectId, String expectedValue) {
		super();
		this.flashApp = flashApp;
		this.objectId = objectId;
		this.expectedValue = expectedValue;
	}
    /*
	@Override
	public boolean attemptCall() {
		textValue = flashApp.getText(objectId);
		if (textValue == null || !textValue.equals(expectedValue)) {
			return false;
		}
		return true;
	}
    */

    @Override
    public boolean attemptCall() {
    boolean blah;

        blah = proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"Click\" value=\"logout\"/>"});
        return blah;

    }

	@Override
	public String getErrorMessage() {
		return "The text  for " + objectId + " did not have value '" + expectedValue + "' but had value '" + textValue + "'";
	}

}
