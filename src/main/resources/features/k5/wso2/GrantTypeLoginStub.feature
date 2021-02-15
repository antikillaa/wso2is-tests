Feature: Grant type Login Stub K5

  @k5
  Scenario: Grant type Login Mb Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login     | login   | 30005986 | true  | k3           | k5  |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k5
  Scenario: Grant type Login Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | login   | 30005986 | true  | k3           | k5  |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"