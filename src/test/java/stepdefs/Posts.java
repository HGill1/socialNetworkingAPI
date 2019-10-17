package stepdefs;

import TestObjects.Post;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class Posts extends BaseSteps {
    private static Response response;
    private static Post post;

    @Before("@createPost")
    public void setPostData(){
        post = new Post();
        post.setUserId(1L);
        post.setBody("This is test body");
        post.setTitle("This is test title");
    }

    @After("@createPost")
    public void discardPostObj(){
        post = null;
    }

    @Given("^I have a valid end point for post$")
    public void iHaveAValidEndPointForPost() {
        ENDPOINT = "posts";
    }

    @When("^I do a post request on posts end point with valid post data$")
    public void iDoAPostRequestOnPostsEndPointWithValidPostData() {

            response = given()
                    .when()
                    .body(post)
                    .post(GET_SN_URL+ENDPOINT);
    }

    @When("^I do a get request on post end point$")
    public void iDoAGetRequestOnPostEndPoint() {
        response = given().when().get(GET_SN_URL+ENDPOINT);
    }

    @Then("^I should see a (\\d+) status code in post endpoint response$")
    public void iShouldSeeAStatusCodeInPostEndpointResponse(int statusCode) {
        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @And("^I should see a valid response body in post endpoint response$")
    public void iShouldSeeAValidResponseBodyInPostEndpointResponse() {
        SoftAssertions.assertSoftly(softly->{
            softly.assertThat(response.getBody().jsonPath().getList("$").size()).isGreaterThan(1);
            commonAssertions(softly);
        });
    }

    @When("^I do a get request for post id \"([^\"]*)\" on post end point$")
    public void iDoAGetRequestForPostIdOnPostEndPoint(String postId) throws Throwable {
        response = given().when().get(GET_SN_URL+ENDPOINT+"/"+postId);
    }

    @And("^I should see a valid response body with valid post id \"([^\"]*)\"$")
    public void iShouldSeeAValidResponseBodyWithValidPostId(String postId) throws Throwable {
        SoftAssertions.assertSoftly(softly->{
            softly.assertThat(response.getBody().jsonPath().get("id").toString()).isEqualTo(postId);
            commonAssertions(softly);
        });
    }

    @And("^I should see a valid response body with empty response in post endpoint response$")
    public void iShouldSeeAValidResponseBodyWithEmptyResponseInPostEndpointResponse() {
        assertThat(response.getBody().asString()).isEqualTo("{}");
    }

    @And("^I should see a valid post been created in response body$")
    public void iShouldSeeAValidPostBeenCreatedInResponseBody() {
        int postId = response.getBody().jsonPath().get("id");

        // Response is returning Id only so only Id can be tested.
        assertThat(postId).isGreaterThan(1);
       /* SoftAssertions.assertSoftly(softly->{
            softly.assertThat(response.getBody().jsonPath().get("userId").toString()).isEqualTo(post.getUserId());
            softly.assertThat(response.getBody().jsonPath().get("body").toString()).isEqualTo(post.getBody());
            softly.assertThat(response.getBody().jsonPath().get("title").toString()).isEqualTo(post.getTitle());
        });*/
    }

    private void commonAssertions(SoftAssertions softly) {
        softly.assertThat(response.getBody().asString()).contains("userId");
        softly.assertThat(response.getBody().asString()).contains("id");
        softly.assertThat(response.getBody().asString()).contains("title");
        softly.assertThat(response.getBody().asString()).contains("body");
    }
}
