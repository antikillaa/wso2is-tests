Feature: AuthenticateByClientId RealAC

  @k3
  Scenario: AuthenticateByClientId: Success
    Then Send authenticateByClientId Request id: "login7771"
    And Status code response is: "200"
    And Response Body contains "id" equals "20002571"
    And Response Body contains "domain" equals "master"
    And Response Body contains "mobile" equals "9802435698"

  @k3
  Scenario: AuthenticateByClientId: Учетная запись не найдена
    Then Send authenticateByClientId Request id: "login7771123123123123123"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"
