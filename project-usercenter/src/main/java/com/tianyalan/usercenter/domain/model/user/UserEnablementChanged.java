
package com.tianyalan.usercenter.domain.model.user;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

public class UserEnablementChanged implements DomainEvent {

    private Enablement enablement;
    private Date occurredOn;
    private String username;

    public UserEnablementChanged(
            String aUsername,
            Enablement anEnablement) {

        super();

        this.enablement = anEnablement;
        this.occurredOn = new Date();
        this.username = aUsername;
    }

    public Enablement enablement() {
        return this.enablement;
    }
    
    @Override
    public Date occurredOn() {
        return this.occurredOn;
    }

    public String username() {
        return this.username;
    }
}
