package com.tianyalan.core.application.task;

import com.tianyalan.core.domain.model.project.Project;
import com.tianyalan.core.domain.model.project.ProjectId;
import com.tianyalan.core.domain.model.project.ProjectRepository;
import com.tianyalan.core.domain.model.task.Task;
import com.tianyalan.core.domain.model.task.TaskId;
import com.tianyalan.core.domain.model.task.TaskRepository;

public class TaskApplicationService {

	private TaskRepository taskRepository;
	private ProjectRepository projectRepository;

	public TaskApplicationService(TaskRepository taskRepository,
			ProjectRepository projectRepository) {
		this.taskRepository = taskRepository;
		this.projectRepository = projectRepository;
	}

	public String newTask(DefineTaskCommand command) {
		TaskId taskId = this.taskRepository.nextIdentity();

		ProjectId projectId = new ProjectId(command.getProjectId());
		Project project = projectRepository.projectOfId(projectId);

		if (project == null) {
			throw new IllegalStateException("Unknown project id: "
					+ command.getProjectId());
		}

		Task task = new Task(projectId, taskId, command.getTaskName(),
				command.getTaskDescription());
		
		this.taskRepository.save(task);

		return taskId.id();
	}
}
