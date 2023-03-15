package com.gemini.locators;

import org.openqa.selenium.By;

public class Locator {
    public static By login_button = By.xpath("//button[contains(text(),'Login')]");
    public static By ms_email = By.xpath("//input[@type='email']");
    public static By ms_pswd = By.xpath("//input[@type='password']");
    public static By ms_submit = By.xpath("//input[@type='submit']");
    public static By ms_prompt = By.xpath("//div[contains(text(),'Stay')]");
    public static By userName = By.xpath("//div[contains(@class,'empName')]");

    // Locators for Create ticket form
    public static By create_ticket = By.xpath("//button[contains(text(),'Create')]");
    public static By ticket_form_header = By.xpath("//div[contains(text(),'Create')]");
    public static By subject = By.xpath("//input[@name='subject']");
    public static By desc = By.xpath("//textarea[@name='description']");
    public static By file_upload = By.xpath("//input[@type='file']");

    // Locators for Type Dropdown
    public static By type_dropdown = By.xpath("//label[contains(text(),'Type')]//parent::div/following-sibling::div");
    public static By getIncident_option = By.xpath("//div[contains(text(),'Incident')]");
    public static By getRequest_option = By.xpath("//div[contains(text(),'Request')]");

    // Locators for Department dropdown
    public static By department_dropdown = By.xpath("//label[contains(text(),'Department')]/following-sibling::div");
    public static By getIT_option = By.xpath("//div[contains(text(),'IT')]");
    public static By getHR_option = By.xpath("//div[contains(text(),'HR')]");
    public static By getAccounts_option = By.xpath("//div[contains(text(),'Accounts')]");
    public static By getAdmin_option = By.xpath("//div[contains(text(),'Admin')]");

    // Locators for Category dropdown
    public static By category_dropdown = By.xpath("//label[contains(text(),'Category')]/following-sibling::div");
    public static By getLeaveManage_option = By.xpath("//div[contains(text(),'Leave Management')]");
    public static By getSoftware_option = By.xpath("//div[contains(text(),'Software')]");
    public static By getRegistration_option = By.xpath("//div[contains(text(),'Registration')]");
    public static By getFacility_option = By.xpath("//div[contains(text(),'Facility')]");
    public static By getHardware_option = By.xpath("//div[contains(text(),'Hardware')]");
    public static By getAccess_option = By.xpath("//div[contains(text(),'Access Request')]");
    public static By getTravel_option = By.xpath("//div[contains(text(),'Travel')]");

    // Locators for Sub-category dropdown
    public static By subCategory_dropdown = By.xpath("//label[contains(text(),'Sub-category')]/following-sibling::div");
    public static By getOpensource_option = By.xpath("//div[contains(text(),'OpenSource')]");
    public static By getLicense_option = By.xpath("//div[contains(text(),'License')]");
    public static By getMissedLeaves_option = By.xpath("//div[contains(text(),'Missed Leaves')]");
    public static By getCompOff_option = By.xpath("//div[contains(text(),'Compensatory Off')]");
    public static By getLNSA_option = By.xpath("//div[contains(text(),'LNSA')]");
    public static By getAccessCard_option = By.xpath("//div[contains(text(),'Access card')]");
    public static By getCourier_option = By.xpath("//div[contains(text(),'Courier')]");
    public static By getLunch_option = By.xpath("//div[contains(text(),'Lunch')]");
    public static By getChair_option = By.xpath("//div[contains(text(),'Chair')]");
    public static By getKeyboard_option = By.xpath("//div[contains(text(),'Keyboard')]");
    public static By getDock_option = By.xpath("//div[contains(text(),'Docking')]");
    public static By getMouse_option = By.xpath("//div[contains(text(),'Mouse')]");
    public static By getRAM_option = By.xpath("//div[contains(text(),'RAM')]");
    public static By getLaptop_option = By.xpath("//div[contains(text(),'Laptop')]");
    public static By getMonitor_option = By.xpath("//div[contains(text(),'Monitor')]");
    public static By getOtherDevice_option = By.xpath("//div[contains(text(),'Other')]");
    public static By getMobile_option = By.xpath("//div[contains(text(),'Mobile')]");
    public static By getModifyO365_option = By.xpath("//div[contains(text(),'Modification of DL/o365')]");
    public static By getNewSecurity_option = By.xpath("//div[contains(text(),'New Security group')]");
    public static By getModifySecurity_option = By.xpath("//div[contains(text(),'Modification of security group')]");
    public static By getCreateGroup_option = By.xpath("//div[contains(text(),'Creation Of DL/o365')]");
    public static By getAccommodation_option = By.xpath("//div[contains(text(),'Accommodation')]");
    public static By getTickets_option = By.xpath("//div[contains(text(),'Tickets')]");
    public static By getTravelCards_option = By.xpath("//div[contains(text(),'Travel cards')]");
    public static By getTravelInsurance_option = By.xpath("//div[contains(text(),'Travel insurance')]");
    public static By getTransport_option = By.xpath("//div[contains(text(),'Transport')]");

    // Preview page Locators
    public static By preview_form = By.xpath("//button[text()='Preview']");
    public static By getPreviewSubjectValue = By.xpath("//label[text()='Subject']/following-sibling::p");
    public static By getPreviewDescValue = By.xpath("//label[text()='Description']/following-sibling::p");
    public static By getPreviewDeptValue = By.xpath("//label[text()='Department']/following-sibling::p");
    public static By getPreviewCategoryValue = By.xpath("//label[text()='Category']/following-sibling::p");
    public static By getPreviewSubCategoryValue = By.xpath("//label[text()='Sub-category']/following-sibling::p");
    public static By getPreviewTypeValue = By.xpath("//label[text()='Type']//parent::div/following-sibling::p");
    public static By previewAttachments = By.xpath("//label[text()='Attachments']");

    // Locators for ticket cancellation
    public static By submit_form = By.xpath("//div[contains(@class,'modal')]//button[@type='submit']");
    public static By postSubmitBannerTitle = By.xpath("//div[contains(@class,'modal_header')]");
    public static By getPostSubmitTicketID = By.xpath("//div[contains(@class,'modal-body')]//p[2]");
    public static By postSubmitContinueButton = By.xpath("//button[contains(text(),'Continue')]");
    public static By ticketSearchBox = By.xpath("//input[@name='search']");
    public static By ticketSearchButton = By.xpath("//button[contains(text(),'Search')]");
    public static By ticketID = By.xpath("//tbody//tr[1]//td[1]//a");
    public static By ticketActionButton = By.xpath("//tbody//tr[1]//td[last()]//button");
    public static By ticketCancelButton = By.xpath("//span[contains(text(),'Cancel')]");
    public static By ticketCancelReasonBox = By.xpath("//textarea[@id='userConsent']");
    public static By confirmCancelButton = By.xpath("//button[contains(text(),'Cancel')]");
    public static By ticketStatus = By.xpath("//tbody//tr[1]//td[last()-1]");






}
