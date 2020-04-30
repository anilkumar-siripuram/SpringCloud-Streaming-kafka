package com.kafka.springcloudstreamkafkaordersource;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;

import com.kafka.springcloudstreamkafkaordersource.Models.Order;

@SpringBootApplication
@EnableBinding(Source.class)
public class SpringCloudStreamKafkaOrdersourceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SpringCloudStreamKafkaOrdersourceApplication.class);

	private List<String> names = Arrays.asList("Donald", "Theresa", "Vladimir", "Angela", "Emmanuel", "Shinzō",
			"Jacinda", "Kim");

	private List<String> items = Arrays.asList("Mobile", "Tab", "Desktop", "Laptop", "Head Phone", "Adapter", "Charger",
			"USB Cable", "Watch", "Clock");

	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamKafkaOrdersourceApplication.class, "--spring.cloud.stream.function.definition=supply");
	}

	
	@Bean
	@InboundChannelAdapter(channel = Source.OUTPUT) //Indicates that a method is capable of producing a Message or Message payload.
	public Supplier<Order> supply() {
		return () -> {
			String rName = names.get(new Random().nextInt(names.size()));
			String rItem = items.get(new Random().nextInt(items.size()));
			Order order = new Order(UUID.randomUUID().toString(), rName, rItem);

			LOG.info("{} {} for {} for {}", order.getStatus(), order.getUuid(), order.getItem(), order.getName());

			return order;
		};
	}
}
