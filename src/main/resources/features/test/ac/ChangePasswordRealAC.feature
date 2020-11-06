Feature: Change password Real AC

  @test
  Scenario: Change Password Success
    Then "test" Send Change Password Success Request, id: "123456" password: "P@ssw0rd"
    And Status code response is: "204"


  @test
  Scenario: Change Password Wrong Password
    Then "test" Send Change Password Success Request, id: "123456" password: "P@ssw0rd123123213"
    And Status code response is: "403"
