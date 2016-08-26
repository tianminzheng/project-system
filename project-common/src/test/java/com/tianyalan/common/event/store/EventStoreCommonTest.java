
package com.tianyalan.common.event.store;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

import com.tianyalan.common.event.EventStore;
import com.tianyalan.common.port.adapter.persistence.jdbc.MySQLJDBCEventStore;

//用于被其他上下文所继承并使用
public class EventStoreCommonTest extends TestCase {

    private EventStore eventStore;
    private ApplicationContext applicationContext;
    
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        
        applicationContext = new ClassPathXmlApplicationContext("applicationContext-common.xml");
        
        this.eventStore = MySQLJDBCEventStore.instance();
    }
	
    public EventStoreCommonTest() {
        super();
    }

    protected EventStore eventStore() {

    	return this.eventStore;
    }
}
