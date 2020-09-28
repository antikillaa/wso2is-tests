Feature: Grant type Login Stub

  @k3
  Scenario: Grant type Login Success
    Then Send login by Grant type Request
      | grandType| id_type | id    | scope | finger_print |
      | login    | login   | login7771 | true  | k3           |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Grant type Login Mb Success
    Then Send login by Grant type Request
      | grandType| id_type | id    | scope | finger_print |
      | login_mb    | login   | login7771 | true  | k3           |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"