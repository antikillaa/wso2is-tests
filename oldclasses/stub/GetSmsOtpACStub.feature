Feature: GetSmsOtp Stub

  Scenario: GetSmsOtp: Success 1
    Then Send GetSmsOtp Request id: "111222"
    And Status code response is: "200"
    And Response Body contains "transactionId" equals "1111111111111"

  Scenario: GetSmsOtp: Success 2
    Then Send GetSmsOtp Request id: "333444"
    And Status code response is: "200"
    And Response Body contains "transactionId" equals "2222222222222"

  Scenario: GetSmsOtp: Учетная запись не найдена
    Then Send GetSmsOtp Request id: "444555"
    And Status code response is: "404"
    And Response Body contains "message" equals "Учетная запись не найдена"
    And Response Body contains "exception" equals "UserNotFoundException"

  Scenario: GetSmsOtp: USER_LOCKED
    Then Send GetSmsOtp Request id: "555666"
    And Status code response is: "403"
    And Response Body contains "message" equals "USER_LOCKED"
    And Response Body contains "exception" equals "UserLockedException"

  Scenario: GetSmsOtp: Ошибка проверки ОТП
    Then Send GetSmsOtp Request id: "666777"
    And Status code response is: "401"
    And Response Body contains "message" equals "Ошибка проверки ОТП"
    And Response Body contains "exception" equals "AuthenticationException"

  Scenario: GetSmsOtp: Ошибка проверки IMSI
    Then Send GetSmsOtp Request id: "777888"
    And Status code response is: "403"
    And Response Body contains "message" equals "Ошибка проверки IMSI"
    And Response Body contains "exception" equals "ImsiVerificationException"

  Scenario: GetSmsOtp: Ошибка
    Then Send GetSmsOtp Request id: "888999"
    And Status code response is: "500"
    And Response Body contains "message" equals "Ошибка"
    And Response Body contains "exception" equals "Exception"
