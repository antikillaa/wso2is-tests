Feature: Check Remote Password Restore k5

  @k5
  Scenario: CheckRemotePasswordRestore: Success
    Then Send CheckRemotePasswordRestore Request
      | env | id       | domain |
      | k5  | 30006300 | master |
    And Status code response is: "200"
    And Response Body contains "mobilePhone" equals "9065558877"

  @k5
  Scenario: CheckRemotePasswordRestore: Restore Forbidden
    Then Send CheckRemotePasswordRestore Request
      | env | id       | domain |
      | k5  | 30001380 | master |
    And Status code response is: "403"

  @k5
  Scenario: CheckRemotePasswordRestore: Учетная запись не найдена
    Then Send CheckRemotePasswordRestore Request
      | env | id        | domain |
      | k5  | 200025541 | master |
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"