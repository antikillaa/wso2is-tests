Feature: SmsOtp AC

  @k3
  Scenario: SmsOtp AC
    Then Send Get Sms Otp Request
      | env | domain | ucn      |
      | k3  | master | 20002571 |
    And Status code response is: "200"
    And Response Body contains key: "transactionId"

    Then Send SmsOtp Request
      | env |
      | k3  |
    And Status code response is: "204"