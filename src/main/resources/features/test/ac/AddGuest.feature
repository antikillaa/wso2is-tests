Feature: Add Guest RealAC

  @test
  Scenario: AuthenticateByClientId: Success
    Then "test" Send Add Guest Request
    And Status code response is: "200"
    And Response Body contains "domain" equals "guest"
    And Response Body contains key: "ucn"
