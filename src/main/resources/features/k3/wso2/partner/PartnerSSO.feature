@auth
@authk3
@partnerssok3
@partnersso
@skip
Feature: Partner SSO

  Scenario: Partner SSO INIT
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"
    And Response Body contains key: "params.companyName"
    And Response Body contains key: "params.icon"
    And Response Body contains "error" equals "null"

  Scenario Outline: Partner SSO INIT Negative
    Then Send Partner SSO INIT Request
      | env | clientId   | redirectUri   | scope   | responseType   | state   | path      |
      | k3  | <clientId> | <redirectUri> | <scope> | <responseType> | <state> | authorize |
    And Status code response is: "<status>"
    And Response Body contains "stage" equals "<stage>"
    And Response Body contains "error" not equals "<error>"

    Examples:
      | clientId            | redirectUri                   | scope  | responseType | state   | status | stage        | error |
      | wrong               | http://mobile-bank-partner.ru | openid | code         | fnnvjvn | 200    | FAIL         | null  |
      | mobile-bank-partner | wrong                         | openid | code         | fnnvjvn | 200    | FAIL         | null  |
      | mobile-bank-partner | http://mobile-bank-partner.ru | wrong  | code         | fnnvjvn | 200    | AUTHENTICATE |       |
      | mobile-bank-partner | http://mobile-bank-partner.ru | openid | wrong        | fnnvjvn | 200    | AUTHENTICATE |       |
      | mobile-bank-partner | http://mobile-bank-partner.ru | openid | code         | wrong   | 200    | AUTHENTICATE |       |
      | no                  | http://mobile-bank-partner.ru | openid | code         | fnnvjvn | 200    | FAIL         | null  |
      | mobile-bank-partner | no                            | openid | code         | fnnvjvn | 200    | FAIL         | null  |
      | mobile-bank-partner | http://mobile-bank-partner.ru | no     | code         | fnnvjvn | 200    | AUTHENTICATE |       |
      | mobile-bank-partner | http://mobile-bank-partner.ru | openid | no           | fnnvjvn | 200    | AUTHENTICATE |       |
      | mobile-bank-partner | http://mobile-bank-partner.ru | openid | code         | no      | 200    | AUTHENTICATE |       |
      |                     | http://mobile-bank-partner.ru | openid | code         | fnnvjvn | 200    | FAIL         | null  |
      | mobile-bank-partner |                               | openid | code         | fnnvjvn | 200    | FAIL         | null  |
      | mobile-bank-partner | http://mobile-bank-partner.ru |        | code         | fnnvjvn | 200    | AUTHENTICATE |       |
      | mobile-bank-partner | http://mobile-bank-partner.ru | openid |              | fnnvjvn | 200    | AUTHENTICATE |       |
      | mobile-bank-partner | http://mobile-bank-partner.ru | openid | code         |         | 200    | AUTHENTICATE |       |


  Scenario: Partner SSO AUTHENTICATE
    Given Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | login    | env | clientId                     | redirectUri | path      | responseType | state   | scope                                                      |
      | LOGIN | 20002730 | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | code         | fnnvjvn | surname name gender inn patronymic birthDate maritalStatus |

    And Status code response is: "200"
    And Response Body contains "stage" equals "CHALLENGE"
    And Response Body contains "error" equals "null"

  Scenario Outline: Partner SSO AUTHENTICATE Negative
    Given Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | env | type   | login   | id   | clientId   | redirectUri   | path      | scope   | responseType | state   |
      | k3  | <type> | <login> | <id> | <clientId> | <redirectUri> | authorize | <scope> | code         | fnnvjvn |

    And Status code response is: "<status>"
    And Response Body contains "stage" equals "<stage>"
    And Response Body contains "error.type" equals "<error>"

    Examples:
      | clientId            | redirectUri                   | login    | type  | status | stage        | error                 | scope                                                      |
      | mobile-bank-partner | http://mobile-bank-partner.ru | 20002730 | LOGIN | 200    | CHALLENGE    | null                  | surname name gender inn patronymic birthDate maritalStatus |
      | wrong               | http://mobile-bank-partner.ru | 20002730 | LOGIN | 200    | FAIL         | generic_error         | surname name gender inn patronymic birthDate maritalStatus |
      | mobile-bank-partner | wrong                         | 20002730 | LOGIN | 200    | FAIL         | invalid_request       | surname name gender inn patronymic birthDate maritalStatus |
      | mobile-bank-partner | http://mobile-bank-partner.ru | wrong    | LOGIN | 200    | AUTHENTICATE | authentication_failed | surname name gender inn patronymic birthDate maritalStatus |
      | mobile-bank-partner | http://mobile-bank-partner.ru | 20002730 | wrong | 200    | FAIL         | generic_error         | surname name gender inn patronymic birthDate maritalStatus |
      | no                  | http://mobile-bank-partner.ru | 20002730 | LOGIN | 200    | FAIL         | invalid_request       | surname name gender inn patronymic birthDate maritalStatus |
      | mobile-bank-partner | no                            | 20002730 | LOGIN | 200    | FAIL         | invalid_request       | surname name gender inn patronymic birthDate maritalStatus |
      | mobile-bank-partner | http://mobile-bank-partner.ru | no       | LOGIN | 200    | AUTHENTICATE | authentication_failed | surname name gender inn patronymic birthDate maritalStatus |
      | mobile-bank-partner | http://mobile-bank-partner.ru | 20002730 | no    | 200    | FAIL         | generic_error         | surname name gender inn patronymic birthDate maritalStatus |
      |                     | http://mobile-bank-partner.ru | 20002730 | LOGIN | 200    | FAIL         | invalid_request       | surname name gender inn patronymic birthDate maritalStatus |
      | mobile-bank-partner |                               | 20002730 | LOGIN | 200    | FAIL         | invalid_request       | surname name gender inn patronymic birthDate maritalStatus |
      | mobile-bank-partner | http://mobile-bank-partner.ru |          | LOGIN | 200    | AUTHENTICATE | authentication_failed | surname name gender inn patronymic birthDate maritalStatus |
      | mobile-bank-partner | http://mobile-bank-partner.ru | 20002730 |       | 200    | FAIL         | generic_error         | surname name gender inn patronymic birthDate maritalStatus |


  Scenario: Partner SSO CHALLENGE
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | login    | env | clientId                     | redirectUri | path      | responseType | state   | scope                                                      |
      | LOGIN | 20002730 | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | code         | fnnvjvn | surname name gender inn patronymic birthDate maritalStatus |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode | env | path      |
      | 000000     | k3  | authorize |
    And Status code response is: "302"

  Scenario Outline: Partner SSO CHALLENGE Negative
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | login    | env | clientId                     | redirectUri | path      | responseType | state   | scope                                                      |
      | LOGIN | 20002730 | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | code         | fnnvjvn | surname name gender inn patronymic birthDate maritalStatus |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode   | env | path      |
      | <secureCode> | k3  | authorize |
    And Status code response is: "<status>"
    And Response Body contains "error.type" equals "<error>"

    Examples:
      | secureCode | status | error                 |
      |            | 200    | generic_error         |
      | wrong      | 200    | authentication_failed |


  Scenario: Partner SSO auth-code request
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | login    | env | clientId                     | redirectUri | path      | responseType | state   | scope                                                      |
      | LOGIN | 20002730 | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | code         | fnnvjvn | surname name gender inn patronymic birthDate maritalStatus |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode | env | path      |
      | 000000     | k3  | authorize |
    And Status code response is: "302"

    Then Send Partner SSO auth-code Request
      | env | Authorization                                                                      | path  | grant_type | code |
      | k3  | Basic QzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YTpWaXFLSG9fTXRSYm05bFNTeVJGQ1hmTnRDblFh | token | code       |      |
    And Status code response is: "200"
    And Response Body contains key: "scope"
    And Response Body contains key: "access_token"
    And Response Body contains key: "refresh_token"

  Scenario Outline: Partner SSO auth-code request Negative
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | login    | env | clientId                     | redirectUri | path      | responseType | state   | scope                                                      |
      | LOGIN | 20002730 | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | code         | fnnvjvn | surname name gender inn patronymic birthDate maritalStatus |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode | env | path      |
      | 000000     | k3  | authorize |
    And Status code response is: "302"

    Then Send Partner SSO auth-code Request
      | env | Authorization   | path   | grant_type   | code   |
      | k3  | <Authorization> | <path> | <grant_type> | <code> |
    And Status code response is: "<status>"
    And Response Body contains key: "<error>"

    Examples:
      | path  | grant_type | code  | status | error                                   | Authorization                                                                      |
      | token | code       |       | 500    | additional_properties.technical_message | Basic wrong                                                                        |
      | token | code       |       | 500    | additional_properties.technical_message |                                                                                    |
      | token | wrong      |       | 500    | additional_properties.technical_message | Basic QzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YTpWaXFLSG9fTXRSYm05bFNTeVJGQ1hmTnRDblFh |
      | token |            |       | 500    | additional_properties.technical_message | Basic QzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YTpWaXFLSG9fTXRSYm05bFNTeVJGQ1hmTnRDblFh |
      | token | code       | wrong | 401    | error                                   | Basic QzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YTpWaXFLSG9fTXRSYm05bFNTeVJGQ1hmTnRDblFh |
      | wrong | code       |       | 404    | error                                   | Basic QzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YTpWaXFLSG9fTXRSYm05bFNTeVJGQ1hmTnRDblFh |


  Scenario: Partner SSO user-info request
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | login    | env | clientId                     | redirectUri | path      | responseType | state   | scope                                                      |
      | LOGIN | 20002730 | k3  | mobile-bank-partner | http://mobile-bank-partner.ru | authorize | code         | fnnvjvn | surname name gender inn patronymic birthDate maritalStatus |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode | env | path      |
      | 000000     | k3  | authorize |
    And Status code response is: "302"

    Then Send Partner SSO auth-code Request
      | env | Authorization                                                                      | path  | grant_type | code |
      | k3  | Basic QzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YTpWaXFLSG9fTXRSYm05bFNTeVJGQ1hmTnRDblFh | token | code       |      |
    And Status code response is: "200"
    And Response Body contains key: "scope"
    And Response Body contains key: "access_token"
    And Response Body contains key: "refresh_token"

    Then Send Partner SSO user-info Request
      | env | path |
      | k3  | me   |
    And Status code response is: "200"