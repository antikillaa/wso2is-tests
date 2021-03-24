@auth
@authk3
@authGuestSmbk3
@negative
@negativek3
Feature: Grant type Guest Smb Auth k3

  @k3
  Scenario: Grant type Guest Smb Auth Wrong authorization
    Then Send login by Grant type Request
      | grandType      | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_smb_auth | phone_number | 9809935426 | true  | k3           | k3  | Basic ajhiT29aMnkxRmh3RUtQY2FGU1NnWUhG3123YmZvYTprV3NrV2ZmNzVVUkJnSm9WeWlzalRjTWI4OUlh |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"

  @k3
  Scenario: Grant type guest_smb_auth no phoneNumber parameter
    Then Send login by Grant type Request
      | grandType      | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_smb_auth | no      | 9809935426 | true  | k3           | k3  | Basic UlAyZ3d0aXl4WldMZ2NZM0l3Wl9hM1QzU0Y4YTpxRWVzOWNXUXJPT0hpd29IaXViOXkwZnVTZ0lh |


    And Status code response is: "500"
    And Response Body contains "additional_properties.tech_messages" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'phone_number' is missing] "
