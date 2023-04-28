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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class EmployeeTicketStepDefinition {

    @Given("Create incident with file and subject {string}, dept {string} and description {string}")
    public void createIncidentWithFile(String subject, String dept, String desc) {
        try {
            String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\sample.pdf";
            CommonUtils.createIncidentTicket(subject, desc, dept, filePath);
            CommonUtils.verifyIncidentPreview("Incident", subject, desc, dept, true);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @Given("Create incident without file and subject {string}, dept {string} and description {string}")
    public void createIncidentWithoutFile(String subject, String dept, String desc) {
        try {
            String filePath = "";
            CommonUtils.createIncidentTicket(subject, desc, dept, filePath);
            CommonUtils.verifyIncidentPreview("Incident", subject, desc, dept, false);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }


    @Given("Create request with subject {string}, dept {string}, category {string}, sub-category {string} and description {string}")
    public void createRequestWithAttachment(String subject, String dept, String category, String subCategory, String desc) {

        try {
            String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\sample.pdf";
            CommonUtils.createRequestTicket(subject, desc, dept, category, subCategory, filePath);
            CommonUtils.verifyRequestPreview("Request", subject, desc, dept, category, subCategory, true);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @Given("Create request without file and with subject {string}, dept {string}, category {string}, sub-category {string} and description {string}")
    public void createRequestWithoutFile(String subject, String dept, String category, String subCategory, String desc) {

        try {
            String filePath = "";
            CommonUtils.createRequestTicket(subject, desc, dept, category, subCategory, filePath);
            CommonUtils.verifyRequestPreview("Request", subject, desc, dept, category, subCategory, false);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }


    @When("Submit and cancel the ticket with reason {string}")
    public void submitAndCancelTicket(String reason) {

        try {
            DriverAction.click(EmployeeTicketLocators.submitModalForm);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementAppear(EmployeeTicketLocators.ticketCreatedLogo, 10);
            DriverAction.waitSec(1);
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.postSubmitContinueButton, 10);
            String ticketID = DriverAction.getElementText(EmployeeTicketLocators.postSubmitTicketID).replace("Ticket ID: ", "");
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.postSubmitContinueButton, 6);
            DriverAction.click(EmployeeTicketLocators.postSubmitContinueButton);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(EmployeeDashboardLocators.ticketSearchButton, 10);
            DriverAction.typeText(EmployeeDashboardLocators.ticketSearchBox, ticketID);
            DriverAction.waitSec(1);
            DriverAction.click(EmployeeDashboardLocators.ticketSearchButton);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(EmployeeDashboardLocators.firstTicketID, 10);

            if (DriverAction.isExist(EmployeeDashboardLocators.firstTicketID)) {
                DriverAction.click(EmployeeDashboardLocators.firstTicketID);
                if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
                }
                DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketCancelButton, 10);
                DriverAction.waitSec(1);
                DriverAction.click(EmployeeTicketLocators.ticketCancelButton);
                DriverAction.waitSec(2);
                DriverAction.click(EmployeeTicketLocators.confirmCancelButton, "Cancel");
                if (DriverAction.isExist(EmployeeTicketLocators.inputError)) {
                    GemTestReporter.addTestStep("Required field", "Validated cancel reason required field", STATUS.PASS, DriverAction.takeSnapShot());
                    DriverAction.typeText(EmployeeTicketLocators.ticketCancelReasonBox, reason);
                    DriverAction.waitSec(1);
                    DriverAction.click(EmployeeTicketLocators.confirmCancelButton, "Cancel");
                    if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                        CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
                    }
                    DriverAction.waitUntilElementClickable(EmployeeDashboardLocators.firstTicketID, 10);
                } else
                    GemTestReporter.addTestStep("Required field", "Validation failed for cancel reason required field", STATUS.FAIL, DriverAction.takeSnapShot());

                DriverAction.waitUntilElementClickable(EmployeeDashboardLocators.ticketSearchButton, 10);
                DriverAction.typeText(EmployeeDashboardLocators.ticketSearchBox, ticketID);
                DriverAction.waitSec(1);
                DriverAction.click(EmployeeDashboardLocators.ticketSearchButton);
                if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
                }
                DriverAction.waitUntilElementClickable(EmployeeDashboardLocators.firstTicketID, 10);


            } else GemTestReporter.addTestStep("Ticket Search", "Not found", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @Then("Verify if ticket is cancelled")
    public void verifyIfTicketIsCancelled() {
        try {
            if (DriverAction.getElementText(EmployeeDashboardLocators.firstTicketStatus).equalsIgnoreCase("Cancelled") && !DriverManager.getWebDriver().findElement(EmployeeTicketLocators.ticketActionButton).isEnabled()) {
                GemTestReporter.addTestStep("Ticket status", "Ticket cancelled successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Ticket status", "Ticket status is not cancelled", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Submit and verify the incident details having file {string}, subject {string}, dept {string} and description {string}")
    public void submitAndVerifyIncident(String file, String subject, String dept, String desc) {
        try {
            DriverAction.click(EmployeeTicketLocators.submitModalForm);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(EmployeeTicketLocators.ticketCreatedLogo));
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.postSubmitContinueButton, 10);
            DriverAction.waitSec(1);
            if (DriverAction.getElementText(EmployeeTicketLocators.postSubmitBannerTitle).equalsIgnoreCase("Ticket Created")) {
                GemTestReporter.addTestStep("Ticket Creation", "Ticket created successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Ticket Creation", "Unable to successfully create ticket", STATUS.FAIL, DriverAction.takeSnapShot());
            String category = "Unassigned";
            String subCategory = "Unassigned";
            String status = "Unassigned";
            boolean fileFlag = file.equalsIgnoreCase("attached");

            CommonUtils.verifyTicketDetails("Incident", dept, desc, category, subCategory, status, fileFlag);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @Then("Submit and verify the request details having file {string}, subject {string}, dept {string}, description {string}, category {string} and sub-category {string}")
    public void submitAndVerifyRequest(String file, String subject, String dept, String desc, String category, String subCategory) {
        try {
            DriverAction.click(EmployeeTicketLocators.submitModalForm);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(EmployeeTicketLocators.ticketCreatedLogo));

            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.postSubmitContinueButton, 10);
            DriverAction.waitSec(1);
            if (DriverAction.getElementText(EmployeeTicketLocators.postSubmitBannerTitle).equalsIgnoreCase("Ticket Created")) {
                GemTestReporter.addTestStep("Ticket Creation", "Ticket created successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Ticket Creation", "Unable to successfully create ticket", STATUS.FAIL, DriverAction.takeSnapShot());

            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.postSubmitContinueButton, 6);
            String status;
            if (category.equalsIgnoreCase("access request") || subCategory.equalsIgnoreCase("domain change")) {
                dept = "IT";
                status = "Unassigned";
            }
            else if (subCategory.contains("Courier") || subCategory.contains("Lunch") || subCategory.contains("Face")) {
                dept = "Admin";
                status = "Unassigned";
            } else {
                dept = "Unassigned";
                status = "L1 Approval Pending";
            }
            boolean fileFlag = file.equalsIgnoreCase("attached");
            CommonUtils.verifyTicketDetails("Request", dept, desc, category, subCategory, status, fileFlag);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }


    @Then("Verify if action button is active for the ticket")
    public void verifyIfActionButtonIsActiveForTheTicket() {
        try {
            DriverAction.click(EmployeeTicketLocators.submitModalForm);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementAppear(EmployeeTicketLocators.ticketCreatedLogo, 10);
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.postSubmitContinueButton, 10);
            String ticketID = DriverAction.getElementText(EmployeeTicketLocators.postSubmitTicketID).replace("Ticket ID: ", "");
            DriverAction.click(EmployeeTicketLocators.postSubmitContinueButton);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(EmployeeDashboardLocators.ticketSearchButton, 10);
            DriverAction.typeText(EmployeeDashboardLocators.ticketSearchBox, ticketID);
            DriverAction.waitSec(1);
            DriverAction.click(EmployeeDashboardLocators.ticketSearchButton);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketActionButton, 10);
            if (DriverManager.getWebDriver().findElement(EmployeeTicketLocators.ticketActionButton).isEnabled()) {
                GemTestReporter.addTestStep("Action button", "Action button is active for the new ticket", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Action button", "Action button is not active for the new ticket", STATUS.FAIL, DriverAction.takeSnapShot());

            DriverAction.scrollIntoView(EmployeeTicketLocators.ticketActionButton);
            DriverAction.click(EmployeeTicketLocators.ticketActionButton, "Action button");
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketCancelButton, 10);
            DriverAction.click(EmployeeTicketLocators.ticketCancelButton);
            DriverAction.waitSec(2);
            DriverAction.typeText(EmployeeTicketLocators.ticketCancelReasonBox, "reason");
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.confirmCancelButton, 10);
            DriverAction.click(EmployeeTicketLocators.confirmCancelButton);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }

            DriverAction.waitUntilElementClickable(EmployeeDashboardLocators.firstTicketID, 10);
            DriverAction.waitUntilElementClickable(EmployeeDashboardLocators.ticketSearchButton, 10);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.typeText(EmployeeDashboardLocators.ticketSearchBox, ticketID);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.click(EmployeeDashboardLocators.ticketSearchButton);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            if (!DriverManager.getWebDriver().findElement(EmployeeTicketLocators.ticketActionButton).isEnabled()) {
                GemTestReporter.addTestStep("Action button", "Action button is not active for the cancelled ticket", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Action button", "Action button is active for the cancelled ticket", STATUS.FAIL, DriverAction.takeSnapShot());


        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Given("Create a ticket for incident with subject {string}, desc {string} and dept {string}")
    public void createATicketForTypeWithSubjectDescAndDept(String subject, String desc, String dept) {
        try {
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.createTicket, 10);
            DriverAction.click(EmployeeTicketLocators.createTicket, "Create ticket");
            DriverAction.waitUntilElementAppear(EmployeeTicketLocators.ticketFormHeader, 5);
            DriverAction.typeText(EmployeeTicketLocators.subject, subject);
            DriverAction.typeText(EmployeeTicketLocators.desc, desc);
            DriverAction.click(EmployeeTicketLocators.typeDropdown);
            DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions("Incident"));
            DriverAction.waitSec(2);
            DriverAction.click(EmployeeTicketLocators.ticketDropdown("Department"));
            DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(dept));
            DriverAction.waitSec(2);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @And("Upload file with valid extensions and size")
    public void uploadFileWithValidExtensionsAndSize() {
        try {
            String basePath = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\";
            List<String> files = new ArrayList<>();
            files.add("1MB_JPG.jpg");
            files.add("1MB_PPT.ppt");
            files.add("100KB_DOC.doc");
            files.add("300KB_CSV.csv");
            files.add("431KB_GIF.gif");
            files.add("png-1mb.png");
            files.add("sample.pdf");
            files.add("sample_xls.xls");
            for (String file : files) {
                DriverAction.scrollToBottom();
                DriverAction.fileUpload(EmployeeTicketLocators.fileUpload, basePath + file);
                DriverAction.waitSec(5);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if {string} file can be uploaded")
    public void verifyIfFileCanBeUploaded(String fileFlag) {
        try {
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.scrollIntoView(EmployeeTicketLocators.previewButton);
            DriverAction.waitSec(2);
            DriverAction.click(EmployeeTicketLocators.previewButton, "Preview button");
            DriverAction.waitSec(2);
            if (fileFlag.equalsIgnoreCase("valid") && DriverAction.isExist(EmployeeTicketLocators.previewAttachments)) {
                GemTestReporter.addTestStep("Attachment validation", "Files uploaded successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else if (fileFlag.equalsIgnoreCase("invalid") && !DriverAction.isExist(EmployeeTicketLocators.previewAttachments)) {
                GemTestReporter.addTestStep("Attachment validation", "Unsupported files not uploaded", STATUS.PASS, DriverAction.takeSnapShot());
            } else if (fileFlag.equalsIgnoreCase("valid") && !DriverAction.isExist(EmployeeTicketLocators.previewAttachments)) {
                GemTestReporter.addTestStep("Attachment validation", "Files expected but not uploaded successfully", STATUS.FAIL, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Attachment validation", "Unsupported files uploaded", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @And("Upload file with invalid extensions and size")
    public void uploadFileWithInvalidExtensionsAndSize() {
        try {
            String file1 = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\1MB_MP3.mp3";
            DriverAction.fileUpload(EmployeeTicketLocators.fileUpload, file1);
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.previewButton, 60);
            String file2 = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\png-5mb.png";
//            DriverAction.fileUpload(TicketLocators.fileUpload, file2);

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }


    @Given("Create a ticket for request with subject {string}, desc {string}, category {string}, sub-category {string} and dept {string}")
    public void createATicketForRequestWithSubjectDescCategorySubCategoryAndDept(String subject, String desc, String category, String subCat, String dept) {
        try {
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.createTicket, 10);
            DriverAction.click(EmployeeTicketLocators.createTicket, "Create ticket");
            DriverAction.waitUntilElementAppear(EmployeeTicketLocators.ticketFormHeader, 5);
            DriverAction.typeText(EmployeeTicketLocators.subject, subject);
            DriverAction.typeText(EmployeeTicketLocators.desc, desc);
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.typeDropdown, 10);
            DriverAction.click(EmployeeTicketLocators.typeDropdown);
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdownOptions("Request"), 10);
            DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions("Request"));
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdown("Department"), 10);
            DriverAction.click(EmployeeTicketLocators.ticketDropdown("Department"));
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdownOptions(dept), 10);
            DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(dept));
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdown("Category"), 10);
            DriverAction.click(EmployeeTicketLocators.ticketDropdown("Category"));
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdownOptions(category), 10);
            DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(category));
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdown("Sub-category"), 10);
            DriverAction.click(EmployeeTicketLocators.ticketDropdown("Sub-category"));
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdownOptions(subCat), 10);
            DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(subCat));

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Given("Open create ticket form")
    public void openCreateTicketForm() {
        try {
            CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover,20);
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.createTicket,5);
            DriverAction.click(EmployeeTicketLocators.createTicket, "Create ticket");
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementAppear(EmployeeTicketLocators.ticketDropdown("Department"), 5);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify subject character limit and required field values")
    public void verifySubjectCharacterLimit() {
        try {
            if (DriverAction.isExist(SupportTicketLocators.typeDropdownInfo)) {
                DriverAction.hoverOver(SupportTicketLocators.typeDropdownInfo);
            } else {
                DriverAction.hoverOver(EmployeeTicketLocators.typeDropdownInfo);
            }
            DriverAction.waitSec(1);
            if (DriverAction.isExist(EmployeeTicketLocators.toolTip)) {
                GemTestReporter.addTestStep("Type info tooltip", "Tooltip visible on hover", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Type info tooltip", "Tooltip not visible on hover", STATUS.FAIL, DriverAction.takeSnapShot());

            if (DriverAction.isExist(EmployeeTicketLocators.submitModalForm)) {
                DriverAction.click(EmployeeTicketLocators.submitModalForm, "Submit");
            } else DriverAction.click(EmployeeTicketLocators.submitForm, "Submit");

            List<WebElement> elements = DriverAction.getElements(EmployeeTicketLocators.inputError);
            if (elements.size() == 6 || elements.size() == 4) {
                GemTestReporter.addTestStep("Required fields validation", "Warning for all required fields are displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Required fields validation", "Warning for all required fields are not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < 75; i++) {
                text.append("a");
            }
            DriverAction.typeText(EmployeeTicketLocators.subject,"Insert characters in Subject","Try entering more than 70 characters", text.toString());
            String actualVal = DriverAction.getAttributeName(EmployeeTicketLocators.subject, "value");
            if (actualVal.length() == 70) {
                GemTestReporter.addTestStep("Subject field character limit", "Maximum 70 characters inserted as per limit", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Subject field character limit", "Character limit exceeds from 70 characters", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }


    @Then("Verify if comment {string} can be added for the ticket")
    public void verifyIfCommentsCanBeAddedForTheTicket(String text) {
        try {
            DriverAction.scrollIntoView(EmployeeTicketLocators.ticketCommentSubmit);
            DriverAction.typeText(EmployeeTicketLocators.ticketCommentInput, text);
            DriverAction.waitSec(1);
            DriverAction.click(EmployeeTicketLocators.ticketCommentSubmit, "Update comment");
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            if (DriverAction.isExist(EmployeeTicketLocators.lastTicketComments) && DriverAction.getElementText(EmployeeTicketLocators.lastTicketComments).equalsIgnoreCase(text)) {
                DriverAction.scrollIntoView(EmployeeTicketLocators.lastTicketComments);
                GemTestReporter.addTestStep("Ticket comments", "Comments posted successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Ticket comments", "Comments are not added to the ticket", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Validate comment with file uploads on ticket page")
    public void validateFileUploadsToTicketPage() {
        try {
            DriverAction.scrollIntoView(EmployeeTicketLocators.fileUpload);
            String basePath = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\";
            List<String> files = new ArrayList<>();
            files.add("1MB_JPG.jpg");
            files.add("1MB_PPT.ppt");
            files.add("100KB_DOC.doc");
            files.add("300KB_CSV.csv");
            files.add("431KB_GIF.gif");
            files.add("png-1mb.png");
            files.add("sample.pdf");
            files.add("sample_xls.xls");
            for (String file : files) {
                DriverAction.fileUpload(EmployeeTicketLocators.fileUpload, basePath + file);
                if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 200);
                }
                DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketCommentSubmit, 10);
            }

            DriverAction.typeText(EmployeeTicketLocators.ticketCommentInput, "valid files here");
            DriverAction.scrollIntoView(EmployeeTicketLocators.ticketCommentSubmit);
            DriverAction.click(EmployeeTicketLocators.ticketCommentSubmit, "Update comment");
            CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 60);
            if (DriverAction.getElements(EmployeeTicketLocators.lastTicketCommentDocs).size() == files.size()) {
                GemTestReporter.addTestStep("Comment attachments", "Valid files attached successfully in comment", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Comment attachments", "Valid attachments are not successfully updated", STATUS.FAIL, DriverAction.takeSnapShot());

            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketCommentInput, 60);
            DriverAction.fileUpload(EmployeeTicketLocators.fileUpload, basePath + "1MB_RTF.rtf");
            CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 30);
            DriverAction.typeText(EmployeeTicketLocators.ticketCommentInput, "invalid files here");
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketCommentSubmit, 10);
            DriverAction.click(EmployeeTicketLocators.ticketCommentSubmit, "Update comment");
            CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 60);
            DriverAction.waitSec(3);
            if (!DriverAction.isExist(EmployeeTicketLocators.lastTicketCommentDocs)) {
                GemTestReporter.addTestStep("Comment attachment", "Invalid files not attached", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Comment attachment", "Invalid files attached", STATUS.FAIL, DriverAction.takeSnapShot());


        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if copy paste is available for input fields")
    public void verifyIfCopyPasteIsAvailableForInputFields() {
        try {
            String text = "copy-paste";
            DriverAction.typeText(EmployeeTicketLocators.subject,text);
            Actions builder = new Actions(DriverManager.getWebDriver());
            builder.keyDown( Keys.CONTROL ).sendKeys( "a" ).keyUp( Keys.CONTROL ).build().perform();
            builder.keyDown( Keys.CONTROL ).sendKeys( "c" ).keyUp( Keys.CONTROL ).build().perform();
            builder.keyDown( Keys.CONTROL ).sendKeys( "x" ).keyUp( Keys.CONTROL ).build().perform();
            DriverAction.waitSec(1);
            if(!(DriverAction.getAttributeName(EmployeeTicketLocators.subject,"value").length() >1)) {
                builder.keyDown( Keys.CONTROL ).sendKeys( "v" ).keyUp( Keys.CONTROL ).build().perform();
                if(DriverAction.getAttributeName(EmployeeTicketLocators.subject,"value").equalsIgnoreCase(text)) {
                    GemTestReporter.addTestStep("Copy paste text","Copy paste available for subject input",STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Copy paste text","Copy paste not available for subject input",STATUS.FAIL,DriverAction.takeSnapShot());
            }

            DriverAction.typeText(EmployeeTicketLocators.desc,text);
            builder.keyDown( Keys.CONTROL ).sendKeys( "a" ).keyUp( Keys.CONTROL ).build().perform();
            builder.keyDown( Keys.CONTROL ).sendKeys( "c" ).keyUp( Keys.CONTROL ).build().perform();
            builder.keyDown( Keys.CONTROL ).sendKeys( "x" ).keyUp( Keys.CONTROL ).build().perform();
            DriverAction.waitSec(1);
            if(!(DriverAction.getAttributeName(EmployeeTicketLocators.subject,"value").length() >1)) {
                builder.keyDown( Keys.CONTROL ).sendKeys( "v" ).keyUp( Keys.CONTROL ).build().perform();
                if(DriverAction.getAttributeName(EmployeeTicketLocators.subject,"value").equalsIgnoreCase(text)) {
                    GemTestReporter.addTestStep("Copy paste text","Copy paste available for description input",STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Copy paste text","Copy paste not available for description input",STATUS.FAIL,DriverAction.takeSnapShot());
            }


        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }
}
