#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Check empty fields in the register
  As a user
  I want to recieve an error message 
  So that I can see fields weren't filled

  Scenario: four empty fields
    Given I have the browser open
    When I navigate to the "register" web page
    And I dont fill the "Name" input field in Register
    And I dont fill the "Email" input field in Register
    And I dont fill the "Password" input field in Register
    And I dont fill the "PasswordConfirmation" input field in Register
    And I click the register button
    Then I receive error message "Required" in the input field

  Scenario: name empty field
    When I navigate to the "register" web page
    And I dont fill the "Name" input field in Register
    And I fill the "Email" input field with "example@gmail.com" in Register
    And I fill the "Password" input field with "123123" in Register
    And I fill the "PasswordConfirmation" input field with "123123" in Register
    And I click the register button
    Then I receive error message "Required" in the input field
    
   Scenario: email empty field
    When I navigate to the "register" web page
    And I fill the "Name" input field with "Example example" in Register
    And I dont fill the "Email" input field in Register
    And I fill the "Password" input field with "123123" in Register
    And I fill the "PasswordConfirmation" input field with "123123" in Register
    And I click the register button
    Then I receive error message "Required" in the input field
    
   Scenario: password empty field
    When I navigate to the "register" web page
    And I fill the "Name" input field with "Example example" in Register
    And I fill the "Email" input field with "example@gmail.com" in Register
    And I dont fill the "Password" input field in Register
    And I fill the "PasswordConfirmation" input field with "123123" in Register
    And I click the register button
    Then I receive error message "Required" in the input field
    
   Scenario: email empty field
    When I navigate to the "register" web page
    And I fill the "Name" input field with "Example example" in Register
    And I fill the "Email" input field with "example@gmail.com" in Register
    And I fill the "Password" input field with "123123" in Register
    And I dont fill the "PasswordConfirmation" input field in Register
    And I click the register button
    Then I receive error message "Required" in the input field
