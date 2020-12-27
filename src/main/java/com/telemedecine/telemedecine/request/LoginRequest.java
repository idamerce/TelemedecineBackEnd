package com.telemedecine.telemedecine.request;

import com.sun.istack.NotNull;

public class LoginRequest {
	@NotNull
	private String login;

	@NotNull
	private String password;


	public String getLogin() {
		return login;
	}

	public void setUsername(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
