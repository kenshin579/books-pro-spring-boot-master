package com.apress.spring.config;

import com.apress.spring.message.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

@Configuration
public class MessagingConfig {

    @Autowired
    private ConnectionFactory connectionFactory; //todo: 스프링 부트는 어떻게 connectionFactory에 JsmTemplate 인스턴스를 자동 연결시키나??

    @Value("${myqueue}")
    private String queue; //todo: 실제로 application.properties를 언제 읽어서 assign될까?

    @Bean
    public DefaultMessageListenerContainer messageListener() { //todo: 이건 언제 호출되고 누가 사용하게 되는지?
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(this.connectionFactory);
        container.setDestinationName(queue);
        container.setMessageListener(new Consumer());
        return container;
    }
}
