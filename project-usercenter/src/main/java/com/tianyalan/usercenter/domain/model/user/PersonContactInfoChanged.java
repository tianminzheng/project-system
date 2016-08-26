package com.tianyalan.usercenter.domain.model.user;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

public class PersonContactInfoChanged implements DomainEvent {

	private ContactInformation contactInformation;
    private String username;
    private Date occurredOn;
    
    public PersonContactInfoChanged(
            String aUsername,
            ContactInformation aContactInformation) {

        super();

        this.contactInformation = aContactInformation;
        this.occurredOn = new Date();
        this.username = aUsername;
    }
    
    
    public ContactInformation contactInformation() {
        return this.contactInformation;
    }

	@Override
	public Date occurredOn() {
		return this.occurredOn;
	}

    public String username() {
        return this.username;
    }
}
