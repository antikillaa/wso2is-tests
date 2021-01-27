Feature: Get SmsOtp AC

  @k3
  Scenario: Get SmsOtp AC: Master domain
    Then Send Get Sms Otp Request
      | env | domain | ucn      |
      | k3  | master | 20002571 |
    And Status code response is: "200"
    And Response Body contains key: "transactionId"

  @k3
  Scenario: Get SmsOtp AC: Guest domain
    Then Send Get Sms Otp Request
      | env | domain | ucn      |
      | k3  | guest  | 20059856 |
    And Status code response is: "200"
    And Response Body contains key: "transactionId"