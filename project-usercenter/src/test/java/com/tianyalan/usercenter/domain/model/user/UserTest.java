package com.tianyalan.usercenter.domain.model.user;

import java.util.Date;

import org.junit.Test;
import org.springframework.util.Assert;

import com.tianyalan.usercenter.domain.DomainTest;

public class UserTest extends DomainTest {
	
	public UserTest() throws Exception {
		super.setUp();
	}
	
	@Test
	public void testChangePassword() {		
		User user = userAggregate();
		
		user.changePassword(FIXTURE_PASSWORD, "tianyalan"); //6be0d2669b69b8fbc3fac300e2e52f5c
		
        Assert.isTrue(user.getPassword().equals("6be0d2669b69b8fbc3fac300e2e52f5c") );
        
        expectedEvents(2);
        expectedEvent(UserRegistered.class);
        expectedEvent(UserPasswordChanged.class);
	}

	@Test
	public void testDefineEnablement() {

		User user = userAggregate();
		
		Enablement enablement = new Enablement(true, new Date(), new Date());
		user.defineEnablement(enablement);
		
		expectedEvents(2);
		expectedEvent(UserRegistered.class);
        expectedEvent(UserEnablementChanged.class);
	}
}
