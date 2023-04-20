package com.qa.helpdesk.locators;

import org.openqa.selenium.By;

public class FilterAndCalendarLocators {
    //Filters locators
    public static By filterButton = By.xpath("//button[contains(@class,'filter')]");
    public static By clearFilterButton = By.xpath("//button[contains(@class,'filters_clear')]");
    public static By filterToggleButton = By.xpath("//input[@role='switch']");
    public static By filterOptions(String option) {
        return By.xpath("//button[contains(text(),'"+option+"')]");
    }
    public static By closeFilterButton = By.xpath("//div[@class='p-3']/img[@alt='cross icon']");

    // Calendar locators
    public static By calendarButton = By.xpath("//button[contains(@class,'calenderButton')]");
    public static By calendarButtonOther = By.xpath("//div[contains(@class,'calenderButton')]");
    public static By calendarCard = By.xpath("//div[contains(@class,'rdrCalendarWrapper')]");
    public static By calendarMonthPicker = By.xpath("//span[contains(@class,'rdrMonthPicker')]/select");
    public static By calendarYearPicker = By.xpath("//span[contains(@class,'rdrYearPicker')]/select");
    public static By calendarNextButton = By.xpath("//button[contains(@class,'rdrNextButton')]");
    public static By calendarPrevButton = By.xpath("//button[contains(@class,'rdrPprevButton')]");
    public static By calendarClearButton = By.xpath("//button[contains(text(),'Clear Data')]");
    public static By calendarDayToday = By.xpath("//button[contains(@class,'rdrDayToday')]//span[@class='rdrDayNumber']/span");
    public static By dateSelector(String date) {
        return By.xpath("//span[@class='rdrDayNumber']/span[text()='"+date+"']");
    }
    public static By selectedDate = By.xpath("//span[contains(@class,'rdrStartEdge rdrEndEdge')]/following-sibling::span[2]");
    public static By startDate = By.xpath("//span[contains(@class,'rdrStartEdge')]/following-sibling::span");
    public static By endDate = By.xpath("//span[contains(@class,'rdrEndEdge')]/following-sibling::span");
}
