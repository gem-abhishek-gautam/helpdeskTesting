Feature: Login to helpdesk

  Scenario Outline: Check login functionality
    Given navigate to helpdesk and login
    Then verify if login is successful
    And check if the logged in user name is "<name>"
    Examples:
      | name        |
      |Abhishek Gautam|
