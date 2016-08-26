package com.tianyalan.core.port.adapter.messaging;

import java.util.Date;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.common.event.DomainEvent;
import com.tianyalan.common.event.EventSerializer;
import com.tianyalan.core.application.project.PlanProjectCommand;
import com.tianyalan.core.application.project.ProjectApplicationService;

@Service("rabbitMQDiscussionFinishedListener")
public class RabbitMQDiscussionFinishedListener implements MessageListener {
	
	@Autowired
	private ProjectApplicationService projectApplicationService;

	public void onMessage(Message message) {
		String discussionFinishedEvent = new String(message.getBody());
		
		System.out.println("Receive message: " + new String(message.getBody()));
		
		try {
			DiscussionClosedObject discussionClosedObject = EventSerializer.instance().deserialize(discussionFinishedEvent, DiscussionClosedObject.class);
			
			PlanProjectCommand command = new PlanProjectCommand(discussionClosedObject.getProjectId(), discussionClosedObject.getScheduledDate().toString());
			
			projectApplicationService.planProject(command);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private class DiscussionClosedObject implements DomainEvent {

		private String projectId;
		private String scheduledDate;
	    private Date occurredOn;
	    
	    public DiscussionClosedObject(String projectId, String scheduledDate) {
	    	this.projectId = projectId;
	    	this.scheduledDate = scheduledDate;
	    	
	    	this.occurredOn = new Date();
	    }

		public String getProjectId() {
			return projectId;
		}

		public String getScheduledDate() {
			return scheduledDate;
		}

		@Override
		public Date occurredOn() {
			return this.occurredOn;
		}
	}
}
