Feature: Restore password Real AC

  @skip
  Scenario: Restore Password Success
    Then "test" Send Restore Password Request id: "30000377"

  @skip
  Scenario: Restore Password Random Phone
    Then "test" Send Restore Password Request id: "30000377"
