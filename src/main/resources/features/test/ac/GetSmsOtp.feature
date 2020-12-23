Feature: Get SmsOtp AC

  @test
  Scenario: Get SmsOtp AC
    Then Send Get Sms Otp Request
      | env  | domain | ucn      |
      | test | master | 11000035 |
    And Status code response is: "200"
    And Response Body contains key: "transactionId"

