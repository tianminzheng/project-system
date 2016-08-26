package com.tianyalan.discussion.port.adapter.persistence;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianyalan.common.persistence.MyBatisDAO;
import com.tianyalan.discussion.domain.model.discussion.Discussion;
import com.tianyalan.discussion.domain.model.discussion.DiscussionId;
import com.tianyalan.discussion.domain.model.discussion.DiscussionRepository;

@Repository("discussionRepository")
public class MySQLDiscussionRepository implements DiscussionRepository {

	@Autowired
	private MyBatisDAO myBatisDAO;
	
	@Override
	public Discussion discussionOfId(DiscussionId aDiscussionId) {
		return (Discussion) myBatisDAO.findForObject("discussionOfId", aDiscussionId);
	}

	@Override
	public DiscussionId nextIdentity() {
		return new DiscussionId("DISCUSSION_" + UUID.randomUUID().toString().toUpperCase());
	}

	@Override
	public void saveDiscussion(Discussion aDiscussion) {
		myBatisDAO.insert("saveDiscussion", aDiscussion);
		
	}

	@Override
	public void updateDiscussion(Discussion aDiscussion) {
		myBatisDAO.update("updateDiscussion", aDiscussion);
	}

}
