package com.qa.helpdesk.locators;

import org.openqa.selenium.By;

public class DashboardHeaderLocators {

    public static By headerButtons(String option) {
        return By.xpath("//img[@alt='"+option+"']");
    }
    public static By loaderCover = By.xpath("//div[contains(@class,'Loader')]");
    public static By myTicketsHeader = By.xpath("//div[contains(@class,'tabs_tabItem')]");
    public static By pdfHeader = By.xpath("//span[contains(text(),'Helpdesk User Guide')]");
    public static By contact = By.xpath("//p[contains(text(),'@geminisolutions.com')]");
    public static By closeContact = By.xpath("//button[@class='btn-close']");
    public static By notificationContainer = By.xpath("//div[contains(@class,'notification_container')]");
    public static By notificationButton = By.xpath("//img[@alt='notification']/parent::button");
    public static By unreadNotifications = By.xpath("//div[contains(text(),'Unread')]");
    public static By unreadCount = By.xpath("//div[contains(@class,'header_count')]/span");
    public static By notificationReadButton = By.xpath("//span[contains(@class,'notification_check')]");
    public static By notificationListContainer = By.xpath("//div[contains(@class,'notification_mainBody')]");
    public static By notificationList = By.xpath("//li[contains(@class,'notification_list')]");
    public static By notificationListAlert = By.xpath("//div[contains(@class,'notification_alert')]");
    public static By showMoreNotifications = By.xpath("//u[contains(@class,'showMore')]");
}
