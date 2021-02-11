@auth
@authk3
@authDeviceTokenk3
Feature: Grant type Device Token Stub

  @wip
  Scenario: Grant type DeviceToken: Two Factor Success
    Then Send login by Grant type Request
      | grandType    | id_type       | id               | finger_print | scope | env | Authorization                                                                      |
      | device_token | deviceTokenID | 8888000000056316 | true         | true  | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @wip
  Scenario: Grant type DeviceToken: No deviceTokenID parameter
    Then Send login by Grant type Request
      | grandType    | id_type       | id               | finger_print | scope | env | Authorization                                                                      |
      | device_token | no            | 8888000000056316 | true         | true  | k3  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains "additional_properties.technical_message" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'deviceTokenID' is missing] "
