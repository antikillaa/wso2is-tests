Feature: AuthenticateByClientId k5

  @k5
  Scenario: AuthenticateByClientId: Success
    Then Send authenticateByClientId Request
      | env | id       | domain |
      | k5  | 30000377 | master |
    And Status code response is: "200"
    And Response Body contains "id" equals "30000377"
    And Response Body contains "domain" equals "master"
    And Response Body contains key: "mobile"

  @k5
  Scenario: AuthenticateByClientId: Учетная запись не найдена
    Then Send authenticateByClientId Request
      | env | id                       | domain |
      | k5  | login7771123123123123123 | master |
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"
