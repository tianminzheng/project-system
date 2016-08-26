package com.tianyalan.core.port.adapter.persistence;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianyalan.common.persistence.MyBatisDAO;
import com.tianyalan.core.domain.model.project.Plan;
import com.tianyalan.core.domain.model.project.PlanId;
import com.tianyalan.core.domain.model.project.Project;
import com.tianyalan.core.domain.model.project.ProjectId;
import com.tianyalan.core.domain.model.project.ProjectRepository;

@SuppressWarnings({"unchecked"})
@Repository("projectRepository")
public class MySQLProjectRepository implements ProjectRepository {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public Collection<Project> allProjects() {
		List<Project> list = myBatisDAO.findForList("allProjects");

		return list;
	}

	@Override
	public ProjectId nextIdentity() {
		//使用UUID
		return new ProjectId("PROJECT_" + UUID.randomUUID().toString().toUpperCase());
	}

	@Override
	public void saveProject(Project project) {
		myBatisDAO.insert("saveProject", project);
	}

	@Override
	public Project projectOfId(ProjectId projectId) {
		return (Project) myBatisDAO.findForObject("projectOfId", projectId);
	}

	@Override
	public void removeProject(ProjectId projectId) {
		myBatisDAO.delete("removeProject", projectId);
	}

	@Override
	public PlanId nextPlanIdentity() {
		return new PlanId("PLAN_" + UUID.randomUUID().toString().toUpperCase());
	}

	@Override
	public void savePlan(Plan plan) {
		myBatisDAO.insert("savePlan", plan);
	}

}
