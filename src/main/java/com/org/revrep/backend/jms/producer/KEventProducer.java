package com.org.revrep.backend.jms.producer;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.org.revrep.backend.domain.dao.ReviewDAO;

@Component
public class KEventProducer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void send(String topic, Object messageToDrop) {

		if (StringUtils.isEmpty(topic) || Objects.isNull(messageToDrop)) {
			return;
		}

		System.out.println("publishing message -- " + topic);
		kafkaTemplate.send(topic, messageToDrop);

		System.out.println("message published! -- " + topic);
	}
}
