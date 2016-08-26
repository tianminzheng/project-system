package com.tianyalan.usercenter.application.command;

import java.util.Date;

public class DefineUserEnablementCommand {
	private String username;
	private boolean enabled;
	private Date startDate;
	private Date endDate;

	public DefineUserEnablementCommand(String username, boolean enabled, Date startDate, Date endDate) {

		super();

		this.username = username;
		this.enabled = enabled;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
