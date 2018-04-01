package com.job;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value = "com.job.dao")
public class JobSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSpringbootApplication.class, args);
	}
}
