package consumer.listener;

import consumer.dto.OrderDto;
import consumer.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActiveMqListener {

    private final OrderService service;

    @JmsListener(destination = "${listener.queue}",
            containerFactory = "jmsListenerContainerFactory")
    public void receive(Message<OrderDto> message) {

        var payload = message.getPayload();

        log.info("Сообщение преобразовано в объект: {}", payload);

        try {
            service.createOrder(payload);
        } catch (Exception e) {
            log.error("Ошибка при создании заказа: {}", e.getMessage());
        }
    }
}