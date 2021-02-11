@auth
@authk3
@authGuestSmbk3
Feature: Grant type Guest Smb Auth k3

  @k3
  Scenario: Grant type Guest Smb Auth Wrong authorization
    Then Send login by Grant type Request
      | grandType      | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_smb_auth | phone_number | 9809935444 | true  | k3           | k3  | Basic UlAyZ3d0aX54WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"