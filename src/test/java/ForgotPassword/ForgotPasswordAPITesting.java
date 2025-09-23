package ForgotPassword;

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

public class ForgotPasswordAPITesting {

	static void JsonPathValidation() {

		String requestbody = "{\r\n"
				+ "  \"email\": \"test@example.com\"\r\n"
				+ "}";

		baseURI = "http://127.0.0.1:8000/api/forgot-password/";

		Response response = 
				given()
					.log().all().contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(requestbody)
				.when()
					.post("/api/users")
				.then()
					.extract().response();

		JsonPath js = response.jsonPath();

		System.out.println("Pring jsonpath name :: " + js.getString("name"));
	}

	static void HamcrestValidation() {

		String requestbody = "{\r\n"
				+ "  \"email\": \"test@example.com\"\r\n"
				+ "}";

		baseURI = "http://127.0.0.1:8000/api/forgot-password/";

		given()
			.log().all().contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(requestbody)
		.when()
			.post("/api/users")
		.then()
			.assertThat().body("name", equalTo("John"));

		System.out.println("Assertion Pass ");
	}

	static void PojoSerializationValidation() {

		ForgotPasswordPojoRequest userRequest = new ForgotPasswordPojoRequest();
		userRequest.setEmail("test@example.com");
		
		baseURI = "http://127.0.0.1:8000/api/forgot-password/";

		ForgotPasswordPojoResponse userResponse = given().log().all().contentType(ContentType.JSON)
				.header("x-api-key", "reqres-free-v1").body(userRequest)
				.when()
					.post("/api/users")
				.then()
					//.getBody()
					.extract()
					.as(ForgotPasswordPojoResponse.class);

		System.out.println("Printing the getMessage response :: " +userResponse.getMessage());


		System.out.println("Test cases passed :: ");
	}

	public static void main(String[] args) {

		// JsonPathValidation();
		//HamcrestValidation();
		PojoSerializationValidation();

	}
}