Feature: GetUserDiscredited k5

  @k5
  Scenario: GetUserDiscredited: true
    Then Send GetUserDiscredited Request
      | env | id       | domain |
      | k5  | 30005986 | master |
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  @k5
  Scenario: GetUserDiscredited: false
    Then Send GetUserDiscredited Request
      | env | id       | domain |
      | k5  | 20000509 | master |
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"
