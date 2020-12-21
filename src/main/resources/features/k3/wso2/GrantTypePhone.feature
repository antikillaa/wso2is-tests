Feature: Grant type Phone number k3

  @k3
  Scenario: Grant type Phone number k3 Success
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print |
      | phone_number | phoneNumber | 9152547896 | true  | k3           |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"