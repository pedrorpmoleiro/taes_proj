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
Feature: Check the new name 
  As a user
  I want to update my name
  So that I can see my new name

 Background: 
    Given I have the browser open
    And I navigate to the "login" web page
    And I fill the "Email" input field with "example@gmail.com"
    And I fill the "Password" input field with "123123"
    And I click the login button
    And I click the Perfil button


  Scenario: Errors+happy Path
    Given I click on button Alterar Nome on the Perfil page
    When I set the name text to "" 
    And I click on button Alterar on the Perfil page
    Then I receive error message "Required" in the name field on the Perfil page
    When I set the name text to "lo" 
    And I click on button Alterar on the Perfil page
    Then I receive error message "Too short" in the name field on the Perfil page
    When I set the name text to "exemplo Ex" 
    And I click on button Alterar on the Perfil page
    Then I receive success message "Name Update successfully" on the Perfil page
    And I click on voltar button
    And I verify the text "Bem-vindo exemplo Ex" on the DashBoard
    
    