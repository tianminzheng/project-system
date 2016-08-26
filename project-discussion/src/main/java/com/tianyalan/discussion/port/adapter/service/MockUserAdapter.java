package com.tianyalan.discussion.port.adapter.service;

import java.lang.reflect.Constructor;

import org.springframework.stereotype.Service;

import com.tianyalan.discussion.domain.model.participant.Participant;

//@Service("userAdapter")
public class MockUserAdapter implements UserAdapter {

	@Override
	public <T extends Participant> T toParticipant(String anIdentity,
			Class<T> aParticipantClass) {
		T participant = null;
		
		try {
			participant =  newParticipant(anIdentity, "Firstname", "Lastname", "tianyalan@163.com", aParticipantClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return participant;
	}
	
	private <T extends Participant> T newParticipant(String username, String firstName, String lastName,
			String aEmailAddress, Class<T> aParticipantClass) throws Exception {

		Constructor<T> ctor = aParticipantClass.getConstructor(String.class, String.class, String.class);

		T participant = ctor.newInstance(username, (firstName + " " + lastName).trim(), aEmailAddress);

		return participant;
	}

}
