package com.tianyalan.usercenter.domain.model.user;

import com.tianyalan.common.domain.model.Entity;
import com.tianyalan.common.event.DomainEventPublisher;

public class Person extends Entity {
	private static final long serialVersionUID = 1L;

	private ContactInformation contactInformation;
	private FullName name;
	private User user;
	
	public Person(FullName aName, ContactInformation aContactInformation) {

		this();

		this.setContactInformation(aContactInformation);
		this.setName(aName);
	}

	public Person() {		
	}
	
	public void changeContactInformation(ContactInformation aContactInformation) {
		this.setContactInformation(aContactInformation);

		DomainEventPublisher.instance()
				.publish(new PersonContactInfoChanged(this.user().getUsername(), this.contactInformation()));
	}

	public void changeName(FullName aName) {
		this.setName(aName);

		DomainEventPublisher.instance().publish(new PersonNameChanged(this.user().getUsername(), this.name()));
	}

	public ContactInformation contactInformation() {
		return this.contactInformation;
	}

	public EmailAddress emailAddress() {
		return this.contactInformation().emailAddress();
	}

	public FullName name() {
		return this.name;
	}

	protected void setContactInformation(ContactInformation aContactInformation) {
		this.assertArgumentNotNull(aContactInformation, "The person contact information is required.");

		this.contactInformation = aContactInformation;
	}

	protected void setName(FullName aName) {
		this.assertArgumentNotNull(aName, "The person name is required.");

		this.name = aName;
	}

	protected User user() {
		return this.user;
	}

	public void internalOnlySetUser(User aUser) {
		this.user = aUser;
	}
	
	public long id() {
		return super.id();
	}
}
