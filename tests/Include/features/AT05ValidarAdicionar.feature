Feature: Validade add to "Locais Favoritos"
  I want to add a place on "Locais Favoritos" when I click on button adicionar

  Scenario: Add to Locais Favoritos
    Given I have the browser open
    When I navigate to the "login" web page
    And I fill the "Email" input field with "example@gmail.com"
    And I fill the "Password" input field with "123123"
    And I click the login button
    And I click the Locais button
    And I select option with value "2267095"
    Then I click on button Adicionar
    And I verify the presence of "AS15" on the list of locals
