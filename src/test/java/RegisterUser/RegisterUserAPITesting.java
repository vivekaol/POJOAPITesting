package RegisterUser;

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

public class RegisterUserAPITesting {

	static void JsonPathValidation(String requestbody, String baseURI, String endpoint) {

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

		RegisterUserPojoRequest userRequest = new RegisterUserPojoRequest();
		userRequest.setEmail("test@example.com");
		userRequest.setPassword("password123");
		userRequest.setFull_name("John");
		userRequest.setLast_name("Doe");
		userRequest.setRole("Admin");

		RegisterUserPojoResponse userResponse = given().log().all().baseUri(baseURI).contentType(ContentType.JSON)
				.header("x-api-key", "reqres-free-v1").body(userRequest)
				.when()
					.post(endpoint)
				.then()
					//.getBody()
					.extract()
					.as(RegisterUserPojoResponse.class);

		System.out.println("Printing the getMessage response :: " +userResponse.getMessage());
		System.out.println("Printing the getUser_id response :: " +userResponse.getUser_id());
		System.out.println("Printing the getEmail response :: " +userResponse.getEmail());
		System.out.println("Printing the getAccess_token response :: " +userResponse.getAccess_token());
		System.out.println("Printing the getRefresh_token response :: " +userResponse.getRefresh_token());

		System.out.println("Test cases passed :: ");
	}

	public static void main(String[] args) {

		String requestbody = "{\r\n"
				+ "  \"email\": \"test@example.com\",\r\n"
				+ "  \"password\": \"password123\",\r\n"
				+ "  \"full_name\": \"John\",\r\n"
				+ "  \"last_name\": \"Doe\",\r\n"
				+ "  \"role\": \"Admin\"\r\n"
				+ "}";

		String base_URI = "http://127.0.0.1:8000";
		String end_piont = "/api/register/";
		
		
		// JsonPathValidation(reqbody, base_URI, end_piont);
		//HamcrestValidation(reqbody, base_URI, end_piont);
		PojoSerializationValidation(base_URI, end_piont);

	}
}