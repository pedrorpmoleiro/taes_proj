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
Feature: Validate the voltar button
  As a user
  I want to go back when I click voltar button
  So that I can see the previous page

  Scenario: Validate the voltar button in the login 
    Given I have the browser open
    When I navigate to the "login" web page
    And I click the voltar button on the "login"
    Then I see the "Dashboard Principal" page
    
   Scenario: Validate the voltar button in the register 
    When I navigate to the "register" web page
    And I click the voltar button on the "register"
    Then I see the "Dashboard Principal" page