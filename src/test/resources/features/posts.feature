Feature: It has scenarios for endpoints related to post
  @positive @posts
  Scenario: Verify get post response
    Given I have a valid end point for post
    When I do a get request on post end point
    Then I should see a 200 status code in post endpoint response
    And I should see a valid response body in post endpoint response

  @positive @posts
  Scenario: Verify get one specific post response
    Given I have a valid end point for post
    When I do a get request for post id "1" on post end point
    Then I should see a 200 status code in post endpoint response
    And I should see a valid response body with valid post id "1"

  @negative @posts
  Scenario: Verify get invalid id post response
    Given I have a valid end point for post
    When I do a get request for post id "1000" on post end point
    Then I should see a 404 status code in post endpoint response
    And I should see a valid response body with empty response in post endpoint response

  @positive @posts @createPost
  Scenario: Verify post can be created using post request
    Given I have a valid end point for post
    When I do a post request on posts end point with valid post data
    Then I should see a 201 status code in post endpoint response
    And I should see a valid post been created in response body