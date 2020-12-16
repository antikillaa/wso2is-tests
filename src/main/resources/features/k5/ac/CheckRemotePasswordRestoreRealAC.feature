Feature: Check Remote Password Restore k5

  @k5
  Scenario: CheckRemotePasswordRestore: Success
    Then "k5" Send CheckRemotePasswordRestore Request id: "30006300"
    And Status code response is: "200"
    And Response Body contains "mobilePhone" equals "9065558877"

  @k5
  Scenario: CheckRemotePasswordRestore: Restore Forbidden
    Then "k5" Send CheckRemotePasswordRestore Request id: "30001380"
    And Status code response is: "403"

  @k5
  Scenario: CheckRemotePasswordRestore: Учетная запись не найдена
    Then "k5" Send CheckRemotePasswordRestore Request id: "200025541"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"