package com.tianyalan.core.domain.model.project;

import java.util.Date;

import org.junit.Test;

import com.tianyalan.core.domain.DomainTest;

public class ProjectTest extends DomainTest {
	
	public ProjectTest() throws Exception {
		super.setUp();
	}
	
	@Test
	public void testCreate() throws Exception {

		ProjectPriority projectPriority = new ProjectPriority(300, 200, 50);
		Project project = new Project(this.projectRepository.nextIdentity(), "Project1",
				"TestProject", projectPriority);
		
		assertNotNull(project);
		assertEquals("Project1", project.getName());
        assertEquals("TestProject", project.getDescription());
        
        expectedEvents(1);
        expectedEvent(ProjectCreated.class);
	}
	
	@Test
	public void testPlanProject() throws Exception {
		
		ProjectPriority projectPriority = new ProjectPriority(300, 200, 50);
		Project project = new Project(new ProjectId("P12345"), "Project1",
				"TestProject", projectPriority);
		
		Plan plan = project.planProject(this.projectRepository.nextPlanIdentity(), new Date());
		
		assertNotNull(plan);
		assertNotNull(project.getPlan());

        expectedEvents(2);
        expectedEvent(ProjectCreated.class);
        expectedEvent(ProjectScheduled.class);
	}
}
