@auth
@authk3
@authPhonek3
@k3
@wip
Feature: Grant type Phone number k3

  Scenario: Grant type Phone number k3 Success
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print | env | Authorization |
      | phone_number | phoneNumber | 9152547896 | true  | k3           | k3  | IB            |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type Phone number k3 Refresh token
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print | env |
      | phone_number | phoneNumber | 9152547896 | true  | k3           | k3  |

    And Status code response is: "200"

    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  Scenario: Grant type Phone number k3 Token Exchange
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print | env | Authorization |
      | phone_number | phoneNumber | 9152547896 | true  | k3           | k3  | IB            |

    And Status code response is: "200"

    Then "k3" Send Token Exchange Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  Scenario: Grant type Phone_number Logout
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print | env | Authorization |
      | phone_number | phoneNumber | 9152547896 | true  | k3           | k3  | IB            |

    Then "k3" Send Logout Request
    And Status code response is: "200"

