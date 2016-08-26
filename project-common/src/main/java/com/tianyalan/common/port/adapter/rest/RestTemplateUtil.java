package com.tianyalan.common.port.adapter.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.tianyalan.common.port.adapter.rest.ResultMessageBuilder.ResultMessage;

public class RestTemplateUtil {

	public static ResultMessage postForObject(String url, Object object) {

		RestTemplate rest = new RestTemplate();
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		rest.getMessageConverters().add(converter);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> entity = new HttpEntity<Object>(object, headers);

		ResponseEntity<ResultMessage> response = rest.exchange(url,
				HttpMethod.POST, entity, ResultMessage.class);

		return response.getBody();
	}

	public static ResultMessage postForObjectWithPathVariable(String url, Object object, Object pathVariable) {

		RestTemplate rest = new RestTemplate();
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		rest.getMessageConverters().add(converter);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> entity = new HttpEntity<Object>(object, headers);

		ResponseEntity<ResultMessage> response = rest.exchange(url,
				HttpMethod.POST, entity, ResultMessage.class, pathVariable);

		return response.getBody();
	}
	
	public static ResultMessage getForObject(String url, Object object) {

		RestTemplate rest = new RestTemplate();
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		rest.getMessageConverters().add(converter);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> entity = new HttpEntity<Object>(object, headers);

		ResponseEntity<ResultMessage> response = rest.exchange(url,
				HttpMethod.GET, entity, ResultMessage.class);

		return response.getBody();
	}
}
