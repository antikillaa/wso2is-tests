Feature: DeviceToken RealAC

  Scenario: deviceToken: Success 1
    Then Send DeviceToken Request id: "0000900100000000223400"
    And Status code response is: "200"
    And Response Body contains "userId" equals "123456"
    And Response Body contains "domain" equals "master"
    And Response Body contains "deviceTokenId" equals "0000900100000000223400"

  Scenario: GetSmsOtp: Устройство двухфакторной аутентификации не найдено
    Then Send DeviceToken Request id: "11111"
    And Status code response is: "404"
    And Response Body contains "exception" equals "DeviceNotFoundException"
    And Response Body contains "message" equals "Устройство двухфакторной аутентификации не найдено"
