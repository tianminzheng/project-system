package com.tianyalan.discussion.port.adapter.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("rabbitMQDiscussonFinishedSender")
public class RabbitMQDiscussonFinishedSender {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void pushToMessageQueue(String eventSerialization) {
		amqpTemplate.convertAndSend("discussion_finished_queue", eventSerialization);
		System.out.println("Send message: " + eventSerialization);
	}

}
