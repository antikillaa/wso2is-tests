Feature: GetUserDiscredited k5

  @k5
  Scenario: GetUserDiscredited: true
    Then "k5" Send GetUserDiscredited Request id: "30000377"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  @k5
  Scenario: GetUserDiscredited: false
    Then "k5" Send GetUserDiscredited Request id: "20000509"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"
