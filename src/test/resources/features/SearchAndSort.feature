@employee
Feature: Testcases for search and sort functionality

  Background: Check login functionality
    Given Navigate to helpdesk and login
    Then Verify if login is successful
    And Switch to view "Employee View"

  Scenario Outline: Verify search functionality
    Given Search for keyword "<keyword>"
    Then Verify search result for "<keyword>"
    Examples:
      | keyword |
      | ReQ     |
      | request |
      | test    |
      | $       |

  Scenario: Verify clear text button
    Given Search for keyword "test"
    Then Verify if search box text "test" is cleared

  Scenario: Verify sorting buttons functionality
    Given Verify sorting button for "ID" column
    Then Verify sorting button for "Subject" column
    Then Verify sorting button for "Department" column
    Then Verify sorting button for "Created on" column
    Then Verify sorting button for "Assigned to" column
    Then Verify sorting button for "Status" column
