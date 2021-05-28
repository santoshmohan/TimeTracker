#Author: santosh.mohan@hotmail.com
Feature: Acceptance Criteria
  As a user, I want update exisiting user details and password
  and check verify new details are valid

  @registerUser
  Scenario: Update user Password
    Given I login to the timetracker as an existing user
    And I update the Password of the user
    And I logout from the timetracker
    When I login with the updated timetracker user details
    Then I am able to login successfully
  
  @registerUser
  Scenario: Update user Login
    Given I login to the timetracker as an existing user
    And I update the Login of the user
    And I logout from the timetracker
    When I login with the updated timetracker user details
    Then I am able to login successfully
