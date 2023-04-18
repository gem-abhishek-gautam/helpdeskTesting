package com.qa.helpdesk.locators;


import org.openqa.selenium.By;

public class TableAndPaginationLocators {

    public static By paginationDropdown = By.xpath("//select[contains(@aria-label,'rows')]");
    public static By displayedRows = By.xpath("//p[contains(@class,'displayedRows')]");
    public static By nextPageButton = By.xpath("//button[contains(@aria-label,'next page')]");
    public static By prevPageButton = By.xpath("//button[contains(@aria-label,'previous page')]");
    public static By firstTicketID = By.xpath("//tbody//tr[1]//td[1]//a/u");
    public static By firstTicketStatus = By.xpath("//tbody//tr[1]//td[last()-1]");
    public static By getColValues(String pos) {
        if(pos.equals("1")) {
            return By.xpath("//tbody/tr//td["+pos+"]//u");
        } else return By.xpath("//tbody/tr//td["+pos+"]");
    }
    public static By getAssignValues = By.xpath("//tbody/tr//td[5]//span");
    public static By getStatusValues = By.xpath("//tbody/tr//td[6]//span");
    public static By getTableRows = By.xpath("//tbody/tr");
    public static By getTableColNames = By.xpath("//thead//th");
    public static By getAllTableElements = By.xpath("//tbody//td");
    public static By noTableDataImg = By.xpath("//img[contains(@alt,'No Data')]");
}
