Feature: Change password k3

  @k3
  Scenario: Change Password Success
    Then "k3" Send Change Password Success Request, id: "20002571" password: "123123Qw!"
    And Status code response is: "204"

  @k3
  Scenario: Change Password Wrong Password
    Then "k3" Send Change Password Success Request, id: "20002571" password: "3123123123123123"
    And Status code response is: "403"