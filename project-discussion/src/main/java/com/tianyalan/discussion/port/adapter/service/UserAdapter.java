package com.tianyalan.discussion.port.adapter.service;

import com.tianyalan.discussion.domain.model.participant.Participant;

public interface UserAdapter {

	public <T extends Participant> T toParticipant(
            String anIdentity,
            Class<T> aParticipantClass);
}
