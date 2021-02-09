@ac
@ack3
Feature: Get User AC

  @k3
  Scenario: Get User AC: Master domain
    Then Send Static Password Request
      | env | domain | ucn      |
      | k3  | master | 20002571 |
    And Status code response is: "200"
    And Response Body contains "domain" equals "master"
    And Response Body contains "id" equals "20002571"