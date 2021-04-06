@auth
@authk3
@authDeviceTokenk3
@k3
Feature: Grant type Device Token Stub

  Scenario: Grant type DeviceToken: Two Factor Success
    Then Send login by Grant type Request
      | grandType    | id_type       | id               | finger_print | scope | env | Authorization |
      | device_token | deviceTokenID | 8888000000056316 | true         | true  | k3  | AutoTest      |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"