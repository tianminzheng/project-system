
package com.tianyalan.usercenter.application.command;

public class ChangeUserPasswordCommand {
	private String username;
	private String currentPassword;
	private String changedPassword;

	public ChangeUserPasswordCommand(String aUsername, String aCurrentPassword, String aChangedPassword) {

		super();

		this.username = aUsername;
		this.currentPassword = aCurrentPassword;
		this.changedPassword = aChangedPassword;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCurrentPassword() {
		return this.currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getChangedPassword() {
		return this.changedPassword;
	}

	public void setChangedPassword(String changedPassword) {
		this.changedPassword = changedPassword;
	}
}
