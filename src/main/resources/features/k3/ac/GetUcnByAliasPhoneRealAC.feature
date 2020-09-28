Feature: Get Ucn By Alias Phone And Domain Real AC

  @k3
  Scenario: GetUcnByAliasAndPhoneAndDomain: Success 1
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "20002554", Phone: "79802404578"
    And Status code response is: "200"
    And Response Body contains "id" equals "20002554"
    And Response Body contains "domain" equals "master"

  @k3
  Scenario: GetUcnByAliasAndPhoneAndDomain: Phone only
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "null", Phone: "79802404578"
    And Status code response is: "200"
    And Response Body contains "id" equals "20002554"
    And Response Body contains "domain" equals "master"

  @k3
  Scenario: GetUcnByAliasAndPhoneAndDomain: Alias only
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "20002554", Phone: "null"
    And Status code response is: "200"
    And Response Body contains "id" equals "20002554"
    And Response Body contains "domain" equals "master"

  @k3
  Scenario: GetUcnByAliasAndPhoneAndDomain: UCN only
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "login777", Phone: "null"
    And Status code response is: "200"
    And Response Body contains "id" equals "18001226"
    And Response Body contains "domain" equals "master"



