Feature: Static Password Stub

  Scenario: Static Password: Success 1
    Then Send Static password request with id: "11121"
    And Status code response is: "200"
    And Response Body contains "id" equals "11122"
    And Response Body contains "domain" equals "master"
    And Response Body contains "username" equals "onetwothree"
    And Response Body contains "homePhone" equals "5551234567"
    And Response Body contains "mobile" equals "9163742359"

  Scenario: Static Password: Success 2
    Then Send Static password request with id: "11122"
    And Status code response is: "200"
    And Response Body contains "id" equals "11122"
    And Response Body contains "domain" equals "master"
    And Response Body contains "username" equals "onetwothree"
    And Response Body contains "homePhone" equals "5551234567"
    And Response Body contains "mobile" equals "9163742359"

  Scenario: Static Password: Учетная запись не найдена
    Then Send Static password request with id: "11123"
    And Status code response is: "404"
    And Response Body contains "message" equals "Учетная запись не найдена"
    And Response Body contains "exception" equals "UserNotFoundException"

  Scenario: Static Password: Неверный код подтверждения
    Then Send Static password request with id: "11124"
    And Status code response is: "401"
    And Response Body contains "exception" equals "AuthenticationException"
    And Response Body contains "message" equals "Неверный код подтверждения"

  Scenario: Static Password: Ошибка
    Then Send Static password request with id: "11125"
    And Status code response is: "500"
    And Response Body contains "exception" equals "Exception"
    And Response Body contains "message" equals "Ошибка"

  Scenario: Static Password: USER_LOCKED
    Then Send Static password request with id: "11126"
    And Status code response is: "403"
    And Response Body contains "exception" equals "UserLockedException"
    And Response Body contains "message" equals "USER_LOCKED"
