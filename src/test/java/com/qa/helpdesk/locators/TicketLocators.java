package com.qa.helpdesk.locators;


import org.openqa.selenium.By;

public class TicketLocators {
    public static By createTicket = By.xpath("//button[contains(text(),'Create')]");
    public static By ticketFormHeader = By.xpath("//div[contains(text(),'Create')]");
    public static By inputError = By.xpath("//span[contains(@class,'inputError')]");
    public static By typeDropdownInfo = By.xpath("//label//following-sibling::div/img[@alt='info']");
    public static By toolTip = By.xpath("//div[contains(@class,'Tooltip-popper')]");
    public static By reqLabel = By.xpath("//span[text()='*']");
    public static By subject = By.xpath("//input[@name='subject']");
    public static By desc = By.xpath("//textarea[@name='description']");
    public static By fileUpload = By.xpath("//input[@type='file']");
    public static By ticketCreatedLogo = By.xpath("//img[contains(@alt,'created')]");
    public static By callerMenu(String name) {
      return By.xpath("//div[contains(@id,'listbox')]//div[contains(text(),'"+name+"')]");
    }
    public static By callerNameInput = By.xpath("//div[contains(text(),'Search')]/following-sibling::div/input");
    public static By configInput = By.xpath("//label[contains(text(),'Config')]/following-sibling::div//input");
    public static By getCallerName = By.xpath("//div[contains(@class,'header_labelValue')]");
    public static By typeDropdown = By.xpath("//label[contains(text(),'Type')]/parent::div/following-sibling::div/div");
    public static By getPreviewTypeValue = By.xpath("//label[text()='Type']//parent::div/following-sibling::p");
    public static By ticketDropdownOptions(String options) {
        return By.xpath("//div[contains(text(),'"+options+"')]");
    }
    public static By ticketDropdown(String options) {
        return By.xpath("//label[contains(text(),'"+options+"')]/following-sibling::div");
    }
    public static By ticketPreview(String options) {
        return By.xpath("//label[contains(text(),'"+options+"')]/following-sibling::p");
    }
    public static By ticketDetails(String options) {
        return By.xpath("//div[contains(text(),'"+options+"')]/following-sibling::div");
    }
    public static By previewButton = By.xpath("//button[text()='Preview']");
    public static By previewAttachments = By.xpath("//label[text()='Attachments']");
    public static By submitModalForm = By.xpath("//div[contains(@class,'modal')]//button[@type='submit']");
    public static By submitForm = By.xpath("//button[@type='submit']");
    public static By postSubmitBannerTitle = By.xpath("//div[contains(@class,'modal_header')]");
    public static By postSubmitTicketID = By.xpath("//div[contains(@class,'modal-body')]//p[2]");
    public static By postSubmitContinueButton = By.xpath("//button[contains(text(),'Continue')]");
    public static By ticketActionButton = By.xpath("//tbody//tr[1]//td[last()]//button");
    public static By ticketCancelButton = By.xpath("//span[contains(text(),'Cancel')]");
    public static By ticketCancelReasonBox = By.xpath("//textarea[@id='userConsent']");
    public static By confirmCancelButton = By.xpath("//button[contains(text(),'Cancel')]");
    public static By ticketDetailsCard = By.xpath("//div[contains(@class,'issueCard')]");
    public static By ticketDetailsAttachment = By.xpath("//div[contains(@class,'uploadedFile')]");
    public static By timelineToggle = By.xpath("//div[text()='Show Timeline']");
    public static By lastTimelineStatus = By.xpath("//li[contains(@class,'timeline_step')][last()]//div[contains(@class,'timeline_status')]");
    public static By ticketCommentInput = By.xpath("//textarea[contains(@class,'comment')]");
    public static By ticketCommentSubmit = By.xpath("//button[text()='Update']");
    public static By lastTicketComments = By.xpath("//li[contains(@class,'comments_list')][1]//div[contains(@class,'comments_comment')]");
    public static By lastTicketCommentDocs = By.xpath("//li[contains(@class,'comments_list')][1]//div[contains(@class,'comments_documentValue')]");

}
