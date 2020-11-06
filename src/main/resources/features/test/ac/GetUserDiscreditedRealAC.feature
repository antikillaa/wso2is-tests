Feature: GetUserDiscredited Real AC

  @test
  Scenario: GetUserDiscredited: true
    Then "test" Send GetUserDiscredited Request id: "123456"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  @test
  Scenario: GetUserDiscredited: false
    Then "test" Send GetUserDiscredited Request id: "11000001"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"

  @test
  Scenario: GetUserDiscredited: Get by Alias - true
    Then "test" Send GetUserDiscredited Request id: "321"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "true"

  @test
  Scenario: GetUserDiscredited: Get by Alias - false
    Then "test" Send GetUserDiscredited Request id: "3456"
    And Status code response is: "200"
    And Response Body contains "discredited" equals "false"

  @test
  Scenario: GetUserDiscredited: Учетная запись не найдена
    Then "test" Send GetUserDiscredited Request id: "1231232132131231231"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"
