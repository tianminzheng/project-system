package com.tianyalan.discussion.port.adapter.persistence;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tianyalan.discussion.domain.BaseDomainTest;
import com.tianyalan.discussion.domain.model.discussion.Discussion;
import com.tianyalan.discussion.domain.model.discussion.DiscussionId;
import com.tianyalan.discussion.domain.model.discussion.DiscussionRepository;

public class MySQLDiscussionRepositoryTest extends BaseDomainTest {

	@Autowired
	protected DiscussionRepository discussionRepository;
	
	@Test
	public void testDiscussionOfId() {
		DiscussionId discussionId = new DiscussionId("DiscussionId1");
		
		Discussion discussion = discussionRepository.discussionOfId(discussionId);
		
		Assert.isTrue(discussion != null);
		Assert.isTrue(discussion.discussionId().equals(discussionId));
	}
	
	@Test
	public void testSaveDiscussion() {
		Discussion discusson = buildDiscussion();
		discussionRepository.saveDiscussion(discusson);
	}
	
	@Test
	public void testUpdateDiscussion() {
		Discussion discusson = buildDiscussion();
		discusson.close(new Date());
		discussionRepository.updateDiscussion(discusson);
	}
}
