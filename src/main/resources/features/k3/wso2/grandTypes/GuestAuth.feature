@auth
@authk3
@authGuestk3
Feature: Grant type Guest Auth k3

  @k3
  Scenario: Grant type Guest Auth k3 Success
    Then Send login by Grant type Request
      | grandType  | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | 9809935444 | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Grant type Guest Auth k3 Refresh token
    Then Send login by Grant type Request
      | grandType  | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | 9809935444 | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
    And Status code response is: "200"

    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @wip
  Scenario: Grant type Guest Auth k3 Token Exchange
    Then Send login by Grant type Request
      | grandType  | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | 9809935444 | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
    And Status code response is: "200"

    Then "k3" Send Token Exchange Guest Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k3
  Scenario: Grant type Guest Auth Logout
    Then Send login by Grant type Request
      | grandType  | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | 9809935444 | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |

    Then "k3" Send Logout Request
    And Status code response is: "200"