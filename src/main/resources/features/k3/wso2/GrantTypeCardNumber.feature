Feature: Grant type Card Number K3

  @k3
  Scenario: Grant type Card Number: Success
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print |
      | card_number | cardNumber | 4714870091976451 | true  | k3           |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Grant type Card Number Mb: Success
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print |
      | card_number_mb | cardNumber | 4714870091976451 | true  | k3           |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"



