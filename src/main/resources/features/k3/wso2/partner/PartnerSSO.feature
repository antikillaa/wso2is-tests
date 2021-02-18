@auth
@authk3
@partnerssok3
@partnersso
@k3
Feature: Grant type Guest Auth k3

  @wip
  Scenario: Partner SSO Initialization
    Then Send Partner SSO Initialization Request
      | type  | id_type | id         |  env | clientId                     | redirectUri        |
      | LOGIN | login   | 20002730   |  k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | http://google.com/ |

    And Status code response is: "200"
    And Response Body contains "stage" equals "CHALLENGE"
    And Response Body contains "params.ucn" equals "20002730"
    And Response Body contains "error" equals "null"