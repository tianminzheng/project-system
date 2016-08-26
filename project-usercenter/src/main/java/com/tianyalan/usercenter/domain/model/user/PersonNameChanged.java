package com.tianyalan.usercenter.domain.model.user;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

public class PersonNameChanged implements DomainEvent {

	private FullName name;
    private String username;
    private Date occurredOn;	
    
    public PersonNameChanged(String aUsername, FullName aName) {
        super();
        this.name = aName;
        this.occurredOn = new Date();
        this.username = aUsername;
    }
    
    public FullName name() {
        return this.name;
    }

    public String username() {
        return this.username;
    }

	@Override
	public Date occurredOn() {
		return this.occurredOn;
	}

}
