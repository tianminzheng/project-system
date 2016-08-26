
package com.tianyalan.usercenter.application.command;

public class ChangePrimaryTelephoneCommand {
	private String username;
	private String telephone;

	public ChangePrimaryTelephoneCommand(String username, String telephone) {
		super();

		this.username = username;
		this.telephone = telephone;
	}

	public ChangePrimaryTelephoneCommand() {
		super();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
