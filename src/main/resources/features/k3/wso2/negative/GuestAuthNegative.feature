@auth
@authk3
@authGuestk3
@negative
@negativek3
Feature: Grant type Guest Auth k3

  @k3
  Scenario: Grant type Guest Auth Wrong authorization
    Then Send login by Grant type Request
      | grandType  | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | 9809935444 | true  | k3           | k3  | Basic MjcxSVNzWGZ5Y1U232RTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"

  @k3
  Scenario: Grant type Phone_number no phoneNumber parameter
    Then Send login by Grant type Request
      | grandType  | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | no           | 9809935444 | true  | k3           | k3  | Basic MjcxSVNzWGZ5Y1U2VnRTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |


    And Status code response is: "500"
    And Response Body contains "additional_properties.technical_message" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'phone_number' is missing] "

