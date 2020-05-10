package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address)
	
	{
		AddPlace p=new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("9812313");
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		p.setName(name);
		List<String> mylist=new ArrayList();
		mylist.add("test1");
		mylist.add("test2");
		p.setTypes(mylist);
		
		return p;
	}
	
	public String getDeletePlacePayload(String place_id)
	{
		
		return "{\r\n" + 
				"\"place_id\" : \""+place_id+"\"\r\n" + 
				"}";
	}

}
