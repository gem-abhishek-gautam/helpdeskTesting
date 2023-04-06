@regression
Feature: Testcases for Create Ticket

  Background: Check login functionality
    Given Navigate to helpdesk and login
    Then Verify if login is successful
    And Switch to view "Employee View"

  Scenario Outline: Verify Incident preview and ticket details without file
    Given Create incident without file and subject "<subject>", dept "<dept>" and description "<desc>"
    Then Submit and verify the incident details having file "unattached", subject "<subject>", dept "<dept>" and description "<desc>"
    Examples:
      | subject       | desc                                      | dept     |
      | test incident | this is test incident without file upload | Accounts |
      | test incident | this is test incident without file upload | IT       |
      | test incident | this is test incident without file upload | HR       |
      | test incident | this is test incident without file upload | Admin    |

  Scenario Outline: Verify Incident preview and ticket details with file
    Given Create incident with file and subject "<subject>", dept "<dept>" and description "<desc>"
    Then Submit and verify the incident details having file "attached", subject "<subject>", dept "<dept>" and description "<desc>"
    Examples:
      | subject       | desc                                   | dept     |
      | test incident | this is test incident with file upload | Accounts |
      | test incident | this is test incident with file upload | IT       |
      | test incident | this is test incident with file upload | HR       |
      | test incident | this is test incident with file upload | Admin    |

  Scenario Outline: Verify Request preview and ticket details without file
    Given Create request without file and with subject "<subject>", dept "<dept>", category "<catg>", sub-category "<sub>" and description "<desc>"
    Then Submit and verify the request details having file "unattached", subject "<subject>", dept "<dept>", description "<desc>", category "<catg>" and sub-category "<sub>"
    Examples:
      | subject      | dept     | desc                                     | catg             | sub                            |
      | test request | IT       | this is test request without file upload | Software         | Software License               |
      | test request | IT       | this is test request without file upload | Software         | Unlicensed/OpenSource          |
      | test request | IT       | this is test request without file upload | Access Request   | Creation Of DL/o365 group      |
      | test request | IT       | this is test request without file upload | Access Request   | Modification of DL/o365 group  |
      | test request | IT       | this is test request without file upload | Access Request   | Modification of security group |
      | test request | IT       | this is test request without file upload | Access Request   | New Security group             |
      | test request | IT       | this is test request without file upload | Hardware         | Docking station                |
      | test request | IT       | this is test request without file upload | Hardware         | Keyboard                       |
      | test request | IT       | this is test request without file upload | Hardware         | Mouse                          |
      | test request | IT       | this is test request without file upload | Hardware         | RAM                            |
      | test request | IT       | this is test request without file upload | Hardware         | Laptop                         |
      | test request | IT       | this is test request without file upload | Hardware         | Other                          |
      | test request | Accounts | this is test request without file upload | Travel           | cards                          |
      | test request | Accounts | this is test request without file upload | Travel           | insurance                      |
      | test request | Accounts | this is test request without file upload | Travel           | insurance                      |
      | test request | HR       | this is test request without file upload | Leave Management | LNSA                           |
      | test request | HR       | this is test request without file upload | Leave Management | Off                            |
      | test request | HR       | this is test request without file upload | Leave Management | Missed                         |
      | test request | Admin    | this is test request without file upload | Facility         | Courier                        |
      | test request | Admin    | this is test request without file upload | Facility         | Lunch                          |
      | test request | Admin    | this is test request without file upload | Facility         | Chair                          |
      | test request | Admin    | this is test request without file upload | Hardware         | Mobile                         |
      | test request | Admin    | this is test request without file upload | Registration     | Face                           |


  Scenario Outline: Verify Request preview and ticket details with file
    Given Create request with subject "<subject>", dept "<dept>", category "<catg>", sub-category "<sub>" and description "<desc>"
    Then Submit and verify the request details having file "attached", subject "<subject>", dept "<dept>", description "<desc>", category "<catg>" and sub-category "<sub>"
    Examples:
      | subject      | dept     | desc                                  | catg             | sub                            |
      | test request | IT       | this is test request with file upload | Software         | Software License               |
      | test request | IT       | this is test request with file upload | Software         | Unlicensed/OpenSource          |
      | test request | IT       | this is test request with file upload | Access Request   | Creation Of DL/o365 group      |
      | test request | IT       | this is test request with file upload | Access Request   | Modification of DL/o365 group  |
      | test request | IT       | this is test request with file upload | Access Request   | Modification of security group |
      | test request | IT       | this is test request with file upload | Access Request   | New Security group             |
      | test request | IT       | this is test request with file upload | Hardware         | Docking station                |
      | test request | IT       | this is test request with file upload | Hardware         | Keyboard                       |
      | test request | IT       | this is test request with file upload | Hardware         | Mouse                          |
      | test request | IT       | this is test request with file upload | Hardware         | RAM                            |
      | test request | IT       | this is test request with file upload | Hardware         | Laptop                         |
      | test request | IT       | this is test request with file upload | Hardware         | Other                          |
      | test request | Accounts | this is test request with file upload | Travel           | cards                          |
      | test request | Accounts | this is test request with file upload | Travel           | insurance                      |
      | test request | Accounts | this is test request with file upload | Travel           | insurance                      |
      | test request | HR       | this is test request with file upload | Leave Management | LNSA                           |
      | test request | HR       | this is test request with file upload | Leave Management | Off                            |
      | test request | HR       | this is test request with file upload | Leave Management | Missed                         |
      | test request | Admin    | this is test request with file upload | Facility         | Courier                        |
      | test request | Admin    | this is test request with file upload | Facility         | Lunch                          |
      | test request | Admin    | this is test request with file upload | Facility         | Chair                          |
      | test request | Admin    | this is test request with file upload | Hardware         | Mobile                         |
      | test request | Admin    | this is test request with file upload | Registration     | Face                           |


  Scenario Outline: Check incident ticket cancellation functionality
    Given Create incident without file and subject "<subject>", dept "<dept>" and description "<desc>"
    When Submit and cancel the ticket with reason "cancel"
    Then Verify if ticket is cancelled
    Examples:
      | subject       | desc                                      | dept     |
      | test incident | this is test incident without file upload | Accounts |
      | test incident | this is test incident without file upload | IT       |
      | test incident | this is test incident without file upload | HR       |
      | test incident | this is test incident without file upload | Admin    |


  Scenario Outline: Check request ticket cancellation functionality
    Given Create request without file and with subject "<subject>", dept "<dept>", category "<catg>", sub-category "<sub>" and description "<desc>"
    When Submit and cancel the ticket with reason "cancel"
    Then Verify if ticket is cancelled
    Examples:
      | subject      | dept     | desc                                     | catg             | sub                            |
      | test request | IT       | this is test request without file upload | Software         | Software License               |
      | test request | IT       | this is test request without file upload | Software         | Unlicensed/OpenSource          |
      | test request | IT       | this is test request without file upload | Access Request   | Creation Of DL/o365 group      |
      | test request | IT       | this is test request without file upload | Access Request   | Modification of DL/o365 group  |
      | test request | IT       | this is test request without file upload | Access Request   | Modification of security group |
      | test request | IT       | this is test request without file upload | Access Request   | New Security group             |
      | test request | IT       | this is test request without file upload | Hardware         | Docking station                |
      | test request | IT       | this is test request without file upload | Hardware         | Keyboard                       |
      | test request | IT       | this is test request without file upload | Hardware         | Mouse                          |
      | test request | IT       | this is test request without file upload | Hardware         | RAM                            |
      | test request | IT       | this is test request without file upload | Hardware         | Laptop                         |
      | test request | IT       | this is test request without file upload | Hardware         | Other                          |
      | test request | Accounts | this is test request without file upload | Travel           | cards                          |
      | test request | Accounts | this is test request without file upload | Travel           | insurance                      |
      | test request | Accounts | this is test request without file upload | Travel           | insurance                      |
      | test request | HR       | this is test request without file upload | Leave Management | LNSA                           |
      | test request | HR       | this is test request without file upload | Leave Management | Off                            |
      | test request | HR       | this is test request with file upload    | Leave Management | Missed                         |
      | test request | Admin    | this is test request with file upload    | Facility         | Courier                        |
      | test request | Admin    | this is test request with file upload    | Facility         | Lunch                          |
      | test request | Admin    | this is test request with file upload    | Facility         | Chair                          |
      | test request | Admin    | this is test request with file upload    | Hardware         | Mobile                         |
      | test request | Admin    | this is test request with file upload    | Registration     | Face                           |


  Scenario: Check action button for ticket
    Given Create incident without file and subject "test subject", dept "HR" and description "desc"
    Then Verify if action button is active for the ticket

  Scenario: Check file upload with valid extensions and size limit for incident
    Given Create a ticket for incident with subject "valid file upload", desc "desc test" and dept "HR"
    And Upload file with valid extensions and size
    Then Verify if "valid" file can be uploaded

  Scenario: Check file upload with invalid extensions and size limit for incident
    Given Create a ticket for incident with subject "invalid file upload", desc "desc test" and dept "HR"
    And Upload file with invalid extensions and size
    Then Verify if "invalid" file can be uploaded

  Scenario: Check file upload with valid extensions and size limit for request
    Given Create a ticket for request with subject "valid file upload", desc "desc test", category "Leave Management", sub-category "LNSA" and dept "HR"
    And Upload file with valid extensions and size
    Then Verify if "valid" file can be uploaded

  Scenario: Check file upload with invalid extensions and size limit for request
    Given Create a ticket for request with subject "invalid file upload", desc "desc test", category "Leave Management", sub-category "LNSA" and dept "HR"
    And Upload file with invalid extensions and size
    Then Verify if "invalid" file can be uploaded

  Scenario: Check for field validation in create ticket form
    Given Open create ticket form
    Then Verify subject character limit and required field values

  Scenario: Check comments and files can be added for incident ticket details
    Given Create a ticket for incident with subject "incident test", desc "desc test" and dept "Accounts"
    When Submit and verify the incident details having file "unattached", subject "incident test", dept "Accounts" and description "desc test"
    Then Verify if comment "no file" can be added for the ticket
    Then Validate comment with file uploads on ticket page

  Scenario: Check comments and files can be added for request ticket details
    Given Create request without file and with subject "request test", dept "HR", category "Leave Management", sub-category "LNSA" and description "desc test"
    When Submit and verify the request details having file "unattached", subject "request test", dept "HR", description "desc test", category "Leave Management" and sub-category "LNSA"
    Then Verify if comment "no file" can be added for the ticket
    Then Validate comment with file uploads on ticket page
