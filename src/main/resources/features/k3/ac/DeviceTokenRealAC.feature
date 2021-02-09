@ac
@ack3
Feature: DeviceToken k3

  @k3
  Scenario: deviceToken: Success 1
    Then "k3" Send DeviceToken Request id: "8888000000076094"
    And Status code response is: "200"
    And Response Body contains "userId" equals "20041320"
    And Response Body contains "domain" equals "master"
    And Response Body contains "deviceTokenId" equals "8888000000076094"

  @k3
  Scenario: GetSmsOtp: Устройство двухфакторной аутентификации не найдено
    Then "k3" Send DeviceToken Request id: "11111"
    And Status code response is: "404"
    And Response Body contains "exception" equals "DeviceNotFoundException"
    And Response Body contains "message" equals "Устройство двухфакторной аутентификации не найдено"
