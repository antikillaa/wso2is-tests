Feature: GetUserDiscredited k4

  @k4
  Scenario: GetUserDiscredited: true
    Then "k4" Send GetUserDiscredited Request id: "18020416"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  @k4
  Scenario: GetUserDiscredited: false
    Then "k4" Send GetUserDiscredited Request id: "18004626"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"
