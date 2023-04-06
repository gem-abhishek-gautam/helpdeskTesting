@regression
Feature: Calendar functionality

  Background: Check login functionality
    Given Navigate to helpdesk and login
    Then Verify if login is successful
    And Switch to view "Employee View"

  Scenario: Check calendar current date display
    Given Open calendar on dashboard
    Then Verify if highlighted current date matches today's date

  Scenario Outline: Jump to specific month and year using dropdown options
    Given Open calendar on dashboard
    Then Verify if the month "<month>" and year "<year>" can be selected on calendar
    Examples:
      | month   | year |
      | August  | 2020 |
      | January | 2022 |

  Scenario: Select a date range from calendar
    Given Open calendar on dashboard
    When Specific date range from "15-February-2023" to "30-March-2023" is selected on calendar
    Then Verify if tickets shown are in selected date range from "15-February-2023" to "30-March-2023"

  Scenario: Jump to specific month and year and clear data for calendar
    Given Open calendar on dashboard
    When Verify if the date "20-June-2022" is shown on calendar
    And Clear calendar date filter
    Then Verify if highlighted current date matches today's date
