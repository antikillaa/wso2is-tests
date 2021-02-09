@auth
@authk3
Feature: Grant type Login Stub

  @wip
  Scenario: Grant type login_mb No Device Finger Print
    Then Send login by Grant type Request no parameter
      | grandType | id_type | id       | scope |  env | finger_print | code |
      | login_mb  | login   | 20002571 | true  |  k3  | no           | 500  |
      | login     | login   | 20002571 | true  |  k3  | no           | 500  |






