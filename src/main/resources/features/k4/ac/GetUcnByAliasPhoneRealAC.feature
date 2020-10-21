Feature: Get Ucn By Alias Phone And Domain k4

  @k4
  Scenario: GetUcnByAliasAndPhoneAndDomain: Success 1
    Then "k4" Send GetUcnByAliasAndPhoneAndDomain Request Alias: "18005101", Phone: "9125364152"
    And Status code response is: "200"
    And Response Body contains "id" equals "18005101"
    And Response Body contains "domain" equals "master"



