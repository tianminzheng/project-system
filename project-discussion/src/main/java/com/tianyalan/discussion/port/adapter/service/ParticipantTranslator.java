package com.tianyalan.discussion.port.adapter.service;

import java.lang.reflect.Constructor;

import com.alibaba.fastjson.JSON;
import com.tianyalan.discussion.domain.model.participant.Participant;

public class ParticipantTranslator {

	public <T extends Participant> T toParticipant(String aUserRepresentation, Class<T> aParticipantClass)
			throws Exception {
		
		//对远程对象的字符串进行发序列化
		RemoteUser remoteUser = JSON.parseObject(aUserRepresentation, RemoteUser.class);

		String username = remoteUser.getUsername();
		String firstName = remoteUser.getFirstName();
		String lastName = remoteUser.getLastName();
		String emailAddress = remoteUser.getEmailAddress();

		T participant = this.newParticipant(username, firstName, lastName, emailAddress, aParticipantClass);

		return participant;
	}

	private <T extends Participant> T newParticipant(String username, String firstName, String lastName,
			String aEmailAddress, Class<T> aParticipantClass) throws Exception {

		Constructor<T> ctor = aParticipantClass.getConstructor(String.class, String.class, String.class);

		T participant = ctor.newInstance(username, (firstName + " " + lastName).trim(), aEmailAddress);

		return participant;
	}
	
	

}
