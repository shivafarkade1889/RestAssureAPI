package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
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
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinations extends Utils {

	public ResponseSpecification resp;
	public RequestSpecification resGiven;
	public Response responce;
	static String place_ID;

	TestDataBuild testData = new TestDataBuild();

	@Given("^Add place payload \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_payload_something_something_something(String name, String language, String address)
			throws Throwable {
		resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		resGiven = given().spec(getRequestSpecification()).log().all()
				.body(testData.addPlacePayLoadDynamic(name, language, address));
	}

	@Given("^Add place payload$")
	public void add_place_payload() throws IOException {
		resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		resGiven = given().spec(getRequestSpecification()).log().all().body(testData.addPlacePayLoad());
	}

	@When("^User calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_something_with_something_http_request(String resource, String method) {

		APIResources res = APIResources.valueOf(resource);

		if (method.equalsIgnoreCase("POST")) {
			responce = resGiven.when().post(res.getResource()).then().spec(resp).extract().response();
		} else if (method.equalsIgnoreCase("GET")) {
			responce = resGiven.when().get(res.getResource()).then().spec(resp).extract().response();
		} else if (method.equalsIgnoreCase("DELETE")) {
			responce = resGiven.when().delete(res.getResource()).then().spec(resp).extract().response();
		}
	}

	@Then("^The API call success with status code 200$")
	public void the_api_call_success_with_status_code_200() {
		assertEquals(responce.getStatusCode(), 200);
	}

	@And("^\"([^\"]*)\" in responce body is \"([^\"]*)\"$")
	public void something_in_responce_body_is_something(String keyValue, String Expectedvalue) {
		assertEquals(getResponseKeyValue(responce, keyValue), Expectedvalue);
	}
	
	@And("^Verify place_ID created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verify_placeid_created_maps_to_something_using_something(String expectedName, String resource) throws IOException{
       place_ID = getResponseKeyValue(responce,"place_id");
       resGiven = given().spec(getRequestSpecification()).queryParam("place_id", place_ID);
       user_calls_something_with_something_http_request(resource,"GET");
       String name = getResponseKeyValue(responce,"name");
       assertEquals(name,expectedName);
    }
	
	
	@Given("^Deleteplace payload$")
    public void deleteplace_payload() throws IOException{
		resGiven = given().spec(getRequestSpecification()).body(testData.getDeletePayload(place_ID));
    }

}
