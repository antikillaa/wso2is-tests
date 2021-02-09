@iac
@iack3
Feature: Get User Integration-auth

  @k3
  Scenario: Sms-otp Integration-auth
    Then Send Get Otp Integration-auth Request
      | env | id       | url     |
      | k3  | 18001042 | sms-otp |
    And Status code response is: "200"
    And Response Body contains key: "transactionId"

  @k3
  Scenario: Sms-otp Integration-auth: User Not Found
    Then Send Get Otp Integration-auth Request
      | env | id          | url     |
      | k3  | 18001042123 | sms-otp |
    And Status code response is: "401"