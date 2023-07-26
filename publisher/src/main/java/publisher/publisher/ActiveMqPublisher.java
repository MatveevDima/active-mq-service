package publisher.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import publisher.dto.OrderDto;

import java.util.UUID;

@Component
@Slf4j
@Setter
@ConfigurationProperties("publisher")
public class ActiveMqPublisher {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper mapper;

    private String queue;

    public ActiveMqPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @SneakyThrows
    public void publish(OrderDto request) {

        var textMessage = mapper.writeValueAsString(request);
        var correlationId = UUID.randomUUID().toString();

        log.info("Отправка сообщения {} в ActiveMq, correlationId = {}", textMessage, correlationId);

        jmsTemplate.convertAndSend(queue, textMessage, msg -> {
            msg.clearProperties();
            msg.setJMSCorrelationID(correlationId);
            return msg;
        });
    }
}