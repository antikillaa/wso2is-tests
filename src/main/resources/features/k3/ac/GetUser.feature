Feature: Get User AC

  @k3
  Scenario: Get User AC: Master
    Then Send Get user Request
      | env | domain | ucn      |
      | k3  | master | 20002571 |
    And Status code response is: "200"
    And Response Body contains "domain" equals "master"
    And Response Body contains "id" equals "20002571"

  @k3
  Scenario: Get User AC: Guest
    Then "k3" Send Add Guest Request
    And Status code response is: "200"

    Then Send Get user Request
      | env | domain | ucn   |
      | k3  | guest  | guest |
    And Status code response is: "200"
    And Response Body contains "domain" equals "guest"
    And Response Body contains key: "id"
