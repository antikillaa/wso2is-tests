Feature: QR k4

  @k4
  Scenario: Generate QR
    Then "k4" Send generate QR Request
    And Status code response is: "200"

  @k4
  Scenario: Approve QR
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | login   | 18005101 | true  | k3           | k4  |
    And Status code response is: "200"

    Then "k4" Send generate QR Request
    And Status code response is: "200"

    Then "k4" Send verify QR Request
    And Status code response is: "200"

    Then "k4" Send approve QR Request
    And Status code response is: "200"
