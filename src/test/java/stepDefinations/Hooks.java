package stepDefinations;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    //Run this precondition before a scenario
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //Write a code that give you place id
        //execute this code only when place_id null
        StepDefination stepDefination = new StepDefination();
        if (StepDefination.place_id == null) {
            stepDefination.add_place_payload_with("Shetty", "French", "Asia");
            stepDefination.user_calls_with_http_request("AddPlaceAPI", "POST");
            stepDefination.verifyPlace_IdCreatedMapsToUsing("Shetty", "getPlaceAPI");
        }
    }
}
