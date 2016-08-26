
package com.tianyalan.common.event.store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tianyalan.common.Assertion;
import com.tianyalan.common.event.DomainEvent;
import com.tianyalan.common.event.EventSerializer;
import com.tianyalan.common.event.EventStore;
import com.tianyalan.common.event.StoredEvent;
import com.tianyalan.common.event.TestableDomainEvent;

public class MockEventStore extends Assertion implements EventStore {

	private static final long START_ID = 789;

	private List<StoredEvent> storedEvents;

	public MockEventStore() {
		super();

		this.storedEvents = new ArrayList<StoredEvent>();

		int numberOfStoredEvents = Calendar.getInstance().get(Calendar.MILLISECOND) + 1; // 1-1000

		if (numberOfStoredEvents < 21) {
			numberOfStoredEvents = 21;
		}

		for (int idx = 0; idx < numberOfStoredEvents; ++idx) {
			StoredEvent storedEvent = this.newStoredEvent(START_ID + idx, idx + 1);

			this.storedEvents.add(storedEvent);
		}
	}

	@Override
	public List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId) {
		List<StoredEvent> events = new ArrayList<StoredEvent>();

		for (StoredEvent storedEvent : this.storedEvents) {
			if (storedEvent.getEventId() >= aLowStoredEventId && storedEvent.getEventId() <= aHighStoredEventId) {
				events.add(storedEvent);
			}
		}

		return events;
	}

	@Override
	public List<StoredEvent> allStoredEventsSince(long aStoredEventId) {
		return this.allStoredEventsBetween(aStoredEventId + 1, this.countStoredEvents());
	}

	@Override
	public StoredEvent append(DomainEvent aDomainEvent) {
		String eventSerialization = EventSerializer.instance().serialize(aDomainEvent);

		StoredEvent storedEvent = new StoredEvent(aDomainEvent.getClass().getName(), aDomainEvent.occurredOn(),
				eventSerialization, this.storedEvents.size() + 1);

		this.storedEvents.add(storedEvent);

		return storedEvent;
	}

	@Override
	public void close() {
	}

	@Override
	public long countStoredEvents() {
		return this.storedEvents.size();
	}

	private StoredEvent newStoredEvent(long domainEventId, long storedEventId) {
		EventSerializer serializer = EventSerializer.instance();

		DomainEvent event = new TestableDomainEvent(domainEventId, "name" + domainEventId);
		String serializedEvent = serializer.serialize(event);
		StoredEvent storedEvent = new StoredEvent(event.getClass().getName(), event.occurredOn(), serializedEvent,
				storedEventId);

		return storedEvent;
	}
}
