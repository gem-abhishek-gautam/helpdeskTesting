package com.qa.helpdesk.utils;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.helpdesk.locators.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CommonUtils {

    public static void verifyIncidentPreview(String type, String subject, String desc, String dept, boolean fileFlag) {
        String subjectActual = DriverAction.getElementText(TicketLocators.ticketPreview("Subject"));
        String descActual = DriverAction.getElementText(TicketLocators.ticketPreview("Description"));
        String deptActual = DriverAction.getElementText(TicketLocators.ticketPreview("Department"));
        String typeActual = DriverAction.getElementText(TicketLocators.getPreviewTypeValue);
        if(typeActual.equalsIgnoreCase("Incident")){
            GemTestReporter.addTestStep("Preview ticket type","Ticket type expected: "+type+" Actual: "+typeActual,STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Preview ticket type","Ticket type expected: "+type+" Actual: "+typeActual,STATUS.FAIL,DriverAction.takeSnapShot());

        if(subjectActual.equalsIgnoreCase(subject)){
            GemTestReporter.addTestStep("Preview subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Preview subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.FAIL,DriverAction.takeSnapShot());

        if(deptActual.equalsIgnoreCase(dept)){
            GemTestReporter.addTestStep("Preview Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.PASS,DriverAction.takeSnapShot());
        } else if (dept.trim().isEmpty() && deptActual.equalsIgnoreCase("unassigned")){
            GemTestReporter.addTestStep("Preview Department","Department unassigned",STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Preview Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.FAIL,DriverAction.takeSnapShot());

        if(descActual.equalsIgnoreCase(desc)){
            GemTestReporter.addTestStep("Preview Description","Description expected: "+desc+" Actual: "+descActual,STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Preview Description","Description expected: "+desc+" Actual: "+descActual,STATUS.FAIL,DriverAction.takeSnapShot());

        if(fileFlag == DriverAction.isExist(TicketLocators.previewAttachments)){
            GemTestReporter.addTestStep("Preview Attachment","Preview attachment validated",STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Preview Attachment","Preview attachment not validated",STATUS.FAIL,DriverAction.takeSnapShot());

    }

    public static void verifyRequestPreview(String type, String subject, String desc, String dept, String category, String subCategory, boolean fileFlag) {
        String typeActual = DriverAction.getElementText(TicketLocators.getPreviewTypeValue);
        String subjectActual = DriverAction.getElementText(TicketLocators.ticketPreview("Subject"));
        String descActual = DriverAction.getElementText(TicketLocators.ticketPreview("Description"));
        String deptActual = DriverAction.getElementText(TicketLocators.ticketPreview("Department"));
        String categoryActual = DriverAction.getElementText(TicketLocators.ticketPreview("Category"));
        String subCategoryActual = DriverAction.getElementText(TicketLocators.ticketPreview("Sub-category"));

        if(typeActual.equalsIgnoreCase(type)){
            GemTestReporter.addTestStep("Preview ticket type","Ticket type expected: "+type+" Actual: "+typeActual,STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Verify ticket type","Ticket type expected: "+type+" Actual: "+typeActual,STATUS.FAIL,DriverAction.takeSnapShot());

        if(subjectActual.equalsIgnoreCase(subject)){
            GemTestReporter.addTestStep("Preview subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Preview subject","Subject expected: "+subject+" Actual: "+subjectActual,STATUS.FAIL,DriverAction.takeSnapShot());

        if(deptActual.equalsIgnoreCase(dept)){
            GemTestReporter.addTestStep("Preview Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.PASS,DriverAction.takeSnapShot());
        } else if (dept.trim().isEmpty() && deptActual.equalsIgnoreCase("unassigned")){
            GemTestReporter.addTestStep("Preview Department","Department unassigned",STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Preview Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.FAIL,DriverAction.takeSnapShot());

        if(descActual.equalsIgnoreCase(desc)){
            GemTestReporter.addTestStep("Preview Description","Description expected: "+desc+" Actual: "+descActual,STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Preview Description","Description expected: "+desc+" Actual: "+descActual,STATUS.FAIL,DriverAction.takeSnapShot());

        if(categoryActual.toLowerCase().contains(category.toLowerCase())){
            GemTestReporter.addTestStep("Preview Category","Category expected: "+category+" Actual: "+categoryActual,STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Preview Category","Category expected: "+category+" Actual: "+categoryActual,STATUS.FAIL,DriverAction.takeSnapShot());

        if(subCategoryActual.toLowerCase().contains(subCategory.toLowerCase())){
            GemTestReporter.addTestStep("Preview Sub-Category","Sub-Category expected: "+subCategory+" Actual: "+subCategoryActual,STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Preview Sub-Category","Sub-Category expected: "+subCategory+" Actual: "+subCategoryActual,STATUS.FAIL,DriverAction.takeSnapShot());

        if(fileFlag == DriverAction.isExist(TicketLocators.previewAttachments)){
            GemTestReporter.addTestStep("Preview Attachment","Preview attachment validated",STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Preview Attachment","Preview attachment not validated",STATUS.FAIL,DriverAction.takeSnapShot());

    }

    public static void createIncidentTicket(String subject, String desc, String dept, String filePath) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(),10);
        wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.createTicket));

        if(DriverAction.isExist(TicketLocators.createTicket)) {
            DriverAction.click(TicketLocators.createTicket);
            wait.until(ExpectedConditions.visibilityOfElementLocated(TicketLocators.ticketFormHeader));

            DriverAction.typeText(TicketLocators.subject,subject);
            DriverAction.typeText(TicketLocators.desc,desc);
            DriverAction.waitUntilElementClickable(TicketLocators.typeDropdown,10);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            DriverAction.click(TicketLocators.typeDropdown);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions("Incident")));
            DriverAction.click(TicketLocators.ticketDropdownOptions("Incident"));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdown("Department")));
            DriverAction.click(TicketLocators.ticketDropdown("Department"));
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions(dept)));
            DriverAction.click(TicketLocators.ticketDropdownOptions(dept));

            DriverAction.waitSec(2);
            if(!filePath.isEmpty()){
                DriverAction.fileUpload(TicketLocators.fileUpload,filePath);
            }
            DriverAction.waitSec(3);
            DriverAction.click(TicketLocators.previewButton);
            DriverAction.waitSec(1);
        }

    }

    public static void createRequestTicket(String subject, String desc, String dept, String category, String subCategory, String filePath) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(),10);
        wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.createTicket));
        if(DriverAction.isExist(TicketLocators.createTicket)){
            DriverAction.click(TicketLocators.createTicket);
            wait.until(ExpectedConditions.visibilityOfElementLocated(TicketLocators.ticketFormHeader));
            DriverAction.typeText(TicketLocators.subject,subject);
            DriverAction.typeText(TicketLocators.desc,desc);
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.typeDropdown));
            DriverAction.click(TicketLocators.typeDropdown);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions("Request")));
            DriverAction.click(TicketLocators.ticketDropdownOptions("Request"));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdown("Department")));
            DriverAction.click(TicketLocators.ticketDropdown("Department"));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions(dept)));
            DriverAction.click(TicketLocators.ticketDropdownOptions(dept));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdown("Category")));
            DriverAction.click(TicketLocators.ticketDropdown("Category"));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions(category)));
            JavascriptExecutor executor = (JavascriptExecutor)DriverManager.getWebDriver();
            executor.executeScript("arguments[0].click();", TicketLocators.ticketDropdownOptions(category));

//            DriverAction.click(TicketLocators.ticketDropdownOptions(category));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdown("Sub-category")));
//
            executor.executeScript("arguments[0].click();", TicketLocators.ticketDropdown("Sub-category"));
//            DriverAction.click(TicketLocators.ticketDropdown("Sub-category"));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            wait.until(ExpectedConditions.elementToBeClickable(TicketLocators.ticketDropdownOptions(subCategory)));

            executor.executeScript("arguments[0].click();", TicketLocators.ticketDropdownOptions(subCategory));
//            DriverAction.click(TicketLocators.ticketDropdownOptions(subCategory));
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
            }
            if(!filePath.isEmpty()){
                DriverAction.fileUpload(TicketLocators.fileUpload,filePath);
            }
            DriverAction.waitSec(3);
            DriverAction.click(TicketLocators.previewButton);
            DriverAction.waitSec(1);

        }
    }

    public static void verifyTicketDetails(String type, String dept, String desc, String category, String subCategory, String status, boolean fileFlag) {
        DriverAction.waitSec(3);
        String ticketID = DriverAction.getElementText(TicketLocators.postSubmitTicketID).replace("Ticket ID: ","");
        DriverAction.click(TicketLocators.postSubmitContinueButton);
        if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
            DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
        }
        DriverAction.waitUntilElementClickable(SearchAndSortLocators.ticketSearchButton,10);
        DriverAction.typeText(SearchAndSortLocators.ticketSearchBox,ticketID);
        DriverAction.waitSec(1);
        DriverAction.click(SearchAndSortLocators.ticketSearchButton);
        DriverAction.waitSec(2);
        if(DriverAction.isExist(TableAndPaginationLocators.firstTicketID)){
            DriverAction.click(TableAndPaginationLocators.firstTicketID);
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,15);
            }
            DriverAction.waitUntilElementClickable(TicketLocators.timelineToggle,10);
            String typeActual = DriverAction.getElementText(TicketLocators.ticketDetails("Type"));
            String descActual = DriverAction.getElementText(TicketLocators.ticketDetails("Description"));
            String deptActual = DriverAction.getElementText(TicketLocators.ticketDetails("Department"));
            String statusActual = DriverAction.getElementText(TicketLocators.ticketDetails("Status"));
            String urgencyActual = DriverAction.getElementText(TicketLocators.ticketDetails("Urgency"));
            String impactActual = DriverAction.getElementText(TicketLocators.ticketDetails("Impact"));
            String categoryActual = DriverAction.getElementText(TicketLocators.ticketDetails("Category"));
            String subCategoryActual = DriverAction.getElementText(TicketLocators.ticketDetails("Sub Category"));

            if(DriverAction.isExist(TicketLocators.ticketDetailsCard)){
                if(typeActual.equalsIgnoreCase(type)){
                    GemTestReporter.addTestStep("Verify ticket type","Ticket type expected: "+type+" Actual: "+typeActual,STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Verify ticket type","Ticket type expected: "+type+" Actual: "+typeActual,STATUS.FAIL,DriverAction.takeSnapShot());

                if(deptActual.equalsIgnoreCase(dept)){
                    GemTestReporter.addTestStep("Verify Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.PASS,DriverAction.takeSnapShot());
                } else if (dept.trim().isEmpty() && deptActual.equalsIgnoreCase("unassigned")){
                    GemTestReporter.addTestStep("Verify Department","Department unassigned",STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Verify Department","Department expected: "+dept+" Actual: "+deptActual,STATUS.FAIL,DriverAction.takeSnapShot());

                if(statusActual.equalsIgnoreCase(status)){
                    GemTestReporter.addTestStep("Verify Status","Status expected: "+status+" Actual: "+statusActual,STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Verify Status","Status expected: "+status+" Actual: "+statusActual,STATUS.FAIL,DriverAction.takeSnapShot());

                if(DriverAction.isExist(TicketLocators.timelineToggle)) {
                    if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                        DriverAction.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover,10);
                    }
                    DriverAction.waitUntilElementClickable(TicketLocators.timelineToggle,10);
                    DriverAction.click(TicketLocators.timelineToggle,"Timeline");
                    if(DriverAction.getElementText(TicketLocators.lastTimelineStatus).equalsIgnoreCase(status)) {
                        GemTestReporter.addTestStep("Timeline status","Latest status is updated on timeline",STATUS.PASS,DriverAction.takeSnapShot());
                    } else GemTestReporter.addTestStep("Timeline status","Status is not updated on timeline, Actual: "+DriverAction.getElementText(TicketLocators.lastTimelineStatus)+"",STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Timeline toggle","Timeline toggle not found",STATUS.FAIL,DriverAction.takeSnapShot());

                if(descActual.equalsIgnoreCase(desc)){
                    GemTestReporter.addTestStep("Verify Description","Description expected: "+desc+" Actual: "+descActual,STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Verify Description","Description expected: "+desc+" Actual: "+descActual,STATUS.FAIL,DriverAction.takeSnapShot());

                if(categoryActual.toLowerCase().contains(category.toLowerCase())){
                    GemTestReporter.addTestStep("Verify Category","Category expected: "+category+" Actual: "+categoryActual,STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Verify Category","Category expected: "+category+" Actual: "+categoryActual,STATUS.FAIL,DriverAction.takeSnapShot());

                if(subCategoryActual.toLowerCase().contains(subCategory.toLowerCase())){
                    GemTestReporter.addTestStep("Verify Sub-category","Sub-category expected: "+subCategory+" Actual: "+subCategoryActual,STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Verify Sub-category","Sub-category expected: "+subCategory+" Actual: "+subCategoryActual,STATUS.FAIL,DriverAction.takeSnapShot());

                if(urgencyActual.equalsIgnoreCase("Low")){
                    GemTestReporter.addTestStep("Verify Urgency","Urgency expected: Low Actual: "+urgencyActual,STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Verify Urgency","Urgency expected: Low Actual: "+urgencyActual,STATUS.FAIL,DriverAction.takeSnapShot());

                if(impactActual.equalsIgnoreCase("Low")){
                    GemTestReporter.addTestStep("Verify Impact","Impact expected: Low Actual: "+impactActual,STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Verify Impact","Impact expected: Low Actual: "+impactActual,STATUS.FAIL,DriverAction.takeSnapShot());

                if(fileFlag==DriverAction.isExist(TicketLocators.ticketDetailsAttachment)) {
                    GemTestReporter.addTestStep("Attachment validation","File attachment validated successfully",STATUS.PASS,DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Attachment validation","File attachment validation failed",STATUS.FAIL,DriverAction.takeSnapShot());

            }
        } else GemTestReporter.addTestStep("Ticket Search","Not found",STATUS.FAIL,DriverAction.takeSnapShot());


    }

    public static boolean isListInAscendingOrder(List<String> list) {
        String previous = "";
        for (String current : list) {
            if (current.compareTo(previous) < 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }
    public static boolean isListInDescOrder(List<String> list) {
        if(list == null || list.size() < 2) {
            return true;
        }

        String prev = list.get(0);
        for(int i = 1; i < list.size(); i++) {
            String current = list.get(i);
            if(prev.compareTo(current) < 0) {
                return false;
            }
            prev = current;
        }

        return true;
    }


    public static String getTableColPosition(String colName) {
        int idx=1;
        List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getTableColNames);
        for(WebElement ele:elements) {
            String title = DriverAction.getElementText(ele);
            if(title.equalsIgnoreCase(colName)){
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

}