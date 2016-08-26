package com.tianyalan.common.port.adapter.messaging;

public class MessageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private boolean retry;

    public MessageException(String aMessage, Throwable aCause, boolean isRetry) {
        super(aMessage, aCause);
        this.setRetry(isRetry);
    }

    public MessageException(String aMessage, Throwable aCause) {
        super(aMessage, aCause);
    }

    public MessageException(String aMessage, boolean isRetry) {
        super(aMessage);
        this.setRetry(isRetry);
    }

    public MessageException(String aMessage) {
        super(aMessage);
    }

    public boolean isRetry() {
        return this.retry;
    }

    private void setRetry(boolean aRetry) {
        this.retry = aRetry;
    }
}
