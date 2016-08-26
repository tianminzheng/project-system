package com.tianyalan.usercenter.domain.model.user;

import java.util.Date;

import com.tianyalan.common.domain.model.ValueObject;

public class Enablement extends ValueObject {

	private boolean enabled;
    private Date endDate;
    private Date startDate;
    
    public Enablement(boolean anEnabled, Date aStartDate, Date anEndDate) {
        this();

        //可用性参数验证
        if (aStartDate != null || anEndDate != null) {
            this.assertArgumentNotNull(aStartDate, "The start date must be provided.");
            this.assertArgumentNotNull(anEndDate, "The end date must be provided.");
            this.assertArgumentFalse(aStartDate.after(anEndDate), "Enablement start and/or end date is invalid.");
        }

        this.setEnabled(anEnabled);
        this.setEndDate(anEndDate);
        this.setStartDate(aStartDate);
    }
    
    public Enablement() {
    	
    }
    
    public static Enablement indefiniteEnablement() {
        return new Enablement(true, null, null);
    }
    
    public Enablement(Enablement anEnablement) {
        this(anEnablement.isEnabled(), anEnablement.startDate(), anEnablement.endDate());
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isEnablementEnabled() {
        boolean enabled = false;

        if (this.isEnabled()) {
            if (!this.isTimeExpired()) {
                enabled = true;
            }
        }

        return enabled;
    }

    public Date endDate() {
        return this.endDate;
    }

    public boolean isTimeExpired() {
        boolean timeExpired = false;

        if (this.startDate() != null && this.endDate() != null) {
            Date now = new Date();
            if (now.before(this.startDate()) ||
                now.after(this.endDate())) {
                timeExpired = true;
            }
        }

        return timeExpired;
    }

    public Date startDate() {
        return this.startDate;
    }
    
    private void setEnabled(boolean anEnabled) {
        this.enabled = anEnabled;
    }

    private void setEndDate(Date anEndDate) {
        this.endDate = anEndDate;
    }

    private void setStartDate(Date aStartDate) {
        this.startDate = aStartDate;
    }

	
}
