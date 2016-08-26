package com.tianyalan.core.domain.model.task;

import com.tianyalan.common.domain.model.AbstractId;

public class TaskId extends AbstractId {
	private static final long serialVersionUID = 1L;
	
	public TaskId() {
		super();
	}
	
	public TaskId(String taskId) {
		super(taskId);
	}
}
