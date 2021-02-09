@ac
@ack3
Feature: Check Remote Password Restore k3

  @k3
  Scenario: CheckRemotePasswordRestore: Success
    Then Send CheckRemotePasswordRestore Request
      | env | id       | domain |
      | k3  | 18001042 | master |
    And Status code response is: "200"
    And Response Body contains key: "mobilePhone"

  @k3
  Scenario: CheckRemotePasswordRestore: Удаленное восстановление пароля запрещено
    Then Send CheckRemotePasswordRestore Request
      | env | id       | domain |
      | k3  | 20003121 | master |
    And Status code response is: "403"
    And Response Body contains "exception" equals "RemotePasswordRestoreForbiddenException"
    And Response Body contains "message" equals "Удаленное восстановление пароля запрещено"

  @k3
  Scenario: CheckRemotePasswordRestore: Учетная запись не найдена
    Then Send CheckRemotePasswordRestore Request
      | env | id        | domain |
      | k3  | 200025541 | master |
    And Status code response is: "404"
    And Response Body contains "exception" equals "UserNotFoundException"
    And Response Body contains "message" equals "Учетная запись не найдена"

  @k3
  Scenario: CheckRemotePasswordRestore Guest
    Then Send CheckRemotePasswordRestore Request
      | env | id       | domain |
      | k3  | 20063177 | guest  |
    And Status code response is: "200"
    And Response Body contains key: "mobilePhone"