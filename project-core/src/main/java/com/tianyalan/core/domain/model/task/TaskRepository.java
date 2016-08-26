package com.tianyalan.core.domain.model.task;

import java.util.Collection;

import com.tianyalan.core.domain.model.project.ProjectId;

public interface TaskRepository {

	public TaskId nextIdentity();
	
	public void save(Task task);
	
	public Task taskOfId(TaskId taskId);
	
	public Collection<Task> tasksOfProject(ProjectId projectId);
	
	public void remove(Task task);
	
}
