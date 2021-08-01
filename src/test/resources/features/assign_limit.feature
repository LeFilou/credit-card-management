Feature: Assigning a limit to a credit card
  Scenario: Can assign a limit to a newly credit card
    Given a credit card of id "123456789" that just have been created
    When assigning a limit to it
    Then The credit card has a limit