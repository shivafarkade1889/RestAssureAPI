package data.excel;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryAPIDemo {	
	@Test(priority = 1)
	public void addBook() {

		RestAssured.baseURI = "http://216.10.245.166";
		String addBook = given().log().all().header("Content-Type", "application/json").body(
				"{\"name\":\"Learn Appium Automation with Java\",\"isbn\":\"ccvvbbnnmm\",\"aisle\":\"227\",\"author\":\"John foe\"}")
				.when().post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract()
				.asString();

		JsonPath js = new JsonPath(addBook);
		String id = js.get("ID");
		System.out.println(id);
		getBook(id);
		deleteBook(id);
	}

	@Test(priority = 0)
	public void addBookHashMap() {

		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "Learn RestAssured");
		map.put("isbn", "ffgghhjj");
		map.put("aisle", "964");
		map.put("author", "Shiva");

		RestAssured.baseURI = "http://216.10.245.166";
		String addBook = given().log().all().header("Content-Type", "application/json").body(map).when()
				.post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().asString();

		JsonPath js = new JsonPath(addBook);
		String id = js.get("ID");
		System.out.println(id);
		getBook(id);
		deleteBook(id);
	}

	
	@Test(priority = 2)
	public void addBookExcel() throws IOException {
		TestData getData = new TestData();
		ArrayList data = getData.getData("RestAssuredTC1","RestAssuredData");
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", data.get(1));
		map.put("isbn", data.get(2));
		map.put("aisle", data.get(3));
		map.put("author", data.get(4));

		RestAssured.baseURI = "http://216.10.245.166";
		String addBook = given().log().all().header("Content-Type", "application/json").body(map).when()
				.post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().asString();

		JsonPath js = new JsonPath(addBook);
		String id = js.get("ID");
		System.out.println(id);
		getBook(id);
		deleteBook(id);
	}
	
	public static void deleteBook(String id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("ID", id);
		RestAssured.baseURI = "http://216.10.245.166";
		String deleteBook = given().log().all().header("Content-Type", "application/json").body(map).when()
				.delete("/Library/DeleteBook.php").then().log().all().assertThat().statusCode(200).extract().asString();

		JsonPath js = new JsonPath(deleteBook);
		String msg = js.get("msg");
		System.out.println(msg);
	}
	
	public static void getBook(String id) {
		RestAssured.baseURI = "http://216.10.245.166";
		String getBook = given().log().all().queryParam("ID", id)
				.when()
				.get("/Library/GetBook.php").then().log().all().assertThat().statusCode(200).extract().asString();
		JsonPath js = new JsonPath(getBook);
		String msg = js.get("book_name").toString();
		System.out.println(msg);
		String msg1 = js.get("author").toString();
		System.out.println(msg1);
	}
	
}
