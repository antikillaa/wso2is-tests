Feature: GetUserDiscredited k4

  @k4
  Scenario: GetUserDiscredited: true
    Then Send GetUserDiscredited Request
      | env | id       | domain |
      | k4  | 18020416 | master |
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  @k4
  Scenario: GetUserDiscredited: false
    Then Send GetUserDiscredited Request
      | env | id       | domain |
      | k4  | 18004626 | master |
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"
