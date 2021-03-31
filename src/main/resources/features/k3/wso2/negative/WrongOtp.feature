@auth
@authk3
@negative
@negativek3
@k3
Feature: Wrong OTP

  @wip
  Scenario Outline: Wrong OTP
    Then Send login by Grant type Request
      | grandType   | id_type   | id   | scope | env | finger_print | Authorization   | otp   |
      | <grandType> | <id_type> | <id> | true  | k3  | k3           | <Authorization> | wrong |
    And Status code response is: "401"

    Examples:
      | grandType      | id_type      | id               | Authorization                                                                      |
      | login_mb       | login        | 20002571         | MB                                                                                 |
      | login          | login        | 20002571         | IB                                                                                 |
      | card_number    | cardNumber   | 4714870078440778 | IB                                                                                 |
      | card_number_mb | cardNumber   | 4714870078440778 | MB                                                                                 |
      | phone_number   | phoneNumber  | 9152547896       | IB                                                                                 |
      | guest_auth     | phone_number | 9809935426       | MB                                                                                 |
      | guest_smb_auth | phone_number | 9809935765       | Basic UlAyZ3d0aXl4WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |
