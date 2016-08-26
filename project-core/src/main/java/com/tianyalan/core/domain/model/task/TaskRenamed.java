package com.tianyalan.core.domain.model.task;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

public class TaskRenamed implements DomainEvent {

	private TaskId taskId;
	private String newName;
    private Date occurredOn;
    
    public TaskRenamed(TaskId taskId, String newName) {
    	super();
    	
    	this.taskId = taskId;
    	this.newName = newName;
    	this.occurredOn = new Date();
    }
	
	public TaskId getTaskId() {
		return taskId;
	}

	public String getNewName() {
		return newName;
	}

	@Override
	public Date occurredOn() {
		return this.occurredOn;
	}

}
