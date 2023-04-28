package com.qa.helpdesk.utils;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.locators.EmployeeDashboardLocators;
import com.qa.helpdesk.locators.EmployeeTicketLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CommonUtils {
    static boolean passed = false;

    public static void verifyIncidentPreview(String type, String subject, String desc, String dept, boolean fileFlag) {
        String subjectActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Subject"));
        String descActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Description"));
        String deptActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Department"));
        String typeActual = DriverAction.getElementText(EmployeeTicketLocators.getPreviewTypeValue);
        if (typeActual.equalsIgnoreCase("Incident")) {
            GemTestReporter.addTestStep("Preview ticket type", "Ticket type expected: " + type + " Actual: " + typeActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview ticket type", "Ticket type expected: " + type + " Actual: " + typeActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (subjectActual.equalsIgnoreCase(subject)) {
            GemTestReporter.addTestStep("Preview subject", "Subject expected: " + subject + " Actual: " + subjectActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview subject", "Subject expected: " + subject + " Actual: " + subjectActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (deptActual.equalsIgnoreCase(dept)) {
            GemTestReporter.addTestStep("Preview Department", "Department expected: " + dept + " Actual: " + deptActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else if (dept.trim().isEmpty() && deptActual.equalsIgnoreCase("unassigned")) {
            GemTestReporter.addTestStep("Preview Department", "Department unassigned", STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Department", "Department expected: " + dept + " Actual: " + deptActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (descActual.equalsIgnoreCase(desc)) {
            GemTestReporter.addTestStep("Preview Description", "Description expected: " + desc + " Actual: " + descActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Description", "Description expected: " + desc + " Actual: " + descActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (fileFlag == DriverAction.isExist(EmployeeTicketLocators.previewAttachments)) {
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment validated", STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment not validated", STATUS.FAIL, DriverAction.takeSnapShot());

    }

    public static void verifyRequestPreview(String type, String subject, String desc, String dept, String category, String subCategory, boolean fileFlag) {
        String typeActual = DriverAction.getElementText(EmployeeTicketLocators.getPreviewTypeValue);
        String subjectActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Subject"));
        String descActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Description"));
        String deptActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Department"));
        String categoryActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Category"));
        String subCategoryActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Sub-category"));

        if (typeActual.equalsIgnoreCase(type)) {
            GemTestReporter.addTestStep("Preview ticket type", "Ticket type expected: " + type + " Actual: " + typeActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify ticket type", "Ticket type expected: " + type + " Actual: " + typeActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (subjectActual.equalsIgnoreCase(subject)) {
            GemTestReporter.addTestStep("Preview subject", "Subject expected: " + subject + " Actual: " + subjectActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview subject", "Subject expected: " + subject + " Actual: " + subjectActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (deptActual.equalsIgnoreCase(dept)) {
            GemTestReporter.addTestStep("Preview Department", "Department expected: " + dept + " Actual: " + deptActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else if (dept.trim().isEmpty() && deptActual.equalsIgnoreCase("unassigned")) {
            GemTestReporter.addTestStep("Preview Department", "Department unassigned", STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Department", "Department expected: " + dept + " Actual: " + deptActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (descActual.equalsIgnoreCase(desc)) {
            GemTestReporter.addTestStep("Preview Description", "Description expected: " + desc + " Actual: " + descActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Description", "Description expected: " + desc + " Actual: " + descActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (categoryActual.toLowerCase().contains(category.toLowerCase())) {
            GemTestReporter.addTestStep("Preview Category", "Category expected: " + category + " Actual: " + categoryActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Category", "Category expected: " + category + " Actual: " + categoryActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (subCategoryActual.toLowerCase().contains(subCategory.toLowerCase())) {
            GemTestReporter.addTestStep("Preview Sub-Category", "Sub-Category expected: " + subCategory + " Actual: " + subCategoryActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Sub-Category", "Sub-Category expected: " + subCategory + " Actual: " + subCategoryActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (fileFlag == DriverAction.isExist(EmployeeTicketLocators.previewAttachments)) {
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment validated", STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment not validated", STATUS.FAIL, DriverAction.takeSnapShot());

    }

    public static void verifySupportPreview(boolean fileFlag, String type, String caller, String channel, String dept, String category, String subcategory, String subject, String desc, String status) {
        String typeActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Type"));
        String callerActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Caller"));
        String channelActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Channel"));
        String subjectActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Subject"));
        String descActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Description"));
        String deptActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Department"));
        String categoryActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Category"));
        String subCategoryActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Sub-category"));
        String statusActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Status"));
        String assignActual;
        if (DriverAction.isExist(EmployeeTicketLocators.ticketPreview("Assigned To"))) {
            assignActual = DriverAction.getElementText(EmployeeTicketLocators.ticketPreview("Assigned To"));
            if (assignActual.equalsIgnoreCase("harvesh kumar")) {
                GemTestReporter.addTestStep("Preview ticket Assigned to", "Ticket Assigned to expected: Harvesh Kumar Actual: " + assignActual, STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Verify ticket Assigned to", "Ticket Assigned to expected: Harvesh Kumar Actual: " + assignActual, STATUS.FAIL, DriverAction.takeSnapShot());

        }

        if (typeActual.equalsIgnoreCase(type)) {
            GemTestReporter.addTestStep("Preview ticket type", "Ticket type expected: " + type + " Actual: " + typeActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify ticket type", "Ticket type expected: " + type + " Actual: " + typeActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (callerActual.equalsIgnoreCase(caller)) {
            GemTestReporter.addTestStep("Preview ticket caller", "Ticket caller expected: " + caller + " Actual: " + callerActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify ticket caller", "Ticket caller expected: " + caller + " Actual: " + callerActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (channelActual.equalsIgnoreCase(channel)) {
            GemTestReporter.addTestStep("Preview ticket channel", "Ticket channel expected: " + channel + " Actual: " + channelActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify ticket channel", "Ticket channel expected: " + channel + " Actual: " + channelActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (subjectActual.equalsIgnoreCase(subject)) {
            GemTestReporter.addTestStep("Preview subject", "Subject expected: " + subject + " Actual: " + subjectActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview subject", "Subject expected: " + subject + " Actual: " + subjectActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (deptActual.equalsIgnoreCase(dept)) {
            GemTestReporter.addTestStep("Preview Department", "Department expected: " + dept + " Actual: " + deptActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else if (dept.trim().isEmpty() && deptActual.equalsIgnoreCase("unassigned")) {
            GemTestReporter.addTestStep("Preview Department", "Department unassigned", STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Department", "Department expected: " + dept + " Actual: " + deptActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (descActual.equalsIgnoreCase(desc)) {
            GemTestReporter.addTestStep("Preview Description", "Description expected: " + desc + " Actual: " + descActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Description", "Description expected: " + desc + " Actual: " + descActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (categoryActual.toLowerCase().contains(category.toLowerCase())) {
            GemTestReporter.addTestStep("Preview Category", "Category expected: " + category + " Actual: " + categoryActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Category", "Category expected: " + category + " Actual: " + categoryActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (subCategoryActual.toLowerCase().contains(subcategory.toLowerCase())) {
            GemTestReporter.addTestStep("Preview Sub-Category", "Sub-Category expected: " + subcategory + " Actual: " + subCategoryActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Sub-Category", "Sub-Category expected: " + subcategory + " Actual: " + subCategoryActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (statusActual.equalsIgnoreCase(status)) {
            GemTestReporter.addTestStep("Preview ticket status", "Ticket status expected: " + status + " Actual: " + statusActual, STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify ticket status", "Ticket status expected: " + status + " Actual: " + statusActual, STATUS.FAIL, DriverAction.takeSnapShot());

        if (fileFlag == DriverAction.isExist(EmployeeTicketLocators.previewAttachments)) {
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment validated", STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment not validated", STATUS.FAIL, DriverAction.takeSnapShot());

    }

    public static void createIncidentTicket(String subject, String desc, String dept, String filePath) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.createTicket));

        if (DriverAction.isExist(EmployeeTicketLocators.createTicket)) {
            DriverAction.click(EmployeeTicketLocators.createTicket);
            wait.until(ExpectedConditions.visibilityOfElementLocated(EmployeeTicketLocators.ticketFormHeader));

            DriverAction.typeText(EmployeeTicketLocators.subject, subject);
            DriverAction.typeText(EmployeeTicketLocators.desc, desc);
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.typeDropdown, 10);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            DriverAction.click(EmployeeTicketLocators.typeDropdown);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.ticketDropdownOptions("Incident")));
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions("Incident"));
            } catch (Exception e) {
                DriverAction.click(EmployeeTicketLocators.typeDropdown);
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions("Incident"));
            }

            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.ticketDropdown("Department")));
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Department"));
            } catch (Exception e) {
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Department"));
            }
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.ticketDropdownOptions(dept)));
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(dept));
            } catch (Exception e) {
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Department"));
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(dept));
            }

            DriverAction.waitSec(2);
            if (!filePath.isEmpty()) {
                DriverAction.fileUpload(EmployeeTicketLocators.fileUpload, filePath);
            }
            DriverAction.waitSec(3);
            CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover,10);
            DriverAction.scrollIntoView(EmployeeTicketLocators.previewButton);
            DriverAction.click(EmployeeTicketLocators.previewButton);
            DriverAction.waitSec(1);
        }

    }

    public static void createRequestTicket(String subject, String desc, String dept, String category, String subCategory, String filePath) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.createTicket));
        if (DriverAction.isExist(EmployeeTicketLocators.createTicket)) {
            DriverAction.click(EmployeeTicketLocators.createTicket);
            wait.until(ExpectedConditions.visibilityOfElementLocated(EmployeeTicketLocators.ticketFormHeader));
            DriverAction.typeText(EmployeeTicketLocators.subject, subject);
            DriverAction.typeText(EmployeeTicketLocators.desc, desc);
            wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.typeDropdown));
            DriverAction.click(EmployeeTicketLocators.typeDropdown);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.ticketDropdownOptions("Request")));
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions("Request"));
            } catch (Exception e) {

                DriverAction.click(EmployeeTicketLocators.typeDropdown);
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions("Request"));
            }

            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.ticketDropdown("Department")));
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Department"));
            } catch (Exception e) {
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Department"));
            }
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.ticketDropdownOptions(dept)));
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(dept));
            } catch (Exception e) {
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Department"));
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(dept));
            }
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.ticketDropdown("Category")));
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Category"));
            } catch (Exception e) {
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Category"));
            }

            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.ticketDropdownOptions(category)));
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(category));
            } catch (Exception e) {
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Category"));
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(category));
            }

            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.ticketDropdown("Sub-category")));
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Sub-category"));
            } catch (Exception e) {
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Sub-category"));
            }

            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }

            try {
                wait.until(ExpectedConditions.elementToBeClickable(EmployeeTicketLocators.ticketDropdownOptions(subCategory)));
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(subCategory));
            } catch (Exception e) {
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Sub-category"));
                DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(subCategory));
            }

            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
            }

            if (!filePath.isEmpty()) {
                DriverAction.fileUpload(EmployeeTicketLocators.fileUpload, filePath);
            }
            DriverAction.waitSec(3);
            CommonUtils.waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover,10);
            DriverAction.scrollIntoView(EmployeeTicketLocators.previewButton);
            DriverAction.click(EmployeeTicketLocators.previewButton);
            DriverAction.waitSec(1);

        }
    }

    public static void verifyTicketDetails(String type, String dept, String desc, String category, String subCategory, String status, boolean fileFlag) {
        DriverAction.waitSec(3);
        String ticketID = DriverAction.getElementText(EmployeeTicketLocators.postSubmitTicketID).replace("Ticket ID: ", "");
        DriverAction.click(EmployeeTicketLocators.postSubmitContinueButton);
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }
        DriverAction.waitUntilElementClickable(EmployeeDashboardLocators.ticketSearchButton, 10);
        DriverAction.typeText(EmployeeDashboardLocators.ticketSearchBox, ticketID);
        DriverAction.waitSec(1);
        DriverAction.click(EmployeeDashboardLocators.ticketSearchButton);
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 15);
        }
        if (DriverAction.isExist(EmployeeDashboardLocators.firstTicketID)) {
            DriverAction.click(EmployeeDashboardLocators.firstTicketID);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 15);
            }
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.timelineToggle, 10);
            String typeActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Type"));
            String descActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Description"));
            String deptActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Department"));
            String statusActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Status"));
            String urgencyActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Urgency"));
            String impactActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Impact"));
            String categoryActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Category"));
            String subCategoryActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Sub Category"));

            if (DriverAction.isExist(EmployeeTicketLocators.ticketDetailsCard)) {

                if (typeActual.equalsIgnoreCase(type)) {
                    GemTestReporter.addTestStep("Verify ticket type", "Ticket type expected: " + type + " Actual: " + typeActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify ticket type", "Ticket type expected: " + type + " Actual: " + typeActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (deptActual.equalsIgnoreCase(dept)) {
                    GemTestReporter.addTestStep("Verify Department", "Department expected: " + dept + " Actual: " + deptActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else if (dept.trim().isEmpty() && deptActual.equalsIgnoreCase("unassigned")) {
                    GemTestReporter.addTestStep("Verify Department", "Department unassigned", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Department", "Department expected: " + dept + " Actual: " + deptActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (statusActual.equalsIgnoreCase(status)) {
                    GemTestReporter.addTestStep("Verify Status", "Status expected: " + status + " Actual: " + statusActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Status", "Status expected: " + status + " Actual: " + statusActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (DriverAction.isExist(EmployeeTicketLocators.timelineToggle)) {
                    if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                        waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
                    }
                    DriverAction.waitUntilElementClickable(EmployeeTicketLocators.timelineToggle, 10);
                    DriverAction.click(EmployeeTicketLocators.timelineToggle, "Timeline");
                    if (DriverAction.getElementText(EmployeeTicketLocators.lastTimelineStatus).equalsIgnoreCase(status) && !DriverAction.getElementText(EmployeeTicketLocators.lastTimelineUser).equalsIgnoreCase("Abhishek Gautam")) {
                        GemTestReporter.addTestStep("Timeline status", "Latest status is updated on timeline", STATUS.PASS, DriverAction.takeSnapShot());
                    } else
                        GemTestReporter.addTestStep("Timeline status", "Status is not updated on timeline, Actual: " + DriverAction.getElementText(EmployeeTicketLocators.lastTimelineStatus) + "", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Timeline toggle", "Timeline toggle not found", STATUS.FAIL, DriverAction.takeSnapShot());

                if (descActual.equalsIgnoreCase(desc)) {
                    GemTestReporter.addTestStep("Verify Description", "Description expected: " + desc + " Actual: " + descActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Description", "Description expected: " + desc + " Actual: " + descActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (categoryActual.toLowerCase().contains(category.toLowerCase())) {
                    GemTestReporter.addTestStep("Verify Category", "Category expected: " + category + " Actual: " + categoryActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Category", "Category expected: " + category + " Actual: " + categoryActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (subCategoryActual.toLowerCase().contains(subCategory.toLowerCase())) {
                    GemTestReporter.addTestStep("Verify Sub-category", "Sub-category expected: " + subCategory + " Actual: " + subCategoryActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Sub-category", "Sub-category expected: " + subCategory + " Actual: " + subCategoryActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (urgencyActual.equalsIgnoreCase("Low")) {
                    GemTestReporter.addTestStep("Verify Urgency", "Urgency expected: Low Actual: " + urgencyActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Urgency", "Urgency expected: Low Actual: " + urgencyActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (impactActual.equalsIgnoreCase("Low")) {
                    GemTestReporter.addTestStep("Verify Impact", "Impact expected: Low Actual: " + impactActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Impact", "Impact expected: Low Actual: " + impactActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (fileFlag == DriverAction.isExist(EmployeeTicketLocators.ticketDetailsAttachment)) {
                    GemTestReporter.addTestStep("Attachment validation", "File attachment validated successfully", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Attachment validation", "File attachment validation failed", STATUS.FAIL, DriverAction.takeSnapShot());

            }
        } else GemTestReporter.addTestStep("Ticket Search", "Not found", STATUS.FAIL, DriverAction.takeSnapShot());


    }

    public static void createSupportTicket(String type, String caller, String channel, String dept, String category, String subcategory, String subject, String desc, String status, String filePath) {
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }
        DriverAction.click(EmployeeTicketLocators.createTicket, "Create ticket");
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }

        DriverAction.waitUntilElementClickable(EmployeeTicketLocators.callerNameInput, 5);
        DriverAction.click(EmployeeTicketLocators.callerNameInput, "Caller");
        DriverAction.typeText(EmployeeTicketLocators.callerNameInput, caller);
        DriverAction.waitUntilElementClickable(EmployeeTicketLocators.callerMenu(caller), 5);
        DriverAction.click(EmployeeTicketLocators.callerMenu(caller), caller);

        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }

        if (!caller.equalsIgnoreCase("Abhishek Gautam")) {
            DriverAction.click(EmployeeTicketLocators.ticketDropdown("Channel"), "Channel");
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdownOptions(channel), 5);
            DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(channel), channel);
        }
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }
        if (!dept.equalsIgnoreCase("IT")) {
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdown("Department"), 10);
            DriverAction.click(EmployeeTicketLocators.ticketDropdown("Department"), "Department");
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdownOptions(dept), 5);
            DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(dept), dept);
        }
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }
        if (!type.equalsIgnoreCase("incident")) {
            DriverAction.click(EmployeeTicketLocators.ticketDropdown("Type"), "Type");
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdownOptions(type), 5);
            DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(type), type);
        }
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }

        DriverAction.click(EmployeeTicketLocators.ticketDropdown("Category"), "Category");
        DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdownOptions(category), 5);
        DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(category), category);

        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }

        DriverAction.click(EmployeeTicketLocators.ticketDropdown("Sub-category"), "Sub-category");
        DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdownOptions(subcategory), 10);
        DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(subcategory), subcategory);

        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }

        DriverAction.scrollToBottom();
        DriverAction.waitUntilElementClickable(EmployeeTicketLocators.subject, 5);
        DriverAction.typeText(EmployeeTicketLocators.subject, subject);
        DriverAction.typeText(EmployeeTicketLocators.desc, desc);

        if (!status.equalsIgnoreCase("open")) {
            DriverAction.scrollIntoView(EmployeeTicketLocators.ticketDropdown("Status"));
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdown("Status"), 5);
            try {
                DriverAction.click(EmployeeTicketLocators.ticketDropdown("Status"), "Status");
            } catch (Exception e) {
                JavascriptExecutor exe = (JavascriptExecutor) DriverManager.getWebDriver();
                exe.executeScript("argument[0].click();", DriverAction.getElement(EmployeeTicketLocators.ticketDropdown("Status")));
            }
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdownOptions(status), 5);
            DriverAction.click(EmployeeTicketLocators.ticketDropdownOptions(status), status);
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.ticketDropdown("Assigned To"), 5);
            DriverAction.click(EmployeeTicketLocators.ticketDropdown("Assigned To"), "Assigned To");
            DriverAction.waitSec(1);
            DriverAction.scrollIntoView(EmployeeTicketLocators.callerMenu("Harvesh Kumar"));
            DriverAction.click(EmployeeTicketLocators.callerMenu("Harvesh Kumar"));
        }
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }
        if (!filePath.isEmpty()) {
            DriverAction.fileUpload(EmployeeTicketLocators.fileUpload, filePath);
        }
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }
    }

    public static void verifySupportTicketDetails(String type, String dept, String desc, String category, String subCategory, String status, String caller, String channel, boolean fileFlag) {
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 15);
        }
        String ticketID = DriverAction.getElementText(EmployeeTicketLocators.postSubmitTicketID).replace("Ticket ID: ", "");
        DriverAction.click(EmployeeTicketLocators.postSubmitContinueButton, "Continue");
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
        }

        DriverAction.waitUntilElementClickable(EmployeeDashboardLocators.ticketTabs("My Department"), 10);
        DriverAction.click(EmployeeDashboardLocators.ticketTabs("My Department"), "My Department");
        DriverAction.waitSec(3);
        DriverAction.waitUntilElementClickable(EmployeeDashboardLocators.ticketSearchButton, 10);
        DriverAction.typeText(EmployeeDashboardLocators.ticketSearchBox, ticketID);
        DriverAction.waitSec(1);
        DriverAction.click(EmployeeDashboardLocators.ticketSearchButton);
        if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
            waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 15);
        }
        if (DriverAction.isExist(EmployeeDashboardLocators.firstTicketID)) {
            DriverAction.click(EmployeeDashboardLocators.firstTicketID);
            if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 15);
            }
            DriverAction.waitUntilElementClickable(EmployeeTicketLocators.timelineToggle, 10);
            String callerActual = DriverAction.getElementText(EmployeeTicketLocators.getCallerName).split("\\|")[0].trim();
            String channelActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Channel"));
            String typeActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Type"));
            String descActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Description"));
            String deptActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Department"));
            String statusActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Status"));
            String urgencyActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Urgency"));
            String impactActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Impact"));
            String categoryActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Category"));
            String subCategoryActual = DriverAction.getElementText(EmployeeTicketLocators.ticketDetails("Sub Category"));

            if (DriverAction.isExist(EmployeeTicketLocators.ticketDetailsCard)) {
                if (callerActual.equalsIgnoreCase(caller)) {
                    GemTestReporter.addTestStep("Verify ticket caller", "Ticket caller expected: " + caller + " Actual: " + callerActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify ticket caller", "Ticket caller expected: " + caller + " Actual: " + callerActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (channelActual.equalsIgnoreCase(channel)) {
                    GemTestReporter.addTestStep("Verify ticket channel", "Ticket channel expected: " + channel + " Actual: " + channelActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify ticket channel", "Ticket channel expected: " + channel + " Actual: " + channelActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (typeActual.equalsIgnoreCase(type)) {
                    GemTestReporter.addTestStep("Verify ticket type", "Ticket type expected: " + type + " Actual: " + typeActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify ticket type", "Ticket type expected: " + type + " Actual: " + typeActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (deptActual.equalsIgnoreCase(dept)) {
                    GemTestReporter.addTestStep("Verify Department", "Department expected: " + dept + " Actual: " + deptActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else if (dept.trim().isEmpty() && deptActual.equalsIgnoreCase("unassigned")) {
                    GemTestReporter.addTestStep("Verify Department", "Department unassigned", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Department", "Department expected: " + dept + " Actual: " + deptActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (status.equalsIgnoreCase("open")) {
                    status = "Unassigned";
                } else status = "Assigned";
                if (statusActual.equalsIgnoreCase(status)) {
                    GemTestReporter.addTestStep("Verify Status", "Status expected: " + status + " Actual: " + statusActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Status", "Status expected: " + status + " Actual: " + statusActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (DriverAction.isExist(EmployeeTicketLocators.timelineToggle)) {
                    if (DriverAction.isExist(EmployeeDashboardLocators.loaderCover)) {
                        waitUntilElementDisappear(EmployeeDashboardLocators.loaderCover, 20);
                    }
                    DriverAction.waitUntilElementClickable(EmployeeTicketLocators.timelineToggle, 10);
                    DriverAction.click(EmployeeTicketLocators.timelineToggle, "Timeline");
                    if (DriverAction.getElementText(EmployeeTicketLocators.lastTimelineStatus).equalsIgnoreCase(status)) {
                        GemTestReporter.addTestStep("Timeline status", "Latest status is updated on timeline", STATUS.PASS, DriverAction.takeSnapShot());
                    } else
                        GemTestReporter.addTestStep("Timeline status", "Status is not updated on timeline, Actual: " + DriverAction.getElementText(EmployeeTicketLocators.lastTimelineStatus) + "", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Timeline toggle", "Timeline toggle not found", STATUS.FAIL, DriverAction.takeSnapShot());

                if (descActual.equalsIgnoreCase(desc)) {
                    GemTestReporter.addTestStep("Verify Description", "Description expected: " + desc + " Actual: " + descActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Description", "Description expected: " + desc + " Actual: " + descActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (categoryActual.toLowerCase().contains(category.toLowerCase())) {
                    GemTestReporter.addTestStep("Verify Category", "Category expected: " + category + " Actual: " + categoryActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Category", "Category expected: " + category + " Actual: " + categoryActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (subCategoryActual.toLowerCase().contains(subCategory.toLowerCase())) {
                    GemTestReporter.addTestStep("Verify Sub-category", "Sub-category expected: " + subCategory + " Actual: " + subCategoryActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Sub-category", "Sub-category expected: " + subCategory + " Actual: " + subCategoryActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (urgencyActual.equalsIgnoreCase("Low")) {
                    GemTestReporter.addTestStep("Verify Urgency", "Urgency expected: Low Actual: " + urgencyActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Urgency", "Urgency expected: Low Actual: " + urgencyActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (impactActual.equalsIgnoreCase("Low")) {
                    GemTestReporter.addTestStep("Verify Impact", "Impact expected: Low Actual: " + impactActual, STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Verify Impact", "Impact expected: Low Actual: " + impactActual, STATUS.FAIL, DriverAction.takeSnapShot());

                if (fileFlag == DriverAction.isExist(EmployeeTicketLocators.ticketDetailsAttachment)) {
                    GemTestReporter.addTestStep("Attachment validation", "File attachment validated successfully", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Attachment validation", "File attachment validation failed", STATUS.FAIL, DriverAction.takeSnapShot());

            }
        } else
            GemTestReporter.addTestStep("Ticket Search", "Ticket not found", STATUS.FAIL, DriverAction.takeSnapShot());


    }

    public static boolean isListInOrder(List<String> list) {
        if (list == null || list.size() < 2) {
            return true;
        }

        String prev = list.get(0);
        boolean isAscending = true;
        boolean isDescending = true;

        for (int i = 1; i < list.size(); i++) {
            String current = list.get(i);
            if (prev.compareTo(current) > 0) {
                isAscending = false;
            } else if (prev.compareTo(current) < 0) {
                isDescending = false;
            }
            prev = current;
        }

        return isAscending || isDescending;
    }


    public static String getTableColPosition(String colName) {
        int idx = 1;
        List<WebElement> elements = DriverAction.getElements(EmployeeDashboardLocators.getTableColNames);
        for (WebElement ele : elements) {
            String title = DriverAction.getElementText(ele);
            if (title.equalsIgnoreCase(colName)) {
                break;
            } else {
                idx++;
            }
        }
        return String.valueOf(idx);
    }

    public static boolean dateRangeValidate(String startDateStr, String endDateStr, String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        Date date = dateFormat.parse(dateStr);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
        Date startDate = sdf.parse(startDateStr);
        Date endDate = sdf.parse(endDateStr);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        endDate = calendar.getTime();

        return (date.after(startDate) || date.equals(startDate)) && (date.before(endDate) || date.equals(endDate));

    }

    public static void waitUntilElementDisappear(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), seconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitUntilElementAppear(By locator, int duration) {
        WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(duration));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
        js.executeScript("document.documentElement.scrollTop = 0;");

    }

    public static void waitForElement(By locator, int duration) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void clickSearchBoxCross() {
        try {
            DriverAction.click(EmployeeDashboardLocators.searchBoxCrossIcon, "Cross Icon");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to click on Search Box, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void clearData() {
        try {
//            waitForElement(EmployeeDashboardLocators.calendarImg, 10);
//            DriverAction.waitSec(3);
//            DriverAction.click(EmployeeDashboardLocators.calendarImg, "Calendar icon");
            DriverAction.scrollIntoView(EmployeeDashboardLocators.notificationIcon);
            DriverAction.waitSec(3);
            DriverAction.click(EmployeeDashboardLocators.clearData);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to clear the data, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void columnSorting(String colName) {
        try {
            List<String> colItems = null;
            List<String> colItems2 = null;

            Select rowsPerPage = new Select(DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.rowsPerPageDropDown));
            DriverAction.waitSec(2);
            rowsPerPage.selectByValue("25");
            DriverAction.waitSec(2);

            if (DriverAction.isExist(EmployeeDashboardLocators.employeeView)) {
                switch (colName) {
                    case ("ID"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.idCol);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.idCol);
                        break;

                    case ("Subject"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.subjectCol);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.subjectCol);
                        break;

                    case ("Department"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.departmentCol);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.departmentCol);
                        break;

                    case ("Created on"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.createdOnCol);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.createdOnCol);
                        break;

                    case ("Assigned to"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.assignedToCol);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.assignedToCol);
                        break;

                    case ("Status"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.statusCol);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.statusCol);
                        break;
                }
            } else if (DriverAction.isExist(EmployeeDashboardLocators.supportView)) {
                switch (colName) {
                    case ("ID"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.idCol);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.idCol);
                        break;

                    case ("Subject"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.subjectCol);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.subjectCol);
                        break;

                    case ("Caller"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.callerCol);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.callerCol);
                        break;

                    case ("Created on"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.createdOnColSupportView);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.createdOnColSupportView);
                        break;

                    case ("Assigned to"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.assignedToColSupportView);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.assignedToColSupportView);
                        break;

                    case ("Status"):
                        colItems = DriverAction.getElementsText(EmployeeDashboardLocators.statusColSupportView);
                        DriverAction.click(EmployeeDashboardLocators.getSortingHeading(colName));
                        colItems2 = DriverAction.getElementsText(EmployeeDashboardLocators.statusColSupportView);
                        break;
                }
            } else {
                GemTestReporter.addTestStep("View Verification", "Unexpected View is Existing", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (!(colItems == (colItems2))) {
                GemTestReporter.addTestStep("Column Sorted", "Column is Sorted", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Column NOT Sorted", "Column is NOT Sorted", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to sort the columns, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void closeFilterTab() {
        try {
            //   DriverAction.waitUntilElementAppear(EmployeeDashboardLocators.crossIcon, 3);
            DriverAction.click(EmployeeDashboardLocators.crossIcon, "Close Button");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to close the Filter Tab, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void clickOnNotificationBtn() {
        try {
            DriverAction.waitUntilElementAppear(EmployeeDashboardLocators.notificationIcon, 3);
            DriverAction.click(EmployeeDashboardLocators.notificationIcon, "Notification Icon");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to click on Notification Button, Error Occurred", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void clickOnShowMoreBtn() {
        try {
            DriverAction.waitUntilElementAppear(EmployeeDashboardLocators.showMore, 10);
            DriverAction.click(EmployeeDashboardLocators.showMore, "Show More");
            DriverAction.waitSec(3);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to click on Show More Button, Error Occurred", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void clickOnUnreadTab() {
        try {
            DriverAction.waitUntilElementAppear(EmployeeDashboardLocators.unreadOption, 3);
            DriverAction.click(EmployeeDashboardLocators.unreadOption);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to click on Unread Tab, Error Occurred", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void switchesToEmployeeView() {
        try {
            DriverAction.waitSec(4);
            DriverAction.click(EmployeeDashboardLocators.supportView, "Support View");
            DriverAction.waitSec(4);
            DriverAction.click(EmployeeDashboardLocators.employeeView, "Employee View");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to Switch to Employee View, Error Occurred", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void clickSideMenuButton() {
        try {
            if (DriverAction.isExist(EmployeeDashboardLocators.sideMenuButton)) {
                DriverAction.click(EmployeeDashboardLocators.sideMenuButton, "Side Menu Button");
            } else {
                GemTestReporter.addTestStep("Side Menu Button", "Side Menu Button does not Exist!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to click on Side Menu Button, Error Occurred", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void clickPreviousButton() {
        try {
            if (DriverAction.isExist(EmployeeDashboardLocators.previousBtn)) {
                DriverAction.click(EmployeeDashboardLocators.previousBtn, "Previous Button");
            } else {
                GemTestReporter.addTestStep("Previous Button", "Previous Button does not Exist!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to click on Previous Button, Error Occurred", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void clickNextButton() {
        try {
            if (DriverAction.isExist(EmployeeDashboardLocators.nextBtn)) {
                DriverAction.click(EmployeeDashboardLocators.nextBtn, "Next Button");
            } else {
                GemTestReporter.addTestStep("Next Button", "Next Button does not Exist!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to click on Next Button, Error Occurred", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void clickToggleButton() {
        try {
            if (DriverAction.isExist(EmployeeDashboardLocators.filterTicketsToggle)) {
                DriverAction.click(EmployeeDashboardLocators.filterTicketsToggle, "Toggle Button");
            } else {
                GemTestReporter.addTestStep("Toggle Button", "Toggle Button does not Exist!", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to click on Toggle Button, Error Occurred", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void verifyTicketDetailsPage() {
        try {
            String ticketDetailsPage = DriverAction.getCurrentURL();
            if (ticketDetailsPage.contains("ticket-details")) {
                GemTestReporter.addTestStep("Ticket Details Page Verification", "Ticket Details Page is Successfully Opened", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Ticket Details Page Verification", "Ticket Details Page is not Opened", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to verify Ticket Details Page, Error Occurred", "Exception occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void selectRowsPerPageValue(String drpDownValue) {
        try {
            if (DriverAction.isExist(EmployeeDashboardLocators.rowsPerPageDropDown)) {
                DriverAction.scrollIntoView(EmployeeDashboardLocators.nextBtn);
                Select rowsPerPage = new Select(DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.rowsPerPageDropDown));
                DriverAction.waitSec(2);
                rowsPerPage.selectByValue(drpDownValue);
                DriverAction.waitSec(2);
                DriverAction.scrollIntoView(EmployeeDashboardLocators.rowsPerPageDropDown);
                DriverAction.waitSec(2);
                GemTestReporter.addTestStep("Rows Per Page DropDown", drpDownValue + " selected from Rows Per Page DropDown", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Rows Per Page DropDown", "Rows Per Page DropDown does not Exist", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to select value from Rows Per Page DropDown, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void clickOnTab(String tabName) {
        try {
            switch (tabName) {
                case ("Assigned"):
                    waitForElement(EmployeeDashboardLocators.headerName("Assigned"), 10);
                    DriverAction.click(EmployeeDashboardLocators.headerName("Assigned"), "Assigned Tab");
                    break;

                case ("Unassigned"):
                    waitForElement(EmployeeDashboardLocators.headerName("Unassigned"), 10);
                    DriverAction.click(EmployeeDashboardLocators.headerName("Unassigned"), "Unassigned Tab");
                    break;

                case ("My Department"):
                    waitForElement(EmployeeDashboardLocators.headerName("My Department"), 10);
                    DriverAction.click(EmployeeDashboardLocators.headerName("My Department"), "My Department Tab");
                    break;

                case ("Others"):
                    waitForElement(EmployeeDashboardLocators.headerName("Others"), 10);
                    DriverAction.click(EmployeeDashboardLocators.headerName("Others"), "Others Tab");
                    break;
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to Click on the Tab, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void fetchDepartmentFromTicket(String nameOfDepartment, int k) {
        try {
            boolean flag = false;
            List<WebElement> myDepartmentTabTicketList = DriverAction.getElements(EmployeeDashboardLocators.idCol);
            try {
                DriverAction.click(myDepartmentTabTicketList.get(k));
            } catch (Exception e) {
                GemTestReporter.addTestStep("Ticked ID", "Ticket ID is not clickable Or Ticket can not be opened because the Department is not 'HR' ", STATUS.PASS, DriverAction.takeSnapShot());
            }
//            DriverAction.waitSec(3);
            CommonUtils.waitUntilLoaderDisappear();
            String actualDeptName = DriverAction.getElementText(EmployeeDashboardLocators.departmentTypeDetails);
            if (nameOfDepartment.contains(actualDeptName)) {
                flag = true;
            }
            if (flag) {
                GemTestReporter.addTestStep("Verify My Department", "Assigned Department is verified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify My Department", "Assigned Department is not verified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to fetch the Department from the Ticket, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void searchDataThroughGlobalSearch(String value) {
        try {
            waitUntilLoaderDisappear();
            DriverAction.typeText(EmployeeDashboardLocators.searchBox, value, "Search Box");
            DriverAction.click(EmployeeDashboardLocators.searchBtn);
            waitUntilLoaderDisappear();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to search data through Global Search, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void selectDepartmentFilters(String departmentTypes) {
        String[] departmentValues = departmentTypes.split(",");
        int departmentValuesSize = departmentValues.length;
        int departmentCount = 0;
        for (String s : departmentValues) {
            DriverAction.click(EmployeeDashboardLocators.departmentName(s));
            DriverAction.waitSec(1);
            departmentCount++;
        }
        if (departmentCount == departmentValuesSize) {
            GemTestReporter.addTestStep("Filter Selection", "Successfully selected the : " + departmentValuesSize + ", Department Filters", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Filter Selection", "Unable to select the Department Filters", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    public static void selectStatusFilters(String statusTypes) {
        try {
            String[] statusValues = statusTypes.split(",");
            int statusValuesSize = statusValues.length;
            int statusCount = 0;
            for (String s2 : statusValues) {
                DriverAction.click(EmployeeDashboardLocators.statusName(s2));
                DriverAction.waitSec(1);
                statusCount++;
            }
            if (statusCount == statusValuesSize) {
                GemTestReporter.addTestStep("Filter Selection", "Successfully selected the : " + statusValuesSize + ", Status Filters", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Filter Selection", "Unable to select the Status Filters", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to select the status filters, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public static void verifyStatusFiltersResult(String statusTypes) {
        try {
            boolean flag = false;
            boolean flag2 = true;
            ArrayList<String> statusColList = new ArrayList<>();
            selectRowsPerPageValue("25");
            int count = 0;
            while (flag2) {
                statusColList.addAll(DriverAction.getElementsText(EmployeeDashboardLocators.statusCol));
                if (DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.nextBtn).isEnabled()) {
                    DriverAction.click(EmployeeDashboardLocators.nextBtn, "Next Side Button");
                }
                waitUntilLoaderDisappear();
                flag2 = DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.nextBtn).isEnabled();
            }
            String[] statusValues = statusTypes.split(",");
            if (statusValues.length > 1) {
                for (String s : statusColList) {
                    for (String statusValue : statusValues) {
                        if (s.equalsIgnoreCase(statusValue)) {
                            count++;
                        }
                        if (count == 1) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                        count = 0;
                    }
                }
            } else {
                for (String s : statusColList) {
                    if (s.equalsIgnoreCase(statusTypes)) {
                        count++;
                    }
                    if (count == 1) {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                    count = 0;
                }
            }
            if (flag) {
                GemTestReporter.addTestStep("Filters Verification", "Results shown are from selected Status Filters", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Filters Verification", "Results shown does not contains all selected Status Filters", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (
                Exception e) {
            GemTestReporter.addTestStep("Unable to verify the status filters, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    public static void verifyDepartmentFiltersResult(String departmentTypes) {
        try {
            boolean flag = false;
            boolean flag2 = true;
            ArrayList<String> departmentColList = new ArrayList<>();
            selectRowsPerPageValue("25");
            int count = 0;
            while (flag2) {
                departmentColList.addAll(DriverAction.getElementsText(EmployeeDashboardLocators.departmentCol));
                if (DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.nextBtn).isEnabled()) {
                    DriverAction.click(EmployeeDashboardLocators.nextBtn, "Next Side Button");
                }
                waitUntilLoaderDisappear();
                flag2 = DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.nextBtn).isEnabled();
            }
            String[] departmentValues = departmentTypes.split(",");
            if (departmentValues.length > 1) {
                for (String s : departmentColList) {
                    for (String departmentValue : departmentValues) {
                        if (s.equalsIgnoreCase(departmentValue)) {
                            count++;
                        }
                        if (count == 1) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                        count = 0;
                    }
                }
            } else {
                for (String s : departmentColList) {
                    if (s.equalsIgnoreCase(departmentTypes)) {
                        count++;
                    }
                    if (count == 1) {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                    count = 0;
                }
            }
            if (flag) {
                GemTestReporter.addTestStep("Filters Verification", "Results shown are from selected Department Filters", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Filters Verification", "Results shown does not contains all selected Department Filters", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to verify the department filters, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);

        }
    }

    public static void waitUntilLoaderDisappear() {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(EmployeeDashboardLocators.loadScreen));
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    public static void waitUntilElementIsClickable(By loc) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(loc));
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    public static List<String> getAllColumnData(String columnName) {
        String locatorName = "";
        try {
            switch (columnName) {
                case "calender":
                    locatorName = "calenderData";
                    break;
                case "ID":
                    locatorName = "sortIDColumn";
                    break;
                case "Subject":
                    locatorName = "sortSubjectColumn";
                    break;
                case "Assigned to":
                    locatorName = "sortAssignedToColumn";
                    break;
                case "Status":
                    locatorName = "sortStatusColumn";
                    break;
                case "Created on":
                    locatorName = "sortCreatedOnColumn";
                    break;
                case "Department":
                    locatorName = "sortDepartmentColumn";
                    break;

            }
            if (DriverAction.isExist(EmployeeDashboardLocators.rowPerPageDownArrow("25")))
                DriverAction.getElement(EmployeeDashboardLocators.rowPerPageDownArrow("25")).click();
            DriverAction.waitSec(2);
            List<String> presentDataInColumn = DriverAction.getElementsText(EmployeeDashboardLocators.elementByClass(locatorName, ""));
            while (DriverAction.getElement(EmployeeDashboardLocators.nextPageBtn).isEnabled()) {
                DriverAction.getElement(EmployeeDashboardLocators.nextPageBtn).click();
                CommonUtils.waitUntilLoaderDisappear();
                presentDataInColumn.addAll(DriverAction.getElementsText(EmployeeDashboardLocators.elementByClass(locatorName, "")));
            }
            return presentDataInColumn;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    public static void clickOnButton(String btnName) {
        String locatorType = "";
        try {
            switch (btnName) {
                case "Search":
                case "Update":
                case "Clear Data":
                case "Clear all":
                case "Filter":
                case "Create New Ticket":
                case "Submit":
                case "Preview":
                case "Continue":
                    locatorType = "button";
                    break;
                case "Choose Resolution Code":
                case "Choose Reason":
                case "VIP":
                case "Support View":
                case "Unread":
                    locatorType = "div";
                    break;
                case "info":
                case "history":
                case "check":
                case "logout":
                case "toggle icon":
                case "notification":
                case "Support":
                case "userGuide":
                case "calender":
                    locatorType = "image";
                    break;
                case "Cancel Ticket":
                    locatorType = "span";
                    break;
                case "Cancel Ticket button":
                    locatorType = "button";
                    btnName = "Cancel Ticket";
                    break;
            }
            CommonUtils.waitUntilLoaderDisappear();
            CommonUtils.waitUntilElementIsClickable(EmployeeDashboardLocators.elementWithText(locatorType, btnName));
            if (DriverAction.isExist(EmployeeDashboardLocators.elementWithText(locatorType, btnName))) {
                DriverAction.click(EmployeeDashboardLocators.elementWithText(locatorType, btnName));
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    public static List<String> getMonthList() {
        List<String> tempList = new ArrayList<>();
        tempList.add("January");
        tempList.add("February");
        tempList.add("March");
        tempList.add("April");
        tempList.add("May");
        tempList.add("June");
        tempList.add("July");
        tempList.add("August");
        tempList.add("September");
        tempList.add("October");
        tempList.add("November");
        tempList.add("December");
        return tempList;
    }

    public static void selectDateRange(String startDate, String endDate) {
        try {
            String[] startDateArr = startDate.split("/");
            CommonUtils.waitUntilLoaderDisappear();
            DriverAction.getElement(EmployeeDashboardLocators.selectCustomYear(startDateArr[2])).click();
            DriverAction.getElement(EmployeeDashboardLocators.selectCustomMonth(startDateArr[1])).click();
            List<WebElement> duplicateDates = DriverAction.getElements(EmployeeDashboardLocators.selectCustomDate(startDateArr[0]));
            if (duplicateDates.size() > 1) {
                for (WebElement duplicateDate : duplicateDates) {
                    try {
                        duplicateDate.click();
                        break;
                    } catch (Exception ignored) {
                    }
                }
            } else DriverAction.getElement(EmployeeDashboardLocators.selectCustomDate(startDateArr[0])).click();

            String[] endDateArr = endDate.split("/");
            CommonUtils.waitUntilLoaderDisappear();

            DriverAction.getElement(EmployeeDashboardLocators.selectCustomYear(endDateArr[2])).click();
            DriverAction.getElement(EmployeeDashboardLocators.selectCustomMonth(endDateArr[1])).click();
            duplicateDates = DriverAction.getElements(EmployeeDashboardLocators.selectCustomDate(endDateArr[0]));
            if (duplicateDates.size() > 1) {
                for (WebElement duplicateDate : duplicateDates) {
                    try {
                        duplicateDate.click();
                        break;
                    } catch (Exception ignored) {
                    }
                }
            } else DriverAction.getElement(EmployeeDashboardLocators.selectCustomDate(endDateArr[0])).click();
            if (DriverAction.isExist(EmployeeDashboardLocators.elementByClass("span", "rdrStartEdge"))
                    && DriverAction.isExist(EmployeeDashboardLocators.elementByClass("span", "rdrEndEdge")))
                GemTestReporter.addTestStep("Select custom date",
                        "Custom date is selected", STATUS.PASS, DriverAction.takeSnapShot());

            else GemTestReporter.addTestStep("Select custom date",
                    "Custom date is not selected", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    public static String formatDate(String date) {
        try {
            String formattedDate = "";
            String[] tempArr = date.split("/");
            formattedDate = formattedDate + tempArr[0];
            switch (tempArr[1]) {
                case "01":
                    formattedDate = formattedDate + " Jan ";
                    break;
                case "02":
                    formattedDate = formattedDate + " Feb ";
                    break;
                case "03":
                    formattedDate = formattedDate + " Mar ";
                    break;
                case "04":
                    formattedDate = formattedDate + " Apr ";
                    break;
                case "05":
                    formattedDate = formattedDate + " May ";
                    break;
                case "06":
                    formattedDate = formattedDate + " Jun ";
                    break;
                case "07":
                    formattedDate = formattedDate + " Jul ";
                    break;
                case "08":
                    formattedDate = formattedDate + " Aug ";
                    break;
                case "09":
                    formattedDate = formattedDate + " Sep ";
                    break;
                case "10":
                    formattedDate = formattedDate + " Oct ";
                    break;
                case "11":
                    formattedDate = formattedDate + " Nov ";
                    break;
                case "12":
                    formattedDate = formattedDate + " Dec ";
                    break;
            }
            formattedDate = formattedDate + tempArr[2];
            return formattedDate;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    public static boolean addFilter(String keywords) {
        passed = false;
        try {
            String[] keywordsList = keywords.split(";");
            CommonUtils.waitUntilElementIsClickable(EmployeeDashboardLocators.elementWithText("button", "Filter"));
            DriverAction.click(EmployeeDashboardLocators.elementWithText("button", "Filter"));
            for (String s : keywordsList) {
                CommonUtils.waitUntilLoaderDisappear();
                CommonUtils.waitUntilElementIsClickable(EmployeeDashboardLocators.elementWithText("button", s));
                if (DriverAction.isExist(EmployeeDashboardLocators.elementByClass("div", "filters_filterWrapper__pVutl container"))
                        && DriverAction.isExist(EmployeeDashboardLocators.elementWithText("div", "Filters"))) {
                    DriverAction.click(EmployeeDashboardLocators.elementWithText("button", s));
                    passed = true;
                } else {
                    passed = false;
                    break;
                }
            }
            return passed;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    public static boolean checkIfFilterIsApplied(String type, String filterValues) {
        passed = false;
        boolean flag = false;
        try {
            DriverAction.waitSec(2);
            String[] tempArr = filterValues.split(";");
            List<String> tempList = new ArrayList<>();
            if (type.equalsIgnoreCase("Department")) {
                tempList = CommonUtils.getAllColumnData("Department");
            } else if (type.equalsIgnoreCase("Status")) {
                tempList = CommonUtils.getAllColumnData("Status");
            } else if (type.equalsIgnoreCase("Both")) {
                tempList = CommonUtils.getAllColumnData("Department");
                tempList.addAll(CommonUtils.getAllColumnData("Status"));
            }
            if (tempList.size() == 0) {
                passed = true;
            } else {
                for (String s : tempList) {
                    for (String value : tempArr) {
                        if (s.equalsIgnoreCase(value)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        passed = true;
                    } else {
                        passed = false;
                        break;
                    }
                }
            }
            return passed;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    public static void searchKeyword(String typeOfKeyWord, String keywords) {
        passed = false;
        try {
            String[] keywordList = keywords.split(";");
            DriverAction.waitSec(2);
            for (String s : keywordList) {
                DriverAction.getElement(EmployeeDashboardLocators.elementById("input", "search")).clear();
                DriverAction.getElement(EmployeeDashboardLocators.elementWithText("button", "Search")).click();
                DriverAction.getElement(EmployeeDashboardLocators.elementById("input", "search")).sendKeys(s);
                DriverAction.getElement(EmployeeDashboardLocators.elementWithText("button", "Search")).click();
                CommonUtils.waitUntilLoaderDisappear();
                if (typeOfKeyWord.equalsIgnoreCase("Valid")) {
                    if ((DriverAction.isExist(EmployeeDashboardLocators.elementByClass("sortIDColumn", "")) ||
                            DriverAction.isExist(EmployeeDashboardLocators.elementByClass("sortSubjectColumn", ""))) &&
                            DriverAction.getElement(EmployeeDashboardLocators.elementById("input", "search")).getAttribute("value").equalsIgnoreCase(s)
                            && CommonUtils.checkNumberOfTicketOnHeader())
                        GemTestReporter.addTestStep("Check if valid keyword is searched",
                                "Successfully searched valid keyword: " + s, STATUS.PASS, DriverAction.takeSnapShot());
                    else {
                        GemTestReporter.addTestStep("Check if valid keyword is searched",
                                "Unable to search valid keyword: " + s, STATUS.FAIL, DriverAction.takeSnapShot());
                        break;
                    }
                } else if (typeOfKeyWord.equalsIgnoreCase("Invalid")) {
                    if ((!DriverAction.isExist(EmployeeDashboardLocators.elementByClass("sortIDColumn", "")) ||
                            !DriverAction.isExist(EmployeeDashboardLocators.elementByClass("sortSubjectColumn", "")))
                            && CommonUtils.checkNumberOfTicketOnHeader())
                        GemTestReporter.addTestStep("Check if invalid keyword is searched",
                                "Successfully searched invalid keyword: " + s, STATUS.PASS, DriverAction.takeSnapShot());
                    else {
                        GemTestReporter.addTestStep("Check if invalid keyword is searched",
                                "Unable to search invalid keyword: " + s, STATUS.FAIL, DriverAction.takeSnapShot());
                        break;
                    }
                }

            }

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    public static boolean checkNumberOfTicketOnHeader() {
        try {
            String[] headerCountString = DriverAction.getElementText(EmployeeDashboardLocators.elementByClass("div", "tabs_tabItem__5Muc1 tabs_fontLightGreen__TtaCd")).split(" ");
            String[] paginationRows = DriverAction.getElementText(EmployeeDashboardLocators.elementByClass("para", "MuiTablePagination-displayedRows css-1chpzqh")).split(" ");
            if (paginationRows.length == 3) {
                return paginationRows[paginationRows.length - 1].equalsIgnoreCase(headerCountString[headerCountString.length - 1].substring(1, headerCountString[headerCountString.length - 1].length() - 1));
            } else
                return paginationRows.length == 2 && (headerCountString[headerCountString.length - 1].substring(1, headerCountString[headerCountString.length - 1].length() - 1).equalsIgnoreCase("0"));
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    public String getDate() {
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate;
            formattedDate = currentDate.format(formatter);
            return formattedDate;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to get the date, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public int monthCount() {
        try {
            List<String> monthCounter = DriverAction.getElementsText(EmployeeDashboardLocators.monthCount);
            return monthCounter.size();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to get the Month Count, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public int weekDayCount() {
        try {
            List<String> weekDayCounter = DriverAction.getElementsText(EmployeeDashboardLocators.weekDayCount);
            return weekDayCounter.size();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to get the Week day Count, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public int getTicketsCount(String tabName) {
        try {
            waitForElement(EmployeeDashboardLocators.headerName(tabName), 10);
            clickOnTab(tabName);
            List<String> ticketsCountList = DriverAction.getElementsText(EmployeeDashboardLocators.idCol);
            if (DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.nextBtn).isEnabled()) {
                while (DriverManager.getWebDriver().findElement(EmployeeDashboardLocators.nextBtn).isEnabled()) {
//                    DriverAction.scrollIntoView(EmployeeDashboardLocators.nextBtn);
                    waitForElement(EmployeeDashboardLocators.nextBtn, 10);
                    DriverAction.click(EmployeeDashboardLocators.nextBtn, "Next Button");
                    ticketsCountList.addAll(DriverAction.getElementsText(EmployeeDashboardLocators.idCol));
                }
            } else {
                GemTestReporter.addTestStep("Next Button", "Next Button is Disabled because of not enough tickets", STATUS.PASS, DriverAction.takeSnapShot());
            }
            return ticketsCountList.size();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to get the ticket count, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public String getAssignedTicketsCount() {
        try {
            String assignedTabText = DriverAction.getElementText(EmployeeDashboardLocators.headerName("Assigned"));
            String[] s = assignedTabText.split(" ");
            return s[1];
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to get Assigned tickets count, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public String getUnassignedTicketsCount() {
        try {
            String unassignedTabText = DriverAction.getElementText(EmployeeDashboardLocators.headerName("Unassigned"));
            String[] s = unassignedTabText.split(" ");
            return s[1];
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to get Unassigned tickets count, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }

    public String getMyDepartmentTicketsCount() {
        try {
            String myDepartmentTabText = DriverAction.getElementText(EmployeeDashboardLocators.headerName("My Department"));
            String[] s = myDepartmentTabText.split(" ");
            return s[2];
        } catch (Exception e) {
            GemTestReporter.addTestStep("Unable to get My Department tickets count, Error Occurred", "Exception Occurred :" + e.getMessage(), STATUS.FAIL, DriverAction.takeSnapShot());
            throw e;
        }
    }
}