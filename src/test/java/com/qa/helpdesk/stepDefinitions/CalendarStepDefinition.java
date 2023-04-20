package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.helpdesk.locators.DashboardHeaderLocators;
import com.qa.helpdesk.locators.FilterAndCalendarLocators;
import com.qa.helpdesk.locators.SupportTicketLocators;
import com.qa.helpdesk.locators.TableAndPaginationLocators;
import com.qa.helpdesk.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class CalendarStepDefinition {


    @Given("Open calendar on dashboard")
    public void openCalendar() {
        try {
            if(DriverAction.getElementText(DashboardHeaderLocators.getActiveTab).equalsIgnoreCase("others") && DriverAction.isExist(FilterAndCalendarLocators.calendarButtonOther)) {
                DriverAction.click(FilterAndCalendarLocators.calendarButtonOther, "Calendar");
                DriverAction.waitSec(2);
            }
            else if (DriverAction.isExist(FilterAndCalendarLocators.calendarButton)) {
                DriverAction.click(FilterAndCalendarLocators.calendarButton, "Calendar");
                DriverAction.waitSec(2);
            } else
                GemTestReporter.addTestStep("Calendar button", "Dashboard calendar not found", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if highlighted current date matches today's date")
    public void verifyCurrentDate() {
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMMM-yyyy");
            String today = currentDate.format(formatter);
            String calDay = DriverAction.getElementText(FilterAndCalendarLocators.calendarDayToday);
            Select selectMonth = new Select(DriverAction.getElement(FilterAndCalendarLocators.calendarMonthPicker));
            Select selectYear = new Select(DriverAction.getElement(FilterAndCalendarLocators.calendarYearPicker));

            String calMonth = DriverAction.getElementText(selectMonth.getFirstSelectedOption());
            String calYear = DriverAction.getElementText(selectYear.getFirstSelectedOption());
            String date = calDay + "-" + calMonth + "-" + calYear;
            if (today.equals(date)) {
                GemTestReporter.addTestStep("Calendar current date", "Current date matched expected: " + today + " actual: " + date + "", STATUS.PASS, DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Calendar current date", "Current date mismatched expected: " + today + " actual: " + date + "", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @Then("Verify if the date {string} is shown on calendar")
    public void verifyIfTheDateIsShownOnCalendar(String date) {
        try {
            List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
            if (DriverAction.isExist(FilterAndCalendarLocators.calendarCard)) {
                String day = date.split("-")[0];
                String month = date.split("-")[1];
                String year = date.split("-")[2];
                Select selectMonth = new Select(DriverAction.getElement(FilterAndCalendarLocators.calendarMonthPicker));
                Select selectYear = new Select(DriverAction.getElement(FilterAndCalendarLocators.calendarYearPicker));

                String calMonth = DriverAction.getElementText(selectMonth.getFirstSelectedOption());
                String calYear = DriverAction.getElementText(selectYear.getFirstSelectedOption());

                if (Integer.parseInt(year) > Integer.parseInt(calYear)) {
                    for (int i = 0; i < 12 * (Integer.parseInt(year) - Integer.parseInt(calYear)); i++) {
                        if (DriverAction.isExist(FilterAndCalendarLocators.calendarNextButton)) {
                            DriverAction.click(FilterAndCalendarLocators.calendarNextButton,"Next button");
                        }
                    }
                } else {
                    for (int i = 0; i < 12 * (Integer.parseInt(calYear) - Integer.parseInt(year)); i++) {
                        if (DriverAction.isExist(FilterAndCalendarLocators.calendarPrevButton)) {
                            DriverAction.click(FilterAndCalendarLocators.calendarPrevButton,"Prev button");
                        }
                    }
                }

                if (months.indexOf(month) > months.indexOf(calMonth)) {
                    int clicks = months.indexOf(month) - months.indexOf(calMonth);
                    for (int i = 0; i < clicks; i++) {
                        if (DriverAction.isExist(FilterAndCalendarLocators.calendarNextButton)) {
                            DriverAction.click(FilterAndCalendarLocators.calendarNextButton,"Next button");
                        }

                    }
                } else {
                    int clicks = months.indexOf(calMonth) - months.indexOf(month);
                    for (int i = 0; i < clicks; i++) {
                        if (DriverAction.isExist(FilterAndCalendarLocators.calendarPrevButton)) {
                            DriverAction.click(FilterAndCalendarLocators.calendarPrevButton,"Prev button");
                        }

                    }
                }

                DriverAction.click(FilterAndCalendarLocators.dateSelector(day));
                DriverAction.waitSec(2);
                String calDay = DriverAction.getElementText(FilterAndCalendarLocators.selectedDate);
                calMonth = DriverAction.getElementText(selectMonth.getFirstSelectedOption());
                calYear = DriverAction.getElementText(selectYear.getFirstSelectedOption());
                if (calDay.contains(day) && calMonth.contains(month) && calYear.contains(year)) {
                    GemTestReporter.addTestStep("Date Visibility", "Selected date is visible, Expected: "+date+"", STATUS.PASS, DriverAction.takeSnapShot());
                } else GemTestReporter.addTestStep("Date Visibility", "Selected date is not visible, Expected: "+date+"", STATUS.FAIL, DriverAction.takeSnapShot());


            } else GemTestReporter.addTestStep("Calendar Visibility", "Calendar not visible", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if the month {string} and year {string} can be selected on calendar")
    public void verifyMonthAndYear(String month, String year) {
        try {
            Select selectMonth = new Select(DriverAction.getElement(FilterAndCalendarLocators.calendarMonthPicker));
            Select selectYear = new Select(DriverAction.getElement(FilterAndCalendarLocators.calendarYearPicker));
            DriverAction.dropDown(FilterAndCalendarLocators.calendarMonthPicker, month);
            DriverAction.dropDown(FilterAndCalendarLocators.calendarYearPicker, year);
            if (DriverAction.getElementText(selectMonth.getFirstSelectedOption()).equalsIgnoreCase(month) && DriverAction.getElementText(selectYear.getFirstSelectedOption()).equalsIgnoreCase(year)) {
                GemTestReporter.addTestStep("Month and year selection", "Selected month-year values matched", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Month and year selection", "Selected month-year values not matched Expected: " + month + " " + year + "", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }


    }

    @And("Clear calendar date filter")
    public void clearCalendarDateFilter() {
        try {
            DriverAction.waitSec(2);
            DriverAction.click(FilterAndCalendarLocators.calendarClearButton,"Clear data");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @When("Specific date range from {string} to {string} is selected on calendar")
    public void specificDateRangeIsSelectedOnCalendar(String fromDate, String toDate) {
        try {
            if (DriverAction.isExist(FilterAndCalendarLocators.calendarCard)) {
                String startDay = fromDate.split("-")[0];
                String startMonth = fromDate.split("-")[1];
                String startYear = fromDate.split("-")[2];

                String endDay = toDate.split("-")[0];
                String endMonth = toDate.split("-")[1];
                String endYear = toDate.split("-")[2];

                DriverAction.dropDown(FilterAndCalendarLocators.calendarMonthPicker, startMonth);
                DriverAction.dropDown(FilterAndCalendarLocators.calendarYearPicker, startYear);
                DriverAction.click(FilterAndCalendarLocators.dateSelector(startDay));
                if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
                }
                DriverAction.dropDown(FilterAndCalendarLocators.calendarMonthPicker, endMonth);
                DriverAction.dropDown(FilterAndCalendarLocators.calendarYearPicker, endYear);
                DriverAction.click(FilterAndCalendarLocators.dateSelector(endDay));

                GemTestReporter.addTestStep("Calendar Date selection", "Date range selected successfully", STATUS.PASS, DriverAction.takeSnapShot());
                if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
                }
            } else GemTestReporter.addTestStep("Calendar Visibility", "Calendar not visible", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if tickets shown are in selected date range from {string} to {string}")
    public void verifyIfTicketsShownAreInSelectedDateRange(String start, String end) {
        try {
            DriverAction.click(DashboardHeaderLocators.myTicketsHeader,"Tickets");
            DriverAction.dropDown(TableAndPaginationLocators.paginationDropdown,"25");
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            String pos = CommonUtils.getTableColPosition("Created on");
            List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getColValues(pos));
            int flag=0;
            String invalidDate="";
            for(WebElement ele:elements) {
                String dateVal = DriverAction.getElementText(ele);
                if(!CommonUtils.dateRangeValidate(start,end,dateVal)){
                    flag=1;
                    invalidDate = dateVal;
                    break;
                }
            }
            if(flag==1) {
                GemTestReporter.addTestStep("Date Range","Ticket found outside selected date range: "+invalidDate+"",STATUS.FAIL,DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Date Range","Tickets found within selected date range",STATUS.PASS,DriverAction.takeSnapShot());


        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred","Exception: "+e,STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @And("Select date {string} from calendar")
    public void selectDateFromCalendar(String date) {
        try {
            if(DriverAction.isExist(FilterAndCalendarLocators.calendarCard)) {
                String startDay = date.split("-")[0];
                String startMonth = date.split("-")[1];
                String startYear = date.split("-")[2];

                DriverAction.dropDown(FilterAndCalendarLocators.calendarMonthPicker, startMonth);
                DriverAction.dropDown(FilterAndCalendarLocators.calendarYearPicker, startYear);
                DriverAction.click(FilterAndCalendarLocators.dateSelector(startDay));
                if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                    CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
                }

            } else GemTestReporter.addTestStep("Calendar Visibility", "Calendar not visible", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if tickets are shown for selected date {string} only")
    public void verifyIfTicketsAreShownForSelectedDateOnly(String date) {
        try {
            DriverAction.click(DashboardHeaderLocators.myTicketsHeader,"Tickets");
            DriverAction.dropDown(TableAndPaginationLocators.paginationDropdown,"25");
            if(DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            String pos = CommonUtils.getTableColPosition("Created on");
            List<WebElement> elements = DriverAction.getElements(TableAndPaginationLocators.getColValues(pos));
            int flag=0;
            String invalidDate="";
            for(WebElement ele:elements) {
                String dateVal = DriverAction.getElementText(ele);
                if(!CommonUtils.dateRangeValidate(date,date,dateVal)){
                    flag=1;
                    invalidDate = dateVal;
                    break;
                }
            }
            if(flag==1) {
                GemTestReporter.addTestStep("Date Selection","Ticket found outside selected date: "+invalidDate+"",STATUS.FAIL,DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Date Selection","Tickets found within selected date",STATUS.PASS,DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

}
