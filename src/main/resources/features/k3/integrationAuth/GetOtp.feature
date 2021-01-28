Feature: Get Otp Integration-auth

  @k3
  Scenario: Get Otp Integration-auth
    Then Send Get Otp Integration-auth Request
      | env | id       | url    |
      | k3  | 18001042 | getOtp |
    And Status code response is: "200"
    And Response Body contains key: "transactionId"

  @k3
  Scenario: Get Otp Integration-auth: User Not Found
    Then Send Get Otp Integration-auth Request
      | env | id          | url    |
      | k3  | 18001042123 | getOtp |
    And Status code response is: "401"