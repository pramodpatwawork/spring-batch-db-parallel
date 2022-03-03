package com.mywork.pp.spring.batch.db.parallel;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchDbParallelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchDbParallelApplication.class, args);
	}

}
