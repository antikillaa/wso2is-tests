Feature: Grant type QR Auth

  @test
  Scenario: Grant type QR Auth Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | finger_print | scope | password | env  |
      | login_mb  | login   | 11000035 | true         | true  | true     | test |
    And Status code response is: "200"

    Then "test" Send login by Grant type QR Auth Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"