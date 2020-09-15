Feature: Check Remote Password Restore Real AC

  Scenario: CheckRemotePasswordRestore: Success
    Then Send CheckRemotePasswordRestore Request id: "11000035"
    And Status code response is: "200"
    And Response Body contains "mobilePhone" equals "9252918595"

  Scenario: CheckRemotePasswordRestore: Удаленное восстановление пароля запрещено
    Then Send CheckRemotePasswordRestore Request id: "123456"
    And Status code response is: "403"
    And Response Body contains "exception" equals "RemotePasswordRestoreForbiddenException"
    And Response Body contains "message" equals "Удаленное восстановление пароля запрещено"

  Scenario: CheckRemotePasswordRestore: Учетная запись не найдена
    Then Send CheckRemotePasswordRestore Request id: "1234561"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"