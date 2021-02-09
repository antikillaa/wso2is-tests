@ac
@ack3
Feature: User activateOrDeactivate

  @k3
  Scenario: User activateOrDeactivate: true
    Then "k3" Send Add Guest Request
    And Status code response is: "200"

    Then Send User activateOrDeactivate request
      | env | activateOrDeactivate | ucn |
      | k3  | true                 |     |
    And Status code response is: "200"

  @k3
  Scenario: User activateOrDeactivate: false
    Then "k3" Send Add Guest Request
    And Status code response is: "200"

    Then Send User activateOrDeactivate request
      | env | activateOrDeactivate | ucn |
      | k3  | false                |     |
    And Status code response is: "200"

  @k3
  Scenario: User activateOrDeactivate: not found
    Then Send User activateOrDeactivate request
      | env | activateOrDeactivate | ucn       |
      | k3  | false                | 321312312 |
    And Status code response is: "404"