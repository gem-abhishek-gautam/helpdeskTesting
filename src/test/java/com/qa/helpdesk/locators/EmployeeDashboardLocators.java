package com.qa.helpdesk.locators;

import org.openqa.selenium.By;

public class EmployeeDashboardLocators {
    public static By headerButtons(String option) {
        return By.xpath("//img[@alt='"+option+"']");
    }
    public static By ticketTabs(String tab) {
        return By.xpath("//div[contains(text(),'"+tab+"')]");
    }
    public static By loaderCover = By.xpath("//div[contains(@class,'Loader')]");
    public static By firstTicketID = By.xpath("//tbody//tr[1]//td[1]//a/u");
    public static By firstTicketStatus = By.xpath("//tbody//tr[1]//td[last()-1]");
    public static By getColValues(String pos) {
        if(pos.equals("1")) {
            return By.xpath("//tbody/tr//td["+pos+"]//u");
        } else return By.xpath("//tbody/tr//td["+pos+"]");
    }
    public static By getTableColNames = By.xpath("//thead//th");
    public static By getAllTableElements = By.xpath("//tbody//td");
    public static By ticketSearchBox = By.xpath("//input[@name='search']");
    public static By ticketSearchButton = By.xpath("//button[contains(text(),'Search')]");
    public static By loadScreen = By.xpath("//div[@class='Loader_cover__LDxAU']");
    public static By loggedUserName = By.xpath("//div[@class='leftmenu_empName__hP5QR']");
    public static By loggedUserDesignation = By.xpath("//div[@class='leftmenu_empRole__I-c4k']");
    public static By idCol = By.xpath("//tr/td/a");
    public static By tickedID = By.xpath("(//tr/td/a)[1]");
    public static By subjectCol = By.xpath("//tr/td[2]");
    public static By departmentCol = By.xpath("//tbody/tr/td[3]");
    public static By callerCol = By.xpath("//tbody/tr/td[3]");
    public static By createdOnCol = By.xpath("//tbody/tr/td[5]");
    public static By createdOnColSupportView = By.xpath("//tbody/tr/td[4]");
    public static By assignedToCol = By.xpath("//tbody/tr/td[6]");
    public static By assignedToColSupportView = By.xpath("//tbody/tr/td[5]");
    public static By statusCol = By.xpath("//tbody/tr/td[7]");
    public static By statusColSupportView = By.xpath("//tbody/tr/td[6]");
    //SearchTestCases
    public static By searchBoxCrossIcon = By.xpath("//img[@alt='cross']");
    public static By searchBox = By.xpath("//input[@id='search']");
    public static By searchBtn = By.xpath("//button[@type=\"submit\" and text()='Search']");
    public static By resultList = By.xpath("//tbody/tr");
    public static By noData = By.xpath("//img[@alt='No Data !']");
    //CalendarTestCases

    public static By weekDayCount = By.xpath("//span[@class='rdrWeekDay']");
    public static By clearData = By.xpath("//button[text()='Clear Data']");
    public static By filterBtn = By.xpath("(//button[@type='submit'])[2]");

    //FilterTestCases
    public static By filterTicketsToggle = By.xpath("//input[@type='checkbox']");

    public static By crossIcon = By.xpath("//img[@alt=\"cross icon\"]");
    public static By userGuideIcon = By.xpath("//img[@alt='userGuide']");
    public static By userGuideTitle = By.xpath("//span[text()='Helpdesk User Guide.pdf']");
    public static By notificationIcon = By.xpath("//button[@type='button']/img[@alt='notification']");
    public static By unreadOption = By.xpath("//div[text()='Unread']");
    public static By notificationHeading = By.xpath("//span[text()='Notifications']");
    public static By notificationCheckIcon = By.xpath("//span/img[@alt='check']");
    public static By showMore = By.xpath("//u[text()='Show More']");
    public static By notificationsList = By.xpath("//div[@class=\"notification_mainBody__E6eFf\"]//li");
    public static By firstNotification = By.xpath("(//div[@class=\"notification_mainBody__E6eFf\"]//li)[1]");
    public static By noNewNotifications = By.xpath("//div[text()='No new notification !!']");
    public static By contactUs = By.xpath("//img[@alt='Support']");
    public static By contactEmail = By.xpath("//p[text()='support.helpdesk@geminisolutions.com']");
    public static By sideMenuButton = By.xpath("//img[@alt='toggle icon']");
    public static By sideMenuMyTickets = By.xpath("//a[@class='menuitem_navItem__xo3ke menuitem_menuItem__FmRos active']//span[contains(text(),'My Tickets')]");
    public static By sideMenuTicketManagement = By.xpath("//a[@class='menuitem_navItem__xo3ke menuitem_menuItem__FmRos active']");
    public static By unreadNotificationHeaderCount = By.xpath("//div[@class=\"header_count__-dTHO\"]");
    public static By checkIcon = By.xpath("//img[@alt='check']");
    public static By rowsPerPageDropDown = By.xpath("//select[@aria-label=\"rows per page\"]");
    public static By numberOfRows = By.xpath("//tbody/tr");
    public static By previousBtn = By.xpath("//button[@title='Go to previous page']");
    public static By nextBtn = By.xpath("//button[@title='Go to next page']");
    public static By markAllReadIconChecked = By.xpath("//img[@src='../assets/ICON_Check_Active.svg']");
    public static By nextPageBtn = By.xpath("//button[@title='Go to next page']");
    public static By supportView = By.xpath("//div[text()='Support View']");
    public static By employeeView = By.xpath("//div[text()='Employee View']");
    public static By departmentTypeDetails = By.xpath("//div[text()='Department']//following-sibling::div");
    public static By monthCount = By.xpath("//span[@class=\"rdrMonthPicker\"]//option");

    public static By headerName(String headName) {
        return By.xpath("//div[text()='" + headName + "']");
    }

    //SortingTestCases
    //public static By idBtn = By.xpath("//th[@id='ID']");
    public static By getSortingHeading(String headingName) {
        return By.xpath("//*[text()= '" + headingName + "']");
    }

    public static By departmentName(String name) {
        return By.xpath("//button[text()='" + name + "']");
    }

    public static By statusName(String name) {
        return By.xpath("//button[text()='" + name + "']");
    }

    public static By elementById(String elementType, String idValue) {
        return switch (elementType) {
            case "image" -> By.xpath("//img[@id='" + idValue + "']");
            case "input" -> By.xpath("//input[@id='" + idValue + "']");
            case "textarea" -> By.xpath("//textarea[@id='" + idValue + "']");
            default -> null;
        };
    }

    public static By rowPerPageDownArrow(String value) {
        return By.xpath("//option[@value='" + value + "']");
    }

    public static By elementByClass(String elementType, String classValue) {
        return switch (elementType) {
            case "select" -> By.xpath("//select[@class='" + classValue + "']");
            case "u" -> By.xpath("//u[@class='" + classValue + "']");
            case "tr" -> By.xpath("//tr[@class='" + classValue + "']");
            case "span" -> By.xpath("//span[@class='" + classValue + "']");
            case "para" -> By.xpath("//p[@class='" + classValue + "']");
            case "div" -> By.xpath("//div[@class='" + classValue + "']");
            case "input" -> By.xpath("//input[@class='" + classValue + "']");
            case "thead" -> By.xpath("//thead[@class='" + classValue + "']");
            case "sortIDColumn" -> By.xpath("//u[@class='Table_ticketId__x+qd2']");
            case "sortSubjectColumn" ->
                    By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-q34dxg'][2]");
            case "sortAssignedToColumn" ->
                    By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-q34dxg'][3]");
            case "sortStatusColumn" ->
                    By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-q34dxg'][4]");
            case "sortCreatedOnColumn" ->
                    By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignLeft MuiTableCell-sizeMedium css-q34dxg'][3]");
            case "sortDepartmentColumn" ->
                    By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignLeft MuiTableCell-sizeMedium css-q34dxg'][1]");
            case "calenderData" -> By.xpath("//tr//td[5]");
            case "button" -> By.xpath("//button[@class='" + classValue + "']");
            default -> null;
        };
    }

    public static By elementWithText(String elementType, String elementText) {
        return switch (elementType) {
            case "button" -> By.xpath("//button[contains(text(),'" + elementText + "')]");
            case "div" -> By.xpath("//div[contains(text(),'" + elementText + "')]");
            case "para" -> By.xpath("//p[contains(text(),'" + elementText + "')]");
            case "image" -> By.xpath("//img[@alt='" + elementText + "']");
            case "span" -> By.xpath("//span[contains(text(),'" + elementText + "')]");
            case "filterRowData" -> By.xpath("//tr//td[contains(text(),'" + elementText + "')]");
            case "label" -> By.xpath("//label[contains(text(),'" + elementText + "')]");
            case "u" -> By.xpath("//u[contains(text(),'" + elementText + "')]");
            default -> null;
        };
    }

    public static By highlightedDateInCalendar = By.xpath("//button[.//span[@class='rdrStartEdge rdrEndEdge']]//span[2]//span");
    public static By calendarMonthOptions = By.xpath("//span[@class='rdrMonthPicker']//option");
    public static By calendarYearOptions = By.xpath("//span[@class='rdrYearPicker']//option");
    public static By selectCustomYear(String year) {
        return By.xpath("//span[@class='rdrYearPicker']//option[contains(text(),'" + year + "')]");
    }
    public static By selectCustomMonth(String month) {
        switch (month) {
            case "01" -> month = "January";
            case "02" -> month = "February";
            case "03" -> month = "March";
            case "04" -> month = "April";
            case "05" -> month = "May";
            case "06" -> month = "June";
            case "07" -> month = "July";
            case "08" -> month = "August";
            case "09" -> month = "September";
            case "10" -> month = "October";
            case "11" -> month = "November";
            case "12" -> month = "December";
        }
        return By.xpath("//span[@class='rdrMonthPicker']//option[contains(text(),'" + month + "')]");
    }

    public static By selectCustomDate(String startDay) {
        return By.xpath("//span[@class='rdrDayNumber']//span[contains(text(),'" + startDay + "')]");

    }

    public static By selectMonth = By.xpath("//span[contains(@class,'rdrMonthPicker')]//select");
    public static By selectYear = By.xpath("//span[contains(@class,'rdrYearPicker')]//select");

}
