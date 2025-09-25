import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
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

	static void JsonPathValidation(String requestbody, String baseURI, String endpoint) {

		Response response = given().baseUri(baseURI).log().all().contentType(ContentType.JSON)
				.header("x-api-key", "reqres-free-v1").body(requestbody).when().post(endpoint).then().extract()
				.response();

		JsonPath js = response.jsonPath();

		System.out.println("Pring jsonpath name :: " + js.getString("name"));
	}

	static void HamcrestValidation(String requestbody, String baseURI, String endpoint) {

		given().baseUri(baseURI).log().all().contentType(ContentType.JSON).header("x-api-key", "reqres-free-v1")
				.body(requestbody).when().post(endpoint).then().assertThat().body("name", equalTo("kumar"));

		System.out.println("Assertion Pass ");
	}

	static void PojoSerializationValidation(String baseURI, String endpoint) {

		POJOUserRequest userRequest = new POJOUserRequest();
		userRequest.setName("Vivek");
		userRequest.setJob("Software");

		POJOUserResponse UserResponse = given().baseUri(baseURI).log().all().contentType(ContentType.JSON)
				.header("x-api-key", "reqres-free-v1").body(userRequest).when().post(endpoint).then()
				// .getBody()
				.extract().as(POJOUserResponse.class);

		System.out.println("Printing the usernaem rspone :: " + UserResponse.getResName());
		System.out.println("Printing the job rspone :: " + UserResponse.getResJob());
		System.out.println("Printing the ID rspone :: " + UserResponse.getResId());
		System.out.println("Printing the createdAt rspone :: " + UserResponse.getResCreatedAt());

		System.out.println("Test cases passed :: ");
	}

	public static void main(String[] args) {

		String base_URI = "https://reqres.in";
		String end_point = "/api/users";

		String reqbody = "{\r\n" + "    \"name\": \"kumar\",\r\n" + "    \"job\": \"leader\"\r\n" + "}";

		// JsonPathValidation(reqbody, base_URI, end_point);
		// HamcrestValidation(reqbody, base_URI, end_point);
		PojoSerializationValidation(base_URI, end_point);

	}
}