Feature: Grant type Login Stub

  @k3
  Scenario: Grant type Login Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print |
      | login     | login   | 20002571 | true  | k3           |
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
      | grandType | id_type | id       | scope | finger_print |
      | login_mb  | login   | 20002571 | true  | k3           |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Grant type Login Refresh Token
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print |
      | login     | login   | 20002571 | true  | k3           |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"

    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k3
  Scenario: Grant type Login Mb Refresh Token
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print |
      | login_mb  | login   | 20002571 | true  | k3           |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"

    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k3
  Scenario: Grant type card_number_mb Logout
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print |
      | login_mb  | login   | 20002571 | true  | k3           |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"

    Then "k3" Send Logout Request
    And Status code response is: "200"

  @k3
  Scenario: Grant type card_number_mb Logout
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print |
      | login     | login   | 20002571 | true  | k3           |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"

    Then "k3" Send Logout Request
    And Status code response is: "200"