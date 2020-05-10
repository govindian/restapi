package stepDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild testdata=new TestDataBuild();
	static String place_id;
	
	@Given("Add place Payload {string} {string} {string}")
	public void add_place_Payload(String name, String language, String address) throws IOException{
		// Write code here that turns the phrase above into concrete actions
		//RestAssured.baseURI="https://www.rahulshettyacademy.com/";
				
		res=given().spec(getRequestSpecification())
		.body(testdata.addPlacePayload(name,language,address));		
	  //  throw new io.cucumber.java.PendingException();
	}

	@When("user calls add {string} with {string} http request")
	public void user_calls_add_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions
		ApiResources resourceAPI=ApiResources.valueOf(resource);
	//	ApiResources restcall=ApiResources.valueOf(method);
		
		 resspec=new ResponseSpecBuilder().expectStatusCode(200)
					.expectContentType(ContentType.JSON)
					.build();
		
				 if(method.equalsIgnoreCase("POST"))
					 		 
					 response=res.when().post(resourceAPI.getResource());
					 else if (method.equalsIgnoreCase("GET"))
					 response=res.when().get(resourceAPI.getResource());
				
			//	.then().extract().response();
				// Write code here that turns the phrase above into concrete actions
	  //  throw new io.cucumber.java.PendingException();
	}

	@Then("API call is success and response code is {int}")
	public void api_call_is_success_and_response_code_is(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	    
	  //  throw new io.cucumber.java.PendingException();
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is_ok(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	//	String resp=response.asString();
	//	JsonPath jp=new JsonPath(resp);
		assertEquals(getJsonPath(response,keyValue),Expectedvalue);
		

	 //   throw new io.cucumber.java.PendingException();
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id=getJsonPath(response,"place_id");
		res=given().spec(getRequestSpecification()).queryParam("place_id", place_id);
		user_calls_add_with_http_request(resource,"GET");
		String actualname=getJsonPath(response,"name");
		assertEquals(expectedname,actualname);
		
	   // throw new io.cucumber.java.PendingException();
	}
	
	@Given("Delete place api")
	public void delete_place_api() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res=given().spec(getRequestSpecification()).body(testdata.getDeletePlacePayload(place_id));
	  //  throw new io.cucumber.java.PendingException();
	}



}
