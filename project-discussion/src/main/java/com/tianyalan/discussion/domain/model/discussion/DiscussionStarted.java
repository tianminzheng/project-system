package com.tianyalan.discussion.domain.model.discussion;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

public class DiscussionStarted implements DomainEvent {

	private DiscussionId discussionId;
    private Date occurredOn;	

    public DiscussionStarted(DiscussionId discussionId) {
    	 super();
    	 
    	 this.discussionId = discussionId;
    	 this.occurredOn = new Date();
    }
    
	public DiscussionId getDiscussionId() {
		return discussionId;
	}

	@Override
	public Date occurredOn() {
		return this.occurredOn;
	}

}
