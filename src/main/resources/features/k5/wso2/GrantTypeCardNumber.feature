@auth
@authk5
@authCardk5
Feature: Grant type Card Number K5

  @k5
  Scenario: Grant type Card Number: Success
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | cardNumber | 5278830191579360 | true  | k3           | k5  |
    And Status code response is: "200"

    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k5
  Scenario: Grant type Card Number Mb: Success
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 5278830191579360 | true  | k3           | k5  |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k5
  Scenario: Grant type Card Number: Refresh token
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | cardNumber | 5278830191579360 | true  | k3           | k5  |
    And Status code response is: "200"

    Then "k5" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k5
  Scenario: Grant type Card Number Mb: Refresh token
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 5278830191579360 | true  | k3           | k5  |
    And Status code response is: "200"

    Then "k5" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k5
  Scenario: Grant type Card Number Mb: Token Exchange
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 5278830191579360 | true  | k3           | k5  |
    And Status code response is: "200"

    Then "k5" Send Token Exchange Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k5
  Scenario: Grant type Card Number: Token Exchange
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number    | cardNumber | 5278830191579360 | true  | k3           | k5  |
    And Status code response is: "200"

    Then "k5" Send Token Exchange Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k5
  Scenario: Grant type card_number_mb Logout
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 5278830191579360 | true  | k3           | k5  |
    And Status code response is: "200"

    Then "k5" Send Logout Request
    And Status code response is: "200"

  @k5
  Scenario: Grant type card_number Logout
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | cardNumber | 5278830191579360 | true  | k3           | k5  |
    And Status code response is: "200"

    Then "k5" Send Logout Request
    And Status code response is: "200"

  @k5
  Scenario: Login by Card MB without UNK
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env | Authorization                                                                      |
      | card_number_mb | cardNumber | 1234222233334444 | true  | k3           | k5  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "500"

  @k5
  Scenario: Login by Card without UNK
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization                                                                      |
      | card_number | cardNumber | 1234222233334444 | true  | k3           | k5  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "500"

  @k5
  Scenario: Login by Card MB Moscow bank with UNK
    Then Send login by Grant type Request
      | grandType         | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number_mb    | cardNumber | 2200650565343957 | true  | k3           | k5  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k5
  Scenario: Login by Card Moscow bank with UNK
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number    | cardNumber | 2200650565343957 | true  | k3           | k5  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k5
  Scenario: Login by Card MB Moscow bank without UNK
    Then Send login by Grant type Request
      | grandType         | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number_mb    | cardNumber | 4652062963184265 | true  | k3           | k5  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "500"
    And Response Body contains "message_title" equals "Что-то пошло не так"


  @k5
  Scenario: Login by Card MB Moscow bank without UNK
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number    | cardNumber | 4652062963184265 | true  | k3           | k5  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "500"
    And Response Body contains "message_title" equals "Приносим извинения за доставленные неудобства. Воспользуйтесь старой версией интернет-банка по ссылке: [url=https://online-old.vtb.ru]https://online-old.vtb.ru[/url]"

  @k5
  Scenario: Login by Card issued for third person
    Then Send login by Grant type Request
      | grandType         | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number       | cardNumber | 2200650565343666 | true  | k3           | k5  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "403"

  @k5
  Scenario: Login by Card MB issued for third person
    Then Send login by Grant type Request
      | grandType         | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number_mb    | cardNumber | 2200650565343666 | true  | k3           | k5  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "403"