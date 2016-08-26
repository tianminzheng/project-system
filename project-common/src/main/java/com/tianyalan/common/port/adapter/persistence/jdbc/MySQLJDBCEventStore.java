
package com.tianyalan.common.port.adapter.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.tianyalan.common.event.DomainEvent;
import com.tianyalan.common.event.EventSerializer;
import com.tianyalan.common.event.EventStore;
import com.tianyalan.common.event.StoredEvent;
import com.tianyalan.common.port.adapter.persistence.EventStoreException;

public class MySQLJDBCEventStore implements EventStore, ApplicationContextAware {

	private static MySQLJDBCEventStore instance;

	private DataSource eventStoreDataSource;
	private EventSerializer serializer;

	public synchronized static MySQLJDBCEventStore instance() {
		return instance;
	}

	public MySQLJDBCEventStore(DataSource aDataSource) {
		super();

		this.setCollaborationDataSource(aDataSource);
		this.setSerializer(EventSerializer.instance());
	}

	@Override
	public List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId) {
		Connection connection = this.connection();

		ResultSet result = null;

		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT event_id, event_body, type_name, occurred_on FROM common_stored_event "
							+ "WHERE event_id > ? and event_id < ? " + "ORDER BY event_id");

			statement.setLong(1, aLowStoredEventId);
			statement.setLong(2, aHighStoredEventId);

			result = statement.executeQuery();

			List<StoredEvent> storedEvents = this.buildStoredEvents(result);

			connection.commit();

			return storedEvents;

		} catch (Throwable t) {
			throw new EventStoreException(
					"Cannot query event between: " + aLowStoredEventId + " and " + aHighStoredEventId + " because: " + t.getMessage(), t);
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					// ignore
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}

	@Override
	public List<StoredEvent> allStoredEventsSince(long aStoredEventId) {
		Connection connection = this.connection();

		ResultSet result = null;

		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT event_id, event_body, type_name, occurred_on FROM common_stored_event "
							+ "WHERE event_id > ? " + "ORDER BY event_id");

			statement.setLong(1, aStoredEventId);

			result = statement.executeQuery();

			List<StoredEvent> storedEvents = this.buildStoredEvents(result);

			connection.commit();

			return storedEvents;

		} catch (Throwable t) {
			throw new EventStoreException(
					"Cannot query event since: " + aStoredEventId + " because: " + t.getMessage(), t);
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					// ignore
				}
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}

	@Override
	public StoredEvent append(DomainEvent aDomainEvent) {
		Connection connection = this.connection();

		StoredEvent storedEvent = null;
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(
					"INSERT INTO common_stored_event(event_body, occurred_on, type_name) VALUES(?, ?, ?)");

			String eventSerialization = EventSerializer.instance().serialize(aDomainEvent);

			storedEvent = new StoredEvent(aDomainEvent.getClass().getName(), aDomainEvent.occurredOn(),
					eventSerialization);

			statement.setString(1, storedEvent.getEventBody());
			statement.setTimestamp(2, new java.sql.Timestamp(storedEvent.getOccurredOn().getTime()));
			statement.setString(3, storedEvent.getTypeName());

			statement.executeUpdate();

			connection.commit();
		} catch (Throwable t) {
			throw new EventStoreException("Append event failed, because: " + t.getMessage(), t);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// ignore
			}
		}

		return storedEvent;
	}

	@Override
	public long countStoredEvents() {
		Connection connection = this.connection();
		PreparedStatement statement;

		long result = 0;
		try {
			statement = connection.prepareStatement("SELECT count(*) as count FROM common_stored_event");

			ResultSet rs = statement.executeQuery();
			
			rs.next();
			result = rs.getInt("count");
		} catch (Throwable t) {
			throw new EventStoreException("Query count of events failed, because: " + t.getMessage(), t);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// ignore
			}
		}

		return result;
	}

	@Override
	public void close() {
		// no-op
	}
	
	private List<StoredEvent> buildStoredEvents(ResultSet aResultSet) throws Exception {

        List<StoredEvent> events = new ArrayList<StoredEvent>();

        while (aResultSet.next()) {
            long eventId = aResultSet.getLong("event_id");

            String eventClassName = aResultSet.getString("type_name");

            String eventBody = aResultSet.getString("event_body");
            
            Date occurredOn = aResultSet.getDate("occurred_on");

//            Class<DomainEvent> eventClass = (Class<DomainEvent>) Class.forName(eventClassName);
//
//            DomainEvent domainEvent = this.serializer().deserialize(eventBody, eventClass);

            events.add(new StoredEvent(eventClassName, occurredOn, eventBody, eventId));
        }

        return events;
    }

	private DataSource collaborationDataSource() {
		return this.eventStoreDataSource;
	}

	private void setCollaborationDataSource(DataSource aDataSource) {
		this.eventStoreDataSource = aDataSource;
	}

	private Connection connection() {
		Connection connection = null;

		try {
			connection = this.collaborationDataSource().getConnection();
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot acquire database connection.");
		}

		return connection;
	}

	private void setSerializer(EventSerializer aSerializer) {
		this.serializer = aSerializer;
	}

	@Override
	public synchronized void setApplicationContext(ApplicationContext anApplicationContext) throws BeansException {
		instance = (MySQLJDBCEventStore) anApplicationContext.getBean("mysqlJdbcEventStore");
	}
}
