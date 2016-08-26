package com.tianyalan.usercenter.application;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tianyalan.usercenter.application.command.AuthenticateUserQuery;
import com.tianyalan.usercenter.application.command.ChangeUserPasswordCommand;
import com.tianyalan.usercenter.application.command.ChangeUserPersonalNameCommand;
import com.tianyalan.usercenter.application.command.DefineUserEnablementCommand;
import com.tianyalan.usercenter.application.command.RegisterUserCommand;
import com.tianyalan.usercenter.domain.DomainTest;
import com.tianyalan.usercenter.domain.model.user.PersonNameChanged;
import com.tianyalan.usercenter.domain.model.user.User;
import com.tianyalan.usercenter.domain.model.user.UserDescriptor;
import com.tianyalan.usercenter.domain.model.user.UserEnablementChanged;
import com.tianyalan.usercenter.domain.model.user.UserPasswordChanged;


public class UserApplicationServiceTest extends DomainTest {
	
	@Autowired
	UserApplicationService userApplicationService;
	
	@Test
	public void testRegisterUser() {
		RegisterUserCommand command = new RegisterUserCommand(
				"invitationIdentifier",
				"tianyalan",
				"password",
				"Tianya",
				"Lan",
				true,
				new Date(),
				new Date(),
				"tianyalan@163.com",
				"111-555-1212",
				"222-555-1210",
				"Westlake", 
				"Hangzhou", 
				"Zhejiang", 
				"310000", 
				"CN");
		
		User user = userApplicationService.registerUser(command);
		
		Assert.isTrue(user != null);
	}
	
	@Test
	public void testAuthenticateUser() {
		AuthenticateUserQuery query = new AuthenticateUserQuery("tianyalan", "password");
		
		UserDescriptor userDescriptor = userApplicationService.authenticateUser(query);
		
		Assert.isTrue(userDescriptor != null);
		Assert.isTrue(userDescriptor.username().equals(query.getUsername()));
	}
	
	@Test
	public void testUser() {
		String username = "tianyalan";
		
		User user = userApplicationService.user(username);
		
		Assert.isTrue(user != null);
	}

	@Test
	public void testUserDescriptor() {
		String username = "tianyalan";
		
		UserDescriptor userDescriptor = userApplicationService.userDescriptor(username);
		
		Assert.isTrue(userDescriptor != null);
	}

	@Test
	public void testChangeUserPassword() {
		ChangeUserPasswordCommand command = new ChangeUserPasswordCommand("tianyalan", "password", "aChangedPassword");
		
		userApplicationService.changeUserPassword(command);
		
		expectedEvents(1);
        expectedEvent(UserPasswordChanged.class);
	}

	@Test
	public void testChangeUserPersonalName() {
		ChangeUserPersonalNameCommand command = new ChangeUserPersonalNameCommand("tianyalan", "Tianya", "Lan2");
		
		userApplicationService.changeUserPersonalName(command);
		
		expectedEvents(1);
        expectedEvent(PersonNameChanged.class);
	}

	@Test
	public void testDefineUserEnablement() {
		DefineUserEnablementCommand command = new DefineUserEnablementCommand("tianyalan" , true, new Date(), new Date());
		
		userApplicationService.defineUserEnablement(command);
		
		expectedEvents(1);
        expectedEvent(UserEnablementChanged.class);
	}

}
