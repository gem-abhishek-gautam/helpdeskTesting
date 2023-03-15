package com.gemini.stepDefinitions;

import com.gemini.generic.exception.GemException;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.locators.Locator;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import javassist.bytecode.LocalVariableAttribute;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;

import static com.gemini.generic.ui.utils.DriverAction.getElementText;

public class StepDefinition {

    @Before
    public void setup() throws GemException {
        DriverManager.setUpBrowser();
    }

    @Given("navigate to helpdesk and login")
    public void navigatedToHelpdeskAndLogin() throws InterruptedException {
        DriverAction.waitUntilElementAppear(Locator.login_button,10);
        if(DriverAction.isExist(Locator.login_button)) {
            DriverAction.click(Locator.login_button);
            DriverAction.waitSec(5);
            DriverAction.typeText(Locator.ms_email,"abhishek.gautam@geminisolutions.com");
            DriverAction.click(Locator.ms_submit);
            DriverAction.waitSec(5);
            DriverAction.typeText(Locator.ms_pswd,"a.aa.aa...1");
            DriverAction.click(Locator.ms_submit);
            DriverAction.waitSec(5);
            if(DriverAction.isExist(Locator.ms_prompt)){
                DriverAction.click(Locator.ms_submit);
            }
            DriverAction.waitSec(10);
        } else GemTestReporter.addTestStep("Login button","Login button not found", STATUS.FAIL);

    }

    @Given("verify if login is successful")
    public void verifyIfLoginIsSuccessful() {
        String expected_url = "https://helpdeskui-np.geminisolutions.com/#/dashboard/0";
        String actual_url = DriverAction.getCurrentURL();
        if(expected_url.equalsIgnoreCase(actual_url)){
            GemTestReporter.addTestStep("Login to dashboard","Login is successful",STATUS.PASS);
        } else GemTestReporter.addTestStep("Login to dashboard","Login is unsuccessful \nActual: "+actual_url+" \nExpected: "+expected_url,STATUS.FAIL);

    }

    @And("check if the logged in user name is {string}")
    public void checkLoggedInUser(String name) {
        DriverAction.waitUntilElementAppear(Locator.userName,10);
        if(DriverAction.isExist(Locator.userName) && DriverAction.getElementText(Locator.userName).equals(name)){
            GemTestReporter.addTestStep("Verify user","Employee Name matched",STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify user","Employee Name not matched",STATUS.FAIL);
    }
    @Given("create incident with subject {string}, dept {string} and description {string}")
    public void createTicketWithSubjectDescription(String subject, String dept, String desc) {
        if(DriverAction.isExist(Locator.create_ticket)){
            DriverAction.click(Locator.create_ticket);
            DriverAction.waitUntilElementAppear(Locator.ticket_form_header,5);
            DriverAction.typeText(Locator.subject,subject);
            DriverAction.typeText(Locator.desc,desc);
            DriverAction.fileUpload(Locator.file_upload,"C:\\Users\\abhishek.gautam\\Downloads\\sample.pdf");
            DriverAction.click(Locator.type_dropdown);
            DriverAction.click(Locator.getIncident_option);
            DriverAction.waitSec(2);
            DriverAction.click(Locator.department_dropdown);
            if(dept.equalsIgnoreCase("admin")){
                DriverAction.click(Locator.getAdmin_option);
            } else if (dept.equalsIgnoreCase("HR")) {
                DriverAction.click(Locator.getHR_option);
            } else if (dept.equalsIgnoreCase("IT")) {
                DriverAction.click(Locator.getIT_option);
            } else if (dept.equalsIgnoreCase("Accounts")){
                DriverAction.click(Locator.getAccounts_option);
            } else GemTestReporter.addTestStep("Department Value","Value not found: "+dept,STATUS.INFO);
            DriverAction.waitSec(2);
            DriverAction.click(Locator.preview_form);
            DriverAction.waitSec(5);
        }
    }

    @Given("create incident without file and subject {string}, dept {string} and description {string}")
    public void createIncidentWithoutFile(String subject, String dept, String desc) {
        if(DriverAction.isExist(Locator.create_ticket)){
            DriverAction.click(Locator.create_ticket);
            DriverAction.waitUntilElementAppear(Locator.ticket_form_header,5);
            DriverAction.typeText(Locator.subject,subject);
            DriverAction.typeText(Locator.desc,desc);
            DriverAction.click(Locator.type_dropdown);
            DriverAction.click(Locator.getIncident_option);
            DriverAction.waitSec(2);
            DriverAction.click(Locator.department_dropdown);
            if(dept.equalsIgnoreCase("admin")){
                DriverAction.click(Locator.getAdmin_option);
            } else if (dept.equalsIgnoreCase("HR")) {
                DriverAction.click(Locator.getHR_option);
            } else if (dept.equalsIgnoreCase("IT")) {
                DriverAction.click(Locator.getIT_option);
            } else if (dept.equalsIgnoreCase("Accounts")){
                DriverAction.click(Locator.getAccounts_option);
            } else GemTestReporter.addTestStep("Department Value","Value not found: "+dept,STATUS.INFO);

            DriverAction.waitSec(2);
            DriverAction.click(Locator.preview_form);
            DriverAction.waitSec(5);
        }
    }

    @Then("verify if ticket created successfully with subject {string}, dept {string}, description {string} and attachment")
    public void verifyTicketWithAttachment(String subject, String dept, String desc) {
        String subjectActual = DriverAction.getElementText(Locator.getPreviewSubjectValue);
        String descActual = DriverAction.getElementText(Locator.getPreviewDescValue);
        String deptActual = DriverAction.getElementText(Locator.getPreviewDeptValue);
        String typeActual = DriverAction.getElementText(Locator.getPreviewTypeValue);
        if(typeActual.equalsIgnoreCase("Incident")){
            GemTestReporter.addTestStep("Verify ticket type","Ticket type expected: Incident Actual: "+typeActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify ticket type","Ticket type expected: Incident Actual: "+typeActual,STATUS.FAIL);

        if(subjectActual.equalsIgnoreCase(subject)){
            GemTestReporter.addTestStep("Verify subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.FAIL);

        if(deptActual.equalsIgnoreCase(dept)){
            GemTestReporter.addTestStep("Verify Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.PASS);
        } else if (dept.trim().isBlank() && deptActual.equalsIgnoreCase("unassigned")){
            GemTestReporter.addTestStep("Verify Department","Department unassigned",STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.FAIL);

        if(descActual.equalsIgnoreCase(desc)){
            GemTestReporter.addTestStep("Verify Description","Description expected: "+desc+" Actual: "+descActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Description","Description expected: "+desc+" Actual: "+descActual,STATUS.FAIL);

        if(DriverAction.isExist(Locator.previewAttachments)){
            GemTestReporter.addTestStep("Verify Attachment","Attachment found",STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Attachment","Attachment not found",STATUS.PASS);

        DriverAction.click(Locator.submit_form);
        DriverAction.waitSec(5);
        if(DriverAction.getElementText(Locator.postSubmitBannerTitle).equalsIgnoreCase("Ticket Created")){
            GemTestReporter.addTestStep("Ticket Creation","Ticket created successfully",STATUS.PASS);
        } else GemTestReporter.addTestStep("Ticket Creation","Ticket not created successfully",STATUS.FAIL);
    }


    @Then("verify if ticket created successfully with subject {string}, dept {string}, description {string} and without attachment")
    public void verifyTicketWithoutAttachment(String subject, String dept, String desc) {
        String subjectActual = DriverAction.getElementText(Locator.getPreviewSubjectValue);
        String descActual = DriverAction.getElementText(Locator.getPreviewDescValue);
        String deptActual = DriverAction.getElementText(Locator.getPreviewDeptValue);
        String typeActual = DriverAction.getElementText(Locator.getPreviewTypeValue);

        if(typeActual.equalsIgnoreCase("Incident")){
            GemTestReporter.addTestStep("Verify ticket type","Ticket type expected: Incident Actual: "+typeActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify ticket type","Ticket type expected: Incident Actual: "+typeActual,STATUS.FAIL);

        if(subjectActual.equalsIgnoreCase(subject)){
            GemTestReporter.addTestStep("Verify subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.FAIL);

        if(deptActual.equalsIgnoreCase(dept)){
            GemTestReporter.addTestStep("Verify Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.PASS);
        } else if (dept.trim().isBlank() && deptActual.equalsIgnoreCase("unassigned")){
            GemTestReporter.addTestStep("Verify Department","Department unassigned",STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.FAIL);

        if(descActual.equalsIgnoreCase(desc)){
            GemTestReporter.addTestStep("Verify Description","Description expected: "+desc+" Actual: "+descActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Description","Description expected: "+desc+" Actual: "+descActual,STATUS.FAIL);

        if(!DriverAction.isExist(Locator.previewAttachments)){
            GemTestReporter.addTestStep("Verify Attachment","File not attached",STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Attachment","Attachment not expected",STATUS.PASS);


        DriverAction.click(Locator.submit_form);
        DriverAction.waitSec(5);
        if(DriverAction.getElementText(Locator.postSubmitBannerTitle).equalsIgnoreCase("Ticket Created")){
            GemTestReporter.addTestStep("Ticket Creation","Ticket created successfully",STATUS.PASS);
        } else GemTestReporter.addTestStep("Ticket Creation","Ticket not created successfully",STATUS.FAIL);
    }

    @Given("create request with subject {string}, dept {string}, category {string}, sub-category {string} and description {string}")
    public void createRequestWithAttachment(String subject, String dept, String category, String subCategory, String desc) {
        if(DriverAction.isExist(Locator.create_ticket)){
            DriverAction.click(Locator.create_ticket);
            DriverAction.waitUntilElementAppear(Locator.ticket_form_header,5);
            DriverAction.typeText(Locator.subject,subject);
            DriverAction.typeText(Locator.desc,desc);
            DriverAction.fileUpload(Locator.file_upload,"C:\\Users\\abhishek.gautam\\Downloads\\sample.pdf");
            DriverAction.click(Locator.type_dropdown);
            DriverAction.click(Locator.getRequest_option);
            DriverAction.waitSec(2);
            DriverAction.click(Locator.department_dropdown);
            if(dept.equalsIgnoreCase("admin")){
                DriverAction.click(Locator.getAdmin_option);
            } else if (dept.equalsIgnoreCase("HR")) {
                DriverAction.click(Locator.getHR_option);
            } else if (dept.equalsIgnoreCase("IT")) {
                DriverAction.click(Locator.getIT_option);
            } else if (dept.equalsIgnoreCase("Accounts")){
                DriverAction.click(Locator.getAccounts_option);
            } else GemTestReporter.addTestStep("Department Value","Value not found: "+dept,STATUS.INFO);
            DriverAction.waitSec(2);
            DriverAction.click(Locator.category_dropdown);
            if(category.equalsIgnoreCase("Leave Management")){
                DriverAction.click(Locator.getLeaveManage_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                if(subCategory.equalsIgnoreCase("Missed leaves")){
                    DriverAction.click(Locator.getMissedLeaves_option);
                } else if (subCategory.equalsIgnoreCase("LNSA")) {
                    DriverAction.click(Locator.getLNSA_option);
                } else if (subCategory.equalsIgnoreCase("Compensatory Off")) {
                    DriverAction.click(Locator.getCompOff_option);
                }
            } else if (category.equalsIgnoreCase("Software")) {
                DriverAction.click(Locator.getSoftware_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                if(subCategory.equalsIgnoreCase("OpenSource")){
                    DriverAction.click(Locator.getOpensource_option);
                } else if (subCategory.equalsIgnoreCase("License")) {
                    DriverAction.click(Locator.getLicense_option);
                }
            } else if (category.equalsIgnoreCase("Registration")) {
                DriverAction.click(Locator.getRegistration_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                if(subCategory.equalsIgnoreCase("access card")){
                    DriverAction.click(Locator.getAccessCard_option);
                }
            } else if (category.equalsIgnoreCase("Facility")){
                DriverAction.click(Locator.getFacility_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                if(subCategory.equalsIgnoreCase("Courier requirements")){
                    DriverAction.click(Locator.getCourier_option);
                } else if (subCategory.equalsIgnoreCase("Lunch in office")) {
                    DriverAction.click(Locator.getLunch_option);
                } else if (subCategory.equalsIgnoreCase("Chair")) {
                    DriverAction.click(Locator.getChair_option);
                }
            } else if (category.equalsIgnoreCase("Hardware")){
                DriverAction.click(Locator.getHardware_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                DriverManager.getWebDriver().findElement(Locator.subCategory_dropdown).sendKeys(Keys.ENTER);
            } else if (category.equalsIgnoreCase("Access Request")){
                DriverAction.click(Locator.getAccess_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                DriverManager.getWebDriver().findElement(Locator.subCategory_dropdown).sendKeys(Keys.ENTER);
            } else if (category.equalsIgnoreCase("Travel")){
                DriverAction.click(Locator.getTravel_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                DriverManager.getWebDriver().findElement(Locator.subCategory_dropdown).sendKeys(Keys.ENTER);
            } else GemTestReporter.addTestStep("Category Value","Value not found: "+category,STATUS.INFO);

            DriverAction.click(Locator.preview_form);
            DriverAction.waitSec(5);
        }

    }

    @Given("create request without file and with subject {string}, dept {string}, category {string}, sub-category {string} and description {string}")
    public void createRequestWithoutFile(String subject, String dept, String category, String subCategory, String desc) {
        if(DriverAction.isExist(Locator.create_ticket)){
            DriverAction.click(Locator.create_ticket);
            DriverAction.waitUntilElementAppear(Locator.ticket_form_header,5);
            DriverAction.typeText(Locator.subject,subject);
            DriverAction.typeText(Locator.desc,desc);
            DriverAction.click(Locator.type_dropdown);
            DriverAction.click(Locator.getRequest_option);
            DriverAction.waitSec(2);
            DriverAction.click(Locator.department_dropdown);
            if(dept.equalsIgnoreCase("admin")){
                DriverAction.click(Locator.getAdmin_option);
            } else if (dept.equalsIgnoreCase("HR")) {
                DriverAction.click(Locator.getHR_option);
            } else if (dept.equalsIgnoreCase("IT")) {
                DriverAction.click(Locator.getIT_option);
            } else if (dept.equalsIgnoreCase("Accounts")){
                DriverAction.click(Locator.getAccounts_option);
            } else GemTestReporter.addTestStep("Department Value","Value not found: "+dept,STATUS.INFO);
            DriverAction.waitSec(2);
            DriverAction.click(Locator.category_dropdown);
            if(category.equalsIgnoreCase("Leave Management")){
                DriverAction.click(Locator.getLeaveManage_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                if(subCategory.equalsIgnoreCase("Missed leaves")){
                    DriverAction.click(Locator.getMissedLeaves_option);
                } else if (subCategory.equalsIgnoreCase("LNSA")) {
                    DriverAction.click(Locator.getLNSA_option);
                } else if (subCategory.equalsIgnoreCase("Compensatory Off")) {
                    DriverAction.click(Locator.getCompOff_option);
                }
            } else if (category.equalsIgnoreCase("Software")) {
                DriverAction.click(Locator.getSoftware_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                if(subCategory.equalsIgnoreCase("OpenSource")){
                    DriverAction.click(Locator.getOpensource_option);
                } else if (subCategory.equalsIgnoreCase("License")) {
                    DriverAction.click(Locator.getLicense_option);
                }
            } else if (category.equalsIgnoreCase("Registration")) {
                DriverAction.click(Locator.getRegistration_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                if(subCategory.equalsIgnoreCase("access card")){
                    DriverAction.click(Locator.getAccessCard_option);
                }
            } else if (category.equalsIgnoreCase("Facility")){
                DriverAction.click(Locator.getFacility_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                if(subCategory.equalsIgnoreCase("Courier requirements")){
                    DriverAction.click(Locator.getCourier_option);
                } else if (subCategory.equalsIgnoreCase("Lunch in office")) {
                    DriverAction.click(Locator.getLunch_option);
                } else if (subCategory.equalsIgnoreCase("Chair")) {
                    DriverAction.click(Locator.getChair_option);
                }
            } else if (category.equalsIgnoreCase("Hardware")){
                DriverAction.click(Locator.getHardware_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                if(subCategory.equalsIgnoreCase("keyboard")){
                    DriverAction.click(Locator.getKeyboard_option);
                } else if (subCategory.equalsIgnoreCase("Mouse")) {
                    DriverAction.click(Locator.getMouse_option);
                } else if (subCategory.equalsIgnoreCase("RAM")) {
                    DriverAction.click(Locator.getRAM_option);
                } else if (subCategory.equalsIgnoreCase("Docking station")) {
                    DriverAction.click(Locator.getDock_option);
                } else if (subCategory.equalsIgnoreCase("Temp laptop")) {
                    DriverAction.click(Locator.getLaptop_option);
                } else if (subCategory.equalsIgnoreCase("Monitor")) {
                    DriverAction.click(Locator.getMonitor_option);
                } else if (subCategory.equalsIgnoreCase("Other devices")) {
                    DriverAction.click(Locator.getOtherDevice_option);
                } else if (subCategory.equalsIgnoreCase("Mobile devices")) {
                    DriverAction.click(Locator.getMobile_option);
                }
            } else if (category.equalsIgnoreCase("Access Request")){
                DriverAction.click(Locator.getAccess_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                if(subCategory.equalsIgnoreCase("modification of DL/o365 group")){
                    DriverAction.click(Locator.getModifyO365_option);
                } else if (subCategory.equalsIgnoreCase("New security group")) {
                    DriverAction.click(Locator.getNewSecurity_option);
                } else if (subCategory.equalsIgnoreCase("Modification of security group")) {
                    DriverAction.click(Locator.getModifySecurity_option);
                } else if (subCategory.equalsIgnoreCase("Creation of DL/o365 group")) {
                    DriverAction.click(Locator.getCreateGroup_option);
                }
            } else if (category.equalsIgnoreCase("Travel")){
                DriverAction.click(Locator.getTravel_option);
                DriverAction.waitSec(2);
                DriverAction.click(Locator.subCategory_dropdown);
                if(subCategory.equalsIgnoreCase("accommodation")){
                    DriverAction.click(Locator.getAccommodation_option);
                } else if (subCategory.equalsIgnoreCase("travel tickets")) {
                    DriverAction.click(Locator.getTickets_option);
                } else if (subCategory.equalsIgnoreCase("travel cards")) {
                    DriverAction.click(Locator.getTravelCards_option);
                } else if (subCategory.equalsIgnoreCase("travel insurance")) {
                    DriverAction.click(Locator.getTravelInsurance_option);
                } else if (subCategory.equalsIgnoreCase("transport")) {
                    DriverAction.click(Locator.getTransport_option);
                }
            } else GemTestReporter.addTestStep("Category Value","Value not found: "+category,STATUS.INFO);

            DriverAction.click(Locator.preview_form);
            DriverAction.waitSec(5);

        }

    }

    @Then("verify if ticket created successfully with subject {string}, dept {string}, category {string}, sub-category {string}, description {string} and attachment")
    public void verifyRequestWithAttachment(String subject, String dept, String category, String subCategory, String desc) {
        String subjectActual = DriverAction.getElementText(Locator.getPreviewSubjectValue);
        String descActual = DriverAction.getElementText(Locator.getPreviewDescValue);
        String deptActual = DriverAction.getElementText(Locator.getPreviewDeptValue);
        String typeActual = DriverAction.getElementText(Locator.getPreviewTypeValue);
        String categoryActual = DriverAction.getElementText(Locator.getPreviewCategoryValue);
        String subCategoryActual = DriverAction.getElementText(Locator.getPreviewSubCategoryValue);

        if(typeActual.equalsIgnoreCase("Incident")){
            GemTestReporter.addTestStep("Verify ticket type","Ticket type expected: Request Actual: "+typeActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify ticket type","Ticket type expected: Request Actual: "+typeActual,STATUS.FAIL);

        if(subjectActual.equalsIgnoreCase(subject)){
            GemTestReporter.addTestStep("Verify subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.FAIL);

        if(deptActual.equalsIgnoreCase(dept)){
            GemTestReporter.addTestStep("Verify Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.PASS);
        } else if (dept.trim().isBlank() && deptActual.equalsIgnoreCase("unassigned")){
            GemTestReporter.addTestStep("Verify Department","Department unassigned",STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.FAIL);

        if(descActual.equalsIgnoreCase(desc)){
            GemTestReporter.addTestStep("Verify Description","Description expected: "+desc+" Actual: "+descActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Description","Description expected: "+desc+" Actual: "+descActual,STATUS.FAIL);

        if(categoryActual.equalsIgnoreCase(category)){
            GemTestReporter.addTestStep("Verify Category","Category expected: "+category+" Actual: "+descActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Category","Category expected: "+category+" Actual: "+descActual,STATUS.FAIL);

        if(subCategoryActual.equalsIgnoreCase(subCategory)){
            GemTestReporter.addTestStep("Verify Sub-Category","Category expected: "+category+" Actual: "+descActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Sub-Category","Sub-Category expected: "+category+" Actual: "+descActual,STATUS.FAIL);


        if(DriverAction.isExist(Locator.previewAttachments)){
            GemTestReporter.addTestStep("Verify Attachment","Attachment found",STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Attachment","Attachment not found",STATUS.PASS);

        DriverAction.click(Locator.submit_form);
        DriverAction.waitSec(5);
        if(DriverAction.getElementText(Locator.postSubmitBannerTitle).equalsIgnoreCase("Ticket Created")){
            GemTestReporter.addTestStep("Ticket Creation","Ticket created successfully",STATUS.PASS);
        } else GemTestReporter.addTestStep("Ticket Creation","Ticket not created successfully",STATUS.FAIL);

    }

    @Then("verify if ticket created successfully with subject {string}, dept {string}, category {string}, sub-category {string}, description {string} and without attachment")
    public void verifyRequestWithoutAttachment(String subject, String dept, String category, String subCategory, String desc) {
        String subjectActual = DriverAction.getElementText(Locator.getPreviewSubjectValue);
        String descActual = DriverAction.getElementText(Locator.getPreviewDescValue);
        String deptActual = DriverAction.getElementText(Locator.getPreviewDeptValue);
        String typeActual = DriverAction.getElementText(Locator.getPreviewTypeValue);
        String categoryActual = DriverAction.getElementText(Locator.getPreviewCategoryValue);
        String subCategoryActual = DriverAction.getElementText(Locator.getPreviewSubCategoryValue);

        if(typeActual.equalsIgnoreCase("Incident")){
            GemTestReporter.addTestStep("Verify ticket type","Ticket type expected: Request Actual: "+typeActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify ticket type","Ticket type expected: Request Actual: "+typeActual,STATUS.FAIL);

        if(subjectActual.equalsIgnoreCase(subject)){
            GemTestReporter.addTestStep("Verify subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.FAIL);

        if(deptActual.equalsIgnoreCase(dept)){
            GemTestReporter.addTestStep("Verify Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.PASS);
        } else if (dept.trim().isBlank() && deptActual.equalsIgnoreCase("unassigned")){
            GemTestReporter.addTestStep("Verify Department","Department unassigned",STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.FAIL);

        if(descActual.equalsIgnoreCase(desc)){
            GemTestReporter.addTestStep("Verify Description","Description expected: "+desc+" Actual: "+descActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Description","Description expected: "+desc+" Actual: "+descActual,STATUS.FAIL);

        if(categoryActual.equalsIgnoreCase(category)){
            GemTestReporter.addTestStep("Verify Category","Category expected: "+category+" Actual: "+descActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Category","Category expected: "+category+" Actual: "+descActual,STATUS.FAIL);

        if(subCategoryActual.equalsIgnoreCase(subCategory)){
            GemTestReporter.addTestStep("Verify Sub-Category","Category expected: "+category+" Actual: "+descActual,STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Sub-Category","Sub-Category expected: "+category+" Actual: "+descActual,STATUS.FAIL);


        if(!DriverAction.isExist(Locator.previewAttachments)){
            GemTestReporter.addTestStep("Verify Attachment","Attachment found",STATUS.PASS);
        } else GemTestReporter.addTestStep("Verify Attachment","Attachment not found",STATUS.PASS);

        DriverAction.click(Locator.submit_form);
        DriverAction.waitSec(5);
        if(DriverAction.getElementText(Locator.postSubmitBannerTitle).equalsIgnoreCase("Ticket Created")){
            GemTestReporter.addTestStep("Ticket Creation","Ticket created successfully",STATUS.PASS);
        } else GemTestReporter.addTestStep("Ticket Creation","Ticket not created successfully",STATUS.FAIL);

    }

    @Then("submit and cancel the ticket with reason {string}")
    public void submitAndCancelTicket(String reason) {
        DriverAction.click(Locator.submit_form);
        DriverAction.waitSec(5);
        String ticketID = DriverAction.getElementText(Locator.getPostSubmitTicketID);
        DriverAction.click(Locator.postSubmitContinueButton);
        DriverAction.waitSec(5);
//        DriverAction.click(Locator.ticketActionButton);
        DriverAction.typeText(Locator.ticketSearchBox,ticketID);
        DriverAction.waitSec(1);
        DriverAction.click(Locator.ticketSearchButton);
        DriverAction.waitSec(3);
        DriverAction.click(Locator.ticketID);
        DriverAction.waitSec(5);

        DriverAction.click(Locator.ticketCancelButton);
        DriverAction.waitSec(2);
        DriverAction.typeText(Locator.ticketCancelReasonBox,reason);
        DriverAction.waitSec(2);
        DriverAction.click(Locator.confirmCancelButton);
        DriverAction.waitSec(7);
        if(DriverAction.getElementText(Locator.ticketStatus).equalsIgnoreCase("Cancelled")){
            GemTestReporter.addTestStep("Ticket status","Ticket cancelled successfully",STATUS.PASS);
        } else GemTestReporter.addTestStep("Ticket status","Ticket status is not cancelled",STATUS.FAIL);
        DriverAction.waitSec(2);
    }
}
