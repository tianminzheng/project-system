package com.tianyalan.usercenter.domain.model.user;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

public class UserPasswordChanged implements DomainEvent {

    private String username;
    private Date occurredOn;
	
    //一般不再这里传输Password
    public UserPasswordChanged(String aUsername) {
        super();

        this.occurredOn = new Date();
        this.username = aUsername;
    }

    public String username() {
        return this.username;
    }

	@Override
	public Date occurredOn() {
		return this.occurredOn;
	}

}
