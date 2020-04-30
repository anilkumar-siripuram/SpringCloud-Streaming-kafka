package com.kafka.springcloudstreamkafkaordercheck;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kafka.springcloudstreamkafkaordercheck.processor.OrderProcessor;

@SpringBootApplication
@EnableBinding(OrderProcessor.class)
public class SpringCloudStreamKafkaOrdercheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamKafkaOrdercheckApplication.class, args);
	}

}
