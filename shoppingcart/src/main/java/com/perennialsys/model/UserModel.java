package com.perennialsys.model;

/*import javax.validation.constraints.Size;*/

public class UserModel {
	private int userId;
	
	private String userName;
	
	/* @Size(min=2,max=30, message="Enter Valid password") */
	private String password;
	
	private String email;
	
	private long mobileNo;

	private String address;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Override
	public String toString() {
		return "UserModel [userName=" + userName + ", password=" + password + ", email=" + email + ", address="
				+ address + ", mobileNo=" + mobileNo + "]";
	}


}
