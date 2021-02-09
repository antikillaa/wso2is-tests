@auth
@authk3
Feature: Grant type Login Stub

  @wip
  Scenario: Grant type login_mb No Device Finger Print
    Then Send login by Grant type Request no parameter
      | grandType      | id_type       | id               | scope |  env | finger_print | code | Authorization |
      | login_mb       |    login      | 20002571         | true  |  k3  | no           | 500  |               |
      | login          | login         | 20002571         | true  |  k3  | no           | 401  |               |
      | card_number    | cardNumber    | 4714870078440778 | true  |  k3  | no           | 401  |               |
      | card_number_mb | cardNumber    | 4714870078440778 | true  |  k3  | no           | 500  |               |
      | device_token   | deviceTokenID | 8888000000056316 | true  |  k3  | no           | 500  |Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
      | phone_number   | phoneNumber   | 9152547896       | true  |  k3  | no           | 401  |                                                                                    |
      | guest_auth     | phone_number  | 9809935444       | true  |  k3  | no           | 500  |Basic MjcxSVNzWGZ5Y1U2VnRTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |
      | guest_smb_auth | phone_number  | 9809935444       | true  | k3   | no           | 401  |Basic UlAyZ3d0aXl4WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |






