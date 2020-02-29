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
Feature: Verify Temp and Humidity
  I want verify elemts for Temp and humidity when I change places

  Background: 
    Given I have the browser open
    And I navigate to the "login" web page
    And I fill the "Email" input field with "example@gmail.com"
    And I fill the "Password" input field with "123123"
    And I click the login button
    
      Scenario: Change a place AS05
    When I select option with value "2267095"
      Then I verify the presence of "Temperatura"
    And I verify the presence of "Humidade"

  Scenario: Change a place AS01
    When I select option with value "3372783"
      Then I verify the presence of "Temperatura"
    And I verify the presence of "Humidade"

  Scenario: Change a place AS04
    When I select option with value "2267057"
      Then I verify the presence of "Temperatura"
    And I verify the presence of "Humidade"
