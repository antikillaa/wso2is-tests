@auth
@authk3
@authCardk3
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
  Scenario: Grant type Card Number: Refresh token
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
  Scenario: Grant type Card Number: Token Exchange
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number    | cardNumber | 4714870078440778 | true  | k3           | k3  |
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

  @k3
  Scenario: Login by Card MB without UNK
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env | Authorization                                                                      |
      | card_number_mb | cardNumber | 1234222233334444 | true  | k3           | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "500"

  @k3
  Scenario: Login by Card without UNK
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization                                                                      |
      | card_number | cardNumber | 1234222233334444 | true  | k3           | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "500"

  @k3
  Scenario: Login by not active Card status card 240
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | cardNumber | 5368291015786478 | true  | k3           | k3  |
    And Status code response is: "403"
    And Response Body contains "type" equals "card_not_valid"

  @k3
  Scenario: Login by not active Card MB status card 240
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 5368291015786478 | true  | k3           | k3  |
    And Status code response is: "403"
    And Response Body contains "type" equals "card_not_valid"

  @k3
  Scenario: Login by not active Card status card 430
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | cardNumber | 4893470273518297 | true  | k3           | k3  |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Login by not active Card status card 430
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 4893470273518297 | true  | k3           | k3  |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Login by Card wrong authorization
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization                                                                      |
      | card_number | cardNumber | 4893470273518297 | true  | k3           | k3  | Basic Uzh3d123MmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"


  @k3
  Scenario: Login by Card wrong authorization
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization                                                                      |
      | card_number | cardNumber | 4893470273518297 | true  | k3           | k3  | Basic Uzh3d123MmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"

  @k3
  Scenario: Login by by expired card
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | cardNumber | 4714870091976451 | true  | k3           | k3  |
    And Status code response is: "403"
    And Response Body contains "type" equals "card_not_valid"

  @k3
  Scenario: Login by by expired card Mb
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env |
      | card_number_mb | cardNumber | 4714870091976451 | true  | k3           | k3  |
    And Status code response is: "403"
    And Response Body contains "type" equals "card_not_valid"

  @k3
  Scenario: Login by Card MB Moscow bank with UNK
    Then Send login by Grant type Request
      | grandType         | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number_mb    | cardNumber | 2200650565343957 | true  | k3           | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Login by Card Moscow bank with UNK
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number    | cardNumber | 2200650565343957 | true  | k3           | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Login by Card MB Moscow bank without UNK
    Then Send login by Grant type Request
      | grandType         | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number_mb    | cardNumber | 4652062963184265 | true  | k3           | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "500"
    And Response Body contains "message_title" equals "Что-то пошло не так"


  @k3
  Scenario: Login by Card MB Moscow bank without UNK
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number    | cardNumber | 4652062963184265 | true  | k3           | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "500"
    And Response Body contains "message_title" equals "Приносим извинения за доставленные неудобства. Воспользуйтесь старой версией интернет-банка по ссылке: [url=https://online-old.vtb.ru]https://online-old.vtb.ru[/url]"

  @k3
  Scenario: Login by Card issued for third person
    Then Send login by Grant type Request
      | grandType         | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number       | cardNumber | 2200650565343666 | true  | k3           | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "403"

  @k3
  Scenario: Login by Card MB issued for third person
    Then Send login by Grant type Request
      | grandType         | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number_mb    | cardNumber | 2200650565343666 | true  | k3           | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
    And Status code response is: "403"