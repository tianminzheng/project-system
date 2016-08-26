package com.tianyalan.core.port.adapter.persistence;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.springframework.util.Assert;

import com.tianyalan.core.domain.DomainTest;
import com.tianyalan.core.domain.model.project.Plan;
import com.tianyalan.core.domain.model.project.PlanId;
import com.tianyalan.core.domain.model.project.Project;
import com.tianyalan.core.domain.model.project.ProjectId;
import com.tianyalan.core.domain.model.project.ProjectPriority;

public class MySQLProjectRepositoryTest extends DomainTest {

	@Test
	public void testGetAllProjects() {
		Collection<Project> projects = this.projectRepository.allProjects();

		Assert.isTrue(projects.size() > 0);
	}

	@Test
	public void testNextIdentity() {
		ProjectId projectId = this.projectRepository.nextIdentity();

		Assert.isTrue(projectId.id() != null);
	}

	@Test
	public void testRemove() {
		ProjectId projectId = new ProjectId(
				"PROJECT_581F9F50-58F5-4446-B150-34BB52B9B52A");

		this.projectRepository.removeProject(projectId);

		Project project = this.projectRepository.projectOfId(projectId);
		Assert.isNull(project);
	}

	@Test
	public void testProjectOfId() {
		ProjectId projectId = new ProjectId(
				"PROJECT_565C4FC0-FAF2-415A-A4B0-6458ABC5F701");

		Project project = this.projectRepository.projectOfId(projectId);
		Assert.isTrue(project != null);
	}

	@Test
	public void testSave() {
		ProjectId projectId = this.projectRepository.nextIdentity();

		System.out.println(projectId.id());

		//先获取
		Project project = this.projectRepository.projectOfId(projectId);
		Assert.isNull(project);

		ProjectPriority projectPriority = new ProjectPriority(300, 200, 50);
		Project newProject = new Project(projectId, "Project1", "TestProject",
				projectPriority);
		
		//再添加
		this.projectRepository.saveProject(newProject);

		//再获取并验证
		project = this.projectRepository.projectOfId(projectId);

		Assert.isTrue(project != null);
		
		//唯一标识ProjectId相同，则为同一个Project
		Assert.isTrue(project.getProjectId().equals(newProject.getProjectId()));
	}
	
	@Test
	public void testNextPlanIdentity() {
		PlanId planId = this.projectRepository.nextPlanIdentity();

		Assert.isTrue(planId.id() != null);
	}

	@Test
	public void testSavePlan() {
		PlanId planId = this.projectRepository.nextPlanIdentity();
		
		System.out.println(planId.id());
		
		ProjectId projectId = new ProjectId(
				"PROJECT_D9D727C6-EB63-4188-95B6-46F3E757ED4C");
		Project project = this.projectRepository.projectOfId(projectId);
		
		Plan plan = project.planProject(planId, new Date());
		
		this.projectRepository.savePlan(plan);	
	}
}
