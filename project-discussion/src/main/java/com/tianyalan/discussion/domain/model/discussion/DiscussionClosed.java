package com.tianyalan.discussion.domain.model.discussion;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

public class DiscussionClosed implements DomainEvent {

	private String projectId;
	private String scheduledDate;
    private Date occurredOn;
    
    public DiscussionClosed(String projectId, String scheduledDate) {
    	this.projectId = projectId;
    	this.scheduledDate = scheduledDate;
    	
    	this.occurredOn = new Date();
    }

	public String getProjectId() {
		return projectId;
	}

	public String getScheduledDate() {
		return scheduledDate;
	}

	@Override
	public Date occurredOn() {
		return this.occurredOn;
	}

}
