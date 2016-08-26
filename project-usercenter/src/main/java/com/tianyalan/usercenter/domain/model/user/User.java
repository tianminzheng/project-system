package com.tianyalan.usercenter.domain.model.user;

import com.tianyalan.common.domain.model.Entity;
import com.tianyalan.common.event.DomainEventPublisher;
import com.tianyalan.usercenter.domain.model.DomainRegistry;

public class User extends Entity {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Person person;
	private Enablement enablement;

	public User(String anInvitationIdentifier, String aUsername, String aPassword, Enablement anEnablement,
			Person aPerson) {

		this();
		
		this.setUsername(aUsername);
		this.setPerson(aPerson);
		this.setPassword(this.asEncryptedValue(aPassword));
		this.setEnablement(anEnablement);
		
		DomainEventPublisher.instance().publish(new UserRegistered(this.getUsername()));
	}
	
	public User() {
		
	}

	public User(long l) {
		super(l);
	}

	//修改密码
	public void changePassword(String aCurrentPassword, String aChangedPassword) {

		this.assertArgumentNotEmpty(aCurrentPassword, "Current and new password must be provided.");

		//调用加密服务验证当前密码是否正确
		this.assertArgumentEquals(this.getPassword(), this.asEncryptedValue(aCurrentPassword),
				"Current password not confirmed.");

		//调用加密服务进行新密码加密
		this.protectPassword(aCurrentPassword, aChangedPassword);

		DomainEventPublisher.instance().publish(new UserPasswordChanged(this.getUsername()));
	}

	//调用加密服务进行密码加密
	protected String asEncryptedValue(String aPlainTextPassword) {
		String encryptedValue = DomainRegistry.encryptionService().encryptedValue(aPlainTextPassword);

		return encryptedValue;
	}

	public void changePersonalContactInformation(ContactInformation aContactInformation) {
		this.getPerson().changeContactInformation(aContactInformation);
	}

	public void changePersonalName(FullName aPersonalName) {
		this.getPerson().changeName(aPersonalName);
	}

	public void defineEnablement(Enablement anEnablement) {
		this.setEnablement(anEnablement);

		DomainEventPublisher.instance().publish(new UserEnablementChanged(this.getUsername(), this.getEnablement()));
	}

	public boolean isEnabled() {
		return this.getEnablement().isEnablementEnabled();
	}

	public UserDescriptor userDescriptor() {
		return new UserDescriptor(this.getUsername(), this.getPerson().emailAddress().address());
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Person getPerson() {
		return person;
	}

	public Enablement getEnablement() {
		return enablement;
	}
	
	public long id() {
		return super.id();
	}

	protected void setUsername(String username) {
		this.assertArgumentNotEmpty(username, "The username is required.");
		this.assertArgumentLength(username, 3, 250, "The username must be 3 to 250 characters.");

		this.username = username;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	public void setPerson(Person person) {
		this.assertArgumentNotNull(person, "The person is required.");

		this.person = person;
	}

	protected void setEnablement(Enablement enablement) {
		this.assertArgumentNotNull(enablement, "The enablement is required.");

		this.enablement = enablement;
	}

	protected void protectPassword(String aCurrentPassword, String aChangedPassword) {
		this.assertPasswordsNotSame(aCurrentPassword, aChangedPassword);

		this.assertPasswordNotWeak(aChangedPassword);

		this.assertUsernamePasswordNotSame(aChangedPassword);

		this.setPassword(this.asEncryptedValue(aChangedPassword));
	}

	protected void assertPasswordsNotSame(String aCurrentPassword, String aChangedPassword) {
		this.assertArgumentNotEquals(aCurrentPassword, aChangedPassword, "The password is unchanged.");
	}

	//调用PasswordService进行密码强度验证
	protected void assertPasswordNotWeak(String aPlainTextPassword) {		
		this.assertArgumentFalse(DomainRegistry.passwordService().isWeak(aPlainTextPassword),
				"The password must be stronger.");
	}

	protected void assertUsernamePasswordNotSame(String aPlainTextPassword) {
		this.assertArgumentNotEquals(this.getUsername(), aPlainTextPassword,
				"The username and password must not be the same.");
	}

}
