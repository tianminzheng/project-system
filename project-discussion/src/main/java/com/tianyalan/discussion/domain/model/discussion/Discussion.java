package com.tianyalan.discussion.domain.model.discussion;

import java.util.Collection;
import java.util.Date;

import com.tianyalan.common.domain.model.Entity;
import com.tianyalan.common.event.DomainEventPublisher;
import com.tianyalan.common.util.DateUtil;
import com.tianyalan.discussion.domain.model.participant.Owner;
import com.tianyalan.discussion.domain.model.participant.Participant;
import com.tianyalan.discussion.domain.model.participant.Recorder;

public class Discussion extends Entity {
	private static final long serialVersionUID = 1L;

	private DiscussionId discussionId;
	private String projectId;
	private boolean closed;
	private Owner owner;
	private Recorder recorder;
	private Collection<Participant> participants;
	private String subject;
	private Date scheduledDate;

	public Discussion(DiscussionId discussionId, String projectId, Owner owner, Recorder recorder,
			Collection<Participant> participants, String subject) {

		super();

		this.assertArgumentNotNull(owner, "The owner must be provided.");
		this.assertArgumentNotNull(discussionId, "The discussion id must be provided.");
		this.assertArgumentNotEmpty(subject, "The subject must be provided.");

		this.setDiscussionId(discussionId);
		this.setProjectId(projectId);
		this.setOwner(owner);
		this.setRecorder(recorder);
		this.setParticipants(participants);
		this.setSubject(subject);
		this.setClosed(false);

		//发送启动Discussion事件
		DomainEventPublisher.instance().publish(new DiscussionStarted(this.discussionId()));
	}
	
	public Discussion() {
		
	}

	public void close(Date scheduledDate) {
		if (this.isClosed()) {
			throw new IllegalStateException("This discussion is already closed.");
		}

		this.setScheduledDate(scheduledDate);

		//发送关闭Discussion事件
		DomainEventPublisher.instance().publish(new DiscussionClosed(this.projectId(), DateUtil.formatDate(this.scheduledDate)));
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public boolean isClosed() {
		return this.closed;
	}

	public DiscussionId discussionId() {
		return this.discussionId;
	}

	public String subject() {
		return this.subject;
	}

	public Owner getOwner() {
		return owner;
	}

	public Recorder getRecorder() {
		return recorder;
	}

	public Collection<Participant> getParticipants() {
		return participants;
	}

	public String projectId() {
		return projectId;
	}

	private void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	private void setOwner(Owner owner) {
		this.owner = owner;
	}

	private void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}

	private void setParticipants(Collection<Participant> participants) {
		this.participants = participants;
	}

	private void setClosed(boolean isClosed) {
		this.closed = isClosed;
	}

	private void setDiscussionId(DiscussionId discussionId) {
		this.discussionId = discussionId;
	}

	private void setSubject(String subject) {
		this.subject = subject;
	}

	private void setScheduledDate(Date scheduledDate) {
		if (scheduledDate.before(new Date())) {
			throw new IllegalArgumentException("Scheduled date for project must not before than today.");
		}

		this.closed = true;
		this.scheduledDate = scheduledDate;
	}
}
