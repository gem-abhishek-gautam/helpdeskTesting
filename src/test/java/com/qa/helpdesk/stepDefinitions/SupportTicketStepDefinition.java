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

public class SupportTicketStepDefinition {
    @Given("Create ticket with file {string} and type {string}, caller {string}, channel {string}, dept {string}, category {string}, sub-category {string}, subject {string}, desc {string} and status {string}")
    public void createTicketWithFileAndTypeCallerSelf(String fileFlag, String type, String caller, String channel, String dept, String category, String subcategory, String subject, String desc, String status) {

        try {
            String filePath = "";
            if (fileFlag.equalsIgnoreCase("attached")) {
                filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\100KB_DOC.doc";
            }
            CommonUtils.createSupportTicket(type, caller, channel, dept, category, subcategory, subject, desc, status, filePath);

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Submit and verify ticket with file {string} and type {string}, caller {string}, channel {string}, dept {string}, category {string}, sub-category {string}, subject {string}, desc {string} and status {string}")
    public void submitAndVerifyTicketWithFileAndTypeCaller(String file, String type, String caller, String channel, String dept, String category, String subcategory, String subject, String desc, String status) {

        try {
            DriverAction.waitUntilElementClickable(TicketLocators.previewButton, 10);
            DriverAction.click(TicketLocators.previewButton, "Preview");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            boolean fileFlag = file.equalsIgnoreCase("attached");
            CommonUtils.verifySupportPreview(fileFlag, type, caller, channel, dept, category, subcategory, subject, desc, status);

            DriverAction.click(TicketLocators.submitForm, "Submit");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(TicketLocators.ticketCreatedLogo));
            DriverAction.waitUntilElementClickable(TicketLocators.postSubmitContinueButton, 10);
            DriverAction.waitSec(1);
            if (DriverAction.getElementText(TicketLocators.postSubmitBannerTitle).equalsIgnoreCase("Ticket Created")) {
                GemTestReporter.addTestStep("Ticket Creation", "Ticket created successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Ticket Creation", "Unable to successfully create ticket", STATUS.FAIL, DriverAction.takeSnapShot());

            CommonUtils.verifySupportTicketDetails(type, dept, desc, category, subcategory, status, caller, channel, fileFlag);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @When("Caller name {string} is provided")
    public void callerNameIsProvided(String name) {
        try {
            DriverAction.waitUntilElementClickable(TicketLocators.callerNameInput, 10);
            DriverAction.typeText(TicketLocators.callerNameInput, name);
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(TicketLocators.callerMenu(name)));
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.callerMenu(name)));
            DriverAction.click(TicketLocators.callerMenu(name), name);

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify caller info and history")
    public void verifyCallerInfoAndHistory() {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            DriverAction.waitUntilElementClickable(SupportTicketLocators.formCallerInfoButton, 10);
            DriverAction.waitUntilElementClickable(SupportTicketLocators.formCallerHistoryButton, 10);

            if (DriverAction.isExist(SupportTicketLocators.formCallerInfoButton) && DriverManager.getWebDriver().findElement(SupportTicketLocators.formCallerInfoButton).isEnabled()) {
                DriverAction.click(SupportTicketLocators.formCallerInfoButton, "Caller info");
                DriverAction.waitSec(1);
                if (DriverAction.isExist(SupportTicketLocators.callerInfoModal)) {
                    GemTestReporter.addTestStep("Caller info", "Caller info is visible", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Caller info", "Caller info is not visible", STATUS.FAIL, DriverAction.takeSnapShot());

                DriverAction.waitUntilElementClickable(SupportTicketLocators.closeModal, 10);
                DriverAction.click(SupportTicketLocators.closeModal, "Close button");
            } else
                GemTestReporter.addTestStep("Caller info", "Caller info button is inactive", STATUS.FAIL, DriverAction.takeSnapShot());

            if (DriverAction.isExist(SupportTicketLocators.formCallerHistoryButton) && DriverManager.getWebDriver().findElement(SupportTicketLocators.formCallerHistoryButton).isEnabled()) {
                DriverAction.click(SupportTicketLocators.formCallerHistoryButton, "Caller history");
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
                }
                if (DriverAction.isExist(SupportTicketLocators.callerHistory)) {
                    GemTestReporter.addTestStep("Caller history", "Caller history is visible", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Caller history", "Caller history is not visible", STATUS.FAIL, DriverAction.takeSnapShot());

            } else
                GemTestReporter.addTestStep("Caller history", "Caller history button is inactive", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Given("Open ticket details for a ticket")
    public void openTicketDetailsForATicket() {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            DriverAction.waitUntilElementAppear(TableAndPaginationLocators.firstTicketID, 5);
            DriverAction.click(TableAndPaginationLocators.firstTicketID, "Ticket");

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if caller info can be opened")
    public void verifyIfCallerInfoCanBeOpened() {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }

            DriverAction.waitUntilElementClickable(SupportTicketLocators.detailsCallerInfoButton, 5);
            DriverAction.click(SupportTicketLocators.detailsCallerInfoButton, "Caller info");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            DriverAction.waitSec(1);
            if (DriverAction.isExist(SupportTicketLocators.callerInfoModal)) {
                GemTestReporter.addTestStep("Caller info", "Caller info is visible", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Caller info", "Caller info is not visible", STATUS.FAIL, DriverAction.takeSnapShot());

            DriverAction.waitUntilElementClickable(SupportTicketLocators.closeModal, 10);
            DriverAction.click(SupportTicketLocators.closeModal, "Close button");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @And("Verify if caller history can be opened")
    public void verifyIfCallerHistoryCanBeOpened() {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            DriverAction.waitUntilElementClickable(SupportTicketLocators.detailsCallerHistoryButton, 5);
            DriverAction.click(SupportTicketLocators.detailsCallerHistoryButton, "Caller history");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            if (DriverAction.isExist(SupportTicketLocators.callerHistory)) {
                GemTestReporter.addTestStep("Caller history", "Caller history is visible", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Caller history", "Caller history is not visible", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify ticket audit trail for {string}")
    public void verifyTicketAuditTrail(String step) {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            DriverAction.waitSec(2);
            DriverAction.waitUntilElementClickable(SupportTicketLocators.auditTrailButton, 5);
            DriverAction.click(SupportTicketLocators.auditTrailButton, "Audit Trail");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.elementToBeClickable(DashboardHeaderLocators.ticketTabs("Trails")));
            DriverAction.waitSec(2);
            String pos = CommonUtils.getTableColPosition("Event Activities");
            List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getColValues(pos));
            if (DriverAction.getElements(TableAndPaginationLocators.getAllTableElements).size() > 1) {
                int flag = 0;
                for (WebElement ele : elements) {
                    if (DriverAction.getElementText(ele).toLowerCase().contains(step.toLowerCase())) {
                        flag = 1;
                        break;
                    }
                }

                if (flag == 1) {
                    GemTestReporter.addTestStep("Audit trail event", "Audit trail event updated", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Audit trail event", "Audit trail event not updated", STATUS.FAIL, DriverAction.takeSnapShot());

            } else {
                GemTestReporter.addTestStep("Audit trail event", "Audit trail event not updated", STATUS.FAIL, DriverAction.takeSnapShot());
            }

            DriverAction.click(SupportTicketLocators.backButton, "Back");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @And("Edit ticket details for field {string} and value {string}")
    public void editTicketDetailsForFieldAndValue(String field, String value) {
        try {
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            DriverAction.click(SupportTicketLocators.editTicketButton, "Edit");
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdown(field), 5);
            if (field.equalsIgnoreCase("Assigned to")) {
                DriverAction.click(TicketLocators.ticketDropdown("Assigned To"), "Assigned To");
                DriverAction.waitSec(1);
                DriverAction.scrollIntoView(TicketLocators.callerMenu(value));
                DriverAction.click(TicketLocators.callerMenu(value), value);
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
                }
                DriverAction.click(TicketLocators.submitModalForm, "Submit");
            }
            if (field.equalsIgnoreCase("configuration")) {
                DriverAction.typeText(TicketLocators.configInput, value);
                DriverAction.waitUntilElementClickable(TicketLocators.submitModalForm, 5);
                DriverAction.click(TicketLocators.submitModalForm, "Submit");
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
                }
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }
}
