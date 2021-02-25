@guestOnboarding
@guestOnboardingk3
Feature: Guest Onboarding Service

  @k3
  Scenario: Activate Non Client Success
    Then Send login by Grant type Request
      | grandType  | id_type      | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
    And Status code response is: "200"

    Then Send non-client-cards Request
      | env |
      | k3  |
    And Status code response is: "200"
    And Response Body contains key: "id"
    And Response Body contains key: "signature"

    Then Activate Non Client Request
      | env |
      | k3  |
    And Status code response is: "200"

    Then Send is-registered request
      | env |
      | k3  |
    And Status code response is: "200"

    Then Send delete request
      | env |
      | k3  |
    And Status code response is: "200"

    Then Send is-registered request
      | env |
      | k3  |
    And Status code response is: "404"

  @k3
  Scenario: Delete, activated user not exist in CPK, exist in AC
    Then Send login by Grant type Request
      | grandType  | id_type      | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
    And Status code response is: "200"

    Then Send delete request
      | env |
      | k3  |
    And Status code response is: "200"

    Then Send is-registered request
      | env |
      | k3  |
    And Status code response is: "404"

  @k3
  Scenario: Delete, not activated user exist in CPK, exist in AC
    Then Send login by Grant type Request
      | grandType  | id_type      | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
    And Status code response is: "200"

    Then Send non-client-cards Request
      | env |
      | k3  |
    And Status code response is: "200"
    And Response Body contains key: "id"
    And Response Body contains key: "signature"

    Then Send deactivate request
      | env |
      | k3  |
    And Status code response is: "200"

    Then Send is-registered request
      | env |
      | k3  |
    And Status code response is: "404"

  @k3
  Scenario: Deactivate active user
    Then Send login by Grant type Request
      | grandType  | id_type      | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
    And Status code response is: "200"

    Then Send non-client-cards Request
      | env |
      | k3  |
    And Status code response is: "200"
    And Response Body contains key: "id"
    And Response Body contains key: "signature"

    Then Activate Non Client Request
      | env |
      | k3  |
    And Status code response is: "200"

  @k3
  Scenario: Deactivate not active user
    Then Send login by Grant type Request
      | grandType  | id_type      | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
    And Status code response is: "200"

    Then Send deactivate request
      | env |
      | k3  |
    And Status code response is: "200"

