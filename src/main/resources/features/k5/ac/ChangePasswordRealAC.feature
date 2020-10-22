Feature: Change password k5

  @skip
  Scenario: Change Password Success
    Then "k5" Send Change Password Success Request, id: "30000377" password: "Qa!123321"
    And Status code response is: "204"

  @skip
  Scenario: "k4" Send Change Password Success Request
    Then "k5" Send Change Password Success Request, id: "30000377" password: "Qa!13123213"
    And Status code response is: "403"
