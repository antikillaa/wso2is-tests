Feature: QR k3

  @k3
  Scenario: Generate QR
    Then "k3" Send generate QR Request
    And Status code response is: "200"

  @k3
  Scenario: AuthenticateByClientId: Учетная запись не найдена
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print |
      | login_mb  | login   | 20002571 | true  | k3           |
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"

    Then "k3" Send verify QR Request
    And Status code response is: "200"

    Then "k3" Send approve QR Request
    And Status code response is: "200"
