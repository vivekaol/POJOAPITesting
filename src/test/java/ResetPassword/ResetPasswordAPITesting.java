package ResetPassword;

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

public class ResetPasswordAPITesting {

	static void JsonPathValidation(String requestbody, String baseURI, String endpoint) {

		Response response = given().log().all().baseUri(baseURI).contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1")
				.body(requestbody).when().post(endpoint).then().extract().response();

		JsonPath js = response.jsonPath();

		System.out.println("Pring jsonpath name :: " + js.getString("name"));
	}

	static void HamcrestValidation(String requestbody, String baseURI, String endpoint) {

		given().log().all().baseUri(baseURI).contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(requestbody).when()
				.post(endpoint).then().assertThat().body("name", equalTo("John"));

		System.out.println("Assertion Pass ");
	}

	static void PojoSerializationValidation(String baseURI, String endpoint) {

		ResetPasswordPojoRequest userRequest = new ResetPasswordPojoRequest();
		userRequest.setToken("test@example.com");
		userRequest.setNew_password("password123");

		ResetPasswordPojoResponse userResponse = given().log().all().baseUri(baseURI).contentType(ContentType.JSON)
				.header("x-api-key", "reqres-free-v1").body(userRequest).when().post(endpoint).then()
				// .getBody()
				.extract().as(ResetPasswordPojoResponse.class);

		System.out.println("Printing the getMessage response :: " + userResponse.getMessage());

		System.out.println("Test cases passed :: ");
	}

	public static void main(String[] args) {

		String reqbody = "{\r\n" + "  \"token\": \"{{refresh_token}}\",\r\n"
				+ "  \"new_password\": \"newpassword123\"\r\n" + "}";

		String base_URI = "https://52984699b31f.ngrok-free.app";
		String end_point = "/api/reset-password/";

		// JsonPathValidation(reqbody, base_URI, end_point);
		// HamcrestValidation(reqbody, base_URI, end_point);
		PojoSerializationValidation(base_URI, end_point);

	}
}