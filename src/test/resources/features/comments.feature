Feature: It has scenarios for endpoints related to comments
@positive @comments
  Scenario: Verify get comments response
    Given I have a valid end point for comments
    When I do a get request on comments end point
    Then I should see a 200 status code in comments endpoint response
    And I should see a valid response body in comments endpoint response

  @positive @comments
  Scenario: Verify get one specific comment response
    Given I have a valid end point for comments
    When I do a get request for comment id "1" on comments end point
    Then I should see a 200 status code in comments endpoint response
    And I should see a valid response body with valid comment id "1"

  @negative @comments
  Scenario: Verify get request with invalid comment response
    Given I have a valid end point for comments
    When I do a get request for comment id "1000" on comments end point
    Then I should see a 404 status code in comments endpoint response
    And I should see a valid response body with empty response in comments endpoint response

