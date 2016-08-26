package com.tianyalan.discussion.port.adapter.service;

import org.junit.Test;
import org.springframework.util.Assert;

import com.tianyalan.discussion.domain.model.participant.Owner;

public class ParticipantTranslatorTest {

	@Test
	public void test() throws Exception {
		String userRepresentation = "{\"emailAddress\":\"tianyalan@163.com\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"username\":\"tianyalan\"}";
		ParticipantTranslator translator = new ParticipantTranslator();
		
		Owner participant = translator.toParticipant(userRepresentation,  Owner.class);
		
		Assert.isTrue(participant != null);
	}
}
