package com.tianyalan.usercenter.resource;

import com.tianyalan.common.port.adapter.rest.RestTemplateUtil;
import com.tianyalan.common.port.adapter.rest.ResultMessageBuilder.ResultMessage;

public class UserControllerTest {

	public static void main(String[] args) {
		testGetUserByUserName();
//		testGetAuthenticUser();
	}

	private static void testGetUserByUserName() {

		ResultMessage result = RestTemplateUtil.getForObject(
				"http://127.0.0.1:8080/project-usercenter/users/tianyalan", null);

		System.out.println(result.getData());
	}

	private static void testGetAuthenticUser() {

		AuthenticationInfo userObject = new AuthenticationInfo();
		userObject.setUserName("tianyalan");
		userObject.setPassword("changedPassword");

		ResultMessage result = RestTemplateUtil.postForObject(
				"http://127.0.0.1:8080/project-usercenter/users/authentication",
				userObject);

		System.out.println(result.getData());
	}

}
