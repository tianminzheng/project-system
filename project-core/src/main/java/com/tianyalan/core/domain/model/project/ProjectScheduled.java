package com.tianyalan.core.domain.model.project;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

//当Project的计划完成时间被确定时，触发ProjectScheduled事件
public class ProjectScheduled implements DomainEvent {

	private ProjectId projectId;
	private Date scheduledDate;
    private Date occurredOn;
    
    public ProjectScheduled(ProjectId projectId,
    		Date scheduledDate) {
    	
    	super();
    	
    	this.projectId = projectId;
    	this.scheduledDate = scheduledDate;
    	this.occurredOn = new Date();
    }
	
	public ProjectId getProjectId() {
		return projectId;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	@Override
	public Date occurredOn() {
		return this.occurredOn;
	}

}
