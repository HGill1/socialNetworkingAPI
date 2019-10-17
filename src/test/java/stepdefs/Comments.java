package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class Comments extends BaseSteps {

    private static Response response;

    @Given("^I have a valid end point for comments$")
    public void iHaveAValidEndPointForComments() {
        ENDPOINT="comments";
    }

    @When("^I do a get request on comments end point$")
    public void iDoAGetRequestOnCommentsEndPoint() {
       response = given().when().get(GET_SN_URL+ENDPOINT);
    }

    @Then("^I should see a (\\d+) status code in comments endpoint response$")
    public void iShouldSeeAStatusCodeInCommentsEndpointResponse(int statusCode) {
        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @Then("^I should see a valid response body in comments endpoint response$")
    public void i_should_see_a_valid_response_body_in_comments_endpoint_response() throws Throwable {
       SoftAssertions.assertSoftly(softly->{
            softly.assertThat(response.getBody().jsonPath().getList("$").size()).isGreaterThan(1);
           commonAssertions(softly);
       });
    }

    @When("^I do a get request for comment id \"([^\"]*)\" on comments end point$")
    public void iDoAGetRequestForCommentIdOnCommentsEndPoint(String commentId) throws Throwable {
        response = given().when().get(GET_SN_URL+ENDPOINT+"/"+commentId);
    }

    @And("^I should see a valid response body with empty response in comments endpoint response$")
    public void iShouldSeeAValidResponseBodyWithEmptyResponseInCommentsEndpointResponse() {
        assertThat(response.getBody().asString()).isEqualTo("{}");
    }

    @And("^I should see a valid response body with valid comment id \"([^\"]*)\"$")
    public void iShouldSeeAValidResponseBodyWithCommentAndValidCommentId(String commentId) throws Throwable {
        SoftAssertions.assertSoftly(softly->{
            softly.assertThat(response.getBody().jsonPath().get("id").toString()).isEqualTo(commentId);
            commonAssertions(softly);
        });
    }

    private void commonAssertions(SoftAssertions softly) {
        softly.assertThat(response.getBody().asString()).contains("postId");
        softly.assertThat(response.getBody().asString()).contains("id");
        softly.assertThat(response.getBody().asString()).contains("name");
        softly.assertThat(response.getBody().asString()).contains("email");
    }
}
