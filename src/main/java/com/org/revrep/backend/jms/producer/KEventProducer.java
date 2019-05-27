package com.org.revrep.backend.jms.producer;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KEventProducer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void send(String topic, Object messageToDrop) {

		if (StringUtils.isEmpty(topic) || Objects.isNull(messageToDrop)) {
			return;
		}
		log.info("publishing message -- " + topic);
		System.out.println("publishing message -- " + topic);
		kafkaTemplate.send(topic, messageToDrop);
		log.info("message published! -- " + topic);
		System.out.println("message published! -- " + topic);
	}
}
