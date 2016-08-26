package com.tianyalan.usercenter.infrastructure.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.usercenter.domain.DomainTest;
import com.tianyalan.usercenter.domain.model.user.PasswordService;

public class PasswordServiceTest extends DomainTest {

	@Autowired 
	private PasswordService passwordService;
	
	@Test
	public void testGenerateStrongPassword() {
		System.out.println(passwordService.generateStrongPassword());
	}
	
	@Test
	public void testIsWeak() {
		System.out.println(passwordService.isWeak("2dp{#ik"));
	}
}
