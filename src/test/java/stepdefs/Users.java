package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class Users extends BaseSteps {

    private static Response response;

    @Given("^I have a valid end point for users$")
    public void iHaveAValidEndPointForUsers() {
        ENDPOINT="users";
    }

    @When("^I do a get request on users end point$")
    public void iDoAGetRequestOnUsersEndPoint() {
        response = given().when().get(GET_SN_URL+ENDPOINT);
    }

    @Then("^I should see a (\\d+) status code in users endpoint response$")
    public void iShouldSeeAStatusCodeInUsersEndpointResponse(int statusCode) {
        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @And("^I should see a valid response body in users endpoint response$")
    public void iShouldSeeAValidResponseBodyInUsersEndpointResponse() {
        SoftAssertions.assertSoftly(softly->{
            softly.assertThat(response.getBody().jsonPath().getList("$").size()).isGreaterThan(1);
            commonAssertions(softly);
        });
    }

    @When("^I do a get request for user id \"([^\"]*)\" on users end point$")
    public void iDoAGetRequestForUserIdOnUsersEndPoint(String userId) throws Throwable {
        response = given().when().get(GET_SN_URL+ENDPOINT+"/"+userId);
    }

    @And("^I should see a valid response body with valid user id \"([^\"]*)\"$")
    public void iShouldSeeAValidResponseBodyWithValidUserId(String userId) throws Throwable {
        SoftAssertions.assertSoftly(softly->{
            softly.assertThat(response.getBody().jsonPath().get("id").toString()).isEqualTo(userId);
            commonAssertions(softly);
        });
    }

    @And("^I should see a valid response body with empty response in users endpoint response$")
    public void iShouldSeeAValidResponseBodyWithEmptyResponseInUsersEndpointResponse() {
        assertThat(response.getBody().asString()).isEqualTo("{}");
    }

    private void commonAssertions(SoftAssertions softly) {
        softly.assertThat(response.getBody().asString()).contains("id");
        softly.assertThat(response.getBody().asString()).contains("name");
        softly.assertThat(response.getBody().asString()).contains("username");
        softly.assertThat(response.getBody().asString()).contains("email");
        softly.assertThat(response.getBody().asString()).contains("address");
        softly.assertThat(response.getBody().asString()).contains("phone");
        softly.assertThat(response.getBody().asString()).contains("website");
        softly.assertThat(response.getBody().asString()).contains("company");
    }
}
