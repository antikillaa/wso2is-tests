@auth
@authk3
@authDeviceTokenk3
@negative
@negativek3
Feature: Grant type Device Token Stub

  @k3
  Scenario: Grant type DeviceToken: No deviceTokenID parameter
    Then Send login by Grant type Request
      | grandType    | id_type       | id               | finger_print | scope | env | Authorization                                                                      |
      | device_token | no            | 8888000000056316 | true         | true  | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains "additional_properties.technical_message" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'deviceTokenID' is missing] "
