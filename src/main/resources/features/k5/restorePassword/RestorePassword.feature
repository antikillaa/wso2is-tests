Feature: Restore Password K5

  @k5
  Scenario: otp alias: Success
    Then "k5" Send otpRestorePasswordRequest: cred: "30006300", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "30006300"
    And Response Body contains key: "transactionId"

  @k5
  Scenario: otp: not valid (user not found)
    Then "k5" Send otpRestorePasswordRequest: cred: "13123123213123213", type: "login"
    And Status code response is: "401"
    And Response Body contains "type" equals "authentication_failed"

  @k5
  Scenario: pwd: Success
    Then "k5" Send otpRestorePasswordRequest: cred: "30006300", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "30006300"
    And Response Body contains key: "transactionId"
    Then "k5" Send pwdRestorePasswordRequest
    And Status code response is: "200"

  @k5
  Scenario: pwd: Resend otp
    Then "k5" Send otpRestorePasswordRequest: cred: "30006300", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "30006300"
    And Response Body contains key: "transactionId"
    Then "k5" Send pwdRestorePasswordRequest
    And Status code response is: "200"


