
package com.tianyalan.usercenter.application.command;

public class ChangeEmailAddressCommand {
	private String username;
	private String emailAddress;

	public ChangeEmailAddressCommand(String username, String emailAddress) {
		super();

		this.username = username;
		this.emailAddress = emailAddress;
	}

	public ChangeEmailAddressCommand() {
		super();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
