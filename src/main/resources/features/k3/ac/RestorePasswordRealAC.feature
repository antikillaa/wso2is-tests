Feature: Restore password Real AC

  @k3
  Scenario: Restore Password Success
    Then Send Restore Password Request id: "20002554"
    Then Status code response is: "204"

  @k3
  Scenario: Restore Password User Not Found
    Then Send Restore Password Request id: "12312312321321"
    Then Status code response is: "404"