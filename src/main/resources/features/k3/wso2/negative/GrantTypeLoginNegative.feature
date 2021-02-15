@auth
@authk3
@authLogink3
@negative
@negativek3
Feature: Grant type Login Negative

  @k3
  Scenario: Grant type Login no login parameter
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login     | no      | 20002571 | true  | k3           | k3  |
    And Status code response is: "500"
    And Response Body contains "additional_properties.technical_message" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'login' is missing] "



  @k3
  Scenario: Grant type Login Mb no login mb parameter
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | no      | 20002571 | true  | k3           | k3  |
    And Status code response is: "500"
    And Response Body contains "additional_properties.technical_message" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'login' is missing] "

  @k3
  Scenario: Grant type Login Mb No ID
    Then Send login by Grant type Request
      | grandType | id_type | id | scope | finger_print | env |
      | login_mb  | login   | no | true  | k3           | k3  |
    And Status code response is: "401"
    And Response Body contains "type" equals "authentication_failed"

  @k3
  Scenario: Grant type Login No ID
    Then Send login by Grant type Request
      | grandType | id_type | id | scope | finger_print | env |
      | login     | login   | no | true  | k3           | k3  |
    And Status code response is: "401"
    And Response Body contains "type" equals "authentication_failed"





