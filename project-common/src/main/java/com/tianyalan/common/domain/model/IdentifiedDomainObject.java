package com.tianyalan.common.domain.model;

import java.io.Serializable;

import com.tianyalan.common.Assertion;

public class IdentifiedDomainObject extends Assertion implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    protected IdentifiedDomainObject() {
        super();

        this.setId(-1);
    }
    
    protected IdentifiedDomainObject(long id) {
    	super();
    	
    	this.setId(id);
    }

    protected long id() {
        return this.id;
    }

    private void setId(long anId) {
        this.id = anId;
    }
}
