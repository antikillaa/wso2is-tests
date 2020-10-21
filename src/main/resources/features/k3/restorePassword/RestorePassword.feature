Feature: Restore Password

  @k3
  Scenario: otp alias: Success
    Then Send otpRestorePasswordRequest: cred: "18001042", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "18001042"
    And Response Body contains "mobile" equals "+7 495 ***-11-01"
    And Response Body contains key: "transactionId"

  @k3
  Scenario: otp: not valid (user not found)
    Then Send otpRestorePasswordRequest: cred: "13123123213123213", type: "login"
    And Status code response is: "401"
    And Response Body contains "type" equals "authentication_failed"

  @k3
  Scenario: pwd: Success
    Then Send otpRestorePasswordRequest: cred: "18001042", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "18001042"
    And Response Body contains "mobile" equals "+7 495 ***-11-01"
    And Response Body contains key: "transactionId"
    Then Send pwdRestorePasswordRequest
    And Status code response is: "200"

  @k3
  Scenario: pwd: Resend otp
    Then Send otpRestorePasswordRequest: cred: "18001042", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "18001042"
    And Response Body contains "mobile" equals "+7 495 ***-11-01"
    And Response Body contains key: "transactionId"
    Then Send pwdRestorePasswordRequest
    And Status code response is: "200"


