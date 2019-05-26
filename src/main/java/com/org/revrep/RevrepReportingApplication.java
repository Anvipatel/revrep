package com.org.revrep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.org.revrep.backend.config.ReviewReportingServiceConfiguration;


@SpringBootApplication
public class RevrepReportingApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReviewReportingServiceConfiguration.class, args);
	}
}
