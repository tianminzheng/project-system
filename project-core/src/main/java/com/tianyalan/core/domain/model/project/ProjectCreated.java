package com.tianyalan.core.domain.model.project; 

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

//当Project被创建时，触发ProjectCreated事件
public class ProjectCreated implements DomainEvent {

	private String projectId;
	private String description;
	private String name;
	private Date occurredOn;

	public ProjectCreated(String projectId, String name, String description) {

		super();

		this.projectId = projectId;
		this.name = name;
		this.description = description;
		this.occurredOn = new Date();
	}

	public String getProjectId() {
		return projectId;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	@Override
	public Date occurredOn() {
		return this.occurredOn;
	}

}
