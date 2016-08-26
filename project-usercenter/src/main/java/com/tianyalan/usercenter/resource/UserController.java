package com.tianyalan.usercenter.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tianyalan.common.port.adapter.rest.BaseController;
import com.tianyalan.common.port.adapter.rest.ResultMessageBuilder;
import com.tianyalan.usercenter.application.UserApplicationService;
import com.tianyalan.usercenter.application.command.AuthenticateUserQuery;
import com.tianyalan.usercenter.application.presentation.UserRepresentation;
import com.tianyalan.usercenter.domain.model.user.User;
import com.tianyalan.usercenter.domain.model.user.UserDescriptor;

@Controller
@RequestMapping(value = "/users")
public class UserController extends BaseController {

	@Autowired
	private UserApplicationService userApplicationService;

	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	public void getAuthenticUser(
			@RequestBody AuthenticationInfo authenticationInfo,
			HttpServletResponse response) {

		UserDescriptor userDescriptor = userApplicationService
				.authenticateUser(new AuthenticateUserQuery(authenticationInfo
						.getUserName(), authenticationInfo.getPassword()));

		if (userDescriptor.isNullDescriptor()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(
					false,
					"The authentication for user "
							+ authenticationInfo.getUserName() + " is failed"),
					response);
			return;
		}

		writeAjaxJSONResponse(
				ResultMessageBuilder.build(true, "success!", userDescriptor),
				response);
	}

	@RequestMapping(value = "/{userName}", method = RequestMethod.GET)
	public void getUserByUserName(@PathVariable String userName,
			HttpServletResponse response) {

		User user = userApplicationService.user(userName);

		if (user == null) {
			writeAjaxJSONResponse(
					ResultMessageBuilder.build(false, "User " + userName
							+ " is not existed"), response);
			return;
		}

		UserRepresentation userRepresentation = new UserRepresentation(user);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!",
				userRepresentation), response);
	}
}
