Feature: Grant type QR Auth

  @k3
  Scenario: Grant type QR Auth Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | finger_print | scope | password | env |
      | login_mb  | login   | 20002571 | true         | true  | true     | k3  |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"

    Then "k3" Send login by Grant type QR Auth Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"
