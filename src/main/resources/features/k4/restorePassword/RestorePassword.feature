Feature: Restore Password

  @skip
  Scenario: otp alias: Success
    Then "k4" Send otpRestorePasswordRequest: cred: "18001042", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "18001042"
    And Response Body contains key: "transactionId"

  @skip
  Scenario: otp: not valid (user not found)
    Then "k4" Send otpRestorePasswordRequest: cred: "13123123213123213", type: "login"
    And Status code response is: "401"
    And Response Body contains "type" equals "authentication_failed"

  @skip
  Scenario: pwd: Success
    Then "k4" Send otpRestorePasswordRequest: cred: "18001042", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "18001042"
    And Response Body contains key: "transactionId"
    Then "k4" Send pwdRestorePasswordRequest
    And Status code response is: "200"

  @skip
  Scenario: pwd: Resend otp
    Then "k4" Send otpRestorePasswordRequest: cred: "18001042", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "18001042"
    And Response Body contains key: "transactionId"
    Then "k4" Send pwdRestorePasswordRequest
    And Status code response is: "200"


