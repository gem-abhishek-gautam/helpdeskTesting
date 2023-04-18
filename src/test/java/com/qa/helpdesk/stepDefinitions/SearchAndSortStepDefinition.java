package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.locators.DashboardHeaderLocators;
import com.qa.helpdesk.locators.SearchAndSortLocators;
import com.qa.helpdesk.locators.TableAndPaginationLocators;
import com.qa.helpdesk.locators.TicketLocators;
import com.qa.helpdesk.utils.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAndSortStepDefinition {

    @Given("Search for keyword {string}")
    public void searchForTicket(String keyword) {
        try
        {
            if(DriverAction.isExist(SearchAndSortLocators.ticketSearchBox)){
                DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchBox,10);
                DriverAction.typeText(SearchAndSortLocators.ticketSearchBox,keyword);
                DriverAction.waitSec(1);
                DriverAction.click(SearchAndSortLocators.ticketSearchButton);
                if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
                }

            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify search result for {string}")
    public void verifySearchResult(String keyword) {
        try {
            if(DriverAction.isExist(TableAndPaginationLocators.getAllTableElements)) {
                List<WebElement> getIDs = DriverAction.getElements(TableAndPaginationLocators.getAllTableElements);
                int itemFoundFlag=0;
                for(WebElement id: getIDs) {
                    if(DriverAction.getElementText(id).toLowerCase().contains(keyword.toLowerCase())){
                        itemFoundFlag=1;
                        break;
                    }
                }

                if(itemFoundFlag==1 || keyword.contains("$")){
                    GemTestReporter.addTestStep("Ticket search result","Ticket search result is as expected", STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Ticket search result","Ticket search result is not as expected",STATUS.FAIL,DriverAction.takeSnapShot());

            } else GemTestReporter.addTestStep("Ticket search result","No search results for the keyword "+keyword+"",STATUS.INFO,DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }


    @Given("Verify sorting button for {string} column")
    public void checkSortingButtonForColumn(String colName) {
        try
        {
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            List<String> values = new ArrayList<>();
            DriverAction.scrollIntoView(TicketLocators.createTicket);
            DriverAction.waitUntilElementClickable(SearchAndSortLocators.columns(colName),10);
            DriverAction.click(SearchAndSortLocators.columns(colName),colName+" sort button");
            DriverAction.waitUntilElementClickable(TableAndPaginationLocators.paginationDropdown,5);
            DriverAction.dropDown(TableAndPaginationLocators.paginationDropdown,"25");
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            String pos = CommonUtils.getTableColPosition(colName);
            boolean nextActive;
            do {
                List<WebElement> elements = new ArrayList<>();
                if(DriverAction.getElementText(DashboardHeaderLocators.getActiveTab).toLowerCase().contains("department")) {
                    if(!(DriverAction.getElementText(DashboardHeaderLocators.getActiveTab).toLowerCase()).contains("my tickets")) {
                        if(colName.equalsIgnoreCase("Assigned to")) {
                            elements = DriverAction.getElements(TableAndPaginationLocators.getAssignValues);
                        }
                        if(colName.equalsIgnoreCase("status")) {
                            elements = DriverAction.getElements(TableAndPaginationLocators.getStatusValues);
                        }
                    } else
                        elements = DriverAction.getElements(TableAndPaginationLocators.getColValues(pos));
                }
                for(WebElement ele: elements)
                {
                    values.add(DriverAction.getElementText(ele));
                }
                nextActive = DriverManager.getWebDriver().findElement(TableAndPaginationLocators.nextPageButton).isEnabled();
                if(nextActive){
                    DriverAction.click(TableAndPaginationLocators.nextPageButton,"Next page");
                    if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                        DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
                    }
                    DriverAction.waitUntilElementClickable(TableAndPaginationLocators.paginationDropdown,5);
                }
            } while (nextActive);

            if(CommonUtils.isListInOrder(values)) {
                GemTestReporter.addTestStep(""+colName+" Values","Values are in sorted order",STATUS.PASS,DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep(""+colName+" Values","Values are not in sorted order",STATUS.FAIL,DriverAction.takeSnapShot());
            values.clear();
            DriverAction.refresh();
            DriverAction.waitUntilElementClickable(SearchAndSortLocators.columns(colName),5);

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if search box text {string} is cleared")
    public void verifyIfSearchBoxTextIsCleared(String text) {
        try {
            DriverAction.waitUntilElementClickable(SearchAndSortLocators.clearSearchText,10);
            DriverAction.click(SearchAndSortLocators.clearSearchText,"Clear text");
            String value = DriverAction.getAttributeName(SearchAndSortLocators.ticketSearchBox,"value");
            if(!value.equalsIgnoreCase(text)) {
                GemTestReporter.addTestStep("Clear search box","Search box value cleared",STATUS.PASS,DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Clear search box","Search box value not cleared",STATUS.FAIL,DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }
}
