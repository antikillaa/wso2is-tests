Feature: Grant type Device Token Stub

  @skip
  Scenario: Grant type DeviceToken: Two Factor Success
    Then Send login by Grant type Request
      | grandType    | id_type       | id               | finger_print | scope | Authorization |
      | device_token | deviceTokenID | 8888000000056316 | true         | true  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTo5aXhXaGhhcHRmem1kMWlLMXd0UTVPU3FYTU1h |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"