package com.tianyalan.core.domain.model.task;

import com.tianyalan.common.domain.model.Entity;
import com.tianyalan.common.event.DomainEventPublisher;
import com.tianyalan.core.domain.model.project.ProjectId;

public class Task extends Entity {
	private static final long serialVersionUID = 1L;

	private TaskId taskId;
	private ProjectId projectId;
	private String taskName;
	private String taskDescription;
	private TaskStatus status;

	public Task() {		
	}
	
	public Task(ProjectId projectId, TaskId taskId, String taskName,
			String taskDescription) {

		this();
		
		this.setProjectId(projectId);
		this.setTaskId(taskId);
		this.setTaskName(taskName);
		this.setTaskDescription(taskDescription);
		this.setStatus(TaskStatus.NOT_STARTED);

		DomainEventPublisher.instance().publish(
				new TaskDefined(this.taskId, this.projectId, this.taskName,
						this.taskDescription));
	}

	public void changeName(String newTaskName) {
		this.setTaskName(newTaskName);

		DomainEventPublisher.instance().publish(
				new TaskRenamed(this.taskId, newTaskName));
	}

	public void taskInProgress() {
		if(this.getStatus() != TaskStatus.NOT_STARTED)
			throw new IllegalStateException("Task is not in the status of 'NOT_STARTED', can not be in progress.");
		
		this.setStatus(TaskStatus.IN_PROGRESS);
		
		DomainEventPublisher.instance().publish(
				new TaskStatusChanged(this.taskId, TaskStatus.IN_PROGRESS));
	}

	public void taskImpeded() {
		if(this.getStatus() != TaskStatus.NOT_STARTED || this.getStatus() != TaskStatus.IN_PROGRESS)
			throw new IllegalStateException("Task is not in the status of 'NOT_STARTED' or 'IN_PROGRESS', can not be impeded.");
		
		this.setStatus(TaskStatus.IMPEDED);
		
		DomainEventPublisher.instance().publish(
				new TaskStatusChanged(this.taskId, TaskStatus.IMPEDED));
	}

	public void taskDone() {
		if(this.getStatus() != TaskStatus.IN_PROGRESS || this.getStatus() != TaskStatus.IMPEDED)
			throw new IllegalStateException("Task is not in the status of 'IN_PROGRESS' or 'IMPEDED', can not be done.");
		
		this.setStatus(TaskStatus.DONE);
		
		DomainEventPublisher.instance().publish(
				new TaskStatusChanged(this.taskId, TaskStatus.DONE));
	}

	public ProjectId getProjectId() {
		return projectId;
	}

	private void setProjectId(ProjectId projectId) {
		this.projectId = projectId;
	}

	public TaskId getTaskId() {
		return taskId;
	}

	private void setTaskId(TaskId taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	private void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	private void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public TaskStatus getStatus() {
		return status;
	}

	private void setStatus(TaskStatus status) {
		this.status = status;
	}

}
