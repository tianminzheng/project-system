package com.tianyalan.core.domain.model.project;

import java.util.Collection;

public interface ProjectRepository {

	public Collection<Project> allProjects();

	//获取Project的唯一标识，及早获取机制
	public ProjectId nextIdentity();
	
	public void saveProject(Project project);
	
	public Project projectOfId(ProjectId projectId);
	
	public void removeProject(ProjectId projectId);
	
	//获取Plan的唯一标识，及早获取机制
	public PlanId nextPlanIdentity();
	
	public void savePlan(Plan plan);
}
