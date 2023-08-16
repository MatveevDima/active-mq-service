package consumer.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import consumer.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Slf4j
public class Converter implements MessageConverter {

    private final ObjectMapper mapper;

    public Converter() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }


    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {

        var textMessage = (ActiveMQTextMessage) message;
        var correlationId = textMessage.getCorrelationId();

        var payload = textMessage.getText();

        log.info("Входящее сообщение из ActiveMq: correlationId = {}, текст: {}", correlationId, payload);

        OrderDto orderDto = null;

        try {
            orderDto = mapper.readValue(payload, OrderDto.class);
        } catch (Exception e) {
            log.error("Ошибка при обработке сообщения: {}",e.getMessage());
        }

        return orderDto;
    }
}