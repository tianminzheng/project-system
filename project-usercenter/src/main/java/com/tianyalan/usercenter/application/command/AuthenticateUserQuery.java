
package com.tianyalan.usercenter.application.command;

public class AuthenticateUserQuery {
	private String username;
	private String password;

	public AuthenticateUserQuery(String aUsername, String aPassword) {
		super();

		this.username = aUsername;
		this.password = aPassword;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
