package com.tianyalan.usercenter.infrastructure.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.usercenter.domain.DomainTest;
import com.tianyalan.usercenter.domain.model.user.EncryptionService;

public class MD5EncryptionServiceTest extends DomainTest {

	@Autowired
	EncryptionService encryptionService;
	
	@Test
	public void testEncryptedValue() {
		String plainText = "tianyalan";
		String encryptedValue = encryptionService.encryptedValue(plainText);
		
		System.out.print(encryptedValue);		
	}
}
