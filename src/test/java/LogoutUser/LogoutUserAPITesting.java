package LogoutUser;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;

// Validation using hamcrest - equalsTo()
// jsonpath validation - done
// deserialized(POJO) class -- already done
// 3 ways to validating response pass body 
// 1. String 
// 2. Serialize
// 3. Jsonpath - Hamcrest

public class LogoutUserAPITesting {

	static void JsonPathValidation(String requestbody, String baseURI, String endpoint) {
// Looks like request is blank

		Response response = 
				given()
					.log().all().baseUri(baseURI).contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(requestbody)
				.when()
					.post(endpoint)
				.then()
					.extract().response();

		JsonPath js = response.jsonPath();

		System.out.println("Pring jsonpath name :: " + js.getString("name"));
	}

	static void HamcrestValidation(String requestbody, String baseURI, String endpoint) {

		given()
			.log().all().baseUri(baseURI).contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(requestbody)
		.when()
			.post(endpoint)
		.then()
			.assertThat().body("name", equalTo("John"));

		System.out.println("Assertion Pass ");
	}

	static void PojoSerializationValidation(String baseURI, String endpoint) {

		LogoutUserPojoRequest userRequest = new LogoutUserPojoRequest();
		//userRequest.setEmail("test@example.com");
		
		LogoutUserPojoResponse userResponse = given().log().all().baseUri(baseURI).contentType(ContentType.JSON)
				.header("x-api-key", "reqres-free-v1").body(userRequest)
				.when()
					.post(endpoint)
				.then()
					//.getBody()
					.extract()
					.as(LogoutUserPojoResponse.class);

		System.out.println("Printing the getMessage response :: " +userResponse.getMessage());
	
		System.out.println("Test cases passed :: ");
	}

	public static void main(String[] args) {
		
		String reqbody = "";

		String base_URI = "http://127.0.0.1:8000";
		String end_point = "/api/logout/";

		// JsonPathValidation(reqbody, base_URI, end_point);
		//HamcrestValidation(reqbody, base_URI, end_point);
		PojoSerializationValidation(base_URI, end_point);

	}
}