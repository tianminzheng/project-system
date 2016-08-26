package com.tianyalan.common.domain.model;

public class Entity extends IdentifiedDomainObject {

    private static final long serialVersionUID = 1L;

    protected Entity() {
        super();
    }
    
    protected Entity(long id) {
        super(id);
    }
}
