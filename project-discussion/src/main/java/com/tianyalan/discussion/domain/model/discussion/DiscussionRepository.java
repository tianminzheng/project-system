package com.tianyalan.discussion.domain.model.discussion;

public interface DiscussionRepository {

	public Discussion discussionOfId(DiscussionId aDiscussionId);

    public DiscussionId nextIdentity();

    public void saveDiscussion(Discussion aDiscussion);
    
    public void updateDiscussion(Discussion aDiscussion);
    
}
