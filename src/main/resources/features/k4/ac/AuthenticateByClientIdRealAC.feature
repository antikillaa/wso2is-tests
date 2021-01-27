Feature: AuthenticateByClientId k4

  @k4
  Scenario: AuthenticateByClientId: Success
    Then Send authenticateByClientId Request
      | env | id       | domain |
      | k4  | 18005101 | master |
    And Status code response is: "200"
    And Response Body contains "id" equals "18005101"
    And Response Body contains "domain" equals "master"

  @k4
  Scenario: AuthenticateByClientId: Учетная запись не найдена
    Then Send authenticateByClientId Request
      | env | id                       | domain |
      | k4  | login7771123123123123123 | master |
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"
