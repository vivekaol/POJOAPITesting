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

public class APITesting {

	static void JsonPathValidation() {

		String requestbody = "{\r\n" + "    \"name\": \"kumar\",\r\n" + "    \"job\": \"leader\"\r\n" + "}";

		baseURI = "https://reqres.in";

		Response response = given().log().all().contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1")
				.body(requestbody)

				.when().post("/api/users").then().extract().response();

		JsonPath js = response.jsonPath();

		System.out.println("Pring jsonpath name :: " + js.getString("name"));
	}

	static void HamcrestValidation() {

		String requestbody = "{\r\n" + "    \"name\": \"kumar\",\r\n" + "    \"job\": \"leader\"\r\n" + "}";

		baseURI = "https://reqres.in";

		given()
			.log().all().contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1").body(requestbody)
		.when()
			.post("/api/users")
		.then()
			.assertThat().body("name", equalTo("kumar"));

		System.out.println("Assertion Pass ");
	}

	static void PojoSerializationValidation() {

		POJOUserRequest userRequest = new POJOUserRequest();
		userRequest.setName("Vivek");
		userRequest.setJob("Software");

		baseURI = "https://reqres.in";

		POJOUserResponse UserResponse = given().log().all().contentType(ContentType.JSON)
				.header("x-api-key", "reqres-free-v1").body(userRequest)
				.when()
					.post("/api/users")
				.then().getBody().as(POJOUserResponse.class);

		System.out.println("Printing the usernaem rspone :: " + UserResponse.getResName());
		System.out.println("Printing the job rspone :: " + UserResponse.getResJob());
		System.out.println("Printing the ID rspone :: " + UserResponse.getResId());
		System.out.println("Printing the createdAt rspone :: " + UserResponse.getResCreatedAt());

		System.out.println("Test cases passed :: ");
	}

	public static void main(String[] args) {

		// JsonPathValidation();
		//HamcrestValidation();
		PojoSerializationValidation();

	}
}
