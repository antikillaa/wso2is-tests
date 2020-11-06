Feature: Grant type Card Number Mb Stub

  Scenario: Grant type Card Number Mb: Success
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | finger_print | scope | env  |
      | card_number_mb | cardNumber | 1111222233334444 | true         | true  | test |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type Card Number Mb: Success no fingerprint
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | finger_print | scope | env  |
      | card_number_mb | cardNumber | 1111222233334444 | false        | true  | test |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type Card Number Mb: Success No Scope
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | finger_print | scope | env  |
      | card_number_mb | cardNumber | 1111222233334444 | true         | false | test |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains "id_token" equals "null"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "null"
