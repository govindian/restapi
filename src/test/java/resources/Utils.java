package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;


	public RequestSpecification getRequestSpecification() throws IOException
	{
		
		if(req==null)
		{
				PrintStream log = null;
				try {
						log = new PrintStream(new FileOutputStream("logging.txt"));
					} catch (FileNotFoundException e) 
					{
			// TODO Auto-generated catch block
						e.printStackTrace();
		}
					req= new RequestSpecBuilder().setContentType(ContentType.JSON)
				.setBaseUri(getProperty("baseURL"))
				
				
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		
					return req;
		}
		return req;	
	}
	
	public String getProperty(String key) throws IOException
	
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\govmendi\\eclipse-workspace\\Framework\\src\\test\\java\\resources\\global.properties");
		
		prop.load(fis);
		return prop.getProperty(key);
		
		
	}
	
	public String getJsonPath(Response res,String key)
	{
		
		String resp=res.asString();
			JsonPath jp=new JsonPath(resp);
			String value=jp.get(key).toString();
			return value;
		
		
	}
}
