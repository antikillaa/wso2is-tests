@auth
@authk3
@authCardk3
@negative
@negativek3
Feature: Grant type Card Number Negative

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
  Scenario: Grant type Card Number: No cardNumber parameter
    Then Send login by Grant type Request
      | grandType   | id_type    | id               | scope | finger_print | env |
      | card_number | no         | 4714870078440778 | true  | k3           | k3  |

    And Status code response is: "500"
    And Response Body contains "additional_properties.technical_message" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'cardNumber' is missing] "


  @wip
  Scenario: Login by Card empty ID
    Then Send login by Grant type Request
      | grandType   | id_type    | id | scope | finger_print | env |
      | card_number | cardNumber |    | true  | k3           | k3  |

    And Status code response is: "404"

  @wip
  Scenario: Login by Card MB empty ID
    Then Send login by Grant type Request
      | grandType   | id_type    | id  | scope | finger_print | env |
      | card_number | cardNumber |     | true  | k3           | k3  |

    And Status code response is: "404"
