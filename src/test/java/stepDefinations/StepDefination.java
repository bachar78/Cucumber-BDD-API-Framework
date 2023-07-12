package stepDefinations;

import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import pojo.Types;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class StepDefination {
    RequestSpecification resp;
    ResponseSpecification resSpec;
    Response response;
    @Given("Add Place Payload")
    public void add_place_payload() {
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        Types types = new Types(myList);
        Location location = new Location(-38.383494, 33.427362);
        AddPlace p = new AddPlace(50, "Frontline house", "(+91) 983 893 3937", "29, side layout, cohen 09", "https://rahulshettyacademy.com", "French-IN", location, types);


        RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                                       .setContentType(ContentType.JSON).build();
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        resp = given().spec(reqSpec).body(p);

    }

    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        response = resp.when().post("maps/api/place/add/json").then().spec(resSpec).extract().response();
    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        assertEquals(response.statusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
       String res = response.asString();
        JsonPath js = new JsonPath(res);
        assertEquals(js.get(key).toString(), value);
    }
}
