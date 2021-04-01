@auth
@authk3
@authGuestSmbk3
@k3
Feature: Grant type Guest Smb Auth k3

  Scenario: Grant type Guest Smb Auth k3 Success
    Then Send login by Grant type Request
      | grandType      | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_smb_auth | phone_number | 9809935426 | true  | k3           | k3  | Basic UlAyZ3d0aXl4WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  Scenario: Grant type uest Smb Auth k3 Refresh token
    Then Send login by Grant type Request
      | grandType      | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_smb_auth | phone_number | 9809935426 | true  | k3           | k3  | Basic UlAyZ3d0aXl4WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |

    And Status code response is: "200"

    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  Scenario: Grant type Guest Smb Auth Logout
    Then Send login by Grant type Request
      | grandType      | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_smb_auth | phone_number | 9809935426 | true  | k3           | k3  | Basic UlAyZ3d0aXl4WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |

    Then "k3" Send Logout Request
    And Status code response is: "200"