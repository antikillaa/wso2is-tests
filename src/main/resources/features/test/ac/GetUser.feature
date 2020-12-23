Feature: Get User AC

  @test
  Scenario: Get User AC: Master domain
    Then Send Get user Request
      | env  | domain | ucn      |
      | test | master | 11000035 |
    And Status code response is: "200"
    And Response Body contains "domain" equals "master"
    And Response Body contains "id" equals "11000035"

  @test
  Scenario: Get User AC: Guest domain
    Then "test" Send Add Guest Request
    And Status code response is: "200"

    Then Send Get user Request
      | env  | domain | ucn   |
      | test | guest  | guest |
    And Status code response is: "200"
    And Response Body contains "domain" equals "guest"
    And Response Body contains key: "id"
