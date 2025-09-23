import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import com.fasterxml.jackson.core.JsonProcessingException;

public class APITesting {

	public static void main(String[] args) {

		POJOUserRequest userRequest = new POJOUserRequest();
		userRequest.setName("Vivek");
		userRequest.setJob("Software");
		
		baseURI = "https://reqres.in";

		POJOUserResponse UserResponse = 
		given()
		    .contentType(ContentType.JSON).header("x-api-key","reqres-free-v1")
		    .body(userRequest)
		    .log().all()
		.when()
		    .post("/api/users")
		.then()
		    .log().all()
		    .assertThat()
		    .statusCode(201)
		    .extract().as(POJOUserResponse.class);
		
		System.out.println("UserResponse values :: " + UserResponse);
		
		// validation using assertion
//		Assert.assertEquals(UserResponse.getResJob(), "Vivek");
//		Assert.assertEquals(UserResponse.getResName(),"Software");
		
//		Assert.assertTrue(
//				UserResponse.getResName().equals("Vivek") || UserResponse.getResJob().equals("Vivek"),
//			    "Expected Vivek in either name or job field"
//			);
		
	}
}
