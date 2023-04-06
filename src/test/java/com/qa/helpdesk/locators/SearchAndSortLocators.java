package com.qa.helpdesk.locators;

import org.openqa.selenium.By;

public class SearchAndSortLocators {

    public static By ticketSearchBox = By.xpath("//input[@name='search']");
    public static By ticketSearchButton = By.xpath("//button[contains(text(),'Search')]");
    public static By clearSearchText = By.xpath("//div[contains(@class,'cross')]");
    public static By columns(String cols) {
        return By.xpath("//th[text()='"+cols+"']/span");
    }

}
