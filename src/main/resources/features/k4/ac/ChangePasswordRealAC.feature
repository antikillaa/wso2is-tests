Feature: Change password k4

  @skip
  Scenario: Change Password Success
    Then "k4" Send Change Password Success Request, id: "18005101" password: "Qa!123321"
    And Status code response is: "204"

  @skip
  Scenario: "k4" Send Change Password Success Request
    Then "k4" Send Change Password Success Request, id: "18005101" password: "Qa!13123213"
    And Status code response is: "403"
