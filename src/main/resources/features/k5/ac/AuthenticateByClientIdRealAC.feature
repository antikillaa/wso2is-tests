Feature: AuthenticateByClientId k5

  @k5
  Scenario: AuthenticateByClientId: Success
    Then "k5" Send authenticateByClientId Request id: "30000377"
    And Status code response is: "200"
    And Response Body contains "id" equals "30000377"
    And Response Body contains "domain" equals "master"
    And Response Body contains key: "mobile"

  @k5
  Scenario: AuthenticateByClientId: Учетная запись не найдена
    Then "k5" Send authenticateByClientId Request id: "login7771123123123123123"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"
