package com.org.revrep.backend.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@Configuration
@EnableAutoConfiguration
@ComponentScan("com.org.revrep")
@EnableMongoRepositories(basePackageClasses = com.org.revrep.backend.repository.ReviewRepo.class)
public class ReviewReportingServiceConfiguration {

}
