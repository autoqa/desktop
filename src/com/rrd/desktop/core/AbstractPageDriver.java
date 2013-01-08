/**
 * RR Donnelley - Nimblefish
 * Name: Rob Cruz
 * User: robertc
 * Date: 12/27/12
 * Time: 12:51 PM
 */

package com.rrd.desktop.core;

import com.rrd.desktop.calls.*;
import org.testng.Assert;

public class AbstractPageDriver {
    private static final String ACTION_SUCCESS = "true";
    private static final String WIDGET_PROPERTY_DATA_PROVIDER = "dataProvider";
    private static final long TIMEOUT_MILLIS = 10000L;

    private final FlexSelenium flexSelenium;

    public AbstractPageDriver(final FlexSelenium flexSelenium) {
        this.flexSelenium = flexSelenium;
    }

    protected FlexSelenium getFlexSelenium() {
        return flexSelenium;
    }

    protected void setFocus(final String widgetId) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);

        Assert.assertEquals(ACTION_SUCCESS, flexSelenium.setFocus(widgetId));
    }

    protected void clickButton(final String widgetId) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        Assert.assertEquals(ACTION_SUCCESS, flexSelenium.click(widgetId));
    }

    protected void setCheckBoxState(final String widgetId, final boolean state) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        Assert.assertEquals(ACTION_SUCCESS, flexSelenium.selectCheckbox(widgetId, state));
    }

    protected void ensureWidgetEnabled(final String widgetId, final boolean enabledState) {
        ensureWidgetEnabled(widgetId, enabledState, TIMEOUT_MILLIS);
    }

    protected void ensureWidgetEnabled(final String widgetId, final boolean enabledState, final long timeoutMillis) {
        // This mechanism for checking the enabled state is to work around an intermittent issue where the widget
        // does not exist at the time of the enabled check.
        FlashCallTimer.waitForCall(timeoutMillis, new FlashCall() {
            @Override
            public boolean attemptCall() {
                try {
                    return flexSelenium.isEnabled(widgetId) == enabledState;
                }
                catch (Throwable t) {
                    System.out.println(String.format("Enabled state of '%s' threw an Exception: %s", widgetId, t.getMessage()));
                    return false;
                }
            }

            @Override
            public String getErrorMessage() {
                return String.format("The enabled state of widget %s was not %b", widgetId, enabledState);
            }
        });
    }

    protected void ensureWidgetVisibility(final String widgetId, final boolean visibility) {
        ensureWidgetVisibility(widgetId, visibility, TIMEOUT_MILLIS);
    }

    protected void ensureWidgetVisibility(final String widgetId, final boolean visibility, final long timeoutMillis) {
        // This mechanism for checking the visibility state is to work around an intermittent issue where the widget
        // does not exist at the time of the visibility check.
        FlashCallTimer.waitForCall(timeoutMillis, new FlashCall() {
            @Override
            public boolean attemptCall() {
                try {
                    boolean isVisible = flexSelenium.isVisible(widgetId);
                    return visibility == isVisible;
                }
                catch (Throwable t) {
                    System.out.println(String.format("Visibility of '%s' threw an Exception: %s", widgetId, t.getMessage()));
                    return false;
                }
            }

            @Override
            public String getErrorMessage() {
                return String.format("The visibility of widget %s was not %b", widgetId, visibility);
            }
        });
    }

    protected void enterText(final String widgetId, final String text) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        flexSelenium.enterText(widgetId, text);
    }

    protected void ensureWidgetHasText(final String widgetId, final String expectedText) {
        ensureWidgetVisibility(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashTextHasValueCall(flexSelenium, widgetId, expectedText));
    }

    protected void selectTextCommand(final String value){

    }

    /*
    protected void ensureCheckBoxState(final String widgetId, final boolean expectedState) {
        ensureWidgetVisibility(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashCheckboxCheckedCall(flexSelenium, widgetId, expectedState));
    }
    */



     /*
    protected void enterDate(final String widgetId, final Calendar date, final String format) {
        final DateFormat formatter = new SimpleDateFormat(format);
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        flexSelenium.enterDate(widgetId, formatter.format(date.getTime()));
    }

    protected void ensureTabEnabled(final String widgetId, final String tabLabel, final boolean enabled) {
        ensureWidgetVisibility(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashTabEnabledCall(flexSelenium, widgetId, tabLabel, enabled));
    }

    protected void selectTab(final String widgetId, final String tabTitle) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        Assert.assertEquals(ACTION_SUCCESS, flexSelenium.click(widgetId, tabTitle));
    }

    protected void selectComboItem(final String widgetId, final String label) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashPropertySetCall(flexSelenium, widgetId, "dataProvider"));

        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashComboContainsValueCall(flexSelenium, widgetId, label));

        Assert.assertEquals("true", flexSelenium.selectComboByLabel(widgetId, label));

        ensureComboSelectedItem(widgetId, label);
    }

    protected void ensureComboSelectedItem(final String widgetId, final String value) {
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashComboHasSelectedValueCall(flexSelenium, widgetId, value));
    }

    protected void ensureComboNoSelectedItem(final String widgetId) {
        ensureWidgetVisibility(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashCall() {
            @Override
            public boolean attemptCall() {
                return flexSelenium.getSelectionIndex(widgetId) == -1;
            }

            @Override
            public String getErrorMessage() {
                return String.format("Combo box %s has a selected item", widgetId);
            }
        });
    }

    protected void ensureComboSelectedItemNotAvailable(
            final String widgetId, final String value) {
        ensureWidgetVisibility(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashComboDoesNotContainValueCall(flexSelenium, widgetId, value));
    }

    protected void ensureComboContainsValue(final String widgetId, final String value) {
        ensureWidgetVisibility(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashComboContainsValueCall(flexSelenium, widgetId, value));
    }

    protected void selectMultiChoiceList(final String widgetId, final String[] values) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashPropertySetCall(flexSelenium, widgetId, WIDGET_PROPERTY_DATA_PROVIDER));

        for (final String value : values) {
            Assert.assertEquals(ACTION_SUCCESS, flexSelenium.select(widgetId, value));
        }
    }

    protected void selectByIndices(final String widgetId, final int[] indices) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        ensureDataGridPopulated(widgetId);

        for (final int index : indices) {
            flexSelenium.selectIndex(widgetId, index);
        }
    }

    protected void ensureDataGridRowVisible(final String widgetId, final String property, final String value, final boolean visible) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashDataGridRowVisibleCall(flexSelenium, widgetId, property, value, visible));
    }

    protected void ensureDataGridRowWithLabelVisible(final String widgetId, final String property, final String label, final boolean visible) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashDataGridRowLabelVisibleCall(flexSelenium, widgetId, property, label, visible));
    }

    protected void ensureDataGridRowValue(final String widgetId, final int row, final String property, final String expectedValue) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashDataGridRowHasValueCall(flexSelenium, widgetId, row, property, expectedValue));
    }

    protected void ensureDataGridRowLabel(final String widgetId, final int row, final String property, final String expectedValue) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetEnabled(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashDataGridRowHasLabelCall(flexSelenium, widgetId, row, property, expectedValue));
    }
    */


    /*
    protected void ensureWidgetHasMatchingText(final String widgetId, final String regularExpression) {
        ensureWidgetVisibility(widgetId, true);

        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashCall() {
            @Override
            public boolean attemptCall() {
                return flexSelenium.getText(widgetId).matches(regularExpression);
            }

            @Override
            public String getErrorMessage() {
                return String.format("The text of %s did not meet the regular expression '%s'", widgetId, regularExpression);
            }
        });
    }

    protected void ensureWidgetTextNotBlank(final String widgetId) {
        ensureWidgetVisibility(widgetId, true);
        FlashCallTimer.waitForNegatedCall(TIMEOUT_MILLIS, new FlashTextHasValueCall(flexSelenium, widgetId, ""));
    }

    protected void ensureWidgetTextChanged(final String widgetId, final String initialValue) {
        ensureWidgetVisibility(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashTextValueChangedCall(flexSelenium, widgetId, initialValue));
    }

    protected void ensureWidgetHasDate(final String widgetId, final Calendar date, final String format) {
        final DateFormat formatter = new SimpleDateFormat(format);
        final String expectedText = date != null ? formatter.format(date.getTime()) : "";

        ensureWidgetHasText(widgetId, expectedText);
    }

    protected void ensureDataGridPopulated(final String widgetId) {
        ensureWidgetVisibility(widgetId, true);
        ensureWidgetPropertySet(widgetId, WIDGET_PROPERTY_DATA_PROVIDER);
    }

    protected void ensureDataGridRowCount(final String widgetId, final int expectedRowCount) {
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashNumberOfRowsCall(flexSelenium, widgetId, expectedRowCount));
    }

    protected void ensureDataGridRowCountChanged(final String widgetId, final int initialRowCount) {
        FlashCallTimer.waitForNegatedCall(TIMEOUT_MILLIS, new FlashNumberOfRowsCall(flexSelenium, widgetId, initialRowCount));
    }

    protected void ensureDataGridRowValue(final String widgetId, final int row, final int column, final String expectedValue) {
        ensureWidgetVisibility(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashCall() {
            private String lastValue;

            @Override
            public boolean attemptCall() {
                lastValue = flexSelenium.getDataGridValue(widgetId, row, column);
                return expectedValue.equals(lastValue);
            }

            @Override
            public String getErrorMessage() {
                return String.format("%s[%d, %d] did not have value '%s', last value was '%s'", widgetId, row, column, expectedValue, lastValue);
            }
        });
    }

    protected void ensureWidgetErrorString(final String widgetId, final String expectedMessage) {
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashFieldHasErrorMessageCall(flexSelenium, widgetId, expectedMessage));
    }

    protected int getDataGridRowIndex(final String widgetId, final String field, final String value) {
        ensureDataGridRowVisible(widgetId, field, value, true);

        return flexSelenium.getFlexDataGridRowIndexForFieldValue(widgetId, field, value);
    }

    protected int getDataGridRowIndexByLabel(final String widgetId, final String field, final String value) {
        ensureDataGridRowWithLabelVisible(widgetId, field, value, true);

        return flexSelenium.getFlexDataGridRowIndexForFieldLabel(widgetId, field, value);
    }

    protected String getDataGridRowValue(final String widgetId, final String field, final int row) {
        Assert.assertTrue("Not enough rows in the table", getDataGridRowCount(widgetId) > row);

        return flexSelenium.getFlexDataFieldValueForGridRow(widgetId, field, row);
    }

    protected int getDataGridRowCount(final String widgetId) {
        ensureWidgetVisibility(widgetId, true);
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashPropertySetCall(flexSelenium, widgetId, WIDGET_PROPERTY_DATA_PROVIDER));

        return flexSelenium.getFlexDataGridRowCount(widgetId);
    }

    protected void ensureAlertVisibility(final boolean visible) {
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashAlertVisibleCall(flexSelenium, visible));
    }

    protected void closeAlert(final String response) {
        ensureAlertVisibility(true);
        Assert.assertEquals(ACTION_SUCCESS, flexSelenium.call("doFlexAlertResponse", response, response));
        ensureAlertVisibility(false);
    }
     */

    /**
     * Select an element from a list.
     *
     * @param widgetId the id of the widget from which to select
     * @param field the field (member) of the object to use for selection
     * @param value the data value to look for in the field to find the object to select
     */
    protected void selectByFieldValue(final String widgetId, final String field, final String value) {
//		ensureDataGridRowVisible(widgetId, field, value, true);
        Assert.assertEquals(ACTION_SUCCESS, flexSelenium.selectMatchingOnField(widgetId, field, value));
    }

    protected String getWidgetText(final String widgetId) {
        ensureWidgetVisibility(widgetId, true);

        return flexSelenium.getText(widgetId);
    }
    /*
    protected void selectMultipleDataGridRows(final String widgetId, final String property, final String[] values) {
        ensureWidgetVisibility(widgetId, true);
        ensureDataGridPopulated(widgetId);

        int selectedCount = 0;
        for (final String value : values) {
            flexSelenium.addSelectMatchingOnField(widgetId, property, value);
            selectedCount++;

            // Wait for the selection to be made, otherwise we may override it
            FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashNumberOfRowsSelectedCall(flexSelenium, widgetId, selectedCount));
        }
    }

    protected void ensureSelectedListItems(final String widgetId, final String[] expectedItems) {
        ensureWidgetVisibility(widgetId, true);

        final int numSelected = flexSelenium.getNumSelectedItems(widgetId);

        Assert.assertEquals("Wrong number of items selected", expectedItems.length, numSelected);

        final List<String> expectedItemsList = Arrays.asList(expectedItems);
        final List<String> actualItemsList = new ArrayList<String>();
        for (int i = 0; i < numSelected; i++) {
            final String selectedItem = flexSelenium.getSelectedItemAtIndex(widgetId, i);

            actualItemsList.add(selectedItem);
        }

        Collections.sort(expectedItemsList);
        Collections.sort(actualItemsList);

        Assert.assertEquals(expectedItemsList, actualItemsList);
    }

    protected void captureScreenShot(final String fileName) {
        flexSelenium.("./build/" + fileName + ".png");
    }

    protected void ensureWidgetPropertySet(final String widgetId, final String property) {
        FlashCallTimer.waitForCall(TIMEOUT_MILLIS, new FlashPropertySetCall(flexSelenium, widgetId, property));
    }
    */
}
