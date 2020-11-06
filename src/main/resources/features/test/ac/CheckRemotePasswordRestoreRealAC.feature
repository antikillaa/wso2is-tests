Feature: Check Remote Password Restore Real AC

  @test
  Scenario: CheckRemotePasswordRestore: Success
    Then "test" Send CheckRemotePasswordRestore Request id: "11000035"
    And Status code response is: "200"
    And Response Body contains "mobilePhone" equals "9252918595"

  @test
  Scenario: CheckRemotePasswordRestore: Удаленное восстановление пароля запрещено
    Then "test" Send CheckRemotePasswordRestore Request id: "123456"
    And Status code response is: "403"
    And Response Body contains "exception" equals "RemotePasswordRestoreForbiddenException"
    And Response Body contains "message" equals "Удаленное восстановление пароля запрещено"

  @test
  Scenario: CheckRemotePasswordRestore: Учетная запись не найдена
    Then "test" Send CheckRemotePasswordRestore Request id: "1234561"
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"