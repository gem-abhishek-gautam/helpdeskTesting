package com.qa.helpdesk.utils;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.helpdesk.locators.DashboardHeaderLocators;
import com.qa.helpdesk.locators.SearchAndSortLocators;
import com.qa.helpdesk.locators.TableAndPaginationLocators;
import com.qa.helpdesk.locators.TicketLocators;
import org.openqa.selenium.WebElement;

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
        DriverAction.waitUntilElementClickable(TicketLocators.createTicket,10);
        if(DriverAction.isExist(TicketLocators.createTicket)) {
            DriverAction.click(TicketLocators.createTicket);
            DriverAction.waitUntilElementAppear(TicketLocators.ticketFormHeader,5);
            DriverAction.typeText(TicketLocators.subject,subject);
            DriverAction.typeText(TicketLocators.desc,desc);
            DriverAction.click(TicketLocators.typeDropdown);
            DriverAction.click(TicketLocators.ticketDropdownOptions("Incident"));
            DriverAction.waitSec(2);
            DriverAction.click(TicketLocators.ticketDropdown("Department"));
            DriverAction.click(TicketLocators.ticketDropdownOptions(dept));

            DriverAction.waitSec(2);
            if(!filePath.isEmpty()){
                DriverAction.fileUpload(TicketLocators.fileUpload,filePath);
            }
            DriverAction.waitSec(3);
            DriverAction.click(TicketLocators.previewButton);
            DriverAction.waitSec(3);
        }

    }

    public static void createRequestTicket(String subject, String desc, String dept, String category, String subCategory, String filePath) {
        DriverAction.waitUntilElementClickable(TicketLocators.createTicket,10);
        if(DriverAction.isExist(TicketLocators.createTicket)){
            DriverAction.click(TicketLocators.createTicket);
            DriverAction.waitUntilElementAppear(TicketLocators.ticketFormHeader,5);
            DriverAction.typeText(TicketLocators.subject,subject);
            DriverAction.typeText(TicketLocators.desc,desc);
            DriverAction.click(TicketLocators.typeDropdown);
            DriverAction.click(TicketLocators.ticketDropdownOptions("Request"));
            DriverAction.waitSec(2);
            DriverAction.click(TicketLocators.ticketDropdown("Department"));
            DriverAction.click(TicketLocators.ticketDropdownOptions(dept));
            DriverAction.waitSec(2);
            DriverAction.click(TicketLocators.ticketDropdown("Category"));
            DriverAction.click(TicketLocators.ticketDropdownOptions(category));
            DriverAction.waitSec(2);
            DriverAction.click(TicketLocators.ticketDropdown("Sub-category"));
            DriverAction.click(TicketLocators.ticketDropdownOptions(subCategory));
            if(!filePath.isEmpty()){
                DriverAction.fileUpload(TicketLocators.fileUpload,filePath);
            }
            DriverAction.waitSec(3);
            DriverAction.click(TicketLocators.previewButton);
            DriverAction.waitSec(3);

        }
    }

    public static void verifyTicketDetails(String type, String dept, String desc, String category, String subCategory, String status, boolean fileFlag) {
        DriverAction.waitSec(3);
        String ticketID = DriverAction.getElementText(TicketLocators.postSubmitTicketID).replace("Ticket ID: ","");
        DriverAction.click(TicketLocators.postSubmitContinueButton);
        DriverAction.waitSec(5);
        DriverAction.typeText(SearchAndSortLocators.ticketSearchBox,ticketID);
        DriverAction.waitSec(1);
        DriverAction.click(SearchAndSortLocators.ticketSearchButton);
        DriverAction.waitSec(3);
        if(DriverAction.isExist(TableAndPaginationLocators.firstTicketID)){
            DriverAction.click(TableAndPaginationLocators.firstTicketID);
            DriverAction.waitSec(5);

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