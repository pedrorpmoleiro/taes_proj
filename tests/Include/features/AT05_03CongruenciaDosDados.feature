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
Feature: Check the image and the check box 
  As a user
  I want to select a local in the list of favorites locals
  So that I can see the info of that local

 Background: 
    Given I have the browser open
    And I navigate to the "login" web page
    And I fill the "Email" input field with "example@gmail.com"
    And I fill the "Password" input field with "123123"
    And I click the login button
    And I click the Locais button
    
  Scenario: select local
    Given I verify the presence of "AS01" on the list of locals
    When I click on the local "AS01" in the list of favorites
    Then I verify the select option with value "3372783"
    And Verify image of the "AS01"
