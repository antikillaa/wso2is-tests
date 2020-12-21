Feature: QR Test

  @test
  Scenario: Generate QR
    Then "test" Send generate QR Request
    And Status code response is: "200"

  @test
  Scenario: verify QR
    Then Send login by Grant type Request
      | grandType | id_type | id       | finger_print | scope | password | env  |
      | login_mb  | login   | 11000035 | true         | true  | true     | test |
    And Status code response is: "200"

    Then "test" Send generate QR Request
    And Status code response is: "200"

    And Status code response is: "200"
    Then "test" Send verify QR Request
    And Status code response is: "200"

    Then "test" Send approve QR Request
    And Status code response is: "200"
