Feature: Get User AC

  @test
  Scenario: Get User AC: Master domain
    Then Send Static Password Request
      | env  | domain | ucn      |
      | test | master | 11000035 |
    And Status code response is: "200"
    And Response Body contains "domain" equals "master"
    And Response Body contains "id" equals "11000035"
