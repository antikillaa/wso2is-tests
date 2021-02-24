@ac
@ack3
Feature: Add Guest RealAC

  @k3
  Scenario: AuthenticateByClientId: Success
    Then "k3" Send Add Guest Request
    And Status code response is: "200"
    And Response Body contains "domain" equals "guest"
    And Response Body contains key: "ucn"