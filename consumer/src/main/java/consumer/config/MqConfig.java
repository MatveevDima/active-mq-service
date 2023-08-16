package consumer.config;

import consumer.converter.Converter;
import lombok.Setter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
@Setter
@ConfigurationProperties("mq-config")
public class MqConfig {

    private String url;
    private String username;
    private String password;

    @Bean
    public Converter converter() {
        return new Converter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {

        var mqConnectionFactory = new ActiveMQConnectionFactory(url);
        mqConnectionFactory.setUserName(username);
        mqConnectionFactory.setPassword(password);

        return new CachingConnectionFactory(mqConnectionFactory);
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory() {

        var factory = new DefaultJmsListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(converter());

        return factory;
    }
}