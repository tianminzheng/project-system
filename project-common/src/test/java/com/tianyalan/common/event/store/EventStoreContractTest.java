package com.tianyalan.common.event.store;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tianyalan.common.event.DomainEventPublisher;
import com.tianyalan.common.event.EventStore;
import com.tianyalan.common.event.StoredEvent;
import com.tianyalan.common.event.TestableDomainEvent;
import com.tianyalan.common.port.adapter.persistence.jdbc.MySQLJDBCEventStore;

public class EventStoreContractTest extends TestCase {

	private ApplicationContext applicationContext;
	private EventStore eventStore;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		//重置Publisher
		DomainEventPublisher.instance().reset();

		//加载容器配置
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext-common.xml");

		//获取基于MYSQL的数据持久化组件
		this.eventStore = MySQLJDBCEventStore.instance();
	}

	public EventStoreContractTest() {
		super();
	}

	public void testAllStoredEventsBetween() throws Exception {
		EventStore eventStore = this.eventStore();

		long totalEvents = eventStore.countStoredEvents();

		assertEquals(totalEvents,
				eventStore.allStoredEventsBetween(1, totalEvents).size());
	}

	public void testAllStoredEventsSince() throws Exception {
		EventStore eventStore = this.eventStore();

		long totalEvents = eventStore.countStoredEvents();

		assertEquals(totalEvents, eventStore.allStoredEventsSince(0).size());

		assertEquals(0, eventStore.allStoredEventsSince(totalEvents).size());

		if(totalEvents >= 10) {
			assertEquals(10, eventStore.allStoredEventsSince(totalEvents - 10)
					.size());
		}
	}

	public void testAppend() throws Exception {
		EventStore eventStore = this.eventStore();

		long numberOfEvents = eventStore.countStoredEvents();

		TestableDomainEvent domainEvent = new TestableDomainEvent(10001,
				"testDomainEvent");

		StoredEvent storedEvent = eventStore.append(domainEvent);

		assertTrue(eventStore.countStoredEvents() > numberOfEvents);
		assertEquals(eventStore.countStoredEvents(), numberOfEvents + 1);

		assertNotNull(storedEvent);
		
		//反向转换成DomainEvent
		TestableDomainEvent reconstitutedDomainEvent = storedEvent
				.toDomainEvent();

		assertNotNull(reconstitutedDomainEvent);
		assertEquals(domainEvent.id(), reconstitutedDomainEvent.id());
		assertEquals(domainEvent.name(), reconstitutedDomainEvent.name());
		assertEquals(domainEvent.occurredOn(),
				reconstitutedDomainEvent.occurredOn());
	}

	public void testCountStoredEvents() throws Exception {
		EventStore eventStore = this.eventStore();

		long numberOfEvents = eventStore.countStoredEvents();

		TestableDomainEvent lastDomainEvent = null;

		for (int idx = 0; idx < 10; ++idx) {
			TestableDomainEvent domainEvent = new TestableDomainEvent(
					10001 + idx, "testDomainEvent" + idx);

			lastDomainEvent = domainEvent;

			eventStore.append(domainEvent);
		}

		assertEquals(numberOfEvents + 10, eventStore.countStoredEvents());

		numberOfEvents = eventStore.countStoredEvents();

		assertEquals(
				10,
				eventStore.allStoredEventsBetween(numberOfEvents,
						numberOfEvents + 1000).size());
	}

	public void testStoredEvent() throws Exception {
		EventStore eventStore = this.eventStore();

		TestableDomainEvent domainEvent = new TestableDomainEvent(10001,
				"testDomainEvent");

		StoredEvent storedEvent = eventStore.append(domainEvent);

		assertNotNull(storedEvent);

		TestableDomainEvent reconstitutedDomainEvent = storedEvent
				.toDomainEvent();

		assertNotNull(reconstitutedDomainEvent);
		assertEquals(domainEvent.id(), reconstitutedDomainEvent.id());
		assertEquals(domainEvent.name(), reconstitutedDomainEvent.name());
		assertEquals(domainEvent.occurredOn(),
				reconstitutedDomainEvent.occurredOn());
	}

	protected EventStore eventStore() {
		return this.eventStore;
		
		//使用Mock版本EventStore
//		return this.mockEventStore();
	}

	protected EventStore mockEventStore() {
		EventStore eventStore = new MockEventStore();

		assertNotNull(eventStore);

		return eventStore;
	}
}
