@regression
Feature: Testcases for pagination and filters

  Background: Check login functionality
    Given Navigate to helpdesk and login
    Then Verify if login is successful
    And Switch to view "Employee View"

  Scenario Outline: Check the pagination dropdown
    Given Select option "<option>" for pagination
    Then Verify result for selection "<option>"
    And Verify next and prev buttons for "<option>"
    Examples:
      | option |
      | 5      |
      | 10     |
      | 25     |

  Scenario Outline: Check Apply filter functionality
    Given Open filter option
    Then Select filter criteria as department "<dept>" and status "<status>"
    And Verify result for selection dept "<dept>" and status "<status>"
    Examples:
      | dept     | status     |
      |          | Unassigned |
      | IT,HR    | Unassigned |
      | HR       | Rejected   |
      | IT       | Open       |
      | IT       | Unassigned |
      | IT       | Assigned   |
      | IT       | On Hold    |
      | IT       | Resolved   |
      | IT       | Cancelled  |
      | IT       | Closed     |
      | IT       | Re-opened  |
      | IT       | Rejected   |
      | HR       | Open       |
      | HR       | Unassigned |
      | HR       | Assigned   |
      | HR       | On Hold    |
      | HR       | Resolved   |
      | HR       | Cancelled  |
      | HR       | Closed     |
      | HR       | Re-opened  |
      | HR       | Rejected   |
      | Admin    | Open       |
      | Admin    | Unassigned |
      | Admin    | Assigned   |
      | Admin    | On Hold    |
      | Admin    | Resolved   |
      | Admin    | Cancelled  |
      | Admin    | Closed     |
      | Admin    | Re-opened  |
      | Admin    | Rejected   |
      | Accounts | Open       |
      | Accounts | Unassigned |
      | Accounts | Assigned   |
      | Accounts | On Hold    |
      | Accounts | Resolved   |
      | Accounts | Cancelled  |
      | Accounts | Closed     |
      | Accounts | Re-opened  |
      | Accounts | Rejected   |

  Scenario Outline: Check Clear filter functionality
    Given Open filter option
    When Select filter criteria as department "<dept>" and status "<status>"
    And Clear all applied filters
    Then Verify if filters for dept "<dept>" and status "<status>" are cleared
    Examples:
      | dept        | status     |
      | Accounts,IT |            |
      |             | Unassigned |
      | IT          | Open       |
      | IT          | Unassigned |
      | IT          | Assigned   |
      | IT          | On Hold    |
      | IT          | Resolved   |
      | IT          | Cancelled  |
      | IT          | Closed     |
      | IT          | Re-opened  |
      | IT          | Rejected   |
      | HR          | Open       |
      | HR          | Unassigned |
      | HR          | Assigned   |
      | HR          | On Hold    |
      | HR          | Resolved   |
      | HR          | Cancelled  |
      | HR          | Closed     |
      | HR          | Re-opened  |
      | HR          | Rejected   |
      | Admin       | Open       |
      | Admin       | Unassigned |
      | Admin       | Assigned   |
      | Admin       | On Hold    |
      | Admin       | Resolved   |
      | Admin       | Cancelled  |
      | Admin       | Closed     |
      | Admin       | Re-opened  |
      | Admin       | Rejected   |
      | Accounts    | Open       |
      | Accounts    | Unassigned |
      | Accounts    | Assigned   |
      | Accounts    | On Hold    |
      | Accounts    | Resolved   |
      | Accounts    | Cancelled  |
      | Accounts    | Closed     |
      | Accounts    | Re-opened  |
      | Accounts    | Rejected   |


  Scenario Outline: Check filter toggle button functionality
    Given Open filter option
    When Select filter criteria as department "<dept>" and status "<status>"
    And Click on toggle button to hide closed, resolved and cancelled status
    Then Verify if closed, cancelled and resolved tickets are hidden
    Examples:
      | dept | status                      |
      | IT   | Unassigned,Cancelled,Closed |
      |      | Closed,Cancelled,Resolved   |
      |      | Closed,Cancelled            |
      |      | Cancelled,Resolved          |


