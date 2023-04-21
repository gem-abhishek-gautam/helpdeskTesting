package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.generic.utils.ProjectConfigData;
import com.qa.helpdesk.locators.DashboardHeaderLocators;
import com.qa.helpdesk.locators.LoginLocators;
import com.qa.helpdesk.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HeaderButtonStepDefinitions {
    @And("Click on user guide button")
    public void headerButtons() {
        try {
            DriverAction.waitUntilElementClickable(DashboardHeaderLocators.headerButtons("userGuide"),10);
            DriverAction.click(DashboardHeaderLocators.headerButtons("userGuide"),"User-guide");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception: ","Exception:"+ e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }


    @Then("Verify if user guide is opened")
    public void verifyIfUserGuideIsOpened() {
        try
        {
            Set<String> chromeTabs = new HashSet<>(DriverAction.getWindowHandles());
            String[] tabsList = chromeTabs.toArray(new String[chromeTabs.size()]);
            DriverAction.switchToWindow(tabsList[1]);
            DriverAction.waitUntilElementClickable(DashboardHeaderLocators.pdfHeader,10);
            if(DriverAction.isExist(DashboardHeaderLocators.pdfHeader) && DriverAction.getElementText(DashboardHeaderLocators.pdfHeader).equalsIgnoreCase("Helpdesk user guide.pdf"))
            {
                GemTestReporter.addTestStep("Verify user-guide pdf","User-guide pdf is displayed", STATUS.PASS,DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Verify user-guide pdf","User-guide pdf is not displayed", STATUS.FAIL,DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @Given("Click on support button")
    public void clickOnSupportButton() {
        try {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            DriverAction.waitUntilElementClickable(DashboardHeaderLocators.headerButtons("Support"),5);
            DriverAction.click(DashboardHeaderLocators.headerButtons("Support"),"Support");
            DriverAction.waitSec(1);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if support info {string} is displayed")
    public void verifyIfSupportInfoIsDisplayed(String info) {
        try {
            if(DriverAction.getElementText(DashboardHeaderLocators.contact).equalsIgnoreCase(info)) {
                GemTestReporter.addTestStep("Contact info","Email address visible",STATUS.PASS,DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Contact info","Email address is not visible, Expected: "+info+"",STATUS.FAIL,DriverAction.takeSnapShot());
            DriverAction.click(DashboardHeaderLocators.closeContact,"Close contact");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Given("Click on notification button")
    public void clickOnNotificationButton() {
        try {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            DriverAction.waitUntilElementClickable(DashboardHeaderLocators.notificationButton,10);
            if(DriverAction.isExist(DashboardHeaderLocators.notificationButton)) {
                DriverAction.click(DashboardHeaderLocators.notificationButton,"Notification button");
            }
            else GemTestReporter.addTestStep("Notification button","Notification button not visible", STATUS.FAIL, DriverAction.takeSnapShot());
            DriverAction.waitSec(2);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Notifications are displayed")
    public void checkIfNotificationsAreDisplayed() {
        try {
            if(DriverAction.isExist(DashboardHeaderLocators.notificationContainer)){
                GemTestReporter.addTestStep("Notifications","Notification container is visible", STATUS.PASS, DriverAction.takeSnapShot());
                List<WebElement> initialCount = DriverAction.getElements(DashboardHeaderLocators.notificationList);

                if(DriverAction.isExist(DashboardHeaderLocators.showMoreNotifications)) {
                    GemTestReporter.addTestStep("Show more notifications","Show more button for notifications is present",STATUS.PASS,DriverAction.takeSnapShot());
                    DriverAction.scrollIntoView(DashboardHeaderLocators.showMoreNotifications);
                    DriverAction.click(DashboardHeaderLocators.showMoreNotifications,"Show more button");
                    DriverAction.waitSec(2);
                    if(DriverAction.getElements(DashboardHeaderLocators.notificationList).size()>initialCount.size()) {
                        GemTestReporter.addTestStep("Show more notifications","More notifications are displayed",STATUS.PASS,DriverAction.takeSnapShot());
                    } else GemTestReporter.addTestStep("Show more notifications","More notifications are not displayed",STATUS.FAIL,DriverAction.takeSnapShot());

                } else GemTestReporter.addTestStep("Show more notifications","Show more button for notifications is not present",STATUS.FAIL,DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Notifications","Notification container not visible", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Given("Check if is there an unread count for notifications")
    public void checkIfIsThereAnUnreadCountForNotifications() {
        try {
            if(DriverAction.isExist(DashboardHeaderLocators.unreadCount)) {
                DriverAction.waitUntilElementClickable(DashboardHeaderLocators.notificationButton,10);
                DriverAction.click(DashboardHeaderLocators.notificationButton,"Notification button");
                DriverAction.waitSec(2);
                DriverAction.click(DashboardHeaderLocators.unreadNotifications,"Unread");
                DriverAction.waitSec(2);
            } else GemTestReporter.addTestStep("Notifications","No unread count for notifications", STATUS.INFO, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @When("Unread count matches unread notifications")
    public void unreadCountMatchesUnreadNotifications() {
        try {
            int unreadCount=0;
            if(DriverAction.isExist(DashboardHeaderLocators.unreadCount)){
                unreadCount = Integer.parseInt(DriverAction.getElementText(DashboardHeaderLocators.unreadCount));
            }
            if(unreadCount>0) {
                List<WebElement> notifList = new ArrayList<>();
                boolean isExist = DriverAction.isExist(DashboardHeaderLocators.showMoreNotifications);
                while(isExist) {
                    DriverAction.scrollIntoView(DashboardHeaderLocators.showMoreNotifications);
                    try {
                        DriverAction.waitUntilElementClickable(DashboardHeaderLocators.showMoreNotifications,10);
                        DriverAction.click(DashboardHeaderLocators.showMoreNotifications,"Show more");
                    } catch (Exception e) {
                        if(DriverAction.isExist(DashboardHeaderLocators.showMoreNotifications)) {
                            JavascriptExecutor exe = (JavascriptExecutor) DriverManager.getWebDriver();
                            exe.executeScript("arguments[0].click();",DriverAction.getElement(DashboardHeaderLocators.showMoreNotifications));
                        }
                    }

                    notifList=DriverAction.getElements(DashboardHeaderLocators.notificationList);
                    isExist = DriverAction.isExist(DashboardHeaderLocators.showMoreNotifications);
                }
                if(unreadCount==notifList.size()) {
                    GemTestReporter.addTestStep("Unread notifications","Unread notifications count matched",STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Unread notifications","Unread notifications count not matched, Expected: "+unreadCount+" Actual: "+notifList.size()+"",STATUS.FAIL,DriverAction.takeSnapShot());
                DriverAction.click(DashboardHeaderLocators.notificationReadButton,"Mark as read");
                DriverAction.waitSec(3);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify read notification button")
    public void verifyReadNotificationButton() {
        try {
            DriverAction.click(DashboardHeaderLocators.notificationButton,"Notification button");
            DriverAction.waitSec(1);
            DriverAction.click(DashboardHeaderLocators.unreadNotifications,"Unread");
            DriverAction.waitSec(1);
            if(!DriverAction.isExist(DashboardHeaderLocators.unreadCount) && DriverAction.getElementText(DashboardHeaderLocators.notificationListAlert).contains("No new")) {
                GemTestReporter.addTestStep("Notification read","Notifications marked as read",STATUS.PASS,DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Notification read","Notifications were not marked as read",STATUS.FAIL,DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if ticket details can be opened from notifications")
    public void verifyTicketDetails() {
        try {
            DriverAction.waitSec(1);
            if(DriverAction.isExist(DashboardHeaderLocators.notificationListContainer)) {
                List<WebElement> elementList = DriverAction.getElements(DashboardHeaderLocators.notificationList);
                DriverAction.click(elementList.get(1));
                DriverAction.waitSec(3);
                if(DriverAction.getCurrentURL().contains(ProjectConfigData.getProperty("ticketDetailsUrl"))) {
                    GemTestReporter.addTestStep("Ticket details","Details page is displayed",STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Ticket details","Details page not displayed",STATUS.FAIL,DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Given("Click on logout button on header")
    public void clickOnNotificationButtonOnHeader() {
        try {
            if(DriverAction.isExist(DashboardHeaderLocators.headerButtons("logout"))) {
                DriverAction.click(DashboardHeaderLocators.headerButtons("logout"),"Logout button");
            } else {
                GemTestReporter.addTestStep("Logout button","logout button not visible",STATUS.FAIL,DriverAction.takeSnapShot());
            }


        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @Then("Verify if user is logged out of dashboard")
    public void verifyIfUserIsLoggedOutOfDashboard() {
        try {
            if(!DriverAction.getCurrentURL().equalsIgnoreCase(ProjectConfigData.getProperty("dashboardUrl"))) {
                GemTestReporter.addTestStep("Logout button","Logged out successfully",STATUS.PASS,DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Logout button","Logged out successfully",STATUS.PASS,DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Given("Click on toggle button on side menu")
    public void clickOnToggleButtonOnSideMenu() {
        try {
            if(DriverAction.isExist(LoginLocators.employeeName)) {
                DriverAction.click(DashboardHeaderLocators.headerButtons("toggle icon"),"Side menu toggle");
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if menu gets collapsed or expanded")
    public void verifyIfMenuGetsCollapsedOrExpanded() {
        try {
            if(!DriverAction.isExist(LoginLocators.employeeName)) {
                GemTestReporter.addTestStep("Toggle button","Side Menu collapsed successfully",STATUS.PASS,DriverAction.takeSnapShot());
                DriverAction.click(DashboardHeaderLocators.headerButtons("toggle icon"),"Side menu toggle");
                if(DriverAction.isExist(LoginLocators.employeeName)) {
                    GemTestReporter.addTestStep("Toggle button","Side Menu expanded successfully",STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Toggle button","Menu not expanded",STATUS.FAIL,DriverAction.takeSnapShot());

            } else GemTestReporter.addTestStep("Toggle button","Side Menu not collapsed",STATUS.FAIL,DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

}
