package com.org.revrep.backend.jms.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import com.org.revrep.backend.events.entity.NewReviewAdded;

/**
 * @author ANVIP
 *
 */
@Configuration
public class KTopicConfiguration {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootStrapServers;

	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic newReviewAddedTopic() {
		return new NewTopic(NewReviewAdded.EVENT_NAME, 1, (short) 1);
	}

}
