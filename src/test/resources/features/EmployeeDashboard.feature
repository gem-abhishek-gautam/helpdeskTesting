@EmployeeView
Feature: Employee view dashboard

  Background: Check login functionality
    Given Navigate to helpdesk and login
    Then Verify if login is successful
    And Switch to view "Employee View"

#Headers
  Scenario: HelpDesk, Verify the functionality of the Side Menu Button
    Given User clicks on the Toggle Button to collapse Side Menu
    Then User verifies the collapsed Side Menu
    And User clicks on the Toggle Button to expand Side Menu
    Then User verifies the expanded Side Menu

  Scenario: HelpDesk, Verify the User Guide Functionality
    Given User clicks on the User Guide Button
    Then User verifies the User Guide tab

  Scenario: HelpDesk, Verify the Contact Us
    Given User clicks on the Contact Us Icon
    Then User verifies the Contact Icon

  Scenario: Check logout button
    Given Click on logout button on header
    Then Verify if user is logged out of dashboard


#Notification

  Scenario: HelpDesk, Verify the Notification Functionality and Unread Notification Count
    Given User clicks on the Notification Button
    Then User verify the Notification Panel
    And User compares them with Unread Tab Notifications count with Unread Header count


  Scenario: HelpDesk, Verify the Show More Option
    Given User clicks on the Notification Button
    Then User verify the Show More working


  Scenario: HelpDesk, Verify the functionality of Mark all Read icon
    Given User clicks on the Notification Button
    Then User verify the Check icon is unchecked or not
    And User clicks on the Check icon
    Then User verifies that all notifications are read

  Scenario: HelpDesk, Navigate to a Ticket from Notifications
    Given User clicks on the Notification Button
    And User clicks on a Ticket from Notifications
    Then User verifies the appearance of Ticket Details


  # Pagination

  Scenario Outline: HelpDesk, Verify Rows per page
    Given User select <value> from Rows per page dropdown
    Then User verifies that the number of rows is equal to the <value>
    Examples:
      | value |
      | 5     |
      | 10    |
      | 25    |


  Scenario: HelpDesk, Verify Next/Previous Button functionalities
    Given User checks Next button should be enabled and Previous button should be disabled on first page
    And User clicks on Next button and verify that the previous button is enabled
    Then User clicks on Previous button and verify that it is disabled now

  #    SORTING
  Scenario Outline: Helpdesk, Verify if sorting functionality is working as expected for dashboard columns
    Given Click on "<id>" on sort button
    Then Verify if the "<id>" column is sorted in ascending order
    And Click on "<id>" sort button twice
    Then Verify if the "<id>" column is sorted in descending order
    Examples:
      | id          |
      | ID          |
      | Subject     |
      | Assigned to |
      | Status      |
      | Department  |
      | Created on  |

    #    CALENDAR
  Scenario: Helpdesk, Verify if user is able to search tickets with respect to current date selected in calendar
    Given Click on "calender" button
    Then Verify if calendar box appears
    And Check if current date is highlighted in calendar box
    Then Select current date from calendar
    And Verify if displayed data contains current date

  Scenario: Helpdesk, Check if correct number of months and year is present in respective dropdowns
    Given Click on "calender" button
    Then Check if month dropdown have "12" options
    And Check if year dropdown have "101" options

  Scenario: Helpdesk, Check if user is able to move to previous month and next month in calendar box
    Given Click on "calender" button
    Then CLick on previous button and next button to switch between months


  Scenario Outline: Helpdesk, Select random date in calendar box
    Given Click on "calender" button
    Then Verify if calendar box appears
    And Select "<startDate>" and "<endDate>" in calendar
    Examples:
      | startDate  | endDate    |
      | 10/04/2023 | 21/04/2023 |


  Scenario Outline: Helpdesk, Select random date in calendar box and then clear it
    Given Click on "calender" button
    Then Verify if calendar box appears
    And Select "<startDate>" and "<endDate>" in calendar
    Then Click on "Clear Data" button
    And Check if selected dates are deselected
    Examples:
      | startDate  | endDate    |
      | 30/03/2023 | 31/03/2023 |

  Scenario Outline: Helpdesk, Check if after selecting custom dates, the displayed tickets are in date range
    Given Click on "calender" button
    Then Verify if calendar box appears
    And Select "<startDate>" and "<endDate>" in calendar
    Then Check if displayed tickets are between "<startDate>" and "<endDate>"
    Examples:
      | startDate  | endDate    |
      | 30/03/2023 | 31/03/2023 |

  Scenario Outline: Helpdesk, Check if user is able to select specific year and month
    Given  Click on "calender" button
    Then Verify if calendar box appears
    And Select "<month>" and "<year>" from calendar box
    Examples:
      | month  | year |
      | August | 2001 |


#    Filter
  Scenario: Helpdesk, Check if the Hide cancelled, closed and resolved tickets toggle button is working
    Given Click on "Filter" button
    And Switch on the Hide cancelled, closed and resolved tickets toggle button
    Then Check if filter is working properly


  Scenario: Helpdesk, Verify if user is able to clear selected filters in filter box
    Given Select "Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected" from the Filter box
    And Click on "Clear all" button
    Then Check if all applied filters get deselect


  Scenario Outline: Helpdesk, Select department and status filter
    Given Select "<filters>" from the Filter box
    Then Verify if displayed "<type>" rows contains "<filters>" added filters
    Examples:
      | filters                                                                                | type |
      | IT;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected       | Both |
      | IT;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected            | Both |
      | IT;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                       | Both |
      | IT;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                                | Both |
      | IT;Resolved;Cancelled;Closed;Re-opened;Rejected                                        | Both |
      | IT;Cancelled;Closed;Re-opened;Rejected                                                 | Both |
      | IT;Closed;Re-opened;Rejected                                                           | Both |
      | IT;Re-opened;Rejected                                                                  | Both |
      | IT;Rejected                                                                            | Both |
      | IT;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected       | Both |
      | IT;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected            | Both |
      | IT;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                       | Both |
      | IT;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                                | Both |
      | IT;Resolved;Cancelled;Closed;Re-opened;Rejected                                        | Both |
      | IT;Cancelled;Closed;Re-opened;Rejected                                                 | Both |
      | IT;Closed;Re-opened;Rejected                                                           | Both |
      | IT;Re-opened;Rejected                                                                  | Both |
      | IT;Rejected                                                                            | Both |
      | Accounts;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected | Both |
      | Accounts;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected      | Both |
      | Accounts;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                 | Both |
      | Accounts;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                          | Both |
      | Accounts;Resolved;Cancelled;Closed;Re-opened;Rejected                                  | Both |
      | Accounts;Cancelled;Closed;Re-opened;Rejected                                           | Both |
      | Accounts;Closed;Re-opened;Rejected                                                     | Both |
      | Accounts;Re-opened;Rejected                                                            | Both |
      | Accounts;Rejected                                                                      | Both |
      | Accounts;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected | Both |
      | Accounts;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected      | Both |
      | Accounts;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                 | Both |
      | Accounts;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                          | Both |
      | Accounts;Resolved;Cancelled;Closed;Re-opened;Rejected                                  | Both |
      | Accounts;Cancelled;Closed;Re-opened;Rejected                                           | Both |
      | Accounts;Closed;Re-opened;Rejected                                                     | Both |
      | Accounts;Re-opened;Rejected                                                            | Both |
      | Accounts;Rejected                                                                      | Both |
      | HR;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened                | Both |
      | HR;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed                          | Both |
      | HR;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled                                 | Both |
      | HR;Open;Unassigned;Assigned;On Hold;Resolved                                           | Both |
      | HR;Open;Unassigned;Assigned;On Hold                                                    | Both |
      | HR;Open;Unassigned;Assigned                                                            | Both |
      | HR;Open;Unassigned                                                                     | Both |
      | HR;Open                                                                                | Both |
      | HR;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected       | Both |
      | HR;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected            | Both |
      | HR;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                       | Both |
      | HR;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                                | Both |
      | HR;Resolved;Cancelled;Closed;Re-opened;Rejected                                        | Both |
      | HR;Cancelled;Closed;Re-opened;Rejected                                                 | Both |
      | HR;Closed;Re-opened;Rejected                                                           | Both |
      | HR;Re-opened;Rejected                                                                  | Both |
      | HR;Rejected                                                                            | Both |
      | Admin;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened             | Both |
      | Admin;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed                       | Both |
      | Admin;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled                              | Both |
      | Admin;Open;Unassigned;Assigned;On Hold;Resolved                                        | Both |
      | Admin;Open;Unassigned;Assigned;On Hold                                                 | Both |
      | Admin;Open;Unassigned;Assigned                                                         | Both |
      | Admin;Open;Unassigned                                                                  | Both |
      | Admin;Open                                                                             | Both |
      | Admin;Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected    | Both |
      | Admin;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected         | Both |
      | Admin;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                    | Both |
      | Admin;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected                             | Both |
      | Admin;Resolved;Cancelled;Closed;Re-opened;Rejected                                     | Both |
      | Admin;Cancelled;Closed;Re-opened;Rejected                                              | Both |
      | Admin;Closed;Re-opened;Rejected                                                        | Both |
      | Admin;Re-opened;Rejected                                                               | Both |
      | Admin;Rejected                                                                         | Both |

  Scenario Outline: Helpdesk, Select all the department or Status filters and check if correct data is been displayed
    Given Select "<filters>" from the Filter box
    Then Verify if displayed "<type>" rows contains "<filters>" added filters
    Examples:
      | filters                                                                       | type       |
      | IT;Accounts;HR;Admin                                                          | Department |
      | Open;Unassigned;Assigned;On Hold;Resolved;Cancelled;Closed;Re-opened;Rejected | Status     |

  Scenario: Helpdesk, Open filter box and then close it
    Given Click on "Filter" button
    Then Close it and verify if it is closed


    #    SEARCH

  Scenario Outline: Helpdesk, Verify if user is able to search valid keywords in the global search box
    Given Enter "<typeOfKeyword>" keywords "<keywords>" in the global search box
    Examples:
      | typeOfKeyword | keywords     |
      | Valid         | test;req;api |
      | Invalid       | FIFA         |

  Scenario Outline: Helpdesk, Verify if clicking on cross icon present in search box the entered text get removed
    Given Enter "<typeOfKeyword>" keywords "<keywords>" in the global search box
    Then CLick on cross icon and check if entered text get removed
    Examples:
      | typeOfKeyword | keywords |
      | Valid         | test     |



