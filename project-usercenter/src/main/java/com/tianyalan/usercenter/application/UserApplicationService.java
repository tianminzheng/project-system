package com.tianyalan.usercenter.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyalan.usercenter.application.command.AuthenticateUserQuery;
import com.tianyalan.usercenter.application.command.ChangeContactInfoCommand;
import com.tianyalan.usercenter.application.command.ChangeEmailAddressCommand;
import com.tianyalan.usercenter.application.command.ChangePostalAddressCommand;
import com.tianyalan.usercenter.application.command.ChangePrimaryTelephoneCommand;
import com.tianyalan.usercenter.application.command.ChangeSecondaryTelephoneCommand;
import com.tianyalan.usercenter.application.command.ChangeUserPasswordCommand;
import com.tianyalan.usercenter.application.command.ChangeUserPersonalNameCommand;
import com.tianyalan.usercenter.application.command.DefineUserEnablementCommand;
import com.tianyalan.usercenter.application.command.RegisterUserCommand;
import com.tianyalan.usercenter.domain.model.user.ContactInformation;
import com.tianyalan.usercenter.domain.model.user.EmailAddress;
import com.tianyalan.usercenter.domain.model.user.Enablement;
import com.tianyalan.usercenter.domain.model.user.FullName;
import com.tianyalan.usercenter.domain.model.user.Person;
import com.tianyalan.usercenter.domain.model.user.PostalAddress;
import com.tianyalan.usercenter.domain.model.user.Telephone;
import com.tianyalan.usercenter.domain.model.user.User;
import com.tianyalan.usercenter.domain.model.user.UserDescriptor;
import com.tianyalan.usercenter.domain.model.user.UserRepository;
import com.tianyalan.usercenter.infrastructure.services.AuthenticationService;

@Service("userApplicationService")
public class UserApplicationService {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UserRepository userRepository;

	public UserApplicationService() {
		super();
	}

	public User registerUser(RegisterUserCommand aCommand) {
		if (aCommand.getInvitationIdentifier() == null) {
			throw new IllegalStateException(
					"Invitation identifier is not invalid.");
		}

		User user = new User(aCommand.getInvitationIdentifier(),
				aCommand.getUsername(), aCommand.getPassword(), new Enablement(
						aCommand.isEnabled(), aCommand.getStartDate(),
						aCommand.getEndDate()), new Person(new FullName(
						aCommand.getFirstName(), aCommand.getLastName()),
						new ContactInformation(new EmailAddress(aCommand
								.getEmailAddress()), new PostalAddress(aCommand
								.getAddressStateProvince(), aCommand
								.getAddressCity(), aCommand
								.getAddressStateProvince(), aCommand
								.getAddressPostalCode(), aCommand
								.getAddressCountryCode()), new Telephone(
								aCommand.getPrimaryTelephone()), new Telephone(
								aCommand.getSecondaryTelephone()))));

		User existedUser = this.userRepository.userWithUsername(user.getUsername());
		if(existedUser != null) {
			throw new IllegalStateException("User with same username is existed already!");
		}
		
		this.userRepository.addUser(user);

		return user;
	}

	public UserDescriptor authenticateUser(AuthenticateUserQuery query) {
		User user = this.userRepository.userWithUsername(query.getUsername());
		if (user == null) {
			throw new IllegalStateException("User is not existed.");
		}

		UserDescriptor userDescriptor = this.authenticationService
				.authenticate(query.getUsername(), query.getPassword());

		return userDescriptor;
	}

	public User user(String aUsername) {
		User user = this.userRepository.userWithUsername(aUsername);
		
		return user;
	}

	public UserDescriptor userDescriptor(String aUsername) {

		UserDescriptor userDescriptor = null;

		User user = this.user(aUsername);

		if (user != null) {
			userDescriptor = user.userDescriptor();
		}

		return userDescriptor;
	}

	public void changeUserContactInformation(ChangeContactInfoCommand aCommand) {
		User user = this.existingUser(aCommand.getUsername());

		this.internalChangeUserContactInformation(
				user,
				new ContactInformation(new EmailAddress(aCommand
						.getEmailAddress()), new PostalAddress(aCommand
						.getAddressStreetAddress(), aCommand.getAddressCity(),
						aCommand.getAddressStateProvince(), aCommand
								.getAddressPostalCode(), aCommand
								.getAddressCountryCode()), new Telephone(
						aCommand.getPrimaryTelephone()), new Telephone(aCommand
						.getSecondaryTelephone())));
	}
	
	public void changeUserEmailAddress(ChangeEmailAddressCommand aCommand) {
		User user = this.existingUser(aCommand.getUsername());

		this.internalChangeUserContactInformation(
				user,
				user.getPerson()
						.contactInformation()
						.changeEmailAddress(
								new EmailAddress(aCommand.getEmailAddress())));
	}

	public void changeUserPostalAddress(ChangePostalAddressCommand aCommand) {
		User user = this.existingUser(aCommand.getUsername());

		this.internalChangeUserContactInformation(
				user,
				user.getPerson()
						.contactInformation()
						.changePostalAddress(
								new PostalAddress(aCommand
										.getAddressStreetAddress(), aCommand
										.getAddressCity(), aCommand
										.getAddressStateProvince(), aCommand
										.getAddressPostalCode(), aCommand
										.getAddressCountryCode())));
	}

	public void changeUserPrimaryTelephone(
			ChangePrimaryTelephoneCommand aCommand) {
		User user = this.existingUser(aCommand.getUsername());

		this.internalChangeUserContactInformation(
				user,
				user.getPerson()
						.contactInformation()
						.changePrimaryTelephone(
								new Telephone(aCommand.getTelephone())));
	}


	public void changeUserSecondaryTelephone(
			ChangeSecondaryTelephoneCommand aCommand) {
		User user = this.existingUser(aCommand.getUsername());

		this.internalChangeUserContactInformation(
				user,
				user.getPerson()
						.contactInformation()
						.changeSecondaryTelephone(
								new Telephone(aCommand.getTelephone())));
	}

	public void changeUserPassword(ChangeUserPasswordCommand aCommand) {
		User user = this.existingUser(aCommand.getUsername());

		user.changePassword(aCommand.getCurrentPassword(),
				aCommand.getChangedPassword());
		
		userRepository.updateUser(user);
	}

	public void changeUserPersonalName(ChangeUserPersonalNameCommand aCommand) {
		User user = this.existingUser(aCommand.getUsername());

		user.getPerson().changeName(
				new FullName(aCommand.getFirstName(), aCommand.getLastName()));
	}

	public void defineUserEnablement(DefineUserEnablementCommand aCommand) {
		User user = this.existingUser(aCommand.getUsername());

		user.defineEnablement(new Enablement(aCommand.isEnabled(), aCommand
				.getStartDate(), aCommand.getEndDate()));
	}

	private void internalChangeUserContactInformation(User aUser,
			ContactInformation aContactInformation) {
		if (aUser == null) {
			throw new IllegalArgumentException("User must exist.");
		}

		aUser.getPerson().changeContactInformation(aContactInformation);
	}

	private User existingUser(String aUsername) {
		User user = this.user(aUsername);

		if (user == null) {
			throw new IllegalArgumentException("User does not exist for: "
					+ aUsername);
		}

		return user;
	}
}
