Feature: AuthenticateByClientId RealAC

  Scenario: AuthenticateByClientId: Success
    Then Send authenticateByClientId Request id: "123456"
    And Status code response is: "200"
    And Response Body contains "id" equals "123456"
    And Response Body contains "domain" equals "master"
    And Response Body contains "mobile" equals "9876543219"

  Scenario: AuthenticateByClientId: Учетная запись не найдена
    Then Send authenticateByClientId Request id: "1111111111"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"
