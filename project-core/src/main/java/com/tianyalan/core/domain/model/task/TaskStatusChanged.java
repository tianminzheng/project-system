package com.tianyalan.core.domain.model.task;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

public class TaskStatusChanged implements DomainEvent {

	private TaskId taskId;
	private TaskStatus newStatus;
    private Date occurredOn;
    
    public TaskStatusChanged(TaskId taskId, TaskStatus newStatus) {
    	this.taskId = taskId;
    	this.newStatus = newStatus;
    	this.occurredOn = new Date();
    }

	public TaskId getTaskId() {
		return taskId;
	}
	
	public TaskStatus getNewStatus() {
		return newStatus;
	}

	@Override
	public Date occurredOn() {
		return this.occurredOn;
	}

}
