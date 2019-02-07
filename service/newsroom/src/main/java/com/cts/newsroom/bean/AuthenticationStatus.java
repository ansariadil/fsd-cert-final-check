package com.cts.newsroom.bean;

public class AuthenticationStatus {

	private boolean authenticated;
	private boolean isAdmin;
	private boolean checkEmail;
	private User user;
	private String token;
	
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isCheckEmail() {
		return checkEmail;
	}
	public void setCheckEmail(boolean checkEmail) {
		this.checkEmail = checkEmail;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public AuthenticationStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthenticationStatus(boolean authenticated, boolean isAdmin, boolean checkEmail, User user, String token) {
		super();
		this.authenticated = authenticated;
		this.isAdmin = isAdmin;
		this.checkEmail = checkEmail;
		this.user = user;
		this.token = token;
	}
	
	
}