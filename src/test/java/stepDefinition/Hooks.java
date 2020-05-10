package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void addPlace() throws IOException
	{
		if(StepDefinition.place_id==null)
		{
			StepDefinition place =new StepDefinition();
			place.add_place_Payload("Govind", "English", "3163 PB");
			place.user_calls_add_with_http_request("AddPlaceAPI", "POST");
			place.verify_place_id_created_maps_to_using("Govind", "getPlaceAPI");
		}
		
		
	}
	

}
