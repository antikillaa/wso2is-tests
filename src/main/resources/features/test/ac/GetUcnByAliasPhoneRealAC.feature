Feature: Get Ucn By Alias Phone And Domain Real AC

  Scenario: GetUcnByAliasAndPhoneAndDomain: Success 1
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "321", Phone: "9876543219"
    And Status code response is: "200"
    And Response Body contains "id" equals "123456"
    And Response Body contains "domain" equals "master"

  Scenario: GetUcnByAliasAndPhoneAndDomain: Phone only
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "null", Phone: "9876543219"
    And Status code response is: "200"
    And Response Body contains "id" equals "123456"
    And Response Body contains "domain" equals "master"

  Scenario: GetUcnByAliasAndPhoneAndDomain: Alias only
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "321", Phone: "null"
    And Status code response is: "200"
    And Response Body contains "id" equals "123456"
    And Response Body contains "domain" equals "master"

  Scenario: GetUcnByAliasAndPhoneAndDomain: Another Alias
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "132", Phone: "9876543219"
    And Status code response is: "200"
    And Response Body contains "id" not equals "123456"
    And Response Body contains "domain" equals "master"

  Scenario: GetUcnByAliasAndPhoneAndDomain: Another Phone
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "321", Phone: "1111111"
    And Status code response is: "200"
    And Response Body contains "id" equals "123456"
    And Response Body contains "domain" equals "master"

  Scenario: GetUcnByAliasAndPhoneAndDomain: Alias not exist
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "qwqer123123", Phone: "9876543219"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"

  Scenario: GetUcnByAliasAndPhoneAndDomain: Phone not exist
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "321", Phone: "1111111111111111"
    And Status code response is: "200"
    And Response Body contains "id" equals "123456"
    And Response Body contains "domain" equals "master"

  Scenario: GetUcnByAliasAndPhoneAndDomain: Phone not unique, no Alias
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "null", Phone: "9252918596"
    And Status code response is: "403"
    And Response Body contains "exception" equals "NonUniqueEntryFoundException"
    And Response Body contains "message" equals "Найдено более 1 пользователя по условиям поиска"

  Scenario: GetUcnByAliasAndPhoneAndDomain: Phone not unique, no Alias
    Then Send GetUcnByAliasAndPhoneAndDomain Request Alias: "123", Phone: "8962147485"



