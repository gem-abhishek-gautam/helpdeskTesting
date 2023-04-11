package com.qa.helpdesk.locators;

import org.openqa.selenium.By;

public class LoginLocators {
    public static By loginButton = By.xpath("//button[contains(text(),'Login')]");
    public static By loginEmail = By.xpath("//input[@type='email']");
    public static By loginPswd = By.xpath("//input[@type='password']");
    public static By loginError = By.xpath("//div[contains(@class,'ext-error')]");
    public static By submitLoginForm = By.xpath("//input[@type='submit']");
    public static By rejectPrompt = By.xpath("//input[@value='No']");
    public static By microsoftLoginPrompt = By.xpath("//div[contains(text(),'Stay')]");
    public static By employeeName = By.xpath("//div[contains(@class,'empName')]");
    public static By viewDropdown = By.xpath("//div[contains(@class,'control')]");
    public static By getView(String view) {
        return By.xpath("//div[text()='"+view+"']");
    }

}
