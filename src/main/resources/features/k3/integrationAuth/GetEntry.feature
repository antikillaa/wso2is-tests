@iac
@iack3
Feature: Get Entry Integration-auth

  @k3
  Scenario: Get Entry Integration-auth
    Then Send Get Entry Integration-auth Request
      | env | domain | ucn      | system |
      | k3  | master | 18001042 | 91000  |
    And Status code response is: "200"
    And Response Body contains "domain" equals "master"
    And Response Body contains "ucn" equals "18001042"

  @k3
  Scenario: Get Entry Integration-auth: User Not Found
    Then Send Get Entry Integration-auth Request
      | env | domain | ucn            | system |
      | k3  | master | 18001041231232 | 91000  |
    And Status code response is: "404"