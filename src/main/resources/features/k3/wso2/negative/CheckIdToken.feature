@auth
@authk3
@negative
@negativek3
Feature: Check Id Token

  @k3
  Scenario Outline: Check Id Token
    Then Send login by Grant type Request
      | grandType   | id_type   | id   | scope | env | finger_print | Authorization   | otp   |
      | <grandType> | <id_type> | <id> | true  | k3  | k3           | <Authorization> | wrong |
    And Status code response is: "401"

    Examples:
      | grandType      | id_type      | id               | Authorization                                                                      |
      | login_mb       | login        | 20002571         | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
      | login          | login        | 20002571         | Basic QzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YTpWaXFLSG9fTXRSYm05bFNTeVJGQ1hmTnRDblFh |
      | card_number    | cardNumber   | 4714870078440778 | Basic QzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YTpWaXFLSG9fTXRSYm05bFNTeVJGQ1hmTnRDblFh |
      | card_number_mb | cardNumber   | 4714870078440778 | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
      | phone_number   | phoneNumber  | 9152547896       | Basic QzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YTpWaXFLSG9fTXRSYm05bFNTeVJGQ1hmTnRDblFh |
      | guest_auth     | phone_number | 9809935444       | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
      | guest_smb_auth | phone_number | 9809935444       | Basic UlAyZ3d0aXl4WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |
