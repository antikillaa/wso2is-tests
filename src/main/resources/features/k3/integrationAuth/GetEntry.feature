Feature: Get Entry Integration-auth

  @k3
  Scenario: Get Entry Integration-auth
    Then Send Get Entry Integration-auth Request
      | env | domain | ucn      | system |
      | k3  | master | 18001042 | 91000  |
    And Status code response is: "200"
    And Response Body contains key: "transactionId"

  @k3
  Scenario: Get Entry Integration-auth: User Not Found
    Then Send Get Entry Integration-auth Request
      | env | id          | url    |
      | k3  | 18001042123 | getOtp |
    And Status code response is: "404"