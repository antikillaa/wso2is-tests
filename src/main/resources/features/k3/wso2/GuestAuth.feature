@auth
@authk3
@authGuestk3
Feature: Grant type Guest Auth k3

  @k3
  Scenario: Grant type Guest Auth k3 Success
    Then Send login by Grant type Request
      | grandType  | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | 9809935444 | true  | k3           | k3  | Basic MjcxSVNzWGZ5Y1U2VnRTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |

    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains "scope" equals "openid"

  @k3
  Scenario: Grant type Guest Auth k3 Refresh token
    Then Send login by Grant type Request
      | grandType  | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | 9809935444 | true  | k3           | k3  | Basic MjcxSVNzWGZ5Y1U2VnRTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |
    And Status code response is: "200"

    Then "k3" Send Refresh token Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @wip
  Scenario: Grant type Guest Auth k3 Token Exchange
    Then "k3" Send Token Exchange Guest Request

    Then Send login by Grant type Request
      | grandType  | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | 9809935444 | true  | k3           | k3  | Basic MjcxSVNzWGZ5Y1U2VnRTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |
    And Status code response is: "200"

    Then "k3" Send Token Exchange Guest Request
    And Status code response is: "200"
    And Response Body contains key: "access_token"
    And Response Body contains key: "id_token"
    And Response Body contains key: "refresh_token"
    And Response Body contains key: "scope"

  @k3
  Scenario: Grant type Guest Auth Logout
    Then Send login by Grant type Request
      | grandType  | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | 9809935444 | true  | k3           | k3  | Basic MjcxSVNzWGZ5Y1U2VnRTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |

    Then "k3" Send Logout Request
    And Status code response is: "200"

  @k3
  Scenario: Grant type Guest Auth Wrong authorization
    Then Send login by Grant type Request
      | grandType  | id_type      | id         | scope | finger_print | env | Authorization                                                                      |
      | guest_auth | phone_number | 9809935444 | true  | k3           | k3  | Basic MjcxSVNzWGZ5Y1U232RTZkw0Z2dfTURYUWxVYTpKRzByRWZkRmZidDM4UTB4UkV0UlNmWTFWdndh |

    And Status code response is: "500"
    And Response Body contains key: "additional_properties.tech_messages"