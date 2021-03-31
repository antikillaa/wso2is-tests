@auth
@authk3
@authLogink3
@k3
@wip
Feature: Grant type Login Stub

  Scenario: Grant type Login Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env | Authorization |
      | login     | login   | 20002571 | true  | k3           | k3  | IB            |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type Login Mb Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | login   | 20002571 | true  | k3           | k3  |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type Login Refresh Token
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env | Authorization |
      | login     | login   | 20002571 | true  | k3           | k3  | IB            |
    And Status code response is: "200"

    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  Scenario: Grant type Login Mb Refresh Token
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | login   | 20002571 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  Scenario: Grant type Login Token Exchange
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env | Authorization |
      | login     | login   | 20002571 | true  | k3           | k3  | IB            |
    And Status code response is: "200"

    Then "k3" Send Token Exchange Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  Scenario: Grant type Login Mb Token Exchange
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | login   | 20002571 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send Token Exchange Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  Scenario: Grant type login_mb Logout
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | login   | 20002571 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send Logout Request
    And Status code response is: "200"

  Scenario: Grant type login Logout
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env | Authorization |
      | login     | login   | 20002571 | true  | k3           | k3  | IB            |
    And Status code response is: "200"

    Then "k3" Send Logout Request
    And Status code response is: "200"


