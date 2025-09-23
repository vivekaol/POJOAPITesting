package RefreshToken;

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

public class RefreshTokenAPITesting {

	static void JsonPathValidation() {

		String requestbody = "{\r\n"
				+ "  \"refresh_token\": \"<refresh_token>\"\r\n"
				+ "}";

		baseURI = "http://127.0.0.1:8000/api/token/refresh/";

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
				+ "  \"refresh_token\": \"<refresh_token>\"\r\n"
				+ "}";

		baseURI = "http://127.0.0.1:8000/api/token/refresh/";

		given()
			.log().all().contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(requestbody)
		.when()
			.post("/api/users")
		.then()
			.assertThat().body("name", equalTo("John"));

		System.out.println("Assertion Pass ");
	}

	static void PojoSerializationValidation() {

		RefreshTokenPojoRequest userRequest = new RefreshTokenPojoRequest();
		userRequest.setRefresh_token("test@example.com");
		
		baseURI = "http://127.0.0.1:8000/api/token/refresh/";

		RefreshTokenPojoResponse userResponse = given().log().all().contentType(ContentType.JSON)
				.header("x-api-key", "reqres-free-v1").body(userRequest)
				.when()
					.post("/api/users")
				.then()
					//.getBody()
					.extract()
					.as(RefreshTokenPojoResponse.class);

		System.out.println("Printing the getAccess_token response :: " +userResponse.getAccess_token());
		System.out.println("Printing the getRefresh_token response :: " +userResponse.getRefresh_token());

		System.out.println("Test cases passed :: ");
	}

	public static void main(String[] args) {

		// JsonPathValidation();
		//HamcrestValidation();
		PojoSerializationValidation();

	}
}