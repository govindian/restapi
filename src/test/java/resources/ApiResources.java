package resources;

public enum ApiResources {
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	delPlaceAPI("/maps/api/place/delete/json"),
	get("get"),
	post("post");

	private String resource;
	private String restmethod;
	
	ApiResources(String resource) {
		// TODO Auto-generated constructor stub
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	public String getMethod()
	{
		return restmethod;
	}
	

}
