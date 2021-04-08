@auth
@authk3
@authGuestSmbk3
@negative
@negativek3
@k3
Feature: Grant type Guest Smb Auth k3

  Scenario: Grant type Guest Smb Auth Wrong authorization
    Then Send login by Grant type Request
      | grandType      | id_type      | id         | scope | finger_print | env | Authorization   |
      | guest_smb_auth | phone_number | 9809935426 | true  | k3           | k3  | Basic 123123213 |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"

  Scenario: Grant type guest_smb_auth no phoneNumber parameter
    Then Send login by Grant type Request
      | grandType      | id_type | id         | scope | finger_print | env | Authorization |
      | guest_smb_auth | no      | 9809935426 | true  | k3           | k3  | SMB           |

    And Status code response is: "500"
    And Response Body contains "additional_properties.tech_messages" equals "[ExceptionName:IllegalArgumentException; Message:The parameter 'phone_number' is missing] "
