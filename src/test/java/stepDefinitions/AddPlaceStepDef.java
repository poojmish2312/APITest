package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class AddPlaceStepDef extends Utils{
	ResponseSpecification resSpec;
	RequestSpecification res;
	Response response;
	static String place_id;
	TestDataBuild tdb = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws FileNotFoundException {
		res=given().spec(requestSpec())
				.body(tdb.addPlacePayload(name,language,address));
	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource,String httpReq) {
		//constructor will be called with value of resource which you pass
		APIResources resou=APIResources.valueOf(resource);
		resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(httpReq.equals("POST"))
			response=res.when().post(resou.getResource()).then().spec(resSpec).extract().response();
		else if(httpReq.equals("GET"))
			response=res.when().get(resou.getResource());
				
		
	}
	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    
	    assertEquals(getJsonPath(response,key),value);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String string, String string2) throws FileNotFoundException {
		place_id=getJsonPath(response, "place_id");
		res=given().spec(requestSpec()).queryParam("place_id", place_id);
		user_calls_with_post_http_request(string2,"GET");
		String name=getJsonPath(response, "name");
		assertEquals(name, string);
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws FileNotFoundException {
	    res=given().spec(requestSpec())
	    .body(tdb.deletePlacePayload(place_id));
	}
}
