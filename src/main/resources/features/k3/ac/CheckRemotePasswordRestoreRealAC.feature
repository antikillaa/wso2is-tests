Feature: Check Remote Password Restore k3

  @k3
  Scenario: CheckRemotePasswordRestore: Success
    Then "k3" Send CheckRemotePasswordRestore Request id: "18001042"
    And Status code response is: "200"

  @k3
  Scenario: CheckRemotePasswordRestore: Удаленное восстановление пароля запрещено
    Then "k3" Send CheckRemotePasswordRestore Request id: "20003121"
    And Status code response is: "403"
    And Response Body contains "exception" equals "RemotePasswordRestoreForbiddenException"
    And Response Body contains "message" equals "Удаленное восстановление пароля запрещено"

  @k3
  Scenario: CheckRemotePasswordRestore: Учетная запись не найдена
    Then "k3" Send CheckRemotePasswordRestore Request id: "200025541"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"