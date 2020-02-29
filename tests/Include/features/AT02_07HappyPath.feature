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

Feature: Happy path of the app
  As a Normal user
  I want to open the web application,
  I want to see the DashBoard,
  I want to register in the app,
  I want to login 
  So that I can see the DashBoard like a logged in user
  
Scenario: Register - Login 
 		Given I have the browser open
    When I navigate to the "DashBoard" web page
    And I click the "Register" button on the Dashboard
    And I fill the "Name" input field with "Example example" in Register
    And I fill the "Email" input field with "example@gmail.com" in Register
    And I fill the "Password" input field with "123123" in Register
    And I fill the "PasswordConfirmation" input field with "123123" in Register
    And I click the register button
    And I fill the "Email" input field with "example@gmail.com"
    And I fill the "Password" input field with "123123"
    And I click the login button
    Then I verify the presence of "Qualidade do Ar"
    And I verify the presence of "Temperatura"
    And I verify the presence of "Humidade"
    And I verify the presence of button "Log Out"
    And I verify the ausence of button "Login"
    And I verify the ausence of button "Register"
    #acrescentar codigo de validar locais , logout no fim 

