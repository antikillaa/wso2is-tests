@auth
@authk3
@partnerssok3
@partnersso
@k3
Feature: Partner SSO

  @k3
  Scenario: Partner SSO INIT
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"
    And Response Body contains key: "params.companyName"
    And Response Body contains key: "params.icon"
    And Response Body contains "error" equals "null"

  @k3
  Scenario Outline: Partner SSO INIT Negative
    Then Send Partner SSO INIT Request
      | env | clientId   | redirectUri   | scope   | responseType   | state   | path      |
      | k3  | <clientId> | <redirectUri> | <scope> | <responseType> | <state> | authorize |
    And Status code response is: "<status>"
    And Response Body contains "stage" equals "<stage>"
    And Response Body contains "error" not equals "<error>"

    Examples:
      | clientId                     | redirectUri | scope  | responseType | state   | status | stage        | error |
      | wrong                        | /           | openid | code         | fnnvjvn | 200    | FAIL         | null  |
      | C2VYv3b6RHEig2n_56bfnn3GfI4a | wrong       | openid | code         | fnnvjvn | 200    | FAIL         | null  |
      | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | wrong  | code         | fnnvjvn | 200    | AUTHENTICATE |       |
      | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | openid | wrong        | fnnvjvn | 200    | AUTHENTICATE |       |
      | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | openid | code         | wrong   | 200    | AUTHENTICATE |       |


  @k3
  Scenario: Partner SSO AUTHENTICATE
    Given Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | login    | env | clientId                     | redirectUri | path      | responseType | state   | scope                                                      |
      | LOGIN | 20002730 | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | code         | fnnvjvn | surname name gender inn patronymic birthDate maritalStatus |

    And Status code response is: "200"
    And Response Body contains "stage" equals "CHALLENGE"
    And Response Body contains "error" equals "null"

  @k3
  Scenario Outline: Partner SSO AUTHENTICATE Negative
    Given Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | env | type   | login   | id   | clientId   | redirectUri   | path      | scope   | responseType | state   |
      | k3  | <type> | <login> | <id> | <clientId> | <redirectUri> | authorize | <scope> | code         | fnnvjvn |

    And Status code response is: "<status>"
    And Response Body contains "stage" equals "<stage>"
    And Response Body contains "error.type" equals "<error>"

    Examples:
      | clientId                     | redirectUri | login    | type  | status | stage        | error                 | scope                                                      |
      | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | 20002730 | LOGIN | 200    | CHALLENGE    | null                  | surname name gender inn patronymic birthDate maritalStatus |
      | wrong                        | /           | 20002730 | LOGIN | 200    | FAIL         | generic_error         | surname name gender inn patronymic birthDate maritalStatus |
      | C2VYv3b6RHEig2n_56bfnn3GfI4a | wrong       | 20002730 | LOGIN | 200    | FAIL         | invalid_request       | surname name gender inn patronymic birthDate maritalStatus |
      | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | wrong    | LOGIN | 200    | AUTHENTICATE | authentication_failed | surname name gender inn patronymic birthDate maritalStatus |
      | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | 20002730 | wrong | 200    | FAIL         | generic_error         | surname name gender inn patronymic birthDate maritalStatus |


  @k3
  Scenario: Partner SSO CHALLENGE
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | login    | env | clientId                     | redirectUri | path      | responseType | state   | scope                                                      |
      | LOGIN | 20002730 | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | code         | fnnvjvn | surname name gender inn patronymic birthDate maritalStatus |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode | env | path      |
      | 000000     | k3  | authorize |
    And Status code response is: "302"

  @wip
  Scenario Outline: Partner SSO CHALLENGE Negative
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | login    | env | clientId                     | redirectUri | path      | responseType | state   | scope                                                      |
      | LOGIN | 20002730 | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | code         | fnnvjvn | surname name gender inn patronymic birthDate maritalStatus |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode   | env | path      |
      | <secureCode> | k3  | authorize |
    And Status code response is: "<status>"
    Examples:
      | secureCode | status |
      | 000000     | 302    |
      | wrong      | 302    |

  @k3
  Scenario: Partner SSO auth-code request
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | login    | env | clientId                     | redirectUri | path      | responseType | state   | scope                                                      |
      | LOGIN | 20002730 | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | code         | fnnvjvn | surname name gender inn patronymic birthDate maritalStatus |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode | env | path      |
      | 000000     | k3  | authorize |
    And Status code response is: "302"

    Then Send Partner SSO auth-code Request
      | env | Authorization                                                                      | path  |
      | k3  | Basic QzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YTpWaXFLSG9fTXRSYm05bFNTeVJGQ1hmTnRDblFh | token |
    And Status code response is: "200"
    And Response Body contains key: "scope"
    And Response Body contains key: "access_token"
    And Response Body contains key: "refresh_token"

  @k3
  Scenario: Partner SSO user-info request
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | login    | env | clientId                     | redirectUri | path      | responseType | state   | scope                                                      |
      | LOGIN | 20002730 | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | code         | fnnvjvn | surname name gender inn patronymic birthDate maritalStatus |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode | env | path      |
      | 000000     | k3  | authorize |
    And Status code response is: "302"

    Then Send Partner SSO auth-code Request
      | env | Authorization                                                                      | path  |
      | k3  | Basic QzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YTpWaXFLSG9fTXRSYm05bFNTeVJGQ1hmTnRDblFh | token |
    And Status code response is: "200"
    And Response Body contains key: "scope"
    And Response Body contains key: "access_token"
    And Response Body contains key: "refresh_token"

    Then Send Partner SSO user-info Request
      | env | path      |
      | k3  | me   |
    And Status code response is: "200"