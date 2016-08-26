package com.tianyalan.discussion.port.adapter.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tianyalan.discussion.domain.BaseDomainTest;
import com.tianyalan.discussion.domain.model.participant.Owner;
import com.tianyalan.discussion.domain.model.participant.ParticipantService;

public class ParticipantServiceTest extends BaseDomainTest {

	@Autowired
	private ParticipantService participantService;
	
	@Test
	public void test() throws Exception {
		Owner owner = participantService.ownerFrom("tianyalan");
		
		Assert.isTrue(owner != null);
	}
}
