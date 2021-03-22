@auth
@authk3
@authLogink3
@negative
@negativek3
Feature: Grant type Login Negative

  @k3
  Scenario: Grant type login Wrong authorization
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env | Authorization                                                                      |
      | login     | login   | 20002571 | true  | k3           | k3  | Basic Uzh3d123MmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"

  @k3
  Scenario: Grant type login MB Wrong authorization
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env | Authorization                                                                      |
      | login_mb  | login   | 20002571 | true  | k3           | k3  | Basic Uzh3d123MmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"

  @k3
  Scenario: Grant type Login no login parameter
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login     | no      | 20002571 | true  | k3           | k3  |
    And Status code response is: "500"
    And Response Body contains "additional_properties.tech_messages" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'login' is missing] "



  @k3
  Scenario: Grant type Login Mb no login mb parameter
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | no      | 20002571 | true  | k3           | k3  |
    And Status code response is: "500"
    And Response Body contains "additional_properties.tech_messages" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'login' is missing] "

  @k3
  Scenario: Grant type Login Mb No ID
    Then Send login by Grant type Request
      | grandType | id_type | id | scope | finger_print | env |
      | login_mb  | login   | no | true  | k3           | k3  |
    And Status code response is: "500"
    And Response Body contains "additional_properties.tech_messages" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'login' is missing] "

  @k3
  Scenario: Grant type Login No ID
    Then Send login by Grant type Request
      | grandType | id_type | id | scope | finger_print | env |
      | login     | login   | no | true  | k3           | k3  |
    And Status code response is: "500"
    And Response Body contains "additional_properties.tech_messages" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'login' is missing] "





