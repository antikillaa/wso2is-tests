Feature: QR k3

  @skip
  Scenario: Generate QR
    Then "test" Send generate QR Request
    And Status code response is: "200"

  @wip
  Scenario: AuthenticateByClientId: Учетная запись не найдена
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print |
      | login     | login   | 20002571 | true  | k3           |
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"

    Then "k3" Send generate QR Request
    And Status code response is: "200"

    Then "k3" Send verify QR Request
    And Status code response is: "200"
