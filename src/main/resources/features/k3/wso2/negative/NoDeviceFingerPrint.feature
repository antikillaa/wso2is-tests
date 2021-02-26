@auth
@authk3
@negative
@negativek3
Feature: No device finger print

  @k3
  Scenario Outline: Grant type No Device Finger Print
    Then Send login by Grant type Request no parameter
      | grandType      | id_type       | id               | scope |  env | finger_print | code   | Authorization   |
      | <grandType>    | <id_type>     | <id>             | true   |  k3 | no           | <code> | <Authorization> |

  Examples:
      | grandType      | id_type       | id               | code | Authorization |
      | login_mb       | login         | 20002571         | 500  |               |
      | login          | login         | 20002571         | 401  |               |
      | card_number    | cardNumber    | 4714870078440778 | 401  |               |
      | card_number_mb | cardNumber    | 4714870078440778 | 500  |               |
      | device_token   | deviceTokenID | 8888000000056316 | 500  |Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
      | phone_number   | phoneNumber   | 9152547896       | 401  |                                                                                    |
      | guest_auth     | phone_number  | 9809935444       | 500  |Basic MjcxSVNzWGZ5Y1U2VnRTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |
      | guest_smb_auth | phone_number  | 9809935444       | 401  |Basic UlAyZ3d0aXl4WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |


  @k3
  Scenario Outline: Grant types No X Finger Print
    Then Send login by Grant type Request no parameter
      |  grandType     |  id_type    |  id     | scope  | env | x_finger_print | finger_print | code   | Authorization   |
      | <grandType>    | <id_type>   | <id>    | true   | k3  | no             | k3           | <code> | <Authorization> |

    Examples:
      | grandType      | id_type       | id               | code | Authorization |
      | login_mb       | login         | 20002571         | 401  |               |
      | login          | login         | 20002571         | 500  |               |
      | card_number    | cardNumber    | 4714870078440778 | 500  |               |
      | card_number_mb | cardNumber    | 4714870078440778 | 401  |               |
      | device_token   | deviceTokenID | 8888000000056316 | 200  | Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
      | phone_number   | phoneNumber   | 9152547896       | 500  |                                                                                    |
      | guest_auth     | phone_number  | 9809935444       | 401  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhGYmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |
      | guest_smb_auth | phone_number  | 9809935444       | 500  | Basic UlAyZ3d0aXl4WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |

  @k3
  Scenario Outline: Grant type Null Device Finger Print
    Then Send login by Grant type Request no parameter
      |  grandType     |  id_type    |  id     | scope  | env | finger_print   | code   | Authorization   |
      | <grandType>    | <id_type>   | <id>    | true   | k3  | null           | <code> | <Authorization> |

    Examples:
      | grandType      | id_type       | id               | code | Authorization |
      | login_mb       | login         | 20002571         | 401  |               |
      | login          | login         | 20002571         | 401  |               |
      | card_number    | cardNumber    | 4714870078440778 | 401  |               |
      | card_number_mb | cardNumber    | 4714870078440778 | 401  |               |
      | device_token   | deviceTokenID | 8888000000056316 | 401  |Basic Uzh3dWRkMmY2bHdIVEVra214NHB5VGxsbU1ZYTpTOHd1ZGQyZjZsd0hURWtrbXg0cHlUbGxtTVlB |
      | phone_number   | phoneNumber   | 9152547896       | 401  |                                                                                   |
      | guest_auth     | phone_number  | 9809935444       | 401  |Basic MjcxSVNzWGZ5Y1U2VnRTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |
      | guest_smb_auth | phone_number  | 9809935444       | 401  |Basic UlAyZ3d0aXl4WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |




