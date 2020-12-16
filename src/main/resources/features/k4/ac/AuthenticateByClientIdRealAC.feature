Feature: AuthenticateByClientId k4

  @k4
  Scenario: AuthenticateByClientId: Success
    Then "k4" Send authenticateByClientId Request id: "18005101"
    And Status code response is: "200"
    And Response Body contains "id" equals "18005101"
    And Response Body contains "domain" equals "master"

  @k4
  Scenario: AuthenticateByClientId: Учетная запись не найдена
    Then "k4" Send authenticateByClientId Request id: "login7771123123123123123"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"
