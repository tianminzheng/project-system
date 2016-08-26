package com.tianyalan.discussion.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianyalan.common.event.DomainEventPublisher;
import com.tianyalan.discussion.domain.model.discussion.Discussion;
import com.tianyalan.discussion.domain.model.discussion.DiscussionId;
import com.tianyalan.discussion.domain.model.participant.Owner;
import com.tianyalan.discussion.domain.model.participant.Participant;
import com.tianyalan.discussion.domain.model.participant.Recorder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-discussion.xml" })
public abstract class DomainTest extends EventWithMQTest {
	
	@Override
	protected void setUp() throws Exception {

		DomainEventPublisher.instance().reset();
		
		super.setUp();
	}
	
	protected Discussion buildDiscussion() {
		String projectId = "PROJECT_A04E7F7C-94B0-43D8-AC55-20D3212EAD11";
		Owner owner = new Owner("tianyalan", "name", "tianyalan@163.com");
		Recorder recorder = new Recorder("tianyalan", "name", "tianyalan@163.com");
		Collection<Participant> participants = new ArrayList<Participant>();
		
		Discussion discussion = new Discussion(new DiscussionId("DiscussionId1"), projectId, owner, recorder, participants, "subject");
		return discussion;
	}
	
}
