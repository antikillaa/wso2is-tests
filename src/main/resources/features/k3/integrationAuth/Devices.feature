Feature: Get Devices Integration-auth

  @k3
  Scenario: Get Entry Integration-auth
    Then Send Get Devices Integration-auth Request
      | env | device           |
      | k3  | 8888000000093679 |
    And Status code response is: "200"
    And Response Body contains key: "ucn"
    And Response Body contains key: "domain"