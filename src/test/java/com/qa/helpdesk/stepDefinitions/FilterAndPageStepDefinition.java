package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.locators.DashboardHeaderLocators;
import com.qa.helpdesk.locators.FilterAndCalendarLocators;
import com.qa.helpdesk.locators.TableAndPaginationLocators;
import com.qa.helpdesk.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.sl.In;
import org.openqa.selenium.WebElement;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class FilterAndPageStepDefinition {


    @Given("Select option {string} for pagination")
    public void selectOptionForPagination(String option) {
        try {
            if(DriverAction.isExist(TableAndPaginationLocators.paginationDropdown)) {
                DriverAction.scrollIntoView(TableAndPaginationLocators.paginationDropdown);
                DriverAction.waitUntilElementClickable(TableAndPaginationLocators.paginationDropdown,10);
                DriverAction.click(TableAndPaginationLocators.paginationDropdown,"Rows per page");
                DriverAction.dropDown(TableAndPaginationLocators.paginationDropdown,option);
                DriverAction.waitSec(3);
            } else GemTestReporter.addTestStep("Pagination","Dropdown for rows per page not found", STATUS.FAIL,DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify result for selection {string}")
    public void verifyResultForSelection(String option) {
        try {
            List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getTableRows);
            if(elements.size()==Integer.parseInt(option)){
                GemTestReporter.addTestStep("Pagination","Rows per page displayed as expected",STATUS.PASS,DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Pagination","Rows per page displayed not as expected",STATUS.FAIL,DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @Given("Open filter option")
    public void openFilterOption() {
        try
        {
            if(DriverAction.isExist(FilterAndCalendarLocators.filterButton)) {
                DriverAction.click(FilterAndCalendarLocators.filterButton, "Filter button");
                DriverAction.waitSec(2);
            } else GemTestReporter.addTestStep("Filter button","Filter button not found",STATUS.FAIL,DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Select filter criteria as department {string} and status {string}")
    public void selectFilterCriteria(String dept, String status) {
        try
        {
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            if(!dept.isEmpty())
            {
                if(dept.contains(",")){
                    String[] deptList = dept.split(",");
                    for(String i:deptList) {
                        DriverAction.click(FilterAndCalendarLocators.filterOptions(i),"Department");
                        if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                            DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
                        }
                    }
                } else {
                    DriverAction.click(FilterAndCalendarLocators.filterOptions(dept),"Status");
                    if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                        DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
                    }
                }

            }

            if(!status.isEmpty())
            {
                if(status.contains(",")){
                    String[] statusList = status.split(",");
                    for(String j:statusList) {
                        DriverAction.click(FilterAndCalendarLocators.filterOptions(j),"Status");
                        if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                            DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
                        }
                    }
                } else {
                    DriverAction.click(FilterAndCalendarLocators.filterOptions(status),"Status");
                    if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                        DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
                    }
                }

            }
            else GemTestReporter.addTestStep("Filter selection","No filter selection is made",STATUS.INFO,DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }


    }

    @And("Verify result for selection dept {string} and status {string}")
    public void verifyResultForSelection(String dept, String status) {
        try {
            DriverAction.click(FilterAndCalendarLocators.closeFilterButton,"Close button");
            DriverAction.waitSec(3);
            DriverAction.dropDown(TableAndPaginationLocators.paginationDropdown,"25");
            DriverAction.waitSec(2);
            boolean nextActive;
            if(!dept.isEmpty()) {
                int deptFlag=1;
                String pos = CommonUtils.getTableColPosition("Department");
                if(DriverAction.isExist(TableAndPaginationLocators.getTableRows)) {
                    List<String> allDept = new ArrayList<>();
                    do {
                        List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getColValues(pos));
                        for(WebElement ele: elements)
                        {
                            allDept.add(DriverAction.getElementText(ele));
                        }
                        nextActive = DriverManager.getWebDriver().findElement(TableAndPaginationLocators.nextPageButton).isEnabled();
                        if(nextActive){
                            DriverAction.click(TableAndPaginationLocators.nextPageButton,"Next page");
                            DriverAction.waitSec(2);
                        }
                    } while (nextActive);

                    for(String ele:allDept) {
                        if(!dept.toLowerCase().contains(ele.toLowerCase())){
                            deptFlag=0;
                            break;
                        }
                    }

                    if(deptFlag==1){
                        GemTestReporter.addTestStep("Dept filter","Dept filter results are as expected",STATUS.PASS,DriverAction.takeSnapShot());
                    } else GemTestReporter.addTestStep("Dept filter","Dept filter results are not as expected",STATUS.FAIL,DriverAction.takeSnapShot());

                } else GemTestReporter.addTestStep("Dept filter","No results for filter dept "+dept+"",STATUS.INFO,DriverAction.takeSnapShot());
            }

            if(!status.isEmpty()) {
                int statusFlag=1;
                String pos = CommonUtils.getTableColPosition("Status");
                if(DriverAction.isExist(TableAndPaginationLocators.getTableRows)) {
                    List<String> allStatus = new ArrayList<>();
                    do {
                        List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getColValues(pos));
                        for(WebElement ele: elements)
                        {
                            allStatus.add(DriverAction.getElementText(ele));
                        }
                        nextActive = DriverManager.getWebDriver().findElement(TableAndPaginationLocators.nextPageButton).isEnabled();
                        if(nextActive){
                            DriverAction.click(TableAndPaginationLocators.nextPageButton,"Next page");
                            DriverAction.waitSec(2);
                        }
                    } while(nextActive);

                    for(String ele:allStatus) {
                        if(!ele.toLowerCase().contains(status.toLowerCase())){
                            statusFlag=0;
                            break;
                        }
                    }

                    if(statusFlag==1){
                        GemTestReporter.addTestStep("Status filter","Status filter results are as expected",STATUS.PASS,DriverAction.takeSnapShot());
                    } else GemTestReporter.addTestStep("Status filter","Status filter results are not as expected",STATUS.FAIL,DriverAction.takeSnapShot());

                } else GemTestReporter.addTestStep("Status filter","No results for filter status "+status+"",STATUS.INFO,DriverAction.takeSnapShot());

            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @And("Clear all applied filters")
    public void clearAllAppliedFilters() {
        try {
            DriverAction.waitSec(2);
            DriverAction.click(FilterAndCalendarLocators.clearFilterButton,"Clear filters");
            DriverAction.waitSec(2);
            DriverAction.click(FilterAndCalendarLocators.closeFilterButton,"Close filter");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if filters for dept {string} and status {string} are cleared")
    public void verifyIfFiltersForDeptAndStatusAreCleared(String dept, String status) {
        try {
            DriverAction.dropDown(TableAndPaginationLocators.paginationDropdown,"25");
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)){
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,5);
            }
            if(!dept.isEmpty()) {
                int deptFlag=0;
                String pos = CommonUtils.getTableColPosition("Department");
                List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getColValues(pos));
                for(WebElement ele:elements) {
                    if(!DriverAction.getElementText(ele).equalsIgnoreCase(dept)) {
                        deptFlag=1;
                        break;
                    }
                }
                if(deptFlag==1) {
                    GemTestReporter.addTestStep("Clear Department filter","Dept filter cleared successfully",STATUS.PASS,DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Clear Department filter","Dept filter not cleared",STATUS.FAIL,DriverAction.takeSnapShot());
                }
            }
            if(!status.isEmpty()) {
                int statusFlag=0;
                String pos = CommonUtils.getTableColPosition("Status");
                List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getColValues(pos));
                for(WebElement ele:elements) {
                    if(!DriverAction.getElementText(ele).equalsIgnoreCase(status)) {
                        statusFlag=1;
                        break;
                    }
                }
                if(statusFlag==1) {
                    GemTestReporter.addTestStep("Clear Status filter","Status filter cleared successfully",STATUS.PASS,DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Clear Status filter","Status filter not cleared",STATUS.FAIL,DriverAction.takeSnapShot());
                }

            }

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @And("Verify next and prev buttons for {string}")
    public void verifyNextAndPrevButtons(String option) {
        try {
            String displayedRows;
            if(DriverManager.getWebDriver().findElement(TableAndPaginationLocators.nextPageButton).isEnabled()) {
                DriverAction.click(TableAndPaginationLocators.nextPageButton,"Next page button");
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
                displayedRows = DriverAction.getElementText(TableAndPaginationLocators.displayedRows).split("–")[0];
                int rows = DriverAction.getElements(TableAndPaginationLocators.getColValues("1")).size();
                if((Integer.parseInt(option)+1)==Integer.parseInt(displayedRows) && rows==Integer.parseInt(option)){
                    GemTestReporter.addTestStep("Next page button","Successfully navigated to next page",STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Next page button","Some issue while navigating to next page",STATUS.PASS,DriverAction.takeSnapShot());

            } else GemTestReporter.addTestStep("Next page button","Next page button is disabled",STATUS.INFO,DriverAction.takeSnapShot());

            if(DriverManager.getWebDriver().findElement(TableAndPaginationLocators.prevPageButton).isEnabled()) {
                DriverAction.click(TableAndPaginationLocators.prevPageButton,"Prev page button");
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
                displayedRows = DriverAction.getElementText(TableAndPaginationLocators.displayedRows).split("–")[0];
                int rows = DriverAction.getElements(TableAndPaginationLocators.getColValues("1")).size();
                if((rows==Integer.parseInt(option) && displayedRows.equals("1"))) {
                    GemTestReporter.addTestStep("Prev page button","Successfully navigated to prev page",STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Next page button","Some issue while navigating to next page",STATUS.PASS,DriverAction.takeSnapShot());

            } else GemTestReporter.addTestStep("Prev page button","Prev page button is disabled",STATUS.INFO,DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @And("Click on toggle button to hide closed, resolved and cancelled status")
    public void clickOnToggleButton() {
        try {
            DriverAction.waitUntilElementClickable(FilterAndCalendarLocators.filterToggleButton,10);
            DriverAction.click(FilterAndCalendarLocators.filterToggleButton,"Filter toggle button");
            DriverAction.waitSec(3);
            DriverAction.click(FilterAndCalendarLocators.closeFilterButton,"Close filter");
            DriverAction.waitSec(3);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if closed, cancelled and resolved tickets are hidden")
    public void verifyIfClosedCancelledAndResolvedTicketsAreHidden() {
        try {
            DriverAction.dropDown(TableAndPaginationLocators.paginationDropdown,"25");
            DriverAction.waitSec(2);
            boolean nextActive;

            int statusFlag=1;
            String pos = CommonUtils.getTableColPosition("Status");
            if(DriverAction.isExist(TableAndPaginationLocators.getTableRows)) {
                List<String> allStatus = new ArrayList<>();
                do {
                    List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getColValues(pos));
                    for (WebElement ele : elements) {
                        allStatus.add(DriverAction.getElementText(ele));
                    }
                    nextActive = DriverManager.getWebDriver().findElement(TableAndPaginationLocators.nextPageButton).isEnabled();
                    if (nextActive) {
                        DriverAction.click(TableAndPaginationLocators.nextPageButton, "Next page");
                        DriverAction.waitSec(2);
                    }
                } while (nextActive);

                for (String ele : allStatus) {
                    if (ele.equalsIgnoreCase("closed") || ele.equalsIgnoreCase("resolved") || ele.equalsIgnoreCase("cancelled")) {
                        statusFlag = 0;
                        break;
                    }
                }

                if (statusFlag == 1) {
                    GemTestReporter.addTestStep("Toggle status filter", "Closed, Cancelled and Resolved tickets are hidden", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Toggle status filter", "Closed, Cancelled and Resolved tickets are not hidden", STATUS.FAIL, DriverAction.takeSnapShot());

            } else GemTestReporter.addTestStep("Status filter","No results for the filter",STATUS.INFO,DriverAction.takeSnapShot());

            } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }
}
