@qr
@qrk3
Feature: QR service

  @k3
  Scenario: Generate QR
    Then "k3" Send generate QR Request
    And Status code response is: "200"

  @k3
  Scenario: Approve QR
    Then Send login by Grant type Request
      | grandType | id_type | id       | scope | finger_print | env | Authorization                                                                      |
      | login_mb  | login   | 20002571 | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
    And Status code response is: "200"

    Then "k3" Send generate QR Request
    And Status code response is: "200"

    Then "k3" Send get QR status Request
    And Status code response is: "200"
    And Response Body contains "status" equals "PROGRESS"

    Then "k3" Send verify QR Request
    And Status code response is: "200"

    Then "k3" Send get QR status Request
    And Status code response is: "200"
    And Response Body contains "status" equals "WAITING_CONSENT"

    Then "k3" Send approve QR Request
    And Status code response is: "200"

  @k3
  Scenario: Get status QR
    Then "k3" Send generate QR Request
    And Status code response is: "200"

    Then "k3" Send get QR status Request
    And Status code response is: "200"
    And Response Body contains "status" equals "PROGRESS"

