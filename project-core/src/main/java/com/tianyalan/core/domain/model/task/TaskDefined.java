package com.tianyalan.core.domain.model.task;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;
import com.tianyalan.core.domain.model.project.ProjectId;

public class TaskDefined implements DomainEvent {

	private TaskId taskId;
	private ProjectId projectId;
	private String name;
	private String description;
	private Date occurredOn;

	public TaskDefined(TaskId taskId, ProjectId projectId, String name, String description) {

		super();

		this.taskId = taskId;
		this.projectId = projectId;
		this.description = description;
		this.name = name;
		this.occurredOn = new Date();
	}

	public TaskId getTaskId() {
		return taskId;
	}

	public ProjectId getProjectId() {
		return projectId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getOccurredOn() {
		return occurredOn;
	}

	@Override
	public Date occurredOn() {
		return this.occurredOn;
	}

}
