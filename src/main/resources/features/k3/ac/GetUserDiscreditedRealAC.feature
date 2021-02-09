@ac
@ack3
Feature: GetUserDiscredited k3

  @k3
  Scenario: GetUserDiscredited: true
    Then Send GetUserDiscredited Request
      | env | id       | domain |
      | k3  | 20012216 | master |
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  @k3
  Scenario: GetUserDiscredited: false
    Then Send GetUserDiscredited Request
      | env | id       | domain |
      | k3  | 20012215 | master |
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"

  @k3
  Scenario: GetUserDiscredited Guest: Учетная запись не найдена
    Then Send GetUserDiscredited Request
      | env | id        | domain |
      | k3  | 123123176 | master |
    And Status code response is: "404"

  @k3
  Scenario: GetUserDiscredited Guest: false
    Then Send GetUserDiscredited Request
      | env | id       | domain |
      | k3  | 20063177 | guest  |
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"

  @k3
  Scenario: GetUserDiscredited Guest:
    Then Send GetUserDiscredited Request
      | env | id       | domain |
      | k3  | 20063176 | guest  |
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  @k3
  Scenario: GetUserDiscredited Guest: Учетная запись не найдена
    Then Send GetUserDiscredited Request
      | env | id        | domain |
      | k3  | 123123176 | guest  |
    And Status code response is: "404"