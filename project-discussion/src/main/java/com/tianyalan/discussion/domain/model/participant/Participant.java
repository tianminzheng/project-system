package com.tianyalan.discussion.domain.model.participant;

import java.io.Serializable;

public class Participant implements Serializable {

	private static final long serialVersionUID = 1L;

    private String emailAddress;
    private String identity;
    private String name;
    
    public Participant(String anIdentity, String aName, String anEmailAddress) {
        super();

        this.setEmailAddress(anEmailAddress);
        this.setIdentity(anIdentity);
        this.setName(aName);
    }

    public Participant() {
    	
    }
    
    public String emailAddress() {
        return this.emailAddress;
    }

    public String identity() {
        return this.identity;
    }

    public String name() {
        return this.name;
    }
    
    private void setEmailAddress(String anEmailAddress) {
        this.emailAddress = anEmailAddress;
    }

    private void setIdentity(String anIdentity) {
        this.identity = anIdentity;
    }

    private void setName(String aName) {
        this.name = aName;
    }
}
