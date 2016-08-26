package com.tianyalan.usercenter.domain.model.user;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

public class UserRegistered implements DomainEvent {

	private String username;
    private Date occurredOn;

    public UserRegistered(String aUsername) {
    	super();

        this.occurredOn = new Date();
        this.username = aUsername;
    }
	
    @Override
    public Date occurredOn() {
        return this.occurredOn;
    }

    public String username() {
        return this.username;
    }
}
