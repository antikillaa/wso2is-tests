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

  Scenario: Card not active IB
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number | cardNumber | 4111111111111111 | true  | k3           | k3  | AutoTest      |

    And Status code response is: "403"
    And Response Body contains "type" equals "card_not_valid"

  Scenario: Card not active MB
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number_mb | cardNumber | 4111111111111111 | true  | k3           | k3  | AutoTest      |

    And Status code response is: "403"
    And Response Body contains "type" equals "card_not_valid"

  Scenario: Card Moscow bank IB
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number | cardNumber | 4111111111111616 | true  | k3           | k3  | AutoTest      |

    And Status code response is: "200"

  Scenario: Card Moscow bank MB
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number_mb | cardNumber | 4111111111111616 | true  | k3           | k3  | AutoTest      |

    And Status code response is: "200"

  @wip
  Scenario: Card Moscow bank without UNK IB
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number | cardNumber | 4111111116161616 | true  | k3           | k3  | AutoTest      |

    And Status code response is: "500"
    And Response Body contains "type" equals "generic_error"


  @wip
  Scenario: Card Moscow bank without UNK MB
    Then Send login by Grant type Request
      | grandType      | id_type    | id               | scope | finger_print | env | Authorization |
      | card_number_mb | cardNumber | 4111111116161616 | true  | k3           | k3  | AutoTest      |

    And Status code response is: "500"
    And Response Body contains "type" equals "generic_error"