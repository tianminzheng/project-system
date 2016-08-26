package com.tianyalan.discussion.domain.model.discussion;

import com.tianyalan.common.domain.model.AbstractId;

public class DiscussionId extends AbstractId {
	private static final long serialVersionUID = 1L;

	public DiscussionId() {
		super();
	}
	
	public DiscussionId(String discussionId) {
		super(discussionId);
	}
}
