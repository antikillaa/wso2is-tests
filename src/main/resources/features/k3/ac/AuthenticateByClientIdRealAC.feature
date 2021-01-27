Feature: AuthenticateByClientId k3

  @k3
  Scenario: AuthenticateByClientId Master: Success
    Then Send authenticateByClientId Request
      | env | id        | domain |
      | k3  | login7771 | master |
    And Status code response is: "200"
    And Response Body contains "id" equals "20002571"
    And Response Body contains "domain" equals "master"
    And Response Body contains "mobile" equals "9802435698"

  @k3
  Scenario: AuthenticateByClientId Master: Учетная запись не найдена
    Then Send authenticateByClientId Request
      | env | id                       | domain |
      | k3  | login7771123123123123123 | master |
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"

  @k3
  Scenario: AuthenticateByClientId Guest: Success
    Then Send authenticateByClientId Request
      | env | id       | domain |
      | k3  | 20059856 | guest  |
    And Status code response is: "200"
    And Response Body contains "id" equals "20059856"
    And Response Body contains "domain" equals "guest"
    And Response Body contains "mobile" equals "9175223999"

  @k3
  Scenario: AuthenticateByClientId Guest: Учетная запись не найдена
    Then Send authenticateByClientId Request
      | env | id                       | domain |
      | k3  | login7771123123123123123 | guest  |
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"
