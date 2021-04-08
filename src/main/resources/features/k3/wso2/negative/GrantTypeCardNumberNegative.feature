@auth
@authk3
@authCardk3
@negative
@negativek3
@k3
Feature: Grant type Card Number Negative

  Scenario: Login by Card wrong authorization
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization                                                                      |
      | card_number | cardNumber | 4893470273518297 | true  | k3           | k3  | Basic Uzh3d123MmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"


  Scenario: Login by Card wrong authorization
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization                                                                      |
      | card_number | cardNumber | 4893470273518297 | true  | k3           | k3  | Basic Uzh3d123MmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"

  Scenario: Grant type Card Number: No cardNumber parameter
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | no         | 4714870078440778 | true  | k3           | k3  |

    And Status code response is: "500"
    And Response Body contains "additional_properties.tech_messages" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'cardNumber' is missing] "


  Scenario: Login by Card empty ID
    Then Send login by Grant type Request
      | grandType   | id_type    | id | scope | finger_print | env |
      | card_number | cardNumber | no | true  | k3           | k3  |
    And Status code response is: "500"

  Scenario: Login by Card MB empty ID
    Then Send login by Grant type Request
      | grandType   | id_type    | id | scope | finger_print | env |
      | card_number | cardNumber | no | true  | k3           | k3  |
    And Status code response is: "500"

  Scenario: Login by Card MB empty ID
    Then Send login by Grant type Request
      | grandType   | id_type    | id | scope | finger_print | env |
      | card_number | cardNumber | no | true  | k3           | k3  |
    And Status code response is: "500"

  @wip
  Scenario Outline: Login by card stubs
    Then Send login by Grant type Request
      | grandType   | id_type   | id   | scope | finger_print | env | Authorization |
      | <grandType> | <id_type> | <id> | true  | k3           | k3  | AutoTest      |
    And Status code response is: "<status>"
    And Response Body contains "<error_path>" equals "<text>"

    Examples:
      | grandType      | id_type    | id               | status | error_path | text           | case                            |
        #Card not active
      | card_number    | cardNumber | 4111111116111116 | 403    | type       | card_not_valid | Card not active IB              |
      | card_number_mb | cardNumber | 4111111116111116 | 403    | type       | card_not_valid | Card not active MB              |
        #Card Moscow bank
      | card_number    | cardNumber | 4111111111111616 | 200    | scope      | openid         | Card Moscow bank IB             |
      | card_number_mb | cardNumber | 4111111111111616 | 200    | scope      | openid         | Card Moscow bank MB             |
        #Card Moscow bank without UNK
      | card_number    | cardNumber | 4111111116161616 | 500    | type       | generic_error  | Card Moscow bank without UNK IB |
      | card_number_mb | cardNumber | 4111111116161616 | 500    | type       | generic_error  | Card Moscow bank without UNK MB |
        #Card VTB bank without UNK
      | card_number    | cardNumber | 4111111616111111 | 500    | type       | generic_error  | Card VTB bank without UNK IB    |
      | card_number_mb | cardNumber | 4111111616111111 | 500    | type       | generic_error  | Card VTB bank without UNK MB    |
        #Third person issued card
      | card_number    | cardNumber | 4111111116111116 | 403    | type       | card_not_valid | Third person issued card IB     |
      | card_number_mb | cardNumber | 4111111116111116 | 403    | type       | card_not_valid | Third person issued card MB     |
        #Expired card
      | card_number    | cardNumber | 4111111116111611 | 403    | type       | card_not_valid | Expired card IB                 |
      | card_number_mb | cardNumber | 4111111116111611 | 403    | type       | card_not_valid | Expired card MB                 |