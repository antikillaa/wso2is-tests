Feature: GetUserDiscredited k3

  @k3
  Scenario: GetUserDiscredited: true
    Then "k3" Send GetUserDiscredited Request id: "20012216"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  @k3
  Scenario: GetUserDiscredited: false
    Then "k3" Send GetUserDiscredited Request id: "20012215"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"
