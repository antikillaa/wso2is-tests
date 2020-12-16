Feature: Grant type Login Stub

  @test
  Scenario: Grant type Login Success
    Then Send login by Grant type Request
      | grandType | id_type | id    | finger_print | scope | env  |
      | login     | login   | 11122 | true         | true  | test |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @test
  Scenario: Grant type Login: Success no fingerprint
    Then Send login by Grant type Request
      | grandType | id_type | id    | finger_print | scope | env  |
      | login     | login   | 11122 | false        | true  | test |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @test
  Scenario: Grant type Login: Success No Scope
    Then Send login by Grant type Request
      | grandType | id_type | id    | finger_print | scope | env  |
      | login     | login   | 11122 | true         | false | test |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"

  @test
  Scenario: Grant type Login Success Access 500
    Then Send login by Grant type Request
      | grandType | id_type | id     | finger_print | scope | env  |
      | login     | login   | 222500 | true         | true  | test |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @test
  Scenario: Grant type Login: Учетная запись не найдена
    Then Send login by Grant type Request
      | grandType | id_type | id    | finger_print | scope | env  |
      | login     | login   | 11123 | true         | false | test |
    And Status code response is: "404"
    And Response Body contains "additional_properties.exception" equals "UserNotFoundException"
    And Response Body contains "additional_properties.message" equals "Учетная запись не найдена"
    And Response Body contains "type" equals "user_not_found"
    And Response Body contains "message" equals "Обратитесь в техподдержку Банка."
    And Response Body contains "message_title" equals "Возникла непредвиденная ошибка"

  @test
  Scenario: Grant type Login: Неверный код подтверждения
    Then Send login by Grant type Request
      | grandType | id_type | id    | finger_print | scope | env  |
      | login     | login   | 11124 | true         | false | test |
    And Status code response is: "401"
    And Response Body contains "additional_properties.exception" equals "AuthenticationException"
    And Response Body contains "additional_properties.message" equals "Неверный код подтверждения"
    And Response Body contains "type" equals "authentication_failed"
    And Response Body contains "message" equals "Проверьте корректность введенных данных (логин, номер карты или пароль)."
    And Response Body contains "message_title" equals "Вход невозможен"

  @test
  Scenario: Grant type Login: Ошибка
    Then Send login by Grant type Request
      | grandType | id_type | id    | finger_print | scope | env  |
      | login     | login   | 11125 | true         | false | test |
    And Status code response is: "500"
    And Response Body contains "additional_properties.exception" equals "Exception"
    And Response Body contains "additional_properties.message" equals "Ошибка"
    And Response Body contains "type" equals "generic_error"
    And Response Body contains "message" equals "Приносим извинения за доставленные неудобства. Попробуйте войти в ВТБ Онлайн позднее."
    And Response Body contains "message_title" equals "Система временно недоступна"

  @test
  Scenario: Grant type Login: USER_LOCKED
    Then Send login by Grant type Request
      | grandType | id_type | id    | finger_print | scope | env  |
      | login     | login   | 11126 | true         | false | test |
    And Status code response is: "403"
    And Response Body contains "additional_properties.exception" equals "UserLockedException"
    And Response Body contains "additional_properties.message" equals "USER_LOCKED"
    And Response Body contains "additional_properties.lock" equals "PERMANENT"
    And Response Body contains "type" equals "user_locked"
    And Response Body contains "message" equals "Для обеспечения безопасности денежных средств вам заблокирован вход в ВТБ Онлайн. Для восстановления доступа обратитесь в ближайшее отделение."
    And Response Body contains "message_title" equals "Вход заблокирован"