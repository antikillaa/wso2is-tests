Feature: GetUserDiscredited Real AC

  Scenario: GetUserDiscredited: true
    Then Send GetUserDiscredited Request id: "20012216"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  Scenario: GetUserDiscredited: false
    Then Send GetUserDiscredited Request id: "20012215"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"
