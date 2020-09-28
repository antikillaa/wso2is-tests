Feature: Check Remote Password Restore Real AC

  @k3
  Scenario: CheckRemotePasswordRestore: Success
    Then Send CheckRemotePasswordRestore Request id: "20002554"
    And Status code response is: "200"
    And Response Body contains "mobilePhone" equals "9802404578"

  @k3
  Scenario: CheckRemotePasswordRestore: Удаленное восстановление пароля запрещено
    Then Send CheckRemotePasswordRestore Request id: "20003121"
    And Status code response is: "403"
    And Response Body contains "exception" equals "RemotePasswordRestoreForbiddenException"
    And Response Body contains "message" equals "Удаленное восстановление пароля запрещено"

  @k3
  Scenario: CheckRemotePasswordRestore: Учетная запись не найдена
    Then Send CheckRemotePasswordRestore Request id: "200025541"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"