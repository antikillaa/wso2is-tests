Feature: Check Remote Password Restore k4

  @k4
  Scenario: CheckRemotePasswordRestore: Success
    Then "k4" Send CheckRemotePasswordRestore Request id: "18005101"
    And Status code response is: "200"

  @k4
  Scenario: CheckRemotePasswordRestore: Удаленное восстановление пароля запрещено
    Then "k4" Send CheckRemotePasswordRestore Request id: "18004626"
    And Status code response is: "403"
    And Response Body contains "exception" equals "RemotePasswordRestoreForbiddenException"
    And Response Body contains "message" equals "Удаленное восстановление пароля запрещено"

  @k4
  Scenario: CheckRemotePasswordRestore: Учетная запись не найдена
    Then "k4" Send CheckRemotePasswordRestore Request id: "200025541"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"