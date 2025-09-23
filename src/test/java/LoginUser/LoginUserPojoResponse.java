package LoginUser;

public class LoginUserPojoResponse {

	private String message;
	private int    user_id;
	private String email;
	private String role;
	private String access_token;
	private String refresh_token;
	
	public String getMessage() {
		return message;
	}
	public int getUser_id() {
		return user_id;
	}
	public String getEmail() {
		return email;
	}
	public String getRole() {
		return role;
	}
	public String getAccess_token() {
		return access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
}
