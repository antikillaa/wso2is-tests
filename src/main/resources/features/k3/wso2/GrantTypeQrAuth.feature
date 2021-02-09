@auth
@authk3
Feature: Grant type QR Auth

  @k3
  Scenario: Grant type QR Auth Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | finger_print | scope | password | env |
      | login_mb  | login   | 20002571 | true         | true  | true     | k3  |
    And Status code response is: "200"

    Then "k3" Send login by Grant type QR Auth Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Grant type QR Auth Refresh Token
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login     | login   | 20002571 | true  | k3           | k3  |

    And Status code response is: "200"

    Then "k3" Send login by Grant type QR Auth Request
    And Status code response is: "200"

    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k3
  Scenario: Grant type QR Auth Token Exchange
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login     | login   | 20002571 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send login by Grant type QR Auth Request
    And Status code response is: "200"

    Then "k3" Send Token Exchange Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k3
  Scenario: Grant type QR Auth Logout
    Then Send login by Grant type Request
      | grandType | id_type | id       | finger_print | scope | password | env |
      | login_mb  | login   | 20002571 | true         | true  | true     | k3  |
    And Status code response is: "200"

    Then "k3" Send login by Grant type QR Auth Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

    Then "k3" Send Logout Request
    And Status code response is: "200"