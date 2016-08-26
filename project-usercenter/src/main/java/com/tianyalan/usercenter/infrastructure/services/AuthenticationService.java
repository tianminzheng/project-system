
package com.tianyalan.usercenter.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.common.Assertion;
import com.tianyalan.usercenter.domain.model.user.EncryptionService;
import com.tianyalan.usercenter.domain.model.user.User;
import com.tianyalan.usercenter.domain.model.user.UserDescriptor;
import com.tianyalan.usercenter.domain.model.user.UserRepository;

@Service("authenticationService")
public class AuthenticationService extends Assertion {

	@Autowired
	private EncryptionService encryptionService;
	
	@Autowired
	private UserRepository userRepository;

	public UserDescriptor authenticate(String aUsername, String aPassword) {

		this.assertArgumentNotEmpty(aUsername, "Username must be provided.");
		this.assertArgumentNotEmpty(aPassword, "Password must be provided.");

		UserDescriptor userDescriptor = UserDescriptor.nullDescriptorInstance();

		String encryptedPassword = this.encryptionService.encryptedValue(aPassword);

		User user = this.userRepository.userFromAuthenticCredentials(aUsername, encryptedPassword);

		if (user != null && user.isEnabled()) {
			userDescriptor = user.userDescriptor();
		}

		return userDescriptor;
	}
}
