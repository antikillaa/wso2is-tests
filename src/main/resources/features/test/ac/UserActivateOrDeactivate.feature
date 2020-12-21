Feature: User activateOrDeactivate

  @test
  Scenario: User activateOrDeactivate: true
    Then "test" Send Add Guest Request
    And Status code response is: "200"

    Then Send User activateOrDeactivate request
      | env  | activateOrDeactivate | ucn |
      | test | true                 |     |
    And Status code response is: "200"

  @test
  Scenario: User activateOrDeactivate: false
    Then "test" Send Add Guest Request
    And Status code response is: "200"

    Then Send User activateOrDeactivate request
      | env  | activateOrDeactivate | ucn |
      | test | false                |     |
    And Status code response is: "200"

  @test
  Scenario: User activateOrDeactivate: not found
    Then Send User activateOrDeactivate request
      | env  | activateOrDeactivate | ucn       |
      | test | false                | 321312312 |
    And Status code response is: "404"