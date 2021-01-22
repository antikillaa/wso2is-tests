Feature: QR k5

  @k5
  Scenario: Generate QR
    Then "k5" Send generate QR Request
    And Status code response is: "200"

  @skip
  Scenario: Approve QR
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env |
      | login_mb  | login   | 30005986 | true  | k3           | k5  |
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"

    Then "k5" Send generate QR Request
    And Status code response is: "200"

    Then "k5" Send verify QR Request
    And Status code response is: "200"

    Then "k5" Send approve QR Request
    And Status code response is: "200"
