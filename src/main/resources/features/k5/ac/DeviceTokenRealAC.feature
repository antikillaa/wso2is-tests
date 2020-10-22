Feature: DeviceToken k5

  @skip
  Scenario: deviceToken: Success 1
    Then "k5" Send DeviceToken Request id: "8888000000065345"
    And Status code response is: "200"
    And Response Body contains "userId" equals "20002034"
    And Response Body contains "domain" equals "master"
    And Response Body contains "deviceTokenId" equals "8888000000065345"

  @k5
  Scenario: GetSmsOtp: Устройство двухфакторной аутентификации не найдено
    Then "k5" Send DeviceToken Request id: "11111"
    And Status code response is: "404"
    And Response Body contains "exception" equals "DeviceNotFoundException"
    And Response Body contains "message" equals "Устройство двухфакторной аутентификации не найдено"
