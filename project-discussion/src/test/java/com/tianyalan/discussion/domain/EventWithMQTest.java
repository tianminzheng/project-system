package com.tianyalan.discussion.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.common.event.DomainEvent;
import com.tianyalan.common.event.DomainEventPublisher;
import com.tianyalan.common.event.DomainEventSubscriber;
import com.tianyalan.common.event.EventSerializer;
import com.tianyalan.common.event.store.EventStoreCommonTest;
import com.tianyalan.discussion.domain.model.discussion.DiscussionClosed;
import com.tianyalan.discussion.port.adapter.messaging.RabbitMQDiscussonFinishedSender;

//继承自Common工程中的EventStoreCommonTest
public abstract class EventWithMQTest extends EventStoreCommonTest {
	private List<Class<? extends DomainEvent>> handledEvents;
	
	@Autowired
	private RabbitMQDiscussonFinishedSender rabbitMQDiscussonFinishedSender;

	@Override
    protected void setUp() throws Exception {
		Thread.sleep(100L);
		
		DomainEventPublisher.instance().reset();
		DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<DomainEvent>() {
            @Override
            public void handleEvent(DomainEvent aDomainEvent) {
                handledEvents.add(aDomainEvent.getClass());
                
                //保存Event
                eventStore().append(aDomainEvent);
                
                //发布Event
                String eventSerialization = EventSerializer.instance().serialize(aDomainEvent);
                if(aDomainEvent.getClass() == DiscussionClosed.class) {
                	rabbitMQDiscussonFinishedSender.pushToMessageQueue(eventSerialization);
                }
            }

            @Override
            public Class<DomainEvent> subscribedToEventType() {
                return DomainEvent.class;
            }
        });

        this.handledEvents = new ArrayList<Class<? extends DomainEvent>>();
     
        super.setUp();
	}
	
	protected void expectedEvent(Class<? extends DomainEvent> aDomainEventType) {
        this.expectedEvent(aDomainEventType, 1);
    }
	
	protected void expectedEvent(Class<? extends DomainEvent> aDomainEventType, int aTotal) {
        int count = 0;

        for (Class<? extends DomainEvent> type : this.handledEvents) {
            if (type == aDomainEventType) {
                ++count;
            }
        }

        if (count != aTotal) {
            throw new IllegalStateException("Expected " + aTotal + " " + aDomainEventType.getSimpleName() + " events, but handled "
                    + this.handledEvents.size() + " events: " + this.handledEvents);
        }
    }
	
	protected void expectedEvents(int anEventCount) {
        if (this.handledEvents.size() != anEventCount) {
            throw new IllegalStateException("Expected " + anEventCount + " events, but handled " + this.handledEvents.size()
                    + " events: " + this.handledEvents);
        }
    }
}
