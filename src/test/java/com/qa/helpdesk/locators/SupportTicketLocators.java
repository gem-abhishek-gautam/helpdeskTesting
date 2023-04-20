package com.qa.helpdesk.locators;

import org.openqa.selenium.By;

public class SupportTicketLocators {

    public static By typeDropdownInfo = By.xpath("//label//following-sibling::img[@alt='info']");
    public static By detailsCallerInfoButton = By.xpath("//img[contains(@class,'info')]/parent::span");
    public static By formCallerInfoButton = By.xpath("//img[contains(@alt,'info')]/parent::span");
    public static By detailsCallerHistoryButton = By.xpath("//img[contains(@class,'history')]/parent::span");
    public static By formCallerHistoryButton = By.xpath("//img[contains(@alt,'history')]/parent::span");
    public static By callerInfoModal = By.xpath("//div[contains(text(),'Caller Details')]");
    public static By closeModal = By.xpath("//button[@class='btn-close']");
    public static By callerHistory = By.xpath("//div[contains(text(),'Past')]");
    public static By statusChangeMenu(String status) {
        return By.xpath("//div[contains(text(),'"+status+"')]");
    }
    public static By resolutionInput = By.xpath("//textarea[@name='resolutionRemark']");
    public static By auditTrailButton = By.xpath("//img[@alt='auditTrail']");
    public static By backButton = By.xpath("//img[@alt='home']");
    public static By toggleVIP(String mode) {
        if(mode.equalsIgnoreCase("enable")) {
            return By.xpath("//button[contains(@class,'vipClicked')]");
        } else return By.xpath("//button[contains(@class,'vip_')]");
    }
    public static By vipTag = By.xpath("//img[@alt='vipTag']");
    public static By editTicketButton = By.xpath("//img[contains(@class,'edit')]");
    public static By ticketManageButton = By.xpath("//a[contains(@href,'dashboard')]");
    public static By otherTicketDept = By.xpath("//div[contains(@class,'othertickets_input')]/div");
    public static By otherTicketAssign = By.xpath("//div[contains(@class,'assignedTo')]/div");

}
