package com.org.revrep;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.revrep.backend.config.ReviewReportingServiceConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReviewReportingServiceConfiguration.class)
public class RevrepReportingApplicationTests {

	@Test
	public void contextLoads() {
	}

}
