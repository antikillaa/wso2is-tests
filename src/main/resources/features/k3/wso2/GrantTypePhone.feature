@auth
@authk3
@authPhonek3
Feature: Grant type Phone number k3

  @k3
  Scenario: Grant type Phone number k3 Success
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print | env |
      | phone_number | phoneNumber | 9152547896 | true  | k3           | k3  |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
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

  @k3
  Scenario: Grant type Phone number k3 Token Exchange
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print | env |
      | phone_number | phoneNumber | 9152547896 | true  | k3           | k3  |

    And Status code response is: "200"

    Then "k3" Send Token Exchange Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k3
  Scenario: Grant type Phone_number Logout
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print | env |
      | phone_number | phoneNumber | 9152547896 | true  | k3           | k3  |

    Then "k3" Send Logout Request
    And Status code response is: "200"

  @k3
  Scenario: Grant type Phone number Wrong authorization
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print | env | Authorization                                                                      |
      | phone_number | phoneNumber | 9152547896 | true  | k3           | k3  | Basic Uzh3d123MmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"

  @wip
  Scenario: Grant type Phone_number no number parameter
    Then Send login by Grant type Request
      | grandType    | id_type     | id         | scope | finger_print | env |
      | phone_number | no          | 9152547896 | true  | k3           | k3  |

    And Status code response is: "500"
    And Response Body contains "additional_properties.technical_message" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'phoneNumber' is missing] "