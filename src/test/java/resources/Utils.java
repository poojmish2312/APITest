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
	public RequestSpecification requestSpec() throws IOException
	{
		if(req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().setBaseUri(getConfigValue("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		}
		return req;
	}
	
	public String getJsonPath(Response response,String key)
	{
		String result=response.asString();
	    JsonPath jp = new JsonPath(result);
	    return jp.get(key).toString();
	}
	
	public static String getConfigValue(String key) throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream("D:\\EclipseWorkspace\\CucumberProject1\\src\\test\\java\\resources\\config.properties");
		prop.load(fis);
		return prop.getProperty(key);
	
		
		
	}

}
