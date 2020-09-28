Feature: Change password Real AC

  @k3
  Scenario: Change Password Success
    Then Send Change Password Success Request
    And Status code response is: "204"

  @k3
  Scenario: Change Password Wrong Password
    Then Send Change Password Wrong Password Request
    And Status code response is: "403"
