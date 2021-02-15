@auth
@authk3
@authPhonek3
@negative
@negativek3
Feature: Grant type Phone number k3

  @k3
  Scenario: Grant type Phone number Wrong authorization
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print | env | Authorization                                                                      |
      | phone_number | phoneNumber | 9152547896 | true  | k3           | k3  | Basic Uzh3d123MmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"

  @k3
  Scenario: Grant type Phone_number no phoneNumber parameter
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print | env |
      | phone_number | no          | 9152547896 | true  | k3           | k3  |

    And Status code response is: "500"
    And Response Body contains "additional_properties.technical_message" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'phoneNumber' is missing] "

