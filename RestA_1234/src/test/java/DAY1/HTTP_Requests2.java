package DAY1;

import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTP_Requests2 {

	int id;

	@Test(priority = 1)
	public void GetUsers()

	{
		         given() // Use for the pre requests - Headers, etc

				.when() // When is used for the request - Get, post,put , delete
				.get("https://reqres.in/api/users?page=2")

				//.then().statusCode(200).body("page", equalTo(2)).log().all();

				.then().statusCode(200).body("page", equalTo(2)).log().all();
		// Then is to validate the various things , status code , body.
		         //syso

	}

	@Test(priority = 2)
	public void CreateUser() {

		HashMap Data = new HashMap();
		Data.put("name", "Pradeep");
		Data.put("job", "Student");

		id = given().contentType("application/json").body(Data)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");

		// .then().statusCode(201).log().all();

		System.out.println("new Changes done");
	}

	@Test(priority = 3, dependsOnMethods = { "CreateUser" })
	public void UpdateUser() {

		HashMap Data = new HashMap();
		Data.put("name", "Prashant");
		Data.put("job", "Player");

		given().contentType("application/json").body(Data)

				.when().put("https://reqres.in/api/users/" + id)

				.then().statusCode(200).log().all();

	}

	@Test(priority = 4)
	public void DeleteUser()

	{

		given()

				.when().delete("https://reqres.in/api/users/" + id)

				.then().statusCode(204).log().all();

	}

}
