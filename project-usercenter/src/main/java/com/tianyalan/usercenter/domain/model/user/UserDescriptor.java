
package com.tianyalan.usercenter.domain.model.user;

import java.io.Serializable;

import com.tianyalan.common.Assertion;

//User的另一种表现
public final class UserDescriptor extends Assertion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String emailAddress;
	private String username;

	public static UserDescriptor nullDescriptorInstance() {
		return new UserDescriptor();
	}

	public UserDescriptor(String aUsername, String anEmailAddress) {
		super();

		this.setEmailAddress(anEmailAddress);
		this.setUsername(aUsername);
	}

	public String emailAddress() {
		return this.emailAddress;
	}

	public boolean isNullDescriptor() {
		return this.emailAddress() == null || this.username() == null;
	}

	public String username() {
		return this.username;
	}

	@Override
	public String toString() {
		return "UserDescriptor [emailAddress=" + emailAddress + ", username=" + username + "]";
	}

	private UserDescriptor() {
		super();
	}

	private void setEmailAddress(String anEmailAddress) {
		this.assertArgumentNotEmpty(anEmailAddress, "Email address must be provided.");

		this.emailAddress = anEmailAddress;
	}

	private void setUsername(String aUsername) {
		this.assertArgumentNotEmpty(aUsername, "Username must not be set as null.");

		this.username = aUsername;
	}
}
