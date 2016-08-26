package com.tianyalan.discussion.domain.model.participant;


public interface ParticipantService {

	Owner ownerFrom(String anIdentity);
	
	Recorder recorderFrom(String anIdentity);
	
	Participant participantFrom(String anIdentity);
}
