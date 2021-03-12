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

  @wip
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


  @k3
  Scenario: Partner SSO AUTHENTICATE
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | id_type | id       | env | clientId                     | redirectUri | path      |
      | LOGIN | login   | 20002730 | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize |

    And Status code response is: "200"
    And Response Body contains "stage" equals "CHALLENGE"
    And Response Body contains "error" equals "null"

  @k3
  Scenario: Partner SSO CHALLENGE
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | id_type | id       | env | clientId                     | redirectUri | path      |
      | LOGIN | login   | 20002730 | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode | env | path      |
      | 000000     | k3  | authorize |
    And Status code response is: "302"

  @k3
  Scenario: Partner SSO auth-code request
    Then Send Partner SSO INIT Request
      | env | clientId                     | redirectUri | path      | scope  | responseType | state   |
      | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize | openid | code         | fnnvjvn |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | id_type | id       | env | clientId                     | redirectUri | path      |
      | LOGIN | login   | 20002730 | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize |
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
      | type  | id_type | id       | env | clientId                     | redirectUri | path      |
      | LOGIN | login   | 20002730 | k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | /           | authorize |
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