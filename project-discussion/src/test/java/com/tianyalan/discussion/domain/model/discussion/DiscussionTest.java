package com.tianyalan.discussion.domain.model.discussion;

import java.util.Date;

import org.junit.Test;

import com.tianyalan.common.util.DateUtil;
import com.tianyalan.discussion.domain.DomainTest;

public class DiscussionTest extends DomainTest{
	
	public DiscussionTest() throws Exception {
		super.setUp();
	}
	
	@Test
	public void testCreate() throws Exception {	
		
		Discussion discussion = buildDiscussion();
		
		assertNotNull(discussion);
		assertEquals("subject", discussion.subject());
        
        expectedEvents(1);
        expectedEvent(DiscussionStarted.class);		
	}
	
	@Test
	public void testClose() throws Exception {
		Discussion discussion = buildDiscussion();
		discussion.close(DateUtil.getAddDayDate(new Date(), 1));
		
		expectedEvents(2);
        expectedEvent(DiscussionStarted.class);
        expectedEvent(DiscussionClosed.class);
	}
	
	
}
