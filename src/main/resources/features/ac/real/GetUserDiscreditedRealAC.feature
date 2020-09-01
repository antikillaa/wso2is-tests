Feature: GetUserDiscredited Real AC

  Scenario: GetUserDiscredited: true
    Then Send GetUserDiscredited Request id: "123456"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  Scenario: GetUserDiscredited: false
    Then Send GetUserDiscredited Request id: "11000001"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"

  Scenario: GetUserDiscredited: Get by Alias - true
    Then Send GetUserDiscredited Request id: "321"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  Scenario: GetUserDiscredited: Get by Alias - false
    Then Send GetUserDiscredited Request id: "3456"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"

  Scenario: GetUserDiscredited: Учетная запись не найдена
    Then Send GetUserDiscredited Request id: "1231232132131231231"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"
