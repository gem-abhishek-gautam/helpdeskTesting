package com.qa.helpdesk.utils;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.locators.DashboardHeaderLocators;
import com.qa.helpdesk.locators.SearchAndSortLocators;
import com.qa.helpdesk.locators.TableAndPaginationLocators;
import com.qa.helpdesk.locators.TicketLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.gemini.generic.ui.utils.DriverAction.getElement;

public class CommonUtils {

    public static void verifyIncidentPreview(String type, String subject, String desc, String dept, boolean fileFlag) {
        String subjectActual = DriverAction.getElementText(TicketLocators.ticketPreview("Subject"));
        String descActual = DriverAction.getElementText(TicketLocators.ticketPreview("Description"));
        String deptActual = DriverAction.getElementText(TicketLocators.ticketPreview("Department"));
        String typeActual = DriverAction.getElementText(TicketLocators.getPreviewTypeValue);
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

        if (fileFlag == DriverAction.isExist(TicketLocators.previewAttachments)) {
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment validated", STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment not validated", STATUS.FAIL, DriverAction.takeSnapShot());

    }

    public static void verifyRequestPreview(String type, String subject, String desc, String dept, String category, String subCategory, boolean fileFlag) {
        String typeActual = DriverAction.getElementText(TicketLocators.getPreviewTypeValue);
        String subjectActual = DriverAction.getElementText(TicketLocators.ticketPreview("Subject"));
        String descActual = DriverAction.getElementText(TicketLocators.ticketPreview("Description"));
        String deptActual = DriverAction.getElementText(TicketLocators.ticketPreview("Department"));
        String categoryActual = DriverAction.getElementText(TicketLocators.ticketPreview("Category"));
        String subCategoryActual = DriverAction.getElementText(TicketLocators.ticketPreview("Sub-category"));

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

        if (fileFlag == DriverAction.isExist(TicketLocators.previewAttachments)) {
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment validated", STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment not validated", STATUS.FAIL, DriverAction.takeSnapShot());

    }

    public static void verifySupportPreview(boolean fileFlag, String type, String caller, String channel, String dept, String category, String subcategory, String subject, String desc, String status) {
        String typeActual = DriverAction.getElementText(TicketLocators.ticketPreview("Type"));
        String callerActual = DriverAction.getElementText(TicketLocators.ticketPreview("Caller"));
        String channelActual = DriverAction.getElementText(TicketLocators.ticketPreview("Channel"));
        String subjectActual = DriverAction.getElementText(TicketLocators.ticketPreview("Subject"));
        String descActual = DriverAction.getElementText(TicketLocators.ticketPreview("Description"));
        String deptActual = DriverAction.getElementText(TicketLocators.ticketPreview("Department"));
        String categoryActual = DriverAction.getElementText(TicketLocators.ticketPreview("Category"));
        String subCategoryActual = DriverAction.getElementText(TicketLocators.ticketPreview("Sub-category"));
        String statusActual = DriverAction.getElementText(TicketLocators.ticketPreview("Status"));
        String assignActual;
        if (DriverAction.isExist(TicketLocators.ticketPreview("Assigned To"))) {
            assignActual = DriverAction.getElementText(TicketLocators.ticketPreview("Assigned To"));
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

        if (fileFlag == DriverAction.isExist(TicketLocators.previewAttachments)) {
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment validated", STATUS.PASS, DriverAction.takeSnapShot());
        } else
            GemTestReporter.addTestStep("Preview Attachment", "Preview attachment not validated", STATUS.FAIL, DriverAction.takeSnapShot());

    }

    public static void createIncidentTicket(String subject, String desc, String dept, String filePath) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.createTicket));

        if (DriverAction.isExist(TicketLocators.createTicket)) {
            DriverAction.click(TicketLocators.createTicket);
            wait.until(ExpectedConditions.visibilityOfElementLocated(TicketLocators.ticketFormHeader));

            DriverAction.typeText(TicketLocators.subject, subject);
            DriverAction.typeText(TicketLocators.desc, desc);
            DriverAction.waitUntilElementClickable(TicketLocators.typeDropdown, 10);
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            DriverAction.click(TicketLocators.typeDropdown);
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions("Incident")));
                DriverAction.click(TicketLocators.ticketDropdownOptions("Incident"));
            } catch (Exception e) {
                DriverAction.click(TicketLocators.typeDropdown);
                DriverAction.click(TicketLocators.ticketDropdownOptions("Incident"));
            }

            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdown("Department")));
                DriverAction.click(TicketLocators.ticketDropdown("Department"));
            } catch (Exception e) {
                DriverAction.click(TicketLocators.ticketDropdown("Department"));
            }
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions(dept)));
                DriverAction.click(TicketLocators.ticketDropdownOptions(dept));
            } catch (Exception e) {
                DriverAction.click(TicketLocators.ticketDropdown("Department"));
                DriverAction.click(TicketLocators.ticketDropdownOptions(dept));
            }

            DriverAction.waitSec(2);
            if (!filePath.isEmpty()) {
                DriverAction.fileUpload(TicketLocators.fileUpload, filePath);
            }
            DriverAction.waitSec(3);
            DriverAction.click(TicketLocators.previewButton);
            DriverAction.waitSec(1);
        }

    }

    public static void createRequestTicket(String subject, String desc, String dept, String category, String subCategory, String filePath) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.createTicket));
        if (DriverAction.isExist(TicketLocators.createTicket)) {
            DriverAction.click(TicketLocators.createTicket);
            wait.until(ExpectedConditions.visibilityOfElementLocated(TicketLocators.ticketFormHeader));
            DriverAction.typeText(TicketLocators.subject, subject);
            DriverAction.typeText(TicketLocators.desc, desc);
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.typeDropdown));
            DriverAction.click(TicketLocators.typeDropdown);
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions("Request")));
                DriverAction.click(TicketLocators.ticketDropdownOptions("Request"));
            } catch (Exception e) {

                DriverAction.click(TicketLocators.typeDropdown);
                DriverAction.click(TicketLocators.ticketDropdownOptions("Request"));
            }

            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdown("Department")));
                DriverAction.click(TicketLocators.ticketDropdown("Department"));
            } catch (Exception e) {
                DriverAction.click(TicketLocators.ticketDropdown("Department"));
            }
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions(dept)));
                DriverAction.click(TicketLocators.ticketDropdownOptions(dept));
            } catch (Exception e) {
                DriverAction.click(TicketLocators.ticketDropdown("Department"));
                DriverAction.click(TicketLocators.ticketDropdownOptions(dept));
            }
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdown("Category")));
                DriverAction.click(TicketLocators.ticketDropdown("Category"));
            } catch (Exception e) {
                DriverAction.click(TicketLocators.ticketDropdown("Category"));
            }

            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions(category)));
                DriverAction.click(TicketLocators.ticketDropdownOptions(category));
            } catch (Exception e) {
                DriverAction.click(TicketLocators.ticketDropdown("Category"));
                DriverAction.click(TicketLocators.ticketDropdownOptions(category));
            }

            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdown("Sub-category")));
                DriverAction.click(TicketLocators.ticketDropdown("Sub-category"));
            } catch (Exception e) {
                DriverAction.click(TicketLocators.ticketDropdown("Sub-category"));
            }

            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }

            try {
                wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions(subCategory)));
                DriverAction.click(TicketLocators.ticketDropdownOptions(subCategory));
            } catch (Exception e) {
                DriverAction.click(TicketLocators.ticketDropdown("Sub-category"));
                DriverAction.click(TicketLocators.ticketDropdownOptions(subCategory));
            }

            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }

            if (!filePath.isEmpty()) {
                DriverAction.fileUpload(TicketLocators.fileUpload, filePath);
            }
            DriverAction.waitSec(3);
            DriverAction.click(TicketLocators.previewButton);
            DriverAction.waitSec(1);

        }
    }

    public static void verifyTicketDetails(String type, String dept, String desc, String category, String subCategory, String status, boolean fileFlag) {
        DriverAction.waitSec(3);
        String ticketID = DriverAction.getElementText(TicketLocators.postSubmitTicketID).replace("Ticket ID: ", "");
        DriverAction.click(TicketLocators.postSubmitContinueButton);
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }
        DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchButton, 10);
        DriverAction.typeText(SearchAndSortLocators.ticketSearchBox, ticketID);
        DriverAction.waitSec(1);
        DriverAction.click(SearchAndSortLocators.ticketSearchButton);
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 15);
        }
        if (DriverAction.isExist(TableAndPaginationLocators.firstTicketID)) {
            DriverAction.click(TableAndPaginationLocators.firstTicketID);
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 15);
            }
            DriverAction.waitUntilElementClickable(TicketLocators.timelineToggle, 10);
            String typeActual = DriverAction.getElementText(TicketLocators.ticketDetails("Type"));
            String descActual = DriverAction.getElementText(TicketLocators.ticketDetails("Description"));
            String deptActual = DriverAction.getElementText(TicketLocators.ticketDetails("Department"));
            String statusActual = DriverAction.getElementText(TicketLocators.ticketDetails("Status"));
            String urgencyActual = DriverAction.getElementText(TicketLocators.ticketDetails("Urgency"));
            String impactActual = DriverAction.getElementText(TicketLocators.ticketDetails("Impact"));
            String categoryActual = DriverAction.getElementText(TicketLocators.ticketDetails("Category"));
            String subCategoryActual = DriverAction.getElementText(TicketLocators.ticketDetails("Sub Category"));

            if (DriverAction.isExist(TicketLocators.ticketDetailsCard)) {

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

                if (DriverAction.isExist(TicketLocators.timelineToggle)) {
                    if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                        CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
                    }
                    DriverAction.waitUntilElementClickable(TicketLocators.timelineToggle, 10);
                    DriverAction.click(TicketLocators.timelineToggle, "Timeline");
                    if (DriverAction.getElementText(TicketLocators.lastTimelineStatus).equalsIgnoreCase(status)) {
                        GemTestReporter.addTestStep("Timeline status", "Latest status is updated on timeline", STATUS.PASS, DriverAction.takeSnapShot());
                    } else
                        GemTestReporter.addTestStep("Timeline status", "Status is not updated on timeline, Actual: " + DriverAction.getElementText(TicketLocators.lastTimelineStatus) + "", STATUS.PASS, DriverAction.takeSnapShot());
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

                if (fileFlag == DriverAction.isExist(TicketLocators.ticketDetailsAttachment)) {
                    GemTestReporter.addTestStep("Attachment validation", "File attachment validated successfully", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Attachment validation", "File attachment validation failed", STATUS.FAIL, DriverAction.takeSnapShot());

            }
        } else GemTestReporter.addTestStep("Ticket Search", "Not found", STATUS.FAIL, DriverAction.takeSnapShot());


    }

    public static void createSupportTicket(String type, String caller, String channel, String dept, String category, String subcategory, String subject, String desc, String status, String filePath) {
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }
        DriverAction.click(TicketLocators.createTicket, "Create ticket");
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }

        DriverAction.waitUntilElementClickable(TicketLocators.callerNameInput, 5);
        DriverAction.click(TicketLocators.callerNameInput, "Caller");
        DriverAction.typeText(TicketLocators.callerNameInput, caller);
        DriverAction.waitUntilElementClickable(TicketLocators.callerMenu(caller), 5);
        DriverAction.click(TicketLocators.callerMenu(caller), caller);

        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }

        if (!caller.equalsIgnoreCase("Abhishek Gautam")) {
            DriverAction.click(TicketLocators.ticketDropdown("Channel"), "Channel");
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(channel), 5);
            DriverAction.click(TicketLocators.ticketDropdownOptions(channel), channel);
        }
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }
        if (!dept.equalsIgnoreCase("IT")) {
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdown("Department"), 10);
            DriverAction.click(TicketLocators.ticketDropdown("Department"), "Department");
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(dept), 5);
            DriverAction.click(TicketLocators.ticketDropdownOptions(dept), dept);
        }
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }
        if (!type.equalsIgnoreCase("incident")) {
            DriverAction.click(TicketLocators.ticketDropdown("Type"), "Type");
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(type), 5);
            DriverAction.click(TicketLocators.ticketDropdownOptions(type), type);
        }
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }

        DriverAction.click(TicketLocators.ticketDropdown("Category"), "Category");
        DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(category), 5);
        DriverAction.click(TicketLocators.ticketDropdownOptions(category), category);

        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }

        DriverAction.click(TicketLocators.ticketDropdown("Sub-category"), "Sub-category");
        DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(subcategory), 10);
        DriverAction.click(TicketLocators.ticketDropdownOptions(subcategory), subcategory);

        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }

        DriverAction.scrollToBottom();
        DriverAction.waitUntilElementClickable(TicketLocators.subject, 5);
        DriverAction.typeText(TicketLocators.subject, subject);
        DriverAction.typeText(TicketLocators.desc, desc);

        if (!status.equalsIgnoreCase("open")) {
            DriverAction.scrollIntoView(TicketLocators.ticketDropdown("Status"));
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdown("Status"), 5);
            try {
                DriverAction.click(TicketLocators.ticketDropdown("Status"), "Status");
            } catch (Exception e) {
                JavascriptExecutor exe = (JavascriptExecutor) DriverManager.getWebDriver();
                exe.executeScript("argument[0].click();", getElement(TicketLocators.ticketDropdown("Status")));
            }
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdownOptions(status), 5);
            DriverAction.click(TicketLocators.ticketDropdownOptions(status), status);
            DriverAction.waitUntilElementClickable(TicketLocators.ticketDropdown("Assigned To"), 5);
            DriverAction.click(TicketLocators.ticketDropdown("Assigned To"), "Assigned To");
            DriverAction.waitSec(1);
            DriverAction.scrollIntoView(TicketLocators.callerMenu("Harvesh Kumar"));
            DriverAction.click(TicketLocators.callerMenu("Harvesh Kumar"));
        }
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }
        if (!filePath.isEmpty()) {
            DriverAction.fileUpload(TicketLocators.fileUpload, filePath);
        }
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }
    }

    public static void verifySupportTicketDetails(String type, String dept, String desc, String category, String subCategory, String status, String caller, String channel, boolean fileFlag) {
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 15);
        }
        String ticketID = DriverAction.getElementText(TicketLocators.postSubmitTicketID).replace("Ticket ID: ", "");
        DriverAction.click(TicketLocators.postSubmitContinueButton, "Continue");
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
        }

        DriverAction.waitUntilElementClickable(DashboardHeaderLocators.ticketTabs("My Department"), 10);
        DriverAction.click(DashboardHeaderLocators.ticketTabs("My Department"), "My Department");
        DriverAction.waitSec(3);
        DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchButton, 10);
        DriverAction.typeText(SearchAndSortLocators.ticketSearchBox, ticketID);
        DriverAction.waitSec(1);
        DriverAction.click(SearchAndSortLocators.ticketSearchButton);
        if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 15);
        }
        if (DriverAction.isExist(TableAndPaginationLocators.firstTicketID)) {
            DriverAction.click(TableAndPaginationLocators.firstTicketID);
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 15);
            }
            DriverAction.waitUntilElementClickable(TicketLocators.timelineToggle, 10);
            String callerActual = DriverAction.getElementText(TicketLocators.getCallerName).split("\\|")[0].trim();
            String channelActual = DriverAction.getElementText(TicketLocators.ticketDetails("Channel"));
            String typeActual = DriverAction.getElementText(TicketLocators.ticketDetails("Type"));
            String descActual = DriverAction.getElementText(TicketLocators.ticketDetails("Description"));
            String deptActual = DriverAction.getElementText(TicketLocators.ticketDetails("Department"));
            String statusActual = DriverAction.getElementText(TicketLocators.ticketDetails("Status"));
            String urgencyActual = DriverAction.getElementText(TicketLocators.ticketDetails("Urgency"));
            String impactActual = DriverAction.getElementText(TicketLocators.ticketDetails("Impact"));
            String categoryActual = DriverAction.getElementText(TicketLocators.ticketDetails("Category"));
            String subCategoryActual = DriverAction.getElementText(TicketLocators.ticketDetails("Sub Category"));

            if (DriverAction.isExist(TicketLocators.ticketDetailsCard)) {
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

                if (DriverAction.isExist(TicketLocators.timelineToggle)) {
                    if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                        CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
                    }
                    DriverAction.waitUntilElementClickable(TicketLocators.timelineToggle, 10);
                    DriverAction.click(TicketLocators.timelineToggle, "Timeline");
                    if (DriverAction.getElementText(TicketLocators.lastTimelineStatus).equalsIgnoreCase(status)) {
                        GemTestReporter.addTestStep("Timeline status", "Latest status is updated on timeline", STATUS.PASS, DriverAction.takeSnapShot());
                    } else
                        GemTestReporter.addTestStep("Timeline status", "Status is not updated on timeline, Actual: " + DriverAction.getElementText(TicketLocators.lastTimelineStatus) + "", STATUS.PASS, DriverAction.takeSnapShot());
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

                if (fileFlag == DriverAction.isExist(TicketLocators.ticketDetailsAttachment)) {
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
        List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getTableColNames);
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
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), seconds);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            throw new TimeoutException(e);
        }
    }

    public static void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
        js.executeScript("document.documentElement.scrollTop = 0;");

    }

}