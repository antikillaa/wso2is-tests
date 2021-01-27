Feature: Get Ucn By Alias Phone And Domain k4

  @k4
  Scenario: GetUcnByAliasAndPhoneAndDomain: Success 1
    Then Send GetUcnByAliasAndPhoneAndDomain Request
      | env | alias    | phone      | domain |
      | k4  | 18005101 | 9125364152 | master |
    And Status code response is: "200"
    And Response Body contains "id" equals "18005101"
    And Response Body contains "domain" equals "master"



