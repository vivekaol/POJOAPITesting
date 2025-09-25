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

	static void JsonPathValidation(String requestbody, String baseURI) {

		Response response = 
				given()
					.log().all().contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(requestbody)
				.when()
					.post("/api/forgot-password/")
				.then()
					.extract().response();

		JsonPath js = response.jsonPath();

		System.out.println("Pring jsonpath name :: " + js.getString("name"));
	}

	static void HamcrestValidation(String requestbody, String baseURI) {

		given()
			.log().all().contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(requestbody)
		.when()
			.post("/api/forgot-password/")
		.then()
			.assertThat().body("name", equalTo("John"));

		System.out.println("Assertion Pass ");
	}

	static void PojoSerializationValidation(String baseURI) {

		ForgotPasswordPojoRequest userRequest = new ForgotPasswordPojoRequest();
		userRequest.setEmail("test@example.com");
		
		baseURI = "http://127.0.0.1:8000";

		ForgotPasswordPojoResponse userResponse = given().log().all().contentType(ContentType.JSON)
				.header("x-api-key", "reqres-free-v1").body(userRequest)
				.when()
					.post("/api/forgot-password/")
				.then()
					//.getBody()
					.extract()
					.as(ForgotPasswordPojoResponse.class);

		System.out.println("Printing the getMessage response :: " +userResponse.getMessage());


		System.out.println("Test cases passed :: ");
	}

	public static void main(String[] args) {

		String reqbody = "{\r\n"
				+ "  \"email\": \"test@example.com\"\r\n"
				+ "}";

		String base_URI = "http://127.0.0.1:8000";
		
		// JsonPathValidation(reqbody, base_URI);
		//HamcrestValidation(reqbody, base_URI);
		PojoSerializationValidation(base_URI);

	}
}