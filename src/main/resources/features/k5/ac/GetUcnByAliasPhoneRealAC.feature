Feature: Get Ucn By Alias Phone And Domain k5

  @k5
  Scenario: GetUcnByAliasAndPhoneAndDomain: Success 1
    Then Send GetUcnByAliasAndPhoneAndDomain Request
      | env | alias    | phone      | domain |
      | k5  | 30000377 | 9125364152 | master |
    And Status code response is: "200"
    And Response Body contains "id" equals "30000377"
    And Response Body contains "domain" equals "master"



