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
  I want to remove a local on the rota Diaria
  So that I can see the info of my rota Diaria

 Background: 
    Given I have the browser open
    And I navigate to the "login" web page
    And I fill the "Email" input field with "example@gmail.com"
    And I fill the "Password" input field with "123123"
    And I click the login button
    And I click the RotaDiaria button

  Scenario: Remove to Rota Diaria
    When I select option with value "2267095" on the RotaDiaria
    And I click on button Remover on the RotaDiaria    
    And I select option with value "3372783" on the RotaDiaria
    And I click on button Remover on the RotaDiaria
    And I verify the ausence of "AS15" on the list of RotaDiaria
    And I verify the ausence of "AS01" on the list of RotaDiaria
    Then I verify the presence of "No tem aulas para a Rota" on the rota Diaria
    And I verify the text "0%" of "AvgQualidadeDoAr_0" on the rota Diaria