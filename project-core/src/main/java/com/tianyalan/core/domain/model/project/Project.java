package com.tianyalan.core.domain.model.project;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.tianyalan.common.domain.model.Entity;
import com.tianyalan.common.event.DomainEventPublisher;
import com.tianyalan.core.domain.model.task.Task;

public class Project extends Entity {
	private static final long serialVersionUID = 1L;

	private ProjectId projectId;
	private String name;
	private String description;
	private Set<Task> tasks;
	private ProjectPriority projectPriority;
	private Plan plan;

	public Project() {		
	}
	
	//创建Project
	public Project(ProjectId projectId, String name, String description,
			ProjectPriority projectPriority) {

		this();

		this.setTasks(new HashSet<Task>(0));

		this.setProjectId(projectId);
		this.setName(name);
		this.setDescription(description);
		this.setProjectPriority(projectPriority);

		//发送创建Project的领域事件
		DomainEventPublisher.instance()
				.publish(
						new ProjectCreated(this.projectId.id(), this.description,
								this.name));
	}

	//规划Project
	public Plan planProject(PlanId planId, Date scheduledDate) {
		Plan plan = new Plan(this.projectId, planId, scheduledDate);
		this.setPlan(plan);

		//发送Project计划完成的领域事件
		DomainEventPublisher.instance().publish(
				new ProjectScheduled(this.projectId, scheduledDate));

		return plan;
	}

	public void defineProjectTask(Task task) {
		this.assertArgumentEquals(this.getProjectId(), task.getProjectId(),
				"The task must belong to this project.");

		this.tasks.add(task);
	}

	public ProjectId getProjectId() {
		return projectId;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public ProjectPriority getProjectPriority() {
		return projectPriority;
	}

	public Plan getPlan() {
		return plan;
	}

	private void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	private void setProjectId(ProjectId projectId) {
		this.projectId = projectId;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setProjectPriority(ProjectPriority projectPriority) {
		this.projectPriority = projectPriority;
	}

	private void setPlan(Plan plan) {
		this.plan = plan;
	}

}
