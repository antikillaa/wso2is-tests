Feature: Get Ucn By Alias Phone And Domain k5

  @k5
  Scenario: GetUcnByAliasAndPhoneAndDomain: Success 1
    Then "k5" Send GetUcnByAliasAndPhoneAndDomain Request Alias: "30000377", Phone: "9125364152"
    And Status code response is: "200"
    And Response Body contains "id" equals "30000377"
    And Response Body contains "domain" equals "master"



