package publisher.config;

import lombok.Setter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public ConnectionFactory connectionFactory() {

        var mqConnectionFactory = new ActiveMQConnectionFactory(url);
        mqConnectionFactory.setUserName(username);
        mqConnectionFactory.setPassword(password);

        return new CachingConnectionFactory(mqConnectionFactory);
    }

    @Bean
    public JmsTemplate jmsTemplate() {

        var jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        return jmsTemplate;
    }
}