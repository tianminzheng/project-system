package com.tianyalan.discussion.application.discussion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.discussion.domain.model.discussion.Discussion;
import com.tianyalan.discussion.domain.model.discussion.DiscussionId;
import com.tianyalan.discussion.domain.model.discussion.DiscussionRepository;
import com.tianyalan.discussion.domain.model.participant.Owner;
import com.tianyalan.discussion.domain.model.participant.Participant;
import com.tianyalan.discussion.domain.model.participant.ParticipantService;
import com.tianyalan.discussion.domain.model.participant.Recorder;

@Service("discussionApplicationService")
public class DiscussionApplicationService {

	@Autowired
	private DiscussionRepository discussionRepository;

	@Autowired
	private ParticipantService participantService;

	public void newDiscussion(String projectId, String ownerId,
			String recorderId, Collection<String> participantIds, String subject) {

		DiscussionId discussionId = this.discussionRepository.nextIdentity();

		Owner owner = this.participantService.ownerFrom(ownerId);
		if (owner == null) {
			throw new IllegalStateException(
					"The owner for this discussion is invalid: " + ownerId);
		}

		Recorder recorder = this.participantService.recorderFrom(recorderId);
		if (recorder == null) {
			throw new IllegalStateException(
					"The recorder for this discussion is invalid: "
							+ recorderId);
		}

		Collection<Participant> participants = new ArrayList<Participant>();
		if (participantIds != null && participantIds.size() > 0) {
			for (String participantId : participantIds) {
				Participant participant = this.participantService
						.participantFrom(participantId);
				if (participant == null) {
					throw new IllegalStateException(
							"The participant for this discussion is invalid: "
									+ participantId);
				}

				participants.add(participant);
			}
		}

		Discussion discussion = new Discussion(discussionId, projectId, owner,
				recorder, participants, subject);

		this.discussionRepository.saveDiscussion(discussion);
	}

	public void closeDiscussion(String discussionId, Date scheduledDate) {
		Discussion discussion = this.discussionRepository
				.discussionOfId(new DiscussionId(discussionId));
		if (discussion == null) {
			throw new IllegalStateException("Unknown discussion id: "
					+ discussionId);
		}

		discussion.close(scheduledDate);

		this.discussionRepository.updateDiscussion(discussion);
	}
}
