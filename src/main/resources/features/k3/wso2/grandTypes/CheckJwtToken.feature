@auth
@authk3
@authLogink3
@k3
Feature: Check JWT Token

  Scenario Outline: Check JWT Token
    Then Send login by Grant type Request
      | grandType   | id_type   | id   | scope | env | finger_print | Authorization   |
      | <grandType> | <id_type> | <id> | true  | k3  | k3           | <Authorization> |
    And Status code response is: "200"
    Then Check JWT Token

    Examples:
      | grandType      | id_type      | id               | Authorization |
      | login_mb       | login        | 20002571         | MB            |
      | login          | login        | 20002571         | IB            |
      | card_number    | cardNumber   | 4714870078440778 | IB            |
      | card_number_mb | cardNumber   | 4714870078440778 | MB            |
      | phone_number   | phoneNumber  | 9152547896       | IB            |
      | guest_auth     | phone_number | 9809935426       | MB            |

