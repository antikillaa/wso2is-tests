Feature: Grant type Card Number Stub

  Scenario: Grant type Card Number: Success
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | finger_print | scope |
      | card_number | cardNumber | 4714870004240359 | true         | true  |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type Card Number: Success no fingerprint
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | finger_print | scope |
      | card_number | cardNumber | 4714870004240359 | false        | true  |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type Card Number: Success No Scope
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | finger_print | scope |
      | card_number | cardNumber | 4714870004240359 | true         | false |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains "id_token" equals "null"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "null"

  @skip
  Scenario: Grant type Card Number: Учетная запись не найдена
    Then Send login by Grant type Request
      | grandType   | id_type    | id     | finger_print | scope |
      | card_number | cardNumber | 111223 | true         | true  |
    And Status code response is: "404"
    And Response Body contains "additional_properties.exception" equals "CardNotFoundException"
    And Response Body contains "additional_properties.message" equals "Карта не найдена"
    And Response Body contains "type" equals "card_not_found"
    And Response Body contains "message" equals "Вход в систему доступен только клиентам ВТБ, подключенным к ВТБ-Онлайн. Для подключения к ВТБ-Онлайн необходимо один раз посетить любой офис ВТБ с документом, удостоверяющим личность."

  @skip
  Scenario: Grant type Card Number: Карта не найдена
    Then Send login by Grant type Request
      | grandType   | id_type    | id     | finger_print | scope |
      | card_number | cardNumber | 111224 | true         | true  |
    And Status code response is: "404"
    And Response Body contains "additional_properties.exception" equals "CardNotFoundException"
    And Response Body contains "additional_properties.message" equals "Карта не найдена"
    And Response Body contains "type" equals "card_not_found"
    And Response Body contains "message" equals "Вход в систему доступен только клиентам ВТБ, подключенным к ВТБ-Онлайн. Для подключения к ВТБ-Онлайн необходимо один раз посетить любой офис ВТБ с документом, удостоверяющим личность."

  @skip
  Scenario: Grant type Card Number: Внешняя система недоступна
    Then Send login by Grant type Request
      | grandType   | id_type    | id     | finger_print | scope |
      | card_number | cardNumber | 111226 | true         | true  |
    And Status code response is: "500"
    And Response Body contains "additional_properties.exception" equals "GenericException"
    And Response Body contains "additional_properties.message" equals "Внешная система недоступна"
    And Response Body contains "type" equals "generic_error"
    And Response Body contains "message" equals "Приложение временно недоступно. Попробуйте повторить попытку позже."
