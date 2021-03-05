@auth
@authk3
@partnerssok3
@partnersso
@k3
Feature: Grant type Guest Auth k3

  @TODO
  Scenario: Partner SSO AUTHENTICATE
    Then Send Partner SSO AUTHENTICATE Request
      | type  | id_type | id         |  env | clientId                     | redirectUri        |
      | LOGIN | login   | 20002730   |  k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | http://google.com/ |

    And Status code response is: "200"
    And Response Body contains "stage" equals "CHALLENGE"
    And Response Body contains "params.ucn" equals "20002730"
    And Response Body contains "error" equals "null"

  @TODO
  Scenario: Partner SSO CHALLENGE
    Then Send Partner SSO AUTHENTICATE Request
      | type  | id_type | id         |  env | clientId                     | redirectUri        |
      | LOGIN | login   | 20002730   |  k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | http://google.com/ |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode  | env |
      | 000000      | k3  |

    And Status code response is: "302"

  @TODO
  Scenario: Partner SSO INIT
    Then Send Partner SSO INIT Request
      |  env | clientId                     | redirectUri        |
      |  k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | http://google.com/ |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"
    And Response Body contains key: "params.site"
    And Response Body contains key: "params.icon"
    And Response Body contains "error" equals "null"

  @TODO
  Scenario: Partner SSO Success
    Then Send Partner SSO INIT Request
      |  env | clientId                     | redirectUri        |
      |  k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | http://google.com/ |
    And Status code response is: "200"
    And Response Body contains "stage" equals "AUTHENTICATE"
    And Response Body contains key: "params.site"
    And Response Body contains key: "params.icon"
    And Response Body contains "error" equals "null"

    Then Send Partner SSO AUTHENTICATE Request
      | type  | id_type | id         |  env | clientId                     | redirectUri        |
      | LOGIN | login   | 20002730   |  k3  | C2VYv3b6RHEig2n_56bfnn3GfI4a | http://google.com/ |
    And Status code response is: "200"

    Then Send Partner SSO CHALLENGE Request
      | secureCode  | env |
      | 000000      | k3  |

    And Status code response is: "302"