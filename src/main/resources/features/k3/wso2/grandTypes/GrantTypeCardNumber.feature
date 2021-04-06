@auth
@authk3
@authCardk3
@k3
Feature: Grant type Card Number K3

  Scenario: Grant type Card Number: Success
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number | cardNumber | 4714870078440778 | true  | k3           | k3  | IB            |
    And Status code response is: "200"

    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type Card Number Mb: Success
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 4714870078440778 | true  | k3           | k3  |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type Card Number: Refresh token
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number | cardNumber | 4714870078440778 | true  | k3           | k3  | IB            |
    And Status code response is: "200"
    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  Scenario: Grant type Card Number Mb: Refresh token
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 4714870078440778 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  Scenario: Grant type Card Number Mb: Token Exchange
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 4714870078440778 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send Token Exchange Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  Scenario: Grant type Card Number: Token Exchange
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number | cardNumber | 4714870078440778 | true  | k3           | k3  | IB            |
    And Status code response is: "200"

    Then "k3" Send Token Exchange Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  Scenario: Grant type card_number_mb Logout
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env | Authorization                                                                      |
      | card_number_mb | cardNumber | 4714870078440778 | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
    And Status code response is: "200"

    Then "k3" Send Logout Request
    And Status code response is: "200"

  Scenario: Grant type card_number Logout
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number | cardNumber | 4714870078440778 | true  | k3           | k3  | IB            |
    And Status code response is: "200"

    Then "k3" Send Logout Request
    And Status code response is: "200"