@employee
Feature: Header buttons functionality

  Background: Check login functionality
    Given Navigate to helpdesk and login
    Then Verify if login is successful
    And Switch to view "Employee View"

  Scenario: Check user guide and support functionality
    Given Click on support button
    Then Verify if support info "support.helpdesk@geminisolutions.com" is displayed
    And Click on user guide button
    Then Verify if user guide is opened

  Scenario: Check notification button
    Given Click on notification button
    When Notifications are displayed
    Then Verify if ticket details can be opened from notifications

  Scenario: Check 'mark all as read' button for notifications
    Given Check if is there an unread count for notifications
    When Unread count matches unread notifications
    Then Verify read notification button

  Scenario: Check logout button
    Given Click on logout button on header
    Then Verify if user is logged out of dashboard

  Scenario: Check side menu toggle button
    Given Click on toggle button on side menu
    Then Verify if menu gets collapsed or expanded