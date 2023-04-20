@support1
Feature: Testcases for Support view dashboard

  Background: Check login functionality
    Given Navigate to helpdesk and login
    Then Verify if login is successful

  Scenario: Check side menu toggle button for support view
    Given Click on toggle button on side menu
    Then Verify if menu gets collapsed or expanded

  Scenario: Check logout button
    Given Click on logout button on header
    Then Verify if user is logged out of dashboard

  Scenario: Check user guide and support functionality
    Given Click on support button
    Then Verify if support info "support.helpdesk@geminisolutions.com" is displayed
    And Click on user guide button
    Then Verify if user guide is opened

  Scenario: Check notification button
    Given Click on notification button
    When Notifications are displayed
    Then Verify if ticket details can be opened from notifications

  Scenario: Check 'mark all as read' button for notifications in support view
    Given Check if is there an unread count for notifications
    When Unread count matches unread notifications
    Then Verify read notification button

  Scenario: Check tickets count for each tab in support view
    Given Check presence of Assigned, Unassigned, My Department and Others tabs
    Then Verify if ticket count matches the number of tickets

  Scenario: Verify caller info and history in create ticket form
    Given Open create ticket form
    When Caller name "Abhishek Gautam" is provided
    Then Verify caller info and history

  Scenario: Verify caller info and history in ticket details page
    Given Open ticket details for a ticket
    Then Verify if caller info can be opened
    And Verify if caller history can be opened

  Scenario: Check if unassigned tickets can be assigned to a user
    Given Open "Unassigned" ticket category tab
    Then Assign a ticket to "Sheeza Bakshi" and verify it
    And Open "Unassigned" ticket category tab
    Then Assign a ticket to "Abhishek Gautam" and verify it

  Scenario Outline: Change status of an assigned ticket to On-Hold
    Given Open "Unassigned" ticket category tab
    Then Assign a ticket to "Abhishek Gautam" and verify it
    And Open "Assigned" ticket category tab
    Then Change and verify status of a ticket to "On-Hold" with reason "<reason>"
    Examples:
      | reason             |
      | Awaiting Caller    |
      | Awaiting Change    |
      | Awaiting Problem   |
      | Awaiting 3rd party |

  Scenario Outline: Change status of an assigned ticket to Resolved
    Given Open "Unassigned" ticket category tab
    Then Assign a ticket to "Abhishek Gautam" and verify it
    And Open "Assigned" ticket category tab
    Then Change and verify status of a ticket to "Resolved" with resolution "<code>" and remarks "resolve"
    Examples:
      | code       |
      | Manual     |
      | Workaround |
      | Caller     |
      | Duplicate  |
      | No Action  |

  Scenario: Check audit trail for a ticket
    Given Create ticket with file "attached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "IT", category "End User Hardware", sub-category "Desktop", subject "support test with file", desc "support test" and status "Open"
    Then Submit and verify ticket with file "attached" and type "Incident", caller "Abhishek Gautam", channel "Portal", dept "IT", category "End User Hardware", sub-category "Desktop", subject "support test with file", desc "support test" and status "Open"
    Then Verify ticket audit trail for "created"
    And Edit ticket details for field "Assigned To" and value "Sheeza Bakshi"
    Then Verify ticket audit trail for "assigned"
    And Edit ticket details for field "Configuration" and value "config1"
    Then Verify ticket audit trail for "Configuration"

  @test
  Scenario Outline: Check VIP filter for tickets
    Given Open "<tab>" ticket category tab
    When Enable VIP filter for tickets
    Then Verify VIP filter for "<tab>" ticket category
    Examples:
      | tab           |
      | Assigned      |
#      | Unassigned    |
      | My Department |

  @test
  Scenario: Check VIP filter for 'Others' tab
    Given Open "Others" ticket category tab
    When Search for dept "IT" and assigned to "Priyanka"
    And Enable VIP filter for tickets
    Then Verify VIP table results for others tab
    And Search for dept "Accounts" and assigned to "Jaish"
    Then Verify VIP table results for others tab

  @test
  Scenario Outline: Check column sorting for ticket tabs
    Given Open "<tab>" ticket category tab
    Then Verify sorting button for "ID" column
    Then Verify sorting button for "Subject" column
    Then Verify sorting button for "Caller" column
    Then Verify sorting button for "Created on" column
    Then Verify sorting button for "Assigned to" column
    Then Verify sorting button for "Status" column
    Examples:
      | tab           |
      | Assigned      |
      | Unassigned    |
      | My Department |

  @test
  Scenario: Check column sorting for 'Others' tab
    Given Open "Others" ticket category tab
    When Search for dept "IT" and assigned to "Priyanka"
    Then Verify sorting button for "ID" column
    And Search for dept "IT" and assigned to "Priyanka"
    Then Verify sorting button for "Subject" column
    And Search for dept "IT" and assigned to "Priyanka"
    Then Verify sorting button for "Caller" column
    And Search for dept "IT" and assigned to "Priyanka"
    Then Verify sorting button for "Created on" column
    And Search for dept "IT" and assigned to "Priyanka"
    Then Verify sorting button for "Assigned to" column
    And Search for dept "IT" and assigned to "Priyanka"
    Then Verify sorting button for "Status" column

  @test
  Scenario Outline: Verify search functionality for all tabs
    Given Open "<tab>" ticket category tab
    When Search for keyword "<keyword>"
    Then Verify search result for "<keyword>"
    Examples:
      | keyword  | tab           |
      | Android  | Assigned      |
      | request  | Unassigned    |
      | incident | My Department |

  @test
  Scenario Outline: Verify clear text button
    Given Open "<tab>" ticket category tab
    When Search for keyword "test"
    Then Verify if search box text "test" is cleared
    Examples:
      | tab           |
      | Assigned      |
      | Unassigned    |
      | My Department |

  @test
  Scenario: Check if ticket details can be accessed for other departments
    Given Open "Others" ticket category tab
    When Search for dept "IT" and assigned to "Priyanka"
    Then Verify ticket details page access for "IT" dept
    And Search for dept "Accounts" and assigned to "Aditya"
    Then Verify ticket details page access for "Accounts" dept
    And Search for dept "HR" and assigned to "Vishal"
    Then Verify ticket details page access for "HR" dept
    And Search for dept "Admin" and assigned to "Himanshu"
    Then Verify ticket details page access for "Admin" dept

  @test
  Scenario Outline: Select a date range from calendar for ticket tabs
    Given Open "<tab>" ticket category tab
    When Open calendar on dashboard
    Then Verify if highlighted current date matches today's date
    When Specific date range from "15-February-2023" to "30-March-2023" is selected on calendar
    Then Verify if tickets shown are in selected date range from "15-February-2023" to "30-March-2023"
    Examples:
      | tab           |
      | Assigned      |
      | Unassigned    |
      | My Department |

  @test
  Scenario: Select a date range from calendar for 'Others' tab
    Given Open "Others" ticket category tab
    When Search for dept "IT" and assigned to "Priyanka"
    And Open calendar on dashboard
    Then Verify if highlighted current date matches today's date
    And Specific date range from "15-February-2023" to "30-March-2023" is selected on calendar
    Then Verify if tickets shown are in selected date range from "15-February-2023" to "30-March-2023"

  Scenario Outline: Select a particular date from calendar in ticket tabs
    Given Open "<tab>" ticket category tab
    When Open calendar on dashboard
    And Select date "7-April-2023" from calendar
    Then Verify if tickets are shown for selected date "7-April-2023" only
    Examples:
      | tab           |
      | Assigned      |
      | Unassigned    |
      | My Department |

  Scenario: Select a particular date from calendar in 'Others' ticket tab
    Given Open "Others" ticket category tab
    When Search for dept "IT" and assigned to "Priyanka"
    When Open calendar on dashboard
    And Select date "7-April-2023" from calendar
    Then Verify if tickets are shown for selected date "7-April-2023" only

  Scenario Outline: Jump to specific date in ticket tabs and clear data for calendar
    Given Open "<tab>" ticket category tab
    When Open calendar on dashboard
    And Verify if the date "20-June-2022" is shown on calendar
    And Clear calendar date filter
    Then Verify if highlighted current date matches today's date
    Examples:
      | tab           |
      | Assigned      |
      | Unassigned    |
      | My Department |

  Scenario: Jump to specific date in 'Others' tab and clear data for calendar
    Given Open "Others" ticket category tab
    When Search for dept "IT" and assigned to "Priyanka"
    When Open calendar on dashboard
    And Verify if the date "20-June-2022" is shown on calendar
    And Clear calendar date filter
    Then Verify if highlighted current date matches today's date


  @test
  Scenario Outline: Check Apply filter functionality for ticket tabs
    Given Open "Assigned" ticket category tab
    When Open filter option
    And Select filter criteria as department "" and status "<status>"
    Then Verify result for selection dept "" and status "<status>"
    And Open "Unassigned" ticket category tab
    When Open filter option
    And Select filter criteria as department "" and status "<status>"
    Then Verify result for selection dept "" and status "<status>"
    And Open "My Department" ticket category tab
    When Open filter option
    And Select filter criteria as department "" and status "<status>"
    Then Verify result for selection dept "" and status "<status>"
    Examples:
      | status     |
      | Open       |
      | Unassigned |
      | Assigned   |
      | Hold       |
      | Resolved   |
      | Cancelled  |
      | Closed     |
      | Re-open    |


  Scenario Outline: Check Apply filter functionality for 'Others' ticket tab
    Given Open "Others" ticket category tab
    When Search for dept "IT" and assigned to "Priyanka"
    And Select filter criteria as department "" and status "<status>"
    Then Verify result for selection dept "" and status "<status>"
    Examples:
      | status     |
      | Open       |
      | Unassigned |
      | Assigned   |
      | Hold       |
      | Resolved   |
      | Cancelled  |
      | Closed     |
      | Re-open    |

  Scenario Outline: Check filter toggle button functionality in ticket tabs
    Given Open "<tab>" ticket category tab
    When Open filter option
    And Clear all applied filters
    And Select filter criteria as department "" and status "Closed,Cancelled,Resolved"
    And Click on toggle button to hide closed, resolved and cancelled status
    Then Verify if closed, cancelled and resolved tickets are hidden
    Examples:
      | tab           |
      | Assigned      |
      | Unassigned    |
      | My Department |

  Scenario: Check filter toggle button functionality for 'Others' tab
    Given Open "Others" ticket category tab
    When Search for dept "IT" and assigned to "Priyanka"
    And Open filter option
    And Clear all applied filters
    And Select filter criteria as department "" and status "Closed,Cancelled,Resolved"
    And Click on toggle button to hide closed, resolved and cancelled status
    Then Verify if closed, cancelled and resolved tickets are hidden






