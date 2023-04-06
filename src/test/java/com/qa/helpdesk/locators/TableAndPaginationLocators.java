package com.qa.helpdesk.locators;


import org.openqa.selenium.By;

public class TableAndPaginationLocators {

    public static By paginationDropdown = By.xpath("//select[contains(@aria-label,'rows')]");
    public static By displayedRows = By.xpath("//p[contains(@class,'displayedRows')]");
    public static By nextPageButton = By.xpath("//button[contains(@aria-label,'next page')]");
    public static By prevPageButton = By.xpath("//button[contains(@aria-label,'previous page')]");
    public static By firstTicketID = By.xpath("//tbody//tr[1]//td[1]//a");
    public static By firstTicketStatus = By.xpath("//tbody//tr[1]//td[last()-1]");
    public static By getColValues(String pos) {
        return By.xpath("//tbody/tr//td["+pos+"]");
    }
    public static By getTableRows = By.xpath("//tbody/tr");
    public static By getTableColNames = By.xpath("//thead//th");
    public static By getAllTableElements = By.xpath("//tbody//td");
}
