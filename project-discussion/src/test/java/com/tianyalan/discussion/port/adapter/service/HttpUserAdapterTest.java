package com.tianyalan.discussion.port.adapter.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tianyalan.discussion.domain.BaseDomainTest;
import com.tianyalan.discussion.domain.DomainTest;
import com.tianyalan.discussion.domain.model.participant.Owner;

public class HttpUserAdapterTest extends BaseDomainTest {

	@Autowired
	private UserAdapter userAdapter;
	
	@Test
	public void test() throws Exception {
		Owner owner = userAdapter.toParticipant("tianyalan", Owner.class);
		
		Assert.isTrue(owner != null);
	}
}
