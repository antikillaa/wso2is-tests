Feature: User activated

  @test
  Scenario: User activated
    Then "test" Send Add Guest Request
    And Status code response is: "200"

    Then "test" Send Activated Request
    And Status code response is: "200"
    And Response Body don't contains key: "ucn"

    Then Send User activateOrDeactivate request
      | env  | activateOrDeactivate | ucn   |
      | test | true                 | guest |
    And Status code response is: "200"

    Then "test" Send Activated Request
    And Status code response is: "200"
    And Response Body contains key: "ucn"