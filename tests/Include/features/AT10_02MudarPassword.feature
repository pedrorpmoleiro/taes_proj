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
Feature: Check the new password 
  As a user
  I want to update my password
  So that I can do the login with my new password

 Background: 
    Given I have the browser open
    And I navigate to the "login" web page
    And I fill the "Email" input field with "example@gmail.com"
    And I fill the "Password" input field with "123123"
    And I click the login button
    And I click the Perfil button

  Scenario: Errors+happy Path
    Given I click on button Alterar Password on the Perfil page
    When I set the password text to "" 
    When I set the password confirmation text to "" 
    And I click on button Alterar_Pass on the Perfil page
    Then I receive error message "Required" in the password field on the Perfil page
    When I set the password text to "456" 
    When I set the password confirmation text to "456" 
    And I click on button Alterar_Pass on the Perfil page
    Then I receive error message "Too short" in the password field on the Perfil page
    When I set the password text to "456456" 
    When I set the password confirmation text to "123123" 
    And I click on button Alterar_Pass on the Perfil page
    Then I receive error message "Passwords dont match" in the password field on the Perfil page
   When I set the password text to "456456" 
    When I set the password confirmation text to "456456" 
    And I click on button Alterar_Pass on the Perfil page and acceptAlert
    Then I receive success message "Password Update successfully" on the Perfil page
    When I click on voltar button
    And I click on logout button
    And I navigate to the "login" web page
    And I fill the "Email" input field with "example@gmail.com"
    And I fill the "Password" input field with "123123"
    And I click the login button
    Then I verify the error message "The password is invalid" on the login page
    When I fill the "Email" input field with "example@gmail.com"
    And I fill the "Password" input field with "456456"
    And I click the login button
    Then I verify the presence of button "Log Out"
    