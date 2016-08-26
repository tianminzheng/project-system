package com.tianyalan.usercenter.infrastructure.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tianyalan.usercenter.domain.DomainTest;
import com.tianyalan.usercenter.domain.model.user.UserDescriptor;

public class AuthenticationServiceTest extends DomainTest {

	@Autowired
	AuthenticationService authenticationService;
	
	@Test
	public void testAuthenticate() {
		UserDescriptor userDescriptor = authenticationService.authenticate("tianyalan_2", "changedPassword");
		
		Assert.isTrue(userDescriptor != null);
//		Assert.isTrue(userDescriptor.username() == null);
		
		Assert.isTrue(userDescriptor.username().equals("tianyalan_2"));
	}
}
