package com.tianyalan.core.application.project;

public class NewProjectCommand {

	private String projectName;
	private String projectDescription;
	private int projectBenefit;
	private int projectCost;
	private int projectRisk;

	public NewProjectCommand(String projectName, String projectDescription,
			int projectBenefit, int projectCost, int projecRisk) {
		
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.projectBenefit = projectBenefit;
		this.projectCost = projectCost;
		this.projectRisk = projecRisk;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public int getProjectBenefit() {
		return projectBenefit;
	}

	public void setProjectBenefit(int projectBenefit) {
		this.projectBenefit = projectBenefit;
	}

	public int getProjectCost() {
		return projectCost;
	}

	public void setProjectCost(int projectCost) {
		this.projectCost = projectCost;
	}

	public int getProjectRisk() {
		return projectRisk;
	}

	public void setProjectRisk(int projecRisk) {
		this.projectRisk = projecRisk;
	}
}
