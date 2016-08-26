package com.tianyalan.discussion.port.adapter.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tianyalan.common.port.adapter.rest.RestTemplateUtil;
import com.tianyalan.common.port.adapter.rest.ResultMessageBuilder.ResultMessage;
import com.tianyalan.discussion.domain.model.participant.Participant;

@Service("userAdapter")
public class HttpUserAdapter implements UserAdapter {

	private static final String HOST = "localhost";
	private static final String PORT = "8080";
	private static final String PROTOCOL = "http";
	private static final String URL_TEMPLATE = "/project-usercenter/users/{username}";

	@Override
	public <T extends Participant> T toParticipant(String anIdentity,
			Class<T> aParticipantClass) {
		T participant = null;

		this.buildURLFor(URL_TEMPLATE);

		ResultMessage result = RestTemplateUtil.getForObject(this.buildURLFor(URL_TEMPLATE).replace("{username}", anIdentity), null);
		
		if(result.isSuccess()) {
			try {
				participant = new ParticipantTranslator().toParticipant(JSON.toJSONString(result.getData()), aParticipantClass);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return participant;
	}

	private String buildURLFor(String aTemplate) {
		String url = PROTOCOL + "://" + HOST + ":" + PORT + aTemplate;

		return url;
	}
}
