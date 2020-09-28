Feature: Grant type Device Token Stub

  Scenario: Grant type DeviceToken: One Factor
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223401 | true         | true  |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type DeviceToken: One Factor no fingerprint
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223401 | false        | true  |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type DeviceToken: One Factor No Scope
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223401 | true         | false |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "null"
    And Response Body contains "id_token" equals "null"

  Scenario: Grant type DeviceToken: Two Factor Success
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223400 | true         | true  |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type DeviceToken: Two Factor no fingerprint
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223400 | false        | true  |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type DeviceToken: Two Factor No Scope
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223400 | true         | false |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains "id_token" equals "null"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "null"

  Scenario: Grant type DeviceToken: Success Access 500
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope | password |
      | device_token | deviceTokenID | 0000900100000000223500 | true         | true  | false    |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type DeviceToken: Неверный код доступа.
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223402 | true         | true  |
    And Status code response is: "401"
    And Response Body contains "type" equals "incorrect_pin"
    And Response Body contains "message" equals "Неверный код доступа."
    And Response Body contains "additional_properties.remainingPinAttempts" equals "2"

  Scenario: Grant type DeviceToken: Девайс-токен не активен
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223403 | true         | true  |
    And Status code response is: "403"
    And Response Body contains "type" equals "device_token_not_active"
    And Response Body contains "message" equals "В целях безопасности авторизуйтесь повторно в ВТБ Онлайн."
    And Response Body contains "message_title" equals "Произошел сбой"
    And Response Body contains "additional_properties.exception" equals "DeviceTokenNotActiveException"
    And Response Body contains "additional_properties.message" equals "Девайс-токен не активен"

  Scenario: Grant type DeviceToken: Произошел сбой. Вы сбросили код доступа.
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223404 | true         | true  |
    And Status code response is: "403"
    And Response Body contains "type" equals "no_more_pin_attempts"
    And Response Body contains "message" equals "Вы сбросили код доступа."

  Scenario: Grant type DeviceToken: Возникла непредвиденная ошибка. Обратитесь в техподдержку Банка.
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223405 | true         | true  |
    And Status code response is: "403"
    And Response Body contains "type" equals "generic_business_logic"
    And Response Body contains "message" equals "Приносим извинения за доставленные неудобства. Попробуйте войти в ВТБ онлайн позднее."
    And Response Body contains "message_title" equals "Система временно недоступна"
    And Response Body contains "additional_properties.exception" equals "GenericBusinessLogicException"
    And Response Body contains "additional_properties.message" equals "GENERIC_BUSINESS_LOGIC"

  Scenario: Grant type DeviceToken: User Locked
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223406 | true         | true  |
    And Status code response is: "403"
    And Response Body contains "type" equals "user_locked"
    And Response Body contains "message" equals "Для обеспечения безопасности денежных средств вам заблокирован вход в ВТБ Онлайн. Для восстановления доступа обратитесь в ближайшее отделение."
    And Response Body contains "message_title" equals "Вход заблокирован"
    And Response Body contains "additional_properties.exception" equals "UserLockedException"
    And Response Body contains "additional_properties.message" equals "USER_LOCKED"
    And Response Body contains "additional_properties.lock" equals "PERMANENT"

  Scenario: Grant type DeviceToken: Устройство двухфакторной аутентификации не найдено
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223407 | true         | true  |
    And Status code response is: "404"
    And Response Body contains "type" equals "device_not_found"
    And Response Body contains "message" equals "В целях безопасности авторизуйтесь повторно в ВТБ Онлайн."
    And Response Body contains "message_title" equals "Произошел сбой"
    And Response Body contains "additional_properties.exception" equals "DeviceNotFoundException"
    And Response Body contains "additional_properties.message" equals "Устройство двухфакторной аутентификации не найдено"

  Scenario: Grant type DeviceToken: Учетная запись не найдена
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223408 | true         | true  |
    And Status code response is: "404"
    And Response Body contains "type" equals "user_not_found"
    And Response Body contains "message" equals "Обратитесь в техподдержку Банка."
    And Response Body contains "message_title" equals "Возникла непредвиденная ошибка"
    And Response Body contains "additional_properties.exception" equals "UserNotFoundException"
    And Response Body contains "additional_properties.message" equals "Учетная запись не найдена"

  Scenario: Grant type DeviceToken: Ошибка доступа
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope |
      | device_token | deviceTokenID | 0000900100000000223409 | true         | true  |
    And Status code response is: "500"
    And Response Body contains "type" equals "generic_error"
    And Response Body contains "message" equals "Приносим извинения за доставленные неудобства. Попробуйте войти в ВТБ Онлайн позднее."
    And Response Body contains "message_title" equals "Система временно недоступна"

  Scenario: Grant type DeviceToken: Success MSA Update 500
    Then Send login by Grant type Request
      | grandType    | id_type       | id                     | finger_print | scope | password |
      | device_token | deviceTokenID | 0000900100000000223445 | true         | true  | false    |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"
