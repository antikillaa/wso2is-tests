Feature: Grant type Card Number K3

  @k3
  Scenario: Grant type Card Number: Success
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | cardNumber | 4714870078440778 | true  | k3           | k3  |
    And Status code response is: "200"

    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Grant type Card Number Mb: Success
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 4714870078440778 | true  | k3           | k3  |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Grant type Card Number Mb: Refresh token
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | cardNumber | 4714870078440778 | true  | k3           | k3  |
    And Status code response is: "200"
    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k3
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

  @k3
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

  @k3
  Scenario: Grant type card_number_mb Logout
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 4714870078440778 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send Logout Request
    And Status code response is: "200"

  @k3
  Scenario: Grant type card_number Logout
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | cardNumber | 4714870078440778 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send Logout Request
    And Status code response is: "200"

  @wip
  Scenario: Login by Card MB without UNK
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env | Authorization                                                                      |
      | card_number_mb | cardNumber | 1234222233334444 | true  | k3           | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "500"

  @wip
  Scenario: Login by Card without UNK
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization                                                                      |
      | card_number | cardNumber | 1234222233334444 | true  | k3           | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "500"

  @wip
  Scenario: Login by not active Card status card 430
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | cardNumber | 5368291015786478 | true  | k3           | k3  |
    And Status code response is: "403"
    And Response Body contains "type" equals "card_not_valid"

  @wip
  Scenario: Login by not active Card MB status card 430
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 5368291015786478 | true  | k3           | k3  |
    And Status code response is: "403"
    And Response Body contains "type" equals "card_not_valid"

  @wip
  Scenario: Login by not active Card status card 240
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | cardNumber | 5368291015786478 | true  | k3           | k3  |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @wip
  Scenario: Login by not active Card status card 240
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 5368291015786478 | true  | k3           | k3  |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"





