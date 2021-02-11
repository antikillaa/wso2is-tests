@auth
@authk3
@authLogink3
Feature: Grant type Login Stub

  @k3
  Scenario: Grant type Login Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login     | login   | 20002571 | true  | k3           | k3  |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Grant type Login Mb Success
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | login   | 20002571 | true  | k3           | k3  |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Grant type Login Refresh Token
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login     | login   | 20002571 | true  | k3           | k3  |
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
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | login   | 20002571 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k3
  Scenario: Grant type Login Token Exchange
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login     | login   | 20002571 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send Token Exchange Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k3
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

  @k3
  Scenario: Grant type login_mb Logout
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | login   | 20002571 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send Logout Request
    And Status code response is: "200"

  @k3
  Scenario: Grant type login Logout
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login     | login   | 20002571 | true  | k3           | k3  |
    And Status code response is: "200"

    Then "k3" Send Logout Request
    And Status code response is: "200"

  @k3
  Scenario: Grant type login Wrong authorization
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env | Authorization                                                                      |
      | login     | login   | 20002571 | true  | k3           | k3  | Basic Uzh3d123MmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"

  @k3
  Scenario: Grant type login MB Wrong authorization
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env | Authorization                                                                      |
      | login_mb  | login   | 20002571 | true  | k3           | k3  | Basic Uzh3d123MmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"

  @wip
  Scenario: Grant type Login no login parameter
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login     | no   | 20002571 | true  | k3           | k3  |
    And Status code response is: "500"
    And Response Body contains "additional_properties.technical_message" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'login' is missing] "



  @wip
  Scenario: Grant type Login Mb no login mb parameter
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | no   | 20002571 | true  | k3           | k3  |
    And Status code response is: "500"
    And Response Body contains "additional_properties.technical_message" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'login_mb' is missing] "







