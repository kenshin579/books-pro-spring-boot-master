package com.apress.spring;

import com.apress.spring.message.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class SpringBootJmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJmsApplication.class, args);
    }

    @Value("${myqueue}")
    String queue;

    @Bean
    CommandLineRunner sendMessage(JmsTemplate jmsTemplate) {// todo: 스프링부트에서 어떻게 jmsTemplate를 넘겨주나?
        return args -> {
            Producer producer = new Producer(jmsTemplate);
            producer.sendTo(queue, "스프링 부트 시작!");
        };
    }
}
