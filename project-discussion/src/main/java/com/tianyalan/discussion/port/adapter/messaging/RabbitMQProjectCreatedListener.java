package com.tianyalan.discussion.port.adapter.messaging;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.common.event.EventSerializer;
import com.tianyalan.common.event.DomainEvent;
import com.tianyalan.discussion.application.discussion.DiscussionApplicationService;

@Service("rabbitMQProjectCreatedListener")
public class RabbitMQProjectCreatedListener implements MessageListener {

	@Autowired
	private DiscussionApplicationService discussionApplicationService;

	public void onMessage(Message message) {
		String projectCreatedEvent = new String(message.getBody());

		System.out.println("Receive message: " + projectCreatedEvent);

		try {
			ProjectCreatedObject projectCreatedObject = EventSerializer
					.instance().deserialize(projectCreatedEvent,
							ProjectCreatedObject.class);

			discussionApplicationService.newDiscussion(
					projectCreatedObject.getProjectId(), "tianyalan",
					"tianyalan", null, projectCreatedObject.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private class ProjectCreatedObject implements DomainEvent {
		private String projectId;
		private String description;
		private String name;
		private Date occurredOn;

		public ProjectCreatedObject(String projectId, String name,
				String description) {

			super();

			this.projectId = projectId;
			this.name = name;
			this.description = description;
			this.occurredOn = new Date();
		}

		public String getProjectId() {
			return projectId;
		}

		public String getDescription() {
			return description;
		}

		public String getName() {
			return name;
		}

		@Override
		public Date occurredOn() {
			return this.occurredOn;
		}
	}

}
