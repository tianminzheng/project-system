package com.tianyalan.core.port.adapter.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rabbitMQProjectCreatedSender")
public class RabbitMQProjectCreatedSender { 

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void pushToMessageQueue(String message) {
		amqpTemplate.convertAndSend("project_created_queue", message);
		System.out.println("Send message: " + message);
	}
}
