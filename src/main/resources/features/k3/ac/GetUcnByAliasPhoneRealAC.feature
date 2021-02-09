@ac
@ack3
Feature: Get Ucn By Alias Phone And Domain k3

  @k3
  Scenario: Send GetUcnByAliasAndPhoneAndDomain Request by Alias and Phone
    Then Send GetUcnByAliasAndPhoneAndDomain Request
      | env | alias    | phone       | domain |
      | k3  | 20002554 | 79802404578 | master |
    And Status code response is: "200"
    And Response Body contains "id" equals "20002554"
    And Response Body contains "domain" equals "master"

  @k3
  Scenario: GetUcnByAliasAndPhoneAndDomain: Phone only
    Then Send GetUcnByAliasAndPhoneAndDomain Request
      | env | alias | phone       | domain |
      | k3  |       | 79802404578 | master |
    And Status code response is: "200"
    And Response Body contains "id" equals "20002554"
    And Response Body contains "domain" equals "master"

  @k3
  Scenario: GetUcnByAliasAndPhoneAndDomain: Alias only
    Then Send GetUcnByAliasAndPhoneAndDomain Request
      | env | alias    | phone | domain |
      | k3  | 20002554 |       | master |
    And Status code response is: "200"
    And Response Body contains "id" equals "20002554"
    And Response Body contains "domain" equals "master"

  @k3
  Scenario: GetUcnByAliasAndPhoneAndDomain: UCN only
    Then Send GetUcnByAliasAndPhoneAndDomain Request
      | env | alias    | phone | domain |
      | k3  | login777 |       | master |
    And Status code response is: "200"
    And Response Body contains "id" equals "18001226"
    And Response Body contains "domain" equals "master"

  @k3
  Scenario: GetUcnByAliasAndPhoneAndDomain Guest: UCN only
    Then Send GetUcnByAliasAndPhoneAndDomain Request
      | env | alias    | phone | domain |
      | k3  | 20063177 |       | guest  |
    And Status code response is: "200"
    And Response Body contains "id" equals "20063177"
    And Response Body contains "domain" equals "guest"



