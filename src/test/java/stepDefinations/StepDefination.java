package stepDefinations;
import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;


import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class StepDefination extends Utils {
    RequestSpecification resp;
    ResponseSpecification resspec;
    Response response;
    String place_id;
    JsonPath js;
    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        resp = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        //constructor will be called with value of resource which you pass
        APIResources resourceAPI = APIResources.valueOf(resource);
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if (method.equalsIgnoreCase("POST"))
            response = resp.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = resp.when().get(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("DELETE"))
            response = resp.when().delete(resourceAPI.getResource());
        response.then().spec(resspec).extract().response();
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        assertEquals(response.statusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        assertEquals(getJsonPath(response, key), value);
    }

    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String name, String resource) throws IOException {
        APIResources resourceAPI = APIResources.valueOf(resource);
        place_id = getJsonPath(response, "place_id");
        resp = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");
        String getName = getJsonPath(response, "name");
        assertEquals(name, getName);
    }
}
