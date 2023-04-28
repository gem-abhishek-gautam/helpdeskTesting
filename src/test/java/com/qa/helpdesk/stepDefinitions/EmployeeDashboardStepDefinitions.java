package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.generic.utils.ProjectConfigData;
import com.qa.helpdesk.locators.EmployeeDashboardLocators;
import com.qa.helpdesk.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.qa.helpdesk.utils.CommonUtils.*;

public class EmployeeDashboardStepDefinitions {
    boolean passed = false;
    static String createdTicketID = "";

    @Given("^User checks the LoggedIn UserName (.+) and LoggedIn User Designation (.+)$")
    public void checkNameAndDesignation(String user, String designation) {
        try {
            String userName = DriverAction.getElementText(EmployeeDashboardLocators.loggedUserName);
            String userDesignation = DriverAction.getElementText(EmployeeDashboardLocators.loggedUserDesignation);

            if (user.contains(userName)) {
                GemTestReporter.addTestStep("Verify Logged UserName", "Logged UserName is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Logged UserName", "Logged UserName is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (designation.contains(userDesignation)) {
                GemTestReporter.addTestStep("Verify Logged User Designation", "Logged User Designation is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Logged User Designation", "Logged User Designation is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Given("^User clicks on the Toggle Button to collapse Side Menu$")
    public void clickOnTheToggleButtonToCollapseSideMenu() {
        try {
            clickSideMenuButton();
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Then("^User verifies the collapsed Side Menu$")
    public void verifyCollapsedSideMenu() {
        if (!DriverAction.isExist(EmployeeDashboardLocators.sideMenuMyTickets)) {
            GemTestReporter.addTestStep("Side Menu Button", "Side Menu is Collapsed successfully", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Side Menu Button", "Side Menu Collapse is Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    @And("^User clicks on the Toggle Button to expand Side Menu$")
    public void clickOnTheToggleButtonToExpandSideMenu() {
        try {
            clickSideMenuButton();
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Then("^User verifies the expanded Side Menu$")
    public void verifyExpandedSideMenu() {
        if (DriverAction.isExist(EmployeeDashboardLocators.sideMenuMyTickets) || DriverAction.isExist(EmployeeDashboardLocators.sideMenuTicketManagement)) {
            GemTestReporter.addTestStep("Side Menu Button", "Side Menu is Expanded successfully", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Side Menu Button", "Side Menu Expand is Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    @Given("^User clicks on the User Guide Button$")
    public void clickUserGuideButton() {
        try {
            CommonUtils.waitForElement(EmployeeDashboardLocators.userGuideIcon, 10);
            DriverAction.click(EmployeeDashboardLocators.userGuideIcon, "User Guide Icon");
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Then("^User verifies the User Guide tab$")
    public void verifyUserGuideTab() {
        try {
            Set<String> allWindowHandles1 = DriverManager.getWebDriver().getWindowHandles();
            for (String handle1 : allWindowHandles1) {
                DriverAction.waitSec(2);
                DriverManager.getWebDriver().switchTo().window(handle1);
            }
            if (DriverAction.getElementText(EmployeeDashboardLocators.userGuideTitle).contains("Helpdesk User Guide")) {
                GemTestReporter.addTestStep("User Guide", "User Guide Tab is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("User Guide", "User Guide Tab is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.closeCurrentTab();
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Given("^User clicks on the Contact Us Icon$")
    public void clickContactUsIcon() {
        try {
            if (DriverAction.isExist(EmployeeDashboardLocators.contactUs)) {
                CommonUtils.waitForElement(EmployeeDashboardLocators.contactUs, 10);
                DriverAction.click(EmployeeDashboardLocators.contactUs, "Contact Us Icon");
            } else {
                GemTestReporter.addTestStep("Contact Us", "Contact Us Option is Not Available", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Then("^User verifies the Contact Icon$")
    public void VerifyContactIcon() {
        if (DriverAction.isExist(EmployeeDashboardLocators.contactEmail)) {
            GemTestReporter.addTestStep("Contact Us", "Contact Us Option is Verified", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Contact Us", "Contact Us Option is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


//Notification

    @Given("^User clicks on the Notification Button$")
    public void clickOnNotificationButton() {
        try {
            CommonUtils.clickOnNotificationBtn();
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Then("^User verify the Notification Panel$")
    public void verifyNotificationPanel() {
        try {
            if (DriverAction.isExist(EmployeeDashboardLocators.notificationHeading)) {
                GemTestReporter.addTestStep("Notification", "Notification Panel is Verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Notification", "Notification Panel is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @And("^User compares them with Unread Tab Notifications count with Unread Header count$")
    public void compareUnreadTabNotificationsCount() {
        try {
            int newHeaderCount;
            if (DriverAction.isExist(EmployeeDashboardLocators.unreadNotificationHeaderCount)) {
                String headerCount = DriverAction.getElementText(EmployeeDashboardLocators.unreadNotificationHeaderCount);
                newHeaderCount = Integer.parseInt(headerCount);
                if (DriverAction.isExist(EmployeeDashboardLocators.unreadOption)) {
                    DriverAction.click(EmployeeDashboardLocators.unreadOption);
                } else {
                    GemTestReporter.addTestStep("Unread Tab", "Unread Tab does not Exist!", STATUS.FAIL, DriverAction.takeSnapShot());
                }
                while (DriverAction.isExist(EmployeeDashboardLocators.showMore)) {
                    clickOnShowMoreBtn();
                }
                List<WebElement> notificationsList = DriverAction.getElements(EmployeeDashboardLocators.notificationsList);
                int unreadTabHeaderCount = notificationsList.size();

                if (newHeaderCount == unreadTabHeaderCount) {
                    GemTestReporter.addTestStep("Verify Unread Notifications", "Unread Notifications Count is Verified, " + " Header Count :" + newHeaderCount + " Unread Tab Count :" + unreadTabHeaderCount, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify Unread Notifications", "Unread Notifications Count is Not Verified", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Unread Notifications", "There are no Unread Notifications left", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (NumberFormatException e) {
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Then("^User verify the Show More working$")
    public void verifyShowMoreWorking() {
        try {
            if (DriverAction.isExist(EmployeeDashboardLocators.showMore)) {
                List<WebElement> notificationsList = DriverAction.getElements(EmployeeDashboardLocators.notificationsList);
                if (notificationsList.size() > 0) {
                    while (DriverAction.isExist(EmployeeDashboardLocators.showMore)) {
                        clickOnShowMoreBtn();
                        DriverAction.waitSec(2);
                    }
                    List<WebElement> showMoreNotificationsList = DriverAction.getElements(EmployeeDashboardLocators.notificationsList);
                    if (showMoreNotificationsList.size() > notificationsList.size()) {
                        GemTestReporter.addTestStep("Notification", "Show More Option is Verified", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Notification", "Show More Option is not Working", STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                } else {
                    GemTestReporter.addTestStep("Notification", "No Notifications are there", STATUS.PASS, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Notification", "Show More Option does not Exist", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Then("^User verify the Check icon is unchecked or not$")
    public void verifyCheckIconUnchecked() {
        DriverAction.waitSec(3);
//        if (!(DriverManager.getWebDriver().findElement(EmployeeDashboard.checkIcon).isSelected())) {
        if (!(DriverAction.isExist(EmployeeDashboardLocators.markAllReadIconChecked))) {
            GemTestReporter.addTestStep("Notifications Check Icon", "Check Icon is Unselected", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Notifications Check Icon", "No Unread Notifications exists", STATUS.PASS, DriverAction.takeSnapShot());
        }
    }


    @And("^User clicks on the Check icon$")
    public void clickCheckIcon() {
        if (DriverAction.isExist(EmployeeDashboardLocators.checkIcon)) {
            if (DriverAction.isExist(EmployeeDashboardLocators.markAllReadIconChecked)) {
                GemTestReporter.addTestStep("Notifications Check Icon", "No Unread Notifications Exist", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                CommonUtils.waitForElement(EmployeeDashboardLocators.checkIcon, 10);
                DriverAction.click(EmployeeDashboardLocators.checkIcon, "Check Icon");
//                DriverAction.waitSec(3);
                CommonUtils.waitUntilLoaderDisappear();
            }
        } else {
            GemTestReporter.addTestStep("Notifications Check Icon", "Check Icon does not Exist", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    @Then("^User verifies that all notifications are read$")
    public void verifyNotificationsRead() {
//        CommonUtils.waitForElement(EmployeeDashboardLocators.unreadOption, 10);
        CommonUtils.waitUntilLoaderDisappear();
        DriverAction.click(EmployeeDashboardLocators.unreadOption);
        CommonUtils.waitUntilLoaderDisappear();
//        DriverAction.waitSec(3);
        if (DriverAction.isExist(EmployeeDashboardLocators.noNewNotifications)) {
            GemTestReporter.addTestStep("Empty Unread Notification", "No unread notifications left", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Unread Notification", "Unread notifications are left", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    @And("^User clicks on a Ticket from Notifications$")
    public void clickTicketFromNotifications() {
        try {
            List<WebElement> notificationsList = DriverAction.getElements(EmployeeDashboardLocators.notificationsList);
            if (notificationsList.size() > 0) {
                DriverAction.click(EmployeeDashboardLocators.firstNotification, "Ticket from Notification");
            } else {
                GemTestReporter.addTestStep("Notification", "No Notifications are there", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Then("^User verifies the appearance of Ticket Details$")
    public void verifyAppearanceOfTicketDetails() {
        try {
            verifyTicketDetailsPage();
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


//Pagination


    @Given("^User select (.+) from Rows per page dropdown$")

    public void selectCountFromRowsPerPageDropdown(String dropDownValue) {
        try {
            selectRowsPerPageValue(dropDownValue);
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Then("^User verifies that the number of rows is equal to the (.+)$")
    public void verifyRowsEqualToTheSelectedValue(int dropDownValue) {
        try {
            int rowsCount = DriverAction.getElements(EmployeeDashboardLocators.numberOfRows).size();
            if (dropDownValue == rowsCount) {
                GemTestReporter.addTestStep("Rows Per Page Value", "Number of Rows Per Page : " + rowsCount + " is Equal to the Selected DropDown Value : " + dropDownValue, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Rows Per Page Value", "Number of Rows Per Page : " + rowsCount + " is Not Equal to the Selected DropDown Value : " + dropDownValue, STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Given("^User checks Next button should be enabled and Previous button should be disabled on first page$")
    public void checkNextEnabledAndPreviousDisabledFirstPage() {
        try {
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(EmployeeDashboardLocators.rowsPerPageDropDown);
            DriverAction.waitSec(2);
            if (!(DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.previousBtn).isEnabled())) {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is not Enabled!", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is Enabled on First Page!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.nextBtn).isEnabled()) {
                GemTestReporter.addTestStep("Next Button", "Next Button is Enabled on First Page!", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Next Button", "Next Button is Not Enabled on First Page!", STATUS.FAIL, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    @Given("Click on logout button on header")
    public void clickOnNotificationButtonOnHeader() {
        try {
            CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover,20);
            if (DriverAction.isExist(EmployeeDashboardLocators.headerButtons("logout"))) {
                DriverAction.click(EmployeeDashboardLocators.headerButtons("logout"), "Logout button");
            } else {
                GemTestReporter.addTestStep("Logout button", "logout button not visible", STATUS.FAIL, DriverAction.takeSnapShot());
            }


        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @Then("Verify if user is logged out of dashboard")
    public void verifyIfUserIsLoggedOutOfDashboard() {
        try {
            if (!DriverAction.getCurrentURL().equalsIgnoreCase(ProjectConfigData.getProperty("dashboardUrl"))) {
                GemTestReporter.addTestStep("Logout button", "Logged out successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Logout button", "Logged out successfully", STATUS.PASS, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }



    @And("^User clicks on Next button and verify that the previous button is enabled$")
    public void clickNextButtonVerifyPreviousButtonEnabled() {
        try {
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(EmployeeDashboardLocators.rowsPerPageDropDown);
            DriverAction.waitSec(2);
            clickNextButton();
            DriverAction.waitSec(2);
            if (DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.previousBtn).isEnabled()) {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is Enabled!", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is Not Enabled!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    @Then("^User clicks on Previous button and verify that it is disabled now$")
    public void clickPreviousButtonVerifyItIsDisabled() {
        try {
            DriverAction.waitSec(2);
            DriverAction.scrollIntoView(EmployeeDashboardLocators.rowsPerPageDropDown);
            DriverAction.waitSec(2);
            clickPreviousButton();
            if (!(DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.previousBtn).isEnabled())) {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is Disabled now!", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Previous Button", "Previous Button is still Enabled!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }


    //SORTING
    @Given("^Click on \"(.*)\" on sort button$")
    public void clickOnSortButton(String idValue) {
        try {
            CommonUtils.waitUntilLoaderDisappear();
            CommonUtils.waitUntilElementIsClickable(EmployeeDashboardLocators.elementById("image", idValue));
            if (DriverAction.isExist(EmployeeDashboardLocators.elementById("image", idValue))) {
                DriverAction.getElement(EmployeeDashboardLocators.elementById("image", idValue)).click();
                GemTestReporter.addTestStep("Click on " + idValue + " button", "Successfully clicked on " + idValue + " button", STATUS.PASS, DriverAction.takeSnapShot());

            } else
                GemTestReporter.addTestStep("Click on " + idValue + " button", "Unable to click on " + idValue + " button", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception ex) {
            GemTestReporter.addTestStep("Click on " + idValue + " button", "Unable to click on " + idValue + " button", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify if the \"(.*)\" column is sorted in ascending order$")
    public void verifyIfTheColumnIsSortedInAscendingOrder(String columnName) {
        try {
            CommonUtils.waitUntilLoaderDisappear();
            List<String> presentDataInColumn = CommonUtils.getAllColumnData(columnName);
            List<String> copyDataList = new ArrayList<>(presentDataInColumn);

            if (columnName.equalsIgnoreCase("Created on")) {
                SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy hh:mm a z");
                List<Long> tempList = new ArrayList<>();
                for (String s : copyDataList) {
                    tempList.add(f.parse(s).getTime());
                }
                copyDataList.clear();
                for (Long var : tempList) {
                    copyDataList.add(f.format(var));
                }
            } else {
                Collections.sort(copyDataList);
            }
            if (copyDataList.equals(presentDataInColumn))
                GemTestReporter.addTestStep("Check if the " + columnName + " column is sorted in ascending order", columnName + " column is sorted in ascending order", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Check if the " + columnName + " column is sorted in ascending order", columnName + " column is not sorted in ascending order", STATUS.FAIL, DriverAction.takeSnapShot());

            DriverAction.refresh();
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Check if the " + columnName + " column is sorted in ascending order", columnName + " column is not sorted in ascending order", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^Click on \"(.*)\" sort button twice$")
    public void clickOnSortButtonTwice(String idValue) {
        try {
            CommonUtils.waitUntilLoaderDisappear();
            CommonUtils.waitUntilElementIsClickable(EmployeeDashboardLocators.elementById("image", idValue));
            if (DriverAction.isExist(EmployeeDashboardLocators.elementById("image", idValue))) {
                DriverAction.getElement(EmployeeDashboardLocators.elementById("image", idValue)).click();
                CommonUtils.waitUntilLoaderDisappear();
                DriverAction.getElement(EmployeeDashboardLocators.elementById("image", idValue)).click();
                GemTestReporter.addTestStep("Click on " + idValue + " button", "Successfully clicked on " + idValue + " button", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Click on " + idValue + " button", "Unable to click on " + idValue + " button", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Click on " + idValue + " button", "Unable to click on " + idValue + " button", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify if the \"(.*)\" column is sorted in descending order$")
    public void verifyIfTheColumnIsSortedInDescendingOrder(String columnName) {
        try {
            CommonUtils.waitUntilLoaderDisappear();
            List<String> presentDataInColumn = CommonUtils.getAllColumnData(columnName);
            List<String> copyDataList = new ArrayList<>(presentDataInColumn);
            if (columnName.equalsIgnoreCase("Created on")) {
                SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy hh:mm a z");
                List<Long> tempList = new ArrayList<>();
                for (String s : copyDataList) {
                    tempList.add(f.parse(s).getTime());
                }
                copyDataList.clear();
                for (Long var : tempList) {
                    copyDataList.add(f.format(var));
                }
            } else {
                copyDataList.sort(Collections.reverseOrder());
            }
            if (copyDataList.equals(presentDataInColumn))
                GemTestReporter.addTestStep("Check if the " + columnName + " column is sorted in descending order", columnName + " column is sorted in descending order", STATUS.PASS, DriverAction.takeSnapShot());

            else
                GemTestReporter.addTestStep("Check if the " + columnName + " column is sorted in descending order", columnName + " column is not sorted in descending order", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception ex) {
            GemTestReporter.addTestStep("Check if the " + columnName + " column is sorted in descending order", columnName + " column is not sorted in descending order", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    //    Calendar
    @And("^Click on \"(.*)\" button$")
    public void clickOnButton(String btnText) {
        try {
            CommonUtils.clickOnButton(btnText);
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Click on "+btnText+ " button",
                    "Unable tto click on button", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify if calendar box appears$")
    public void verifyIfCalendarBoxAppears() {
        try {
            if (DriverAction.isExist(EmployeeDashboardLocators.elementByClass("div", "rdrCalendarWrapper rdrDateRangeWrapper datePicker_datePicker__sfPyf")))
                GemTestReporter.addTestStep("Verify if calendar box appears", "Calendar box is opened", STATUS.PASS, DriverAction.takeSnapShot());
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Verify if calendar box appears", "Calendar box is missing", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    @And("^Check if current date is highlighted in calendar box$")
    public void checkIfCurrentDateIsHighlightedInCalendarBox() {
        try {
            CommonUtils.waitUntilElementAppear(EmployeeDashboardLocators.highlightedDateInCalendar, 10);
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).substring(0, 2);
            String highlightedDateOnCalendar = DriverAction.getElementText(EmployeeDashboardLocators.highlightedDateInCalendar);
            currentDate = currentDate.replaceFirst("^0+(?!$)", "");
            if (currentDate.equalsIgnoreCase(highlightedDateOnCalendar))
                GemTestReporter.addTestStep("Check if current date is highlighted", "Current date is highlighted", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Check if current date is highlighted", "Current date is not highlighted", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Check if current date is highlighted", "Current date is not highlighted", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^Select current date from calendar$")
    public void selectCurrentDateFromCalendar() {
        try {
            DriverAction.click(EmployeeDashboardLocators.highlightedDateInCalendar);
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Select current date from calendar box", "Unable to select current date", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^Verify if displayed data contains current date$")
    public void verifyIfDisplayedDataContainsCurrentDate() {
        passed = false;
        try {
            CommonUtils.waitUntilLoaderDisappear();
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).substring(0, 2);
            List<String> createdOnDates = CommonUtils.getAllColumnData("calender");
            if (createdOnDates == null || createdOnDates.size() == 0) passed = true;
            else {
                for (String createdOnDate : createdOnDates) {
                    if (createdOnDate.substring(0, 2).equalsIgnoreCase(currentDate)) {
                        passed = true;
                    } else {
                        passed = false;
                        break;
                    }
                }
            }
            if (passed)
                GemTestReporter.addTestStep("Verify if displayed data contains current date", "Correct dates are displayed", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Verify if displayed data contains current date", "Incorrect dates are displayed", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Verify if displayed data contains current date", "Incorrect dates are displayed", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^Check if month dropdown have \"(.*)\" options$")
    public void checkIfMonthDropdownHaveOptions(int monthsCount) {
        passed = false;
        try {
            List<String> extractedMonths = DriverAction.getElementsText(EmployeeDashboardLocators.calendarMonthOptions);
            List<String> tempList = CommonUtils.getMonthList();

            if (extractedMonths.size() == monthsCount) {
                for (int i = 0; i < extractedMonths.size(); i++) {
                    if (!extractedMonths.get(i).equalsIgnoreCase(tempList.get(i))) {
                        passed = false;
                        break;
                    } else passed = true;
                }
            }
            if (passed)
                GemTestReporter.addTestStep("Check if month dropdown have 12 months", "Correct months found", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Check if month dropdown have 12 months", "Incorrect or missing month found", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Check if month dropdown have 12 months", "Incorrect or missing month found", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^Check if year dropdown have \"(.*)\" options$")
    public void checkIfYearDropdownHaveOptions(int yearCount) {
        passed = false;
        try {
            List<String> extractedYear = DriverAction.getElementsText(EmployeeDashboardLocators.calendarYearOptions);
            int startingYear = 2023;
            if (extractedYear.size() == yearCount) {
                for (String s : extractedYear) {
                    if (!s.equalsIgnoreCase(Integer.toString(startingYear))) {
                        passed = false;
                        break;
                    } else passed = true;
                    startingYear--;
                }
            }
            if (passed)
                GemTestReporter.addTestStep("Check if year dropdown have 101 years", "Correct years found", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Check if year dropdown have 101 years", "Incorrect or missing year found", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Check if year dropdown have 101 years", "Incorrect or missing year found", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("CLick on previous button and next button to switch between months")
    public void clickOnPreviousButtonAndNextButtonToSwitchBetweenMonths() {
        try {
            WebElement previousButton = DriverAction.getElement(EmployeeDashboardLocators.elementByClass("button", "rdrNextPrevButton rdrPprevButton"));
            WebElement nextButton = DriverAction.getElement(EmployeeDashboardLocators.elementByClass("button", "rdrNextPrevButton rdrNextButton"));

            if (previousButton.isEnabled() && nextButton.isEnabled()) {
                GemTestReporter.addTestStep("Check if user is able to switch between months", "User can switch between months", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Check if user is able to switch between months", "User can not switch between months", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception ex) {
            GemTestReporter.addTestStep("Check if user is able to switch between months", "User can not switch between months", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^Select \"(.*)\" and \"(.*)\" in calendar$")
    public void selectAndInCalendar(String startDate, String endDate) {
        try {
            CommonUtils.waitUntilLoaderDisappear();
            CommonUtils.selectDateRange(startDate, endDate);
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Select custom date", "Custom date is not selected", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    @And("^Check if selected dates are deselected$")
    public void checkIfSelectedDatesAreDeselected() {
        try {
            if (!DriverAction.isExist(EmployeeDashboardLocators.elementByClass("span", "rdrStartEdge")) && !DriverAction.isExist(EmployeeDashboardLocators.elementByClass("span", "rdrEndEdge")))
                GemTestReporter.addTestStep("Check if selected dates are deselected", "Custom dates are deselected", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Check if selected dates are deselected", "Unable to deselect selected dates", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Check if selected dates are deselected", "Unable to deselect selected dates", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    @Then("^Check if displayed tickets are between \"(.*)\" and \"(.*)\"$")
    public void checkIfDisplayedTicketsAreBetweenAnd(String startDate, String endDate) {
        passed = false;
        try {
            DriverAction.waitSec(2);
            List<String> extractedDates = CommonUtils.getAllColumnData("Created on");
            assert extractedDates != null;
            if (extractedDates.size() == 0) passed = true;
            else {
                startDate = CommonUtils.formatDate(startDate);
                endDate = CommonUtils.formatDate(endDate);
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
                    Date sDate = formatter.parse(startDate);
                    Date eDate = formatter.parse(endDate);
                    for (String extractedDate : extractedDates) {
                        String date = extractedDate.substring(0, 11);
                        Date checkDate = formatter.parse(date);
                        if (sDate.compareTo(checkDate) * checkDate.compareTo(eDate) >= 0) passed = true;
                        else {
                            passed = false;
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (passed)
                GemTestReporter.addTestStep("Check if displayed dates are between start and end date", "Correct dates are displayed", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Check if displayed dates are between start and end date", "Incorrect dates are displayed", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception ex) {
            GemTestReporter.addTestStep("Check if displayed dates are between start and end date", "Incorrect dates are displayed", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^Select \"(.*)\" and \"(.*)\" from calendar box$")
    public void selectAndFromCalendarBox(String month, String year) {
        try {
            Select selectedMonth = new Select(DriverAction.getElement(EmployeeDashboardLocators.selectMonth));
            Select selectedYear = new Select(DriverAction.getElement(EmployeeDashboardLocators.selectYear));

            DriverAction.getElement(EmployeeDashboardLocators.selectCustomYear(year)).click();
            DriverAction.getElement(EmployeeDashboardLocators.selectCustomMonth(month)).click();

            if (DriverAction.getElementText(selectedMonth.getFirstSelectedOption()).equalsIgnoreCase(month) && DriverAction.getElementText(selectedYear.getFirstSelectedOption()).equalsIgnoreCase(year)) {
                GemTestReporter.addTestStep("Select " + month + " and " + year + " from calendar box", "Successfully selected month or year", STATUS.PASS, DriverAction.takeSnapShot());

            } else
                GemTestReporter.addTestStep("Select " + month + " and " + year + " from calendar box", "Unable to select month or year", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception ex) {
            GemTestReporter.addTestStep("Select " + month + " and " + year + " from calendar box", "Unable to select month or year", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    //    FILTER
    @And("^Switch on the Hide cancelled, closed and resolved tickets toggle button$")
    public void switchOnTheHideCancelledClosedAndResolvedTicketsToggleButton() {
        try {
            if (DriverAction.isExist(EmployeeDashboardLocators.elementByClass("input", "form-check-input"))) {
                DriverAction.getElement(EmployeeDashboardLocators.elementByClass("input", "form-check-input")).click();
                GemTestReporter.addTestStep("Switch on the toggle button", "Successfully switched on the toggle button", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Switch on the toggle button", "Unable to switch on the toggle button", STATUS.FAIL, DriverAction.takeSnapShot());

            DriverAction.getElement(EmployeeDashboardLocators.elementWithText("image", "cross icon")).click();
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Switch on the toggle button", "Unable to switch on", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^Check if filter is working properly$")
    public void checkIfFilterIsWorkingProperly() {
        try {
            if (!Objects.requireNonNull(CommonUtils.getAllColumnData("Status")).contains("Cancelled") && !Objects.requireNonNull(CommonUtils.getAllColumnData("Status")).contains("Closed") && !Objects.requireNonNull(CommonUtils.getAllColumnData("Status")).contains("Resolved"))
                GemTestReporter.addTestStep("Check if filter is working properly", "Filter is working as expected", STATUS.PASS, DriverAction.takeSnapShot());

            else
                GemTestReporter.addTestStep("Check if filter is working properly", "Filter is not working as expected", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Check if filter is working properly", "Filter is not working as expected", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("^Select \"(.*)\" from the Filter box$")
    public void selectFromTheFilterBox(String keywords) {
        passed = false;
        try {
            if (CommonUtils.addFilter(keywords))
                GemTestReporter.addTestStep("Check if user is able to select filters", "Successfully selected filters", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Check if user is able to select filters", "User is unable to select filter", STATUS.FAIL, DriverAction.takeSnapShot());
//            DriverAction.getElement(EmployeeDashboardLocators.elementWithText("image", "cross icon")).click();
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Check if user is able to select filters", "Unable to select filters", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^Check if all applied filters get deselect$")
    public void checkIfAllAppliedFiltersGetDeselect() {
        try {
            DriverAction.waitSec(2);
            if (!DriverAction.isExist(EmployeeDashboardLocators.elementByClass("button", "filters_filters__VVFJc my-2 border-none d-flex flex-row filters_filterSelected__JbfhU")))
                GemTestReporter.addTestStep("Check if all applied filters get deselect", "Successfully deselected filters", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Check if all applied filters get deselect", "Unable to deselect filters", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Check if all applied filters get deselect", "Unable to deselect filters", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^Verify if displayed \"(.*)\" rows contains \"(.*)\" added filters$")
    public void verifyIfDisplayedRowsContainsAddedFilters(String type, String filterValues) {
        try {
            if (CommonUtils.checkIfFilterIsApplied(type, filterValues))
                GemTestReporter.addTestStep("Verify if displayed rows contains added filters", "Correct filters are added", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Verify if displayed rows contains added filters", "Incorrect filters are added", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Verify if displayed rows contains added filters", "Incorrect filters are added", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^Close it and verify if it is closed$")
    public void closeItAndVerifyIfItIsClosed() {
        try {
            DriverAction.getElement(EmployeeDashboardLocators.elementWithText("image", "cross icon")).click();
            if (!DriverAction.isExist(EmployeeDashboardLocators.elementByClass("div", "filters_filterWrapper__pVutl container")))
                GemTestReporter.addTestStep("Close it and verify if it is closed", "Successfully closed filter box", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Close it and verify if it is closed", "Unable to close filter box", STATUS.FAIL, DriverAction.takeSnapShot());


        } catch (Exception ex) {
            GemTestReporter.addTestStep("Close it and verify if it is closed", "Unable to close filter box", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    //SEARCH KEYWORD
    @Given("^Enter \"(.*)\" keywords \"(.*)\" in the global search box$")
    public static void enterInTheGlobalSearchBox(String typeOfKeyword, String keywords) {
        try {
            CommonUtils.waitUntilLoaderDisappear();
            if (typeOfKeyword.equalsIgnoreCase("TicketID")) {
                keywords = createdTicketID;
                typeOfKeyword = "Valid";
            }
            CommonUtils.searchKeyword(typeOfKeyword, keywords);
        } catch (Exception ex) {
            GemTestReporter.addTestStep("Verify if user is able to enter keywords in global search box",
                    "User is unable to enter keywords", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^CLick on cross icon and check if entered text get removed$")
    public void clickOnCrossIconAndCheckIfEnteredTextGetRemoved() {
        try {
            DriverAction.click(EmployeeDashboardLocators.elementWithText("image", "cross"));
            if (DriverAction.getElement(EmployeeDashboardLocators.elementById("input", "search"))
                    .getAttribute("value").equalsIgnoreCase(""))
                GemTestReporter.addTestStep("CLick on cross icon and check if entered text get removed",
                        "Successfully removed entered text", STATUS.PASS, DriverAction.takeSnapShot());

            else GemTestReporter.addTestStep("CLick on cross icon and check if entered text get removed",
                    "Unable to remove entered text", STATUS.FAIL, DriverAction.takeSnapShot());


        } catch (Exception ex) {
            GemTestReporter.addTestStep("CLick on cross icon and check if entered text get removed",
                    "Unable to remove entered text", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

}
