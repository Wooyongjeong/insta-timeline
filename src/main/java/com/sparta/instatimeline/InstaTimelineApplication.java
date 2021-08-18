package com.sparta.instatimeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InstaTimelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstaTimelineApplication.class, args);
	}

}
