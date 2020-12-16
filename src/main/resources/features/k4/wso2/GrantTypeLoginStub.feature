Feature: Grant type Login Stub K4

  @skip
  Scenario: Grant type Login Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login     | login   | 18005101 | true  | k3           | k4  |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k4
  Scenario: Grant type Login Mb Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | login   | 18005101 | true  | k3           | k4  |
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"