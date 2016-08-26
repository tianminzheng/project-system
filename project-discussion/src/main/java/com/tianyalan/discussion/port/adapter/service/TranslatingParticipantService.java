package com.tianyalan.discussion.port.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.discussion.domain.model.participant.Owner;
import com.tianyalan.discussion.domain.model.participant.Participant;
import com.tianyalan.discussion.domain.model.participant.ParticipantService;
import com.tianyalan.discussion.domain.model.participant.Recorder;

@Service("participantService")
public class TranslatingParticipantService implements ParticipantService {

	@Autowired
	private UserAdapter userAdapter;

	@Override
	public Owner ownerFrom(String anIdentity) {
		Owner owner = userAdapter.toParticipant(anIdentity, Owner.class);

		return owner;
	}

	@Override
	public Recorder recorderFrom(String anIdentity) {
		Recorder recorder = userAdapter.toParticipant(anIdentity, Recorder.class);

		return recorder;
	}

	@Override
	public Participant participantFrom(String anIdentity) {
		Participant participant = userAdapter.toParticipant(anIdentity, Participant.class);

		return participant;
	}

}
