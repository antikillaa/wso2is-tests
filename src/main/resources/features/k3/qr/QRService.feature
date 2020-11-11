Feature: QR k3

  @k3
  Scenario: Generate QR
    Then Send generate QR Request
      | grandType | id_type | id       | scope | finger_print |
      | login     | login   | 20002571 | true  | k3           |
    And Status code response is: "200"

  @skip
  Scenario: AuthenticateByClientId: Учетная запись не найдена
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print |
      | login     | login   | 20002571 | true  | k3           |
    And Status code response is: "401"
    Then Send Second Factor login by Grant type request
    And Status code response is: "200"
