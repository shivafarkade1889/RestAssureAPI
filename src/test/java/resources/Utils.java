package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification res;
	public RequestSpecification getRequestSpecification() throws IOException {
		
		if(res == null) {
		
		RestAssured.baseURI = getPropertyValues("baseURl");
		PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
		res = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return res;
		}
		return res;
	}
	
	
	
	public static String getPropertyValues(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Shiva\\Desktop\\RestAssuredAPI_Java_Workspace\\RestAPIFrameWork\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	
	public String getResponseKeyValue(Response responce, String key) {
		String reps1 = responce.asString();
		JsonPath js = new JsonPath(reps1);
		return js.get(key).toString();
	}
}
