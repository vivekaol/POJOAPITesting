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
	public void setResName(String resName) {
		this.resName = resName;
	}
	@JsonProperty("id")
	public String getResJob() {
		return resJob;
	}
	 @JsonProperty("createdAt")
	public void setResJob(String resJob) {
		this.resJob = resJob;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResCreatedAt() {
		return resCreatedAt;
	}

	public void setResCreatedAt(String resCreatedAt) {
		this.resCreatedAt = resCreatedAt;
	}

}
