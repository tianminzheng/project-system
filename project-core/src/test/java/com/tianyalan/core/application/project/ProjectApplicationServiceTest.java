package com.tianyalan.core.application.project;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.core.domain.DomainTest;

public class ProjectApplicationServiceTest extends DomainTest {
	
	public ProjectApplicationServiceTest() throws Exception {
		super.setUp();
	}
	
	@Autowired
	ProjectApplicationService projectApplicationService;
	
	@Test
	public void testNewProject() {
		NewProjectCommand command = new NewProjectCommand("TestProject",
				"The first project", 300, 200, 50);

		String newProjectId = projectApplicationService.newProject(command);
		
		System.out.print(newProjectId);
	}
	
	@Test
	public void testPlanProject() {
		PlanProjectCommand command = new PlanProjectCommand("PROJECT_A41EA0A7-41AA-4EEC-BF16-D924C269309E",
				"2016-06-30");

		projectApplicationService.planProject(command);
	}
}
