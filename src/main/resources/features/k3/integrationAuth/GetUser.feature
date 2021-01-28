Feature: Get User Integration-auth

  @k3
  Scenario: Get User Integration-auth: Master
    Then Send Get user Integration-auth Request
      | env | domain | ucn      | system |
      | k3  | master | 18001042 | 91000  |
    And Status code response is: "200"
    And Response Body contains "user.domain" equals "master"
    And Response Body contains "user.ucn" equals "18001042"

  @k3
  Scenario: Get User Integration-auth: Master
    Then Send Get user Integration-auth Request
      | env | domain | ucn         | system |
      | k3  | master | 18001042123 | 91000  |
    And Status code response is: "404"

  @k3
  Scenario: Get User Integration-auth: Guest
    Then Send Get user Integration-auth Request
      | env | domain | ucn      | system |
      | k3  | guest  | 20063177 | 91003  |
    And Status code response is: "200"
    And Response Body contains "user.domain" equals "guest"
    And Response Body contains "user.ucn" equals "20063177"

  @k3
  Scenario: Get User Integration-auth: Guest
    Then Send Get user Integration-auth Request
      | env | domain | ucn         | system |
      | k3  | guest  | 20063177777 | 91003  |
    And Status code response is: "404"

