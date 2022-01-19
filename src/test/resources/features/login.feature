Feature: Login

Scenario: Login
  Given I open browser
  And I maximize browser
  And I open "pipeDrive" page
  And I wait for 2 seconds
  And I click "Login"
  And I fill:
    | email | selengonen02@gmail.com |
    | password | i!H3dPVRWqyXvR4     |
  And I click "loginButton"
  Then I see "menuBar"

