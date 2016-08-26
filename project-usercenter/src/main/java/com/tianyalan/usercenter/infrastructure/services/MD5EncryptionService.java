
package com.tianyalan.usercenter.infrastructure.services;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.stereotype.Service;

import com.tianyalan.common.Assertion;
import com.tianyalan.usercenter.domain.model.user.EncryptionService;

@Service("encryptionService")
public class MD5EncryptionService extends Assertion implements EncryptionService {

	public MD5EncryptionService() {
		super();
	}

	@Override
	public String encryptedValue(String aPlainTextValue) {
		this.assertArgumentNotEmpty(aPlainTextValue, "Plain text value to encrypt must be provided.");
		String encryptedValue = null;

		//使用消息摘要进行加密计算
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(aPlainTextValue.getBytes("UTF-8"));
			BigInteger bigInt = new BigInteger(1, messageDigest.digest());
			encryptedValue = bigInt.toString(16);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

		return encryptedValue;
	}
}

