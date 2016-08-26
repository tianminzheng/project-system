package com.tianyalan.discussion.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.discussion.application.discussion.DiscussionApplicationService;
import com.tianyalan.discussion.domain.BaseDomainTest;
import com.tianyalan.discussion.domain.DomainTest;

public class DiscussionApplicationServiceTest extends DomainTest {
	
	public DiscussionApplicationServiceTest() throws Exception {
		super.setUp();
	}
	
	@Autowired
	private DiscussionApplicationService discussionApplicationService;

	@Test
	public void testNewDiscussion() {
		String projectId = "Project1";
		String ownerId = "tianyalan";
		String recorderId = "tianyalan";
		Collection<String> participantIds = new ArrayList<String>();
		participantIds.add("tianyalan");
		String subject = "subject";
		
		discussionApplicationService.newDiscussion(projectId, ownerId, recorderId, participantIds, subject);
	}
	
	@Test
	public void testCloseDiscussion() {
		discussionApplicationService.closeDiscussion("DISCUSSION_7373CEE9-D762-4F27-ACC2-A0608D5120D1", DateUtils.addDays(new Date(), 1));
	}
}
