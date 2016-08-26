package com.tianyalan.core.application.project;

public class PlanProjectCommand {

	private String projectId;
	private String dateScheduled;
	
	public PlanProjectCommand(String projectId, String dateScheduled) {
		this.projectId = projectId;
		this.dateScheduled = dateScheduled;
	}
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getDateScheduled() {
		return dateScheduled;
	}
	public void setDateScheduled(String dateScheduled) {
		this.dateScheduled = dateScheduled;
	}
}
