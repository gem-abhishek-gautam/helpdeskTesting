package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.locators.*;
import com.qa.helpdesk.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SupportDashboardStepDefinitions {
    @Given("Check presence of Assigned, Unassigned, My Department and Others tabs")
    public void checkPresenceOfTabs() {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            if (DriverAction.isExist(DashboardHeaderLocators.ticketTabs("Assigned"))) {
                GemTestReporter.addTestStep("Assigned Tab presence", "Assigned tab is present", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Assigned Tab presence", "Assigned tab is not present", STATUS.PASS, DriverAction.takeSnapShot());
            if (DriverAction.isExist(DashboardHeaderLocators.ticketTabs("Unassigned"))) {
                GemTestReporter.addTestStep("Unassigned tab presence", "Unassigned tab is present", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Unassigned tab presence", "Unassigned tab is not present", STATUS.FAIL, DriverAction.takeSnapShot());
            if (DriverAction.isExist(DashboardHeaderLocators.ticketTabs("My Department"))) {
                GemTestReporter.addTestStep("My Department tab presence", "My Department tab is present", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("My Department tab presence", "My Department tab is not present", STATUS.FAIL, DriverAction.takeSnapShot());
            if (DriverAction.isExist(DashboardHeaderLocators.ticketTabs("Others"))) {
                GemTestReporter.addTestStep("Others tab presence", "Others tab is present", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Others tab presence", "Others tab is not present", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if ticket count matches the number of tickets")
    public void verifyTheTicketCountMatches() {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 30);
            }
            List<String> tabs = Arrays.asList("Assigned", "Unassigned", "My Department");
            for (String tab : tabs) {
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 30);
                }
                DriverAction.waitUntilElementClickable(DashboardHeaderLocators.ticketTabs(tab), 5);
                try {
                    DriverAction.click(DashboardHeaderLocators.ticketTabs(tab), tab);
                } catch (Exception e) {
                    JavascriptExecutor exe = (JavascriptExecutor) DriverManager.getWebDriver();
                    exe.executeScript("argument[0].click();", DriverAction.getElement(DashboardHeaderLocators.ticketTabs(tab)));
                }
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                DriverAction.waitSec(2);
                List<WebElement> elements = new ArrayList<>();
                if (!DriverAction.isExist(TableAndPaginationLocators.noTableDataImg)) {
                    DriverAction.dropDown(TableAndPaginationLocators.paginationDropdown, "25");
                    if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                        CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                    }
                    elements = DriverAction.getElements(TableAndPaginationLocators.getTableRows);
                    DriverAction.scrollIntoView(TableAndPaginationLocators.nextPageButton);
                    boolean nextActive = DriverManager.getWebDriver().findElement(TableAndPaginationLocators.nextPageButton).isEnabled();
                    while (nextActive) {
                        DriverAction.scrollIntoView(TableAndPaginationLocators.nextPageButton);
                        elements.addAll(DriverAction.getElements(TableAndPaginationLocators.getTableRows));
                        DriverAction.click(TableAndPaginationLocators.nextPageButton, "Next button");
                        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                        }
                        nextActive = DriverManager.getWebDriver().findElement(TableAndPaginationLocators.nextPageButton).isEnabled();
                    }
                } else
                    GemTestReporter.addTestStep("Table results", "No data displayed for " + tab + " tickets", STATUS.FAIL, DriverAction.takeSnapShot());

                DriverAction.refresh();
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }

                String[] displayedCountVal = DriverAction.getElementText(DashboardHeaderLocators.ticketTabs(tab)).replace("\"", "").split(" ");
                String displayedCount = (displayedCountVal[displayedCountVal.length - 1]).replace("(", "").replace(")", "").trim();
                if (displayedCount.trim().equals(String.valueOf(elements.size()).trim())) {
                    GemTestReporter.addTestStep("Ticket count", "Ticket count matched for " + tab + " tickets", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Ticket count", "Ticket count not matched for " + tab + " tickets- Actual: " + elements.size() + " Expected: " + displayedCount.replace("(", "").replace(")", "") + "", STATUS.FAIL, DriverAction.takeSnapShot());
                }


            }

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Given("Open {string} ticket category tab")
    public void openTicketCategoryTab(String tab) {
        try {
            DriverAction.refresh();
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(DashboardHeaderLocators.ticketTabs(tab), 5);
            try {
                DriverAction.click(DashboardHeaderLocators.ticketTabs(tab), tab);
            } catch (Exception e) {
                JavascriptExecutor exe = (JavascriptExecutor) DriverManager.getWebDriver();
                exe.executeScript("argument[0].click();", DriverAction.getElement(DashboardHeaderLocators.ticketTabs(tab)));
            }

            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            if (DriverAction.isExist(LoginLocators.employeeName)) {
                DriverAction.click(DashboardHeaderLocators.headerButtons("toggle icon"), "Side menu toggle");
            }

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Assign a ticket to {string} and verify it")
    public void assignTicketToAndVerifyIt(String name) {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            String id = DriverAction.getElementText(TableAndPaginationLocators.firstTicketID);
            assignID = id;
            String pos = CommonUtils.getTableColPosition("Assigned to");
            List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getColValues(pos));
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            DriverAction.click(elements.get(0), "Assign to");

            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            if (!name.equalsIgnoreCase("Abhishek Gautam")) {
                DriverAction.typeText(TicketLocators.callerNameInput, name);
                DriverAction.waitSec(1);
                wait.until(ExpectedConditions.visibilityOfElementLocated(TicketLocators.callerMenu(name)));
                DriverAction.click(TicketLocators.callerMenu(name), name);
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                DriverAction.waitUntilElementClickable(DashboardHeaderLocators.ticketTabs("My Department"), 10);
                DriverAction.click(DashboardHeaderLocators.ticketTabs("My Department"), "My Department");
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchBox, 10);
                DriverAction.waitSec(1);
                DriverAction.typeText(SearchAndSortLocators.ticketSearchBox, id);
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                DriverAction.click(SearchAndSortLocators.ticketSearchButton, "Search button");
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                DriverAction.waitSec(2);
                if (!DriverAction.isExist(TableAndPaginationLocators.noTableDataImg)) {
                    WebElement ele = DriverAction.getElement(TableAndPaginationLocators.getAssignValues);
                    String user = DriverAction.getElementText(ele);
                    if (user.equalsIgnoreCase(name)) {
                        GemTestReporter.addTestStep("Assign ticket", "Ticket assigned successfully", STATUS.PASS, DriverAction.takeSnapShot());
                    }
                    if (user.equalsIgnoreCase("unassigned")) {
                        GemTestReporter.addTestStep("Assign ticket", "Ticket is still unassigned", STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                } else
                    GemTestReporter.addTestStep("Ticket search result", "Ticket results not found", STATUS.FAIL, DriverAction.takeSnapShot());

                DriverAction.refresh();

            } else {

                wait.until(ExpectedConditions.visibilityOfElementLocated(TicketLocators.ticketDropdownOptions("Assign to me")));
                DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions("Assign to me"), 10);
                DriverAction.waitSec(1);
                DriverAction.click(TicketLocators.ticketDropdownOptions("Assign to me"), "Assign to me");
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                DriverAction.waitUntilElementClickable(DashboardHeaderLocators.ticketTabs("Assigned"), 10);
                DriverAction.click(DashboardHeaderLocators.ticketTabs("Assigned"), "Assigned");
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                DriverAction.waitSec(1);
                DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchBox, 10);
                DriverAction.typeText(SearchAndSortLocators.ticketSearchBox, id);
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                DriverAction.click(SearchAndSortLocators.ticketSearchButton, "Search button");
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                if (!DriverAction.isExist(TableAndPaginationLocators.noTableDataImg) && DriverAction.getElementText(TableAndPaginationLocators.getAssignValues).equalsIgnoreCase(name)) {
                    GemTestReporter.addTestStep("Assign ticket", "Ticket assigned successfully", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Assign ticket", "Ticket is still unassigned", STATUS.FAIL, DriverAction.takeSnapShot());

            }


        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    String assignID = "";

    @Then("Change and verify status of a ticket to {string} with reason {string}")
    public void changeStatusOfATicketToWithReason(String status, String reason) {
        try {
            String pos = CommonUtils.getTableColPosition("Status");
            DriverAction.waitUntilElementClickable(TableAndPaginationLocators.getColValues(pos), 10);
            DriverAction.click(TableAndPaginationLocators.getColValues(pos), "Status");
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.elementToBeClickable(SupportTicketLocators.statusChangeMenu(status)));
            DriverAction.click(SupportTicketLocators.statusChangeMenu(status), status);
            wait.until(ExpectedConditions.elementToBeClickable(SupportTicketLocators.statusChangeMenu("Choose Reason")));
            DriverAction.click(SupportTicketLocators.statusChangeMenu("Choose Reason"), "Choose Reason");
            DriverAction.waitUntilElementClickable(SupportTicketLocators.statusChangeMenu(reason), 10);
            DriverAction.click(SupportTicketLocators.statusChangeMenu(reason), reason);
            DriverAction.waitUntilElementClickable(TicketLocators.submitModalForm, 5);
            DriverAction.click(TicketLocators.submitModalForm, "Submit");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchBox, 10);
            DriverAction.waitSec(1);
            DriverAction.typeText(SearchAndSortLocators.ticketSearchBox, assignID);
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            DriverAction.click(SearchAndSortLocators.ticketSearchButton, "Search");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }

            DriverAction.waitUntilElementClickable(TableAndPaginationLocators.getStatusValues, 5);
            String statusActual = DriverAction.getElementText(TableAndPaginationLocators.getStatusValues);
            if (statusActual.equalsIgnoreCase(status)) {
                GemTestReporter.addTestStep("Status change", "Status changed successfully to " + status + "", STATUS.PASS, DriverAction.takeSnapShot());

            } else
                GemTestReporter.addTestStep("Status change", "Status not changed to " + status + " Actual: " + statusActual + "", STATUS.PASS, DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Change and verify status of a ticket to {string} with resolution {string} and remarks {string}")
    public void changeAndVerifyStatusOfATicketToWithResolutionAndRemarks(String status, String code, String remarks) {
        try {
            String pos = CommonUtils.getTableColPosition("Status");
            DriverAction.waitUntilElementClickable(TableAndPaginationLocators.getColValues(pos), 10);
            DriverAction.click(TableAndPaginationLocators.getColValues(pos), "Status");
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.elementToBeClickable(SupportTicketLocators.statusChangeMenu(status)));
            DriverAction.click(SupportTicketLocators.statusChangeMenu(status), status);
            wait.until(ExpectedConditions.elementToBeClickable(SupportTicketLocators.statusChangeMenu("Resolution Code")));
            DriverAction.click(SupportTicketLocators.statusChangeMenu("Resolution Code"), "Resolution Code");
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(code), 5);
            DriverAction.click(TicketLocators.ticketDropdownOptions(code), code);
            DriverAction.typeText(SupportTicketLocators.resolutionInput, remarks);
            DriverAction.waitUntilElementClickable(TicketLocators.submitModalForm, 5);
            DriverAction.click(TicketLocators.submitModalForm, "Submit");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchBox, 10);
            DriverAction.waitSec(1);
            DriverAction.typeText(SearchAndSortLocators.ticketSearchBox, assignID);
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            DriverAction.click(SearchAndSortLocators.ticketSearchButton, "Search");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }

            DriverAction.waitUntilElementClickable(TableAndPaginationLocators.getStatusValues, 5);
            String statusActual = DriverAction.getElementText(TableAndPaginationLocators.getStatusValues);
            if (statusActual.equalsIgnoreCase(status)) {
                GemTestReporter.addTestStep("Status change", "Status changed successfully to " + status + "", STATUS.PASS, DriverAction.takeSnapShot());

            } else
                GemTestReporter.addTestStep("Status change", "Status not changed to " + status + " Actual: " + statusActual + "", STATUS.PASS, DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }


    @Given("Enable VIP filter for tickets")
    public void enableVIPFilterForTickets() {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            if (!DriverAction.isExist(SupportTicketLocators.toggleVIP("enable"))) {
                DriverAction.waitUntilElementClickable(SupportTicketLocators.toggleVIP("disable"), 10);
                DriverAction.click(SupportTicketLocators.toggleVIP("disable"), "VIP");
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                if (DriverAction.isExist(SupportTicketLocators.toggleVIP("enable"))) {
                    GemTestReporter.addTestStep("Toggle VIP", "VIP enabled successfully", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Toggle VIP", "Unable to apply VIP filter", STATUS.FAIL, DriverAction.takeSnapShot());

            } else
                GemTestReporter.addTestStep("Toggle VIP", "VIP already enabled", STATUS.INFO, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify VIP filter for {string} ticket category")
    public void verifyVIPFilterForTicketsCategories(String tab) {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            try {
                DriverAction.click(DashboardHeaderLocators.ticketTabs(tab), tab);
            } catch (Exception e) {
                JavascriptExecutor exe = (JavascriptExecutor) DriverManager.getWebDriver();
                exe.executeScript("argument[0].click();", DriverAction.getElement(DashboardHeaderLocators.ticketTabs(tab)));
            }
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            List<WebElement> elements = new ArrayList<>();
            if (!DriverAction.isExist(TableAndPaginationLocators.noTableDataImg)) {
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                elements = DriverAction.getElements(SupportTicketLocators.vipTag);
                DriverAction.scrollIntoView(TableAndPaginationLocators.nextPageButton);
                boolean nextActive = DriverManager.getWebDriver().findElement(TableAndPaginationLocators.nextPageButton).isEnabled();
                while (nextActive) {
                    DriverAction.scrollIntoView(TableAndPaginationLocators.nextPageButton);
                    elements.addAll(DriverAction.getElements(SupportTicketLocators.vipTag));
                    DriverAction.click(TableAndPaginationLocators.nextPageButton, "Next button");
                    if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                        CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                    }
                    nextActive = DriverManager.getWebDriver().findElement(TableAndPaginationLocators.nextPageButton).isEnabled();
                }
            } else
                GemTestReporter.addTestStep("Table results", "No data displayed for " + tab + " tickets", STATUS.INFO, DriverAction.takeSnapShot());
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }

            String[] displayedCountVal = DriverAction.getElementText(DashboardHeaderLocators.ticketTabs(tab)).replace("\"", "").split(" ");
            String displayedCount = (displayedCountVal[displayedCountVal.length - 1]).replace("(", "").replace(")", "").trim();
            if (displayedCount.trim().equals(String.valueOf(elements.size()).trim())) {
                GemTestReporter.addTestStep("Ticket count", "Ticket count matched for " + tab + " tickets", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Ticket count", "Ticket count not matched for " + tab + " tickets- Actual: " + elements.size() + " Expected: " + displayedCount.replace("(", "").replace(")", "") + "", STATUS.FAIL, DriverAction.takeSnapShot());
            }


        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @When("Search for dept {string} and assigned to {string}")
    public void searchForDeptAndAssignedTo(String dept, String assign) {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 5);
            }
            DriverAction.scrollIntoView(TicketLocators.createTicket);
            DriverAction.waitUntilElementClickable(SupportTicketLocators.otherTicketDept, 5);
            DriverAction.click(SupportTicketLocators.otherTicketDept, "Choose Department");
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(dept), 5);
            DriverAction.click(TicketLocators.ticketDropdownOptions(dept), dept);
            DriverAction.waitSec(1);
            DriverAction.waitUntilElementClickable(SupportTicketLocators.otherTicketAssign, 5);
            DriverAction.click(SupportTicketLocators.otherTicketAssign, "Choose Assigned");
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(assign), 5);
            DriverAction.click(TicketLocators.ticketDropdownOptions(assign), assign);
            DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchButton, 5);
            DriverAction.click(SearchAndSortLocators.ticketSearchButton, "Search");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify VIP table results for others tab")
    public void verifyVIPTableResultsForOthersTab() {
        try {
            List<WebElement> elements = new ArrayList<>();
            if (!DriverAction.isExist(TableAndPaginationLocators.noTableDataImg)) {
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                }
                elements = DriverAction.getElements(SupportTicketLocators.vipTag);
                DriverAction.scrollIntoView(TableAndPaginationLocators.nextPageButton);
                boolean nextActive = DriverManager.getWebDriver().findElement(TableAndPaginationLocators.nextPageButton).isEnabled();
                while (nextActive) {
                    DriverAction.scrollIntoView(TableAndPaginationLocators.nextPageButton);
                    elements.addAll(DriverAction.getElements(SupportTicketLocators.vipTag));
                    DriverAction.click(TableAndPaginationLocators.nextPageButton, "Next button");
                    if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                        CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
                    }
                    nextActive = DriverManager.getWebDriver().findElement(TableAndPaginationLocators.nextPageButton).isEnabled();
                }
            } else
                GemTestReporter.addTestStep("Table results", "No data displayed for tickets", STATUS.INFO, DriverAction.takeSnapShot());

            String displayedRows = DriverAction.getElementText(TableAndPaginationLocators.displayedRows).split("of")[1].trim();
            if (Integer.parseInt(displayedRows) == elements.size()) {
                GemTestReporter.addTestStep("VIP filter validation", "VIP filter results validated successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("VIP filter validation", "Failed validation for VIP filter rows", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify ticket details page access for {string} dept")
    public void verifyTicketDetailsPageAccess(String dept) {
        try {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            if (DriverAction.isExist(TableAndPaginationLocators.firstTicketID) && dept.equalsIgnoreCase("IT")) {
                GemTestReporter.addTestStep("Ticket details access", "Ticket details can be accessed for own dept", STATUS.PASS, DriverAction.takeSnapShot());
            } else if (DriverAction.isExist(TableAndPaginationLocators.firstTicketID) && !dept.equalsIgnoreCase("IT")) {
                GemTestReporter.addTestStep("Ticket details access", "Ticket details can be accessed for other dept", STATUS.FAIL, DriverAction.takeSnapShot());
            } else if (!DriverAction.isExist(TableAndPaginationLocators.firstTicketID) && !dept.equalsIgnoreCase("IT")) {
                GemTestReporter.addTestStep("Ticket details access", "Ticket details can not be accessed for other dept", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Ticket details access", "Ticket details can not be accessed for own dept", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }


}
