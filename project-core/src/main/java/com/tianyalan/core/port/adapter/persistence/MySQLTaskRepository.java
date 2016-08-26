package com.tianyalan.core.port.adapter.persistence;

import java.util.Collection;

import com.tianyalan.core.domain.model.project.ProjectId;
import com.tianyalan.core.domain.model.task.Task;
import com.tianyalan.core.domain.model.task.TaskId;
import com.tianyalan.core.domain.model.task.TaskRepository;

public class MySQLTaskRepository implements TaskRepository {

	@Override
	public TaskId nextIdentity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Task taskOfId(TaskId taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Task> tasksOfProject(ProjectId projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Task task) {
		// TODO Auto-generated method stub
		
	}

}
