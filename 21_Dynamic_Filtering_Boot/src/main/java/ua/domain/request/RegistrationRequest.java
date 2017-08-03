package ua.domain.request;

import javax.validation.constraints.AssertTrue;

public class RegistrationRequest {

	private String fullName;
	
	private String phone;
	
	private String email;
	
	private String password;
	
	private String passwordRepeat;
	
	private boolean isOwner;
	
	@AssertTrue(message="Not equal")
	public boolean getCheckPassword(){
		if(password==null) return false;
		return password.equals(passwordRepeat);
	}
	
	public boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}
}