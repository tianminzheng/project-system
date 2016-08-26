package com.tianyalan.core.domain.model.project;

import com.tianyalan.common.domain.model.AbstractId;

public class ProjectId extends AbstractId {
	private static final long serialVersionUID = 1L;

	public ProjectId() {
		super();
	}
	
	public ProjectId(String projectId) {
		super(projectId);
	}
}
