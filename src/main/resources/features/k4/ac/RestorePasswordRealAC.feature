Feature: Restore password k4

  @k4
  Scenario: Restore Password Success
    Then "k4" Send Restore Password Request id: "18020416"
    Then Status code response is: "204"

  @k4
  Scenario: Restore Password User Not Found
    Then "k4" Send Restore Password Request id: "12312312321321"
    Then Status code response is: "404"