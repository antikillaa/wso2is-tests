Feature: Add Guest RealAC

  @wip
  Scenario: AuthenticateByClientId: Success
    Then Send login by Grant type Request
      | grandType  | id_type      | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | true  | k3           | k3  | Basic MjcxSVNzWGZ5Y1U2VnRTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |
    And Status code response is: "200"

    Then Send non-client-cards Request
      | env |
      | k3  |
