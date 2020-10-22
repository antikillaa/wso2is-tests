Feature: Restore password k5

  @k5
  Scenario: Restore Password Success
    Then "k5" Send Restore Password Request id: "30000377"
    Then Status code response is: "204"

  @k5
  Scenario: Restore Password User Not Found
    Then "k5" Send Restore Password Request id: "12312312321321"
    Then Status code response is: "404"