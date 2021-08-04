Feature: Assigning a limit to a credit card
  Scenario: Can assign a limit
    Given a new credit card with the id "1234-2258-9873-8991-1111"
    When assigning a limit of 2000 to it
    Then The credit card has a limit of 2000
