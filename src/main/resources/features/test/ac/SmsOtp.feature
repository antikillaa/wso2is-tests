Feature: SmsOtp AC

  @skip
  Scenario: SmsOtp AC
    Then Send Get Sms Otp Request
      | env  | domain | ucn      |
      | test | master | 11000035 |
    And Status code response is: "200"
    And Response Body contains key: "transactionId"

    Then Send SmsOtp Request
      | env  |
      | test |
    And Status code response is: "204"