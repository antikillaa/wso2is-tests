Feature: Get User Integration-auth

  @k3
  Scenario: Get User Integration-auth: Master
    Then Send Get user Integration-auth Request
      | env | domain | ucn      | system |
      | k3  | master | 18001042 | 91000  |
    And Status code response is: "200"
    And Response Body contains "user.domain" equals "master"
    And Response Body contains "user.ucn" equals "18001042"

