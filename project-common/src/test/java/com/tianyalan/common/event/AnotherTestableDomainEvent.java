
package com.tianyalan.common.event;

import java.util.Date;

import com.tianyalan.common.event.DomainEvent;

public class AnotherTestableDomainEvent implements DomainEvent {

    private Date occurredOn;
    private double value;

    public AnotherTestableDomainEvent(double aValue) {
        super();
        this.setOccurredOn(new Date());
        this.setValue(aValue);
    }
    
    @Override
    public Date occurredOn() {
        return this.occurredOn;
    }

    public double value() {
        return this.value;
    }

    private void setOccurredOn(Date occurredOn) {
        this.occurredOn = occurredOn;
    }

    private void setValue(double value) {
        this.value = value;
    }
}
