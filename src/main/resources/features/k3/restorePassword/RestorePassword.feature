@restorePassword
@restorePasswordk3
Feature: Restore Password K3

  @k3
  Scenario: otp alias: Success
    Then "k3" Send otpRestorePasswordRequest: cred: "20002873", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "20002873"
    And Response Body contains key: "transactionId"

  @k3
  Scenario: otp: not valid (user not found)
    Then "k3" Send otpRestorePasswordRequest: cred: "13123123213123213", type: "login"
    And Status code response is: "401"
    And Response Body contains "type" equals "authentication_failed"

  @k3
  Scenario: otp access deny
    Then "k3" Send otpRestorePasswordRequest: cred: "18001042", type: "login"
    And Status code response is: "403"
    And Response Body contains "type" equals "spm_access_deny"


  @k3
  Scenario: pwd: Success
    Then "k3" Send otpRestorePasswordRequest: cred: "20002873", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "20002873"
    And Response Body contains key: "transactionId"
    Then "k3" Send pwdRestorePasswordRequest
    And Status code response is: "200"

  @k3
  Scenario: pwd: Resend otp
    Then "k3" Send otpRestorePasswordRequest: cred: "20002873", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "20002873"
    And Response Body contains key: "transactionId"
    Then "k3" Send pwdRestorePasswordRequest
    And Status code response is: "200"
