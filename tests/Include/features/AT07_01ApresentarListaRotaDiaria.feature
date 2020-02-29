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
Feature: Check the list of rota diaria
  As a user
  I want to click on the rota Diaria button
  So that I can see the info of my rota Diaria

  Background: 
    Given I have the browser open
    And I navigate to the "login" web page
    And I fill the "Email" input field with "example@gmail.com"
    And I fill the "Password" input field with "123123"
    And I click the login button

  Scenario: Rota Diaria Page
    When I click the RotaDiaria button
    Then I verify the presence of the RotaDiaria page
