@ac
@ack3
Feature: User activated

  @k3
  Scenario: User activated
    Then "k3" Send Add Guest Request
    And Status code response is: "200"

    Then "k3" Send Activated Request
    And Status code response is: "200"
    And Response Body don't contains key: "ucn"

    Then Send User activateOrDeactivate request
      | env | activateOrDeactivate | ucn   |
      | k3  | true                 | guest |
    And Status code response is: "200"

    Then "k3" Send Activated Request
    And Status code response is: "200"
    And Response Body contains key: "ucn"