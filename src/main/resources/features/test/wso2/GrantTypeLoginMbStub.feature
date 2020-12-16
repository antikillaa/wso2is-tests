Feature: Grant type Login_Mb Stub

  @test
  Scenario: Grant type Login_Mb Success
    Then Send login by Grant type Request
      | grandType | id_type | id    | finger_print | scope | password | env  |
      | login_mb  | login   | 11000035 | true         | true  | true     | test |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @test
  Scenario: Grant type Login_Mb: Success no fingerprint
    Then Send login by Grant type Request
      | grandType | id_type | id    | finger_print | scope | env  |
      | login_mb  | login   | 11121 | false        | true  | test |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @test
  Scenario: Grant type Login_Mb: Success No Scope
    Then Send login by Grant type Request
      | grandType | id_type | id    | finger_print | scope | env  |
      | login_mb  | login   | 11121 | true         | false | test |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains "id_token" equals "null"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "null"
