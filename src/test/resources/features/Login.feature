@employee @support
Feature: Login to helpdesk

  Scenario: Check login functionality
    Given Navigate to helpdesk and login
    Then Verify if login is successful
    And Verify if the logged in user name is "Abhishek Gautam"

  Scenario: Check login with invalid credentials
    Given Login with invalid domain having email "abhishek@test.com"
    Then Verify if login is unsuccessful
