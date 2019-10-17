Feature: It has scenarios for endpoints related to users
  @positive @users
  Scenario: Verify get users response
    Given I have a valid end point for users
    When I do a get request on users end point
    Then I should see a 200 status code in users endpoint response
    And I should see a valid response body in users endpoint response

  @positive @users
  Scenario: Verify get one specific user response
    Given I have a valid end point for users
    When I do a get request for user id "1" on users end point
    Then I should see a 200 status code in users endpoint response
    And I should see a valid response body with valid user id "1"

  @negative @users
  Scenario: Verify get request with invalid user response
    Given I have a valid end point for users
    When I do a get request for user id "1000" on users end point
    Then I should see a 404 status code in users endpoint response
    And I should see a valid response body with empty response in users endpoint response