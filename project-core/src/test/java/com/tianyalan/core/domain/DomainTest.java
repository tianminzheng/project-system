package com.tianyalan.core.domain;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianyalan.common.event.DomainEventPublisher;
import com.tianyalan.core.domain.event.EventTest;
import com.tianyalan.core.domain.event.EventWithMQTest;
import com.tianyalan.core.domain.model.project.ProjectRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-core.xml" })
public abstract class DomainTest extends EventWithMQTest {
	
	@Autowired
	protected ProjectRepository projectRepository;
	
	@Override
	protected void setUp() throws Exception {

		DomainEventPublisher.instance().reset();
		
		super.setUp();
	}
}
