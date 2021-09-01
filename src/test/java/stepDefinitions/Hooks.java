package stepDefinitions;

import java.io.FileNotFoundException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws FileNotFoundException
	{
		AddPlaceStepDef aps = new AddPlaceStepDef();
		if(AddPlaceStepDef.place_id==null) {
		aps.add_place_payload_with("thirdname","French","some road");
		aps.user_calls_with_post_http_request("AddPlaceAPI","POST");
		aps.verify_place_id_created_maps_to_using("thirdname", "GetPlaceAPI");
		}
	}

}
