import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class POJOUserResponse {
	private String resName;
	private String resJob;
	private String resId;
	private String resCreatedAt;

	@JsonProperty("name")
	public String getResName() {
		return resName;
	}

	@JsonProperty("job")
	public String getResJob() {
		return resJob;
	}

	@JsonProperty("id")
	public String getResId() {
		return resId;
	}

	@JsonProperty("createdAt")
	public String getResCreatedAt() {
		return resCreatedAt;
	}
}
