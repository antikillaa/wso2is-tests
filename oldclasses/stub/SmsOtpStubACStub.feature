Feature: SmsOtp Stub

  Scenario: SmsOtp: Success 1
    Then Send SmsOtp Request id: "111222"
    And Status code response is: "200"
    And Response Body contains "transactionId" equals "1111111111111"

  Scenario: SmsOtp: Success 2
    Then Send SmsOtp Request id: "333444"
    And Status code response is: "200"
    And Response Body contains "transactionId" equals "2222222222222"

  Scenario: SmsOtp: Учетная запись не найдена
    Then Send SmsOtp Request id: "444555"
    And Status code response is: "404"
    And Response Body contains "message" equals "Учетная запись не найдена"
    And Response Body contains "exception" equals "UserNotFoundException"

  Scenario: SmsOtp: USER_LOCKED
    Then Send SmsOtp Request id: "555666"
    And Status code response is: "403"
    And Response Body contains "message" equals "USER_LOCKED"
    And Response Body contains "exception" equals "UserLockedException"

  Scenario: SmsOtp: Ошибка проверки ОТП
    Then Send SmsOtp Request id: "666777"
    And Status code response is: "401"
    And Response Body contains "message" equals "Ошибка проверки ОТП"
    And Response Body contains "exception" equals "AuthenticationException"

  Scenario: SmsOtp: Ошибка проверки IMSI
    Then Send SmsOtp Request id: "777888"
    And Status code response is: "403"
    And Response Body contains "message" equals "Ошибка проверки IMSI"
    And Response Body contains "exception" equals "ImsiVerificationException"

  Scenario: SmsOtp: Ошибка
    Then Send SmsOtp Request id: "888999"
    And Status code response is: "500"
    And Response Body contains "message" equals "Ошибка"
    And Response Body contains "exception" equals "Exception"
