Feature: Restore password k3

  @k3
  Scenario: Restore Password Success
    Then "k3" Send Restore Password Request id: "20002554"
    Then Status code response is: "204"

  @k3
  Scenario: Restore Password User Not Found
    Then "k3" Send Restore Password Request id: "12312312321321"
    Then Status code response is: "404"