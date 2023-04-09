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
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;


public class TicketStepDefinition {

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
            DriverAction.click(TicketLocators.submitForm);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.waitUntilElementAppear(TicketLocators.ticketCreatedLogo,10);
            DriverAction.waitSec(1);
            DriverAction.waitUntilElementClickable(TicketLocators.postSubmitContinueButton,10);
            String ticketID = DriverAction.getElementText(TicketLocators.postSubmitTicketID).replace("Ticket ID: ", "");
            DriverAction.waitUntilElementClickable(TicketLocators.postSubmitContinueButton, 6);
            DriverAction.click(TicketLocators.postSubmitContinueButton);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchButton,10);
            DriverAction.typeText(SearchAndSortLocators.ticketSearchBox, ticketID);
            DriverAction.waitSec(1);
            DriverAction.click(SearchAndSortLocators.ticketSearchButton);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.waitUntilElementClickable(TableAndPaginationLocators.firstTicketID,10);

            if (DriverAction.isExist(TableAndPaginationLocators.firstTicketID)) {
                DriverAction.click(TableAndPaginationLocators.firstTicketID);
                if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
                }
                DriverAction.waitUntilElementClickable(TicketLocators.ticketCancelButton,10);
                DriverAction.waitSec(1);
                DriverAction.click(TicketLocators.ticketCancelButton);
                DriverAction.waitSec(2);
                DriverAction.click(TicketLocators.confirmCancelButton, "Cancel");
                if (DriverAction.isExist(TicketLocators.inputError)) {
                    GemTestReporter.addTestStep("Required field", "Validated cancel reason required field", STATUS.PASS, DriverAction.takeSnapShot());
                    DriverAction.typeText(TicketLocators.ticketCancelReasonBox, reason);
                    DriverAction.waitSec(1);
                    DriverAction.click(TicketLocators.confirmCancelButton, "Cancel");
                    if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                        DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
                    }
                    DriverAction.waitUntilElementClickable(TableAndPaginationLocators.firstTicketID,10);
                } else
                    GemTestReporter.addTestStep("Required field", "Validation failed for cancel reason required field", STATUS.FAIL, DriverAction.takeSnapShot());

                DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchButton,10);
                DriverAction.typeText(SearchAndSortLocators.ticketSearchBox, ticketID);
                DriverAction.waitSec(1);
                DriverAction.click(SearchAndSortLocators.ticketSearchButton);
                if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
                }
                DriverAction.waitUntilElementClickable(TableAndPaginationLocators.firstTicketID,10);


            } else GemTestReporter.addTestStep("Ticket Search", "Not found", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @Then("Verify if ticket is cancelled")
    public void verifyIfTicketIsCancelled() {
        try {
            if (DriverAction.getElementText(TableAndPaginationLocators.firstTicketStatus).equalsIgnoreCase("Cancelled") && !DriverManager.getWebDriver().findElement(TicketLocators.ticketActionButton).isEnabled()) {
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
            DriverAction.click(TicketLocators.submitForm);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(),30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(TicketLocators.ticketCreatedLogo));
            DriverAction.waitUntilElementClickable(TicketLocators.postSubmitContinueButton, 10);
            DriverAction.waitSec(1);
            if (DriverAction.getElementText(TicketLocators.postSubmitBannerTitle).equalsIgnoreCase("Ticket Created")) {
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
            DriverAction.click(TicketLocators.submitForm);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(),30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(TicketLocators.ticketCreatedLogo));

            DriverAction.waitUntilElementClickable(TicketLocators.postSubmitContinueButton, 10);
            DriverAction.waitSec(1);
            if (DriverAction.getElementText(TicketLocators.postSubmitBannerTitle).equalsIgnoreCase("Ticket Created")) {
                GemTestReporter.addTestStep("Ticket Creation", "Ticket created successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Ticket Creation", "Unable to successfully create ticket", STATUS.FAIL, DriverAction.takeSnapShot());

            DriverAction.waitUntilElementClickable(TicketLocators.postSubmitContinueButton, 6);
            String status = "";
            if (category.equalsIgnoreCase("access request")) {
                dept = "IT";
                status = "Unassigned";
            } else if (subCategory.contains("Courier") || subCategory.contains("Lunch") || subCategory.contains("Face")) {
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
            DriverAction.click(TicketLocators.submitForm);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.waitUntilElementAppear(TicketLocators.ticketCreatedLogo,10);
            DriverAction.waitUntilElementClickable(TicketLocators.postSubmitContinueButton, 10);
            String ticketID = DriverAction.getElementText(TicketLocators.postSubmitTicketID).replace("Ticket ID: ", "");
            DriverAction.click(TicketLocators.postSubmitContinueButton);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchButton, 10);
            DriverAction.typeText(SearchAndSortLocators.ticketSearchBox, ticketID);
            DriverAction.waitSec(1);
            DriverAction.click(SearchAndSortLocators.ticketSearchButton);
            DriverAction.waitSec(3);
            if (DriverManager.getWebDriver().findElement(TicketLocators.ticketActionButton).isEnabled()) {
                GemTestReporter.addTestStep("Action button", "Action button is active for the new ticket", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Action button", "Action button is not active for the new ticket", STATUS.FAIL, DriverAction.takeSnapShot());
            DriverAction.click(TicketLocators.ticketActionButton, "Action button");
            DriverAction.click(TicketLocators.ticketCancelButton);
            DriverAction.waitSec(2);
            DriverAction.typeText(TicketLocators.ticketCancelReasonBox, "reason");
            DriverAction.waitSec(2);
            DriverAction.click(TicketLocators.confirmCancelButton);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchButton, 10);
            DriverAction.typeText(SearchAndSortLocators.ticketSearchBox, ticketID);
            DriverAction.waitSec(1);
            DriverAction.click(SearchAndSortLocators.ticketSearchButton);
            DriverAction.waitSec(2);
            if (!DriverManager.getWebDriver().findElement(TicketLocators.ticketActionButton).isEnabled()) {
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
            DriverAction.waitUntilElementClickable(TicketLocators.createTicket, 10);
            DriverAction.click(TicketLocators.createTicket, "Create ticket");
            DriverAction.waitUntilElementAppear(TicketLocators.ticketFormHeader, 5);
            DriverAction.typeText(TicketLocators.subject, subject);
            DriverAction.typeText(TicketLocators.desc, desc);
            DriverAction.click(TicketLocators.typeDropdown);
            DriverAction.click(TicketLocators.ticketDropdownOptions("Incident"));
            DriverAction.waitSec(2);
            DriverAction.click(TicketLocators.ticketDropdown("Department"));
            DriverAction.click(TicketLocators.ticketDropdownOptions(dept));
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
                DriverAction.fileUpload(TicketLocators.fileUpload, basePath + file);
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
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            DriverAction.scrollIntoView(TicketLocators.previewButton);
            DriverAction.waitSec(2);
            DriverAction.click(TicketLocators.previewButton, "Preview button");
            DriverAction.waitSec(2);
            if (fileFlag.equalsIgnoreCase("valid") && DriverAction.isExist(TicketLocators.previewAttachments)) {
                GemTestReporter.addTestStep("Attachment validation", "Files uploaded successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else if (fileFlag.equalsIgnoreCase("invalid") && !DriverAction.isExist(TicketLocators.previewAttachments)) {
                GemTestReporter.addTestStep("Attachment validation", "Unsupported files not uploaded", STATUS.PASS, DriverAction.takeSnapShot());
            } else if (fileFlag.equalsIgnoreCase("valid") && !DriverAction.isExist(TicketLocators.previewAttachments)) {
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
            String file1 = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\1MB_RTF.rtf";
            DriverAction.fileUpload(TicketLocators.fileUpload, file1);
            DriverAction.waitUntilElementClickable(TicketLocators.previewButton, 60);
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
            DriverAction.waitUntilElementClickable(TicketLocators.createTicket, 10);
            DriverAction.click(TicketLocators.createTicket, "Create ticket");
            DriverAction.waitUntilElementAppear(TicketLocators.ticketFormHeader, 5);
            DriverAction.typeText(TicketLocators.subject, subject);
            DriverAction.typeText(TicketLocators.desc, desc);
            DriverAction.waitUntilElementClickable(TicketLocators.typeDropdown,10);
            DriverAction.click(TicketLocators.typeDropdown);
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions("Request"),10);
            DriverAction.click(TicketLocators.ticketDropdownOptions("Request"));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdown("Department"),10);
            DriverAction.click(TicketLocators.ticketDropdown("Department"));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(dept),10);
            DriverAction.click(TicketLocators.ticketDropdownOptions(dept));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdown("Category"),10);
            DriverAction.click(TicketLocators.ticketDropdown("Category"));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(category),10);
            DriverAction.click(TicketLocators.ticketDropdownOptions(category));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdown("Sub-category"),10);
            DriverAction.click(TicketLocators.ticketDropdown("Sub-category"));
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(subCat),10);
            DriverAction.click(TicketLocators.ticketDropdownOptions(subCat));

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Given("Open create ticket form")
    public void openCreateTicketForm() {
        try {
            DriverAction.click(TicketLocators.createTicket, "Create ticket");
            DriverAction.waitUntilElementAppear(TicketLocators.ticketFormHeader, 5);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify subject character limit and required field values")
    public void verifySubjectCharacterLimit() {
        try {
            DriverAction.click(TicketLocators.submitForm, "Submit");
            List<WebElement> elements = DriverAction.getElements(TicketLocators.inputError);
            if (elements.size() == 4) {
                GemTestReporter.addTestStep("Required fields validation", "Warning for all required fields are displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Required fields validation", "Warning for all required fields are not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            StringBuilder text= new StringBuilder();
            for(int i=0;i<75;i++){
                text.append("a");
            }
            DriverAction.typeText(TicketLocators.subject, text.toString());
            String actualVal = DriverAction.getAttributeName(TicketLocators.subject, "value");
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
            DriverAction.scrollIntoView(TicketLocators.ticketCommentSubmit);
            DriverAction.typeText(TicketLocators.ticketCommentInput, text);
            DriverAction.waitSec(1);
            DriverAction.click(TicketLocators.ticketCommentSubmit, "Update comment");
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover))
            {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            if (DriverAction.isExist(TicketLocators.lastTicketComments) && DriverAction.getElementText(TicketLocators.lastTicketComments).equalsIgnoreCase(text)) {
                DriverAction.scrollIntoView(TicketLocators.lastTicketComments);
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
            DriverAction.scrollIntoView(TicketLocators.fileUpload);
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
                DriverAction.fileUpload(TicketLocators.fileUpload, basePath + file);
                if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 100);
                }
                DriverAction.waitUntilElementClickable(TicketLocators.ticketCommentSubmit, 10);
            }

            DriverAction.typeText(TicketLocators.ticketCommentInput, "valid files here");
            DriverAction.scrollIntoView(TicketLocators.ticketCommentSubmit);
            DriverAction.click(TicketLocators.ticketCommentSubmit, "Update comment");
            DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 60);
            if (DriverAction.getElements(TicketLocators.lastTicketCommentDocs).size() == files.size()) {
                GemTestReporter.addTestStep("Comment attachments", "Valid files attached successfully in comment", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Comment attachments", "Valid attachments are not successfully updated", STATUS.FAIL, DriverAction.takeSnapShot());

            DriverAction.waitUntilElementClickable(TicketLocators.ticketCommentInput, 60);
            DriverAction.fileUpload(TicketLocators.fileUpload, basePath + "1MB_RTF.rtf");
            DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 30);
            DriverAction.typeText(TicketLocators.ticketCommentInput, "invalid files here");
            DriverAction.waitUntilElementClickable(TicketLocators.ticketCommentSubmit, 10);
            DriverAction.click(TicketLocators.ticketCommentSubmit, "Update comment");
            DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 60);
            DriverAction.waitSec(3);
            if (!DriverAction.isExist(TicketLocators.lastTicketCommentDocs)) {
                GemTestReporter.addTestStep("Comment attachment", "Invalid files not attached", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Comment attachment", "Invalid files attached", STATUS.FAIL, DriverAction.takeSnapShot());


        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }
}
