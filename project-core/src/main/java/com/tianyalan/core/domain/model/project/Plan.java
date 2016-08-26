package com.tianyalan.core.domain.model.project;

import java.util.Date;

import com.tianyalan.common.domain.model.Entity;

public class Plan extends Entity {
	private static final long serialVersionUID = 1L;

	private PlanId planId;
	private ProjectId projectId;
	private Date scheduledDate;

	public Plan(ProjectId projectId, PlanId planId, Date scheduledDate) {
		//计划时间不能早于今天
		if (scheduledDate.before(new Date())) {
			throw new IllegalArgumentException(
					"Scheduled date for project must not before than today.");
		}
		
		this.setPlanId(planId);
		this.setProjectId(projectId);
		this.setScheduledDate(scheduledDate);
	}

	public PlanId getPlanId() {
		return planId;
	}

	private void setPlanId(PlanId planId) {
		this.planId = planId;
	}

	public ProjectId getProjectId() {
		return projectId;
	}

	private void setProjectId(ProjectId projectId) {
		this.projectId = projectId;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	private void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
}
