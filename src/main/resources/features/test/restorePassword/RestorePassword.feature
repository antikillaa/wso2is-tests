Feature: Restore Password

  Scenario: otp alias: Success
    Then Send otpRestorePasswordRequest: cred: "1234", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "11000035"
    And Response Body contains "mobile" equals "925***8595"
    And Response Body contains key: "transactionId"

  Scenario: otp id: Success
    Then Send otpRestorePasswordRequest: cred: "11000035", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "11000035"
    And Response Body contains "mobile" equals "925***8595"
    And Response Body contains key: "transactionId"

  Scenario: otp: not valid (user not found)
    Then Send otpRestorePasswordRequest: cred: "13123123213123213", type: "login"
    And Status code response is: "404"
    And Response Body contains "type" equals "user_not_found"
    And Response Body contains "message" equals "Обратитесь в техподдержку Банка."
    And Response Body contains "message_title" equals "Возникла непредвиденная ошибка."

