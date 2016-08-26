package com.tianyalan.common.domain.model;

import java.io.Serializable;

import com.tianyalan.common.Assertion;

public abstract class AbstractId extends Assertion implements Identity, Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    public String id() {
        return this.id;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            AbstractId typedObject = (AbstractId) anObject;
            equalObjects = this.id().equals(typedObject.id());
        }

        return equalObjects;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + id + "]";
    }

    protected AbstractId(String anId) {
        this();

        this.setId(anId);
    }

    protected AbstractId() {
        super();
    }

    private void setId(String anId) {
        this.assertArgumentNotEmpty(anId, "The basic identity is required.");
//        this.assertArgumentLength(anId, 36, "The basic identity must be 36 characters.");

        this.id = anId;
    }
}
