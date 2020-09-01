Feature: Change password Real AC

  Scenario: Change Password Success
    Then Send Change Password Success Request
    And Status code response is: "204"


  Scenario: Change Password Wrong Password
    Then Send Change Password Wrong Password Request
    And Status code response is: "403"
