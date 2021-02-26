@auth
@authk3
@negative
@negativek3
Feature: No device finger print

  @skip
  Scenario Outline: Wrong OTP
    Then Send login by Grant type Request
      | grandType   | id_type   | id   | scope |  env | finger_print | code     | Authorization    | otp   |
      | <grandType> | <id_type> | <id> | true  |  k3  | no           | 401      | <Authorization>  | wrong |


    Examples:
      | grandType      | id_type       | id               |  Authorization |
      | login_mb       | login         | 20002571         |                |
      | login          | login         | 20002571         |                |
      | card_number    | cardNumber    | 4714870078440778 |                |
      | card_number_mb | cardNumber    | 4714870078440778 |                |
      | device_token   | deviceTokenID | 8888000000056316 | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
      | phone_number   | phoneNumber   | 9152547896       |                                                                                     |
      | guest_auth     | phone_number  | 9809935444       | Basic MjcxSVNzWGZ5Y1U2VnRTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |
      | guest_smb_auth | phone_number  | 9809935444       | Basic UlAyZ3d0aXl4WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |
