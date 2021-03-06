package com.tianyalan.common.port.adapter.persistence;

public class EventStoreException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EventStoreException(String aMessage) {
        super(aMessage);
    }

    public EventStoreException(String aMessage, Throwable aCause) {
        super(aMessage, aCause);
    }
}
