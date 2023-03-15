Feature: Testcases for Create Ticket

  Background: Check login functionality
    Given navigate to helpdesk and login
    Then verify if login is successful

  Scenario Outline: Create Incident ticket with file upload
    Given create incident with subject "<subject>", dept "<dept>" and description "<desc>"
    Then verify if ticket created successfully with subject "<subject>", dept "<dept>", description "<desc>" and attachment
    Examples:
      |subject      | desc                                 |dept|
      |test incident|this is test incident with file upload|Accounts|

  Scenario Outline: Create Incident ticket without file upload
    Given create incident without file and subject "<subject>", dept "<dept>" and description "<desc>"
    Then verify if ticket created successfully with subject "<subject>", dept "<dept>", description "<desc>" and without attachment
    Examples:
      |subject                  | desc                                    |dept|
      |test request without file|this is a test request without file upload|IT|

  Scenario Outline: Create Request ticket with file upload
    Given create request with subject "<subject>", dept "<dept>", category "<catg>", sub-category "<sub>" and description "<desc>"
    Then verify if ticket created successfully with subject "<subject>", dept "<dept>", category "<catg>", sub-category "<sub>", description "<desc>" and attachment
    Examples:
      |subject     |dept| desc                              | catg    |sub       |
      |test request|IT|this is test request with file upload|Software|OpenSource|

  Scenario Outline: Create Request ticket without file upload
    Given create request without file and with subject "<subject>", dept "<dept>", category "<catg>", sub-category "<sub>" and description "<desc>"
    Then verify if ticket created successfully with subject "<subject>", dept "<dept>", category "<catg>", sub-category "<sub>", description "<desc>" and without attachment
    Examples:
      |subject                  |dept| desc                                |catg          |sub |
      |test request without file|HR|this is test request without file upload|Leave Management|LNSA|

  Scenario Outline: Check ticket cancellation functionality
    Given create incident without file and subject "<subject>", dept "<dept>" and description "<desc>"
    Then submit and cancel the ticket with reason "<reason>"
    Examples:
      | subject     | dept | desc                           | reason |
      |test incident|IT|test incident ticket to be cancelled|cancel|
