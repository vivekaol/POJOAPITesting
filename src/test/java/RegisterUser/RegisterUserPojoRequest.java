package RegisterUser;

public class RegisterUserPojoRequest {

	private String email;
	private String password;
	private String full_name;
	private String last_name;
	private String role;
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getFull_name() {
		return full_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public String getRole() {
		return role;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public void setRole(String role) {
		this.role = role;
	}
		
}
