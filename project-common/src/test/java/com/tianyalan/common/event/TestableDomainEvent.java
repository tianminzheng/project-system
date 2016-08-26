
package com.tianyalan.common.event;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

public class TestableDomainEvent implements DomainEvent {

    private long id;
    private String name;
    private Date occurredOn;

    public TestableDomainEvent(long anId, String aName) {
        super();

        this.setId(anId);
        this.setName(aName);
        this.setOccurredOn(new Date());
    }

    public long id() {
        return id;
    }

    public String name() {
        return name;
    }

    @Override
    public Date occurredOn() {
        return this.occurredOn;
    }

    private void setId(long id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setOccurredOn(Date occurredOn) {
        this.occurredOn = occurredOn;
    }
}
