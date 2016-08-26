package com.tianyalan.core.application.project;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.common.util.DateUtil;
import com.tianyalan.core.domain.model.project.Plan;
import com.tianyalan.core.domain.model.project.PlanId;
import com.tianyalan.core.domain.model.project.Project;
import com.tianyalan.core.domain.model.project.ProjectId;
import com.tianyalan.core.domain.model.project.ProjectPriority;
import com.tianyalan.core.domain.model.project.ProjectRepository;

@Service("projectApplicationService")
public class ProjectApplicationService {

	@Autowired
	private ProjectRepository projectRepository;

	public String newProject(NewProjectCommand command) {

		ProjectId projectId = this.projectRepository().nextIdentity();

		ProjectPriority projectPriority = new ProjectPriority(
				command.getProjectBenefit(), command.getProjectCost(),
				command.getProjectRisk());

		Project project = new Project(projectId, command.getProjectName(),
				command.getProjectDescription(), projectPriority);

		this.projectRepository().saveProject(project);

		return projectId.id();
	}

	public void planProject(PlanProjectCommand command) {

		ProjectId projectId = new ProjectId(command.getProjectId());
		Project project = this.projectRepository().projectOfId(projectId);

		if (project == null) {
			throw new IllegalStateException("Unknown project id: "
					+ command.getProjectId());
		}
		
		PlanId planId = this.projectRepository().nextPlanIdentity(); 
		Date scheduledDate = DateUtil.parseDate(command.getDateScheduled());
		
		Plan plan = project.planProject(planId, scheduledDate);	
		this.projectRepository().savePlan(plan);
	}

	private ProjectRepository projectRepository() {
		return this.projectRepository;
	}
}
