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
    And Response Body contains "message" equals "Возможно, вы неправильно ввели логин или номер карты. Проверьте данные и попробуйте снова."
    And Response Body contains "message_title" equals "Восстановление пароля недоступно"

  Scenario: pwd: Success
    Then Send otpRestorePasswordRequest: cred: "11000035", type: "login"
    And Status code response is: "200"
    And Response Body contains "ucn" equals "11000035"
    And Response Body contains "mobile" equals "925***8595"
    And Response Body contains key: "transactionId"
    Then Send pwdRestorePasswordRequest
    And Status code response is: "200"


