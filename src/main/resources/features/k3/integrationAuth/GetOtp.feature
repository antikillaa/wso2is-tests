Feature: Get User Integration-auth

  @k3
  Scenario: Get Otp Integration-auth
    Then Send Get Otp Integration-auth Request
      | env | id       |
      | k3  | 18001042 |
    And Status code response is: "200"
    And Response Body contains key: "transactionId"

  @k3
  Scenario: Get User Integration-auth: Master
    Then Send Get Otp Integration-auth Request
      | env | id          |
      | k3  | 18001042123 |
    And Status code response is: "401"