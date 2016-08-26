package com.tianyalan.discussion.domain.model.participant;

public class Owner extends Participant {
	private static final long serialVersionUID = 1L;

	public Owner(String anIdentity, String aName, String anEmailAddress) {
		super(anIdentity, aName, anEmailAddress);
	}

	public Owner() {
		super();
	}
}
