Feature: Restore Password

  @k4
  Scenario: otp alias: Success
    Then "k4" Send otpRestorePasswordRequest: cred: "11977566", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "11977566"
    And Response Body contains key: "transactionId"

  @k4
  Scenario: otp: not valid (user not found)
    Then "k4" Send otpRestorePasswordRequest: cred: "13123123213123213", type: "login"
    And Status code response is: "401"
    And Response Body contains "type" equals "authentication_failed"

  @k4
  Scenario: pwd: Success
    Then "k4" Send otpRestorePasswordRequest: cred: "11977566", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "11977566"
    And Response Body contains key: "transactionId"
    Then "k4" Send pwdRestorePasswordRequest
    And Status code response is: "200"

  @k4
  Scenario: pwd: Resend otp
    Then "k4" Send otpRestorePasswordRequest: cred: "11977566", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "11977566"
    And Response Body contains key: "transactionId"
    Then "k4" Send pwdRestorePasswordRequest
    And Status code response is: "200"


