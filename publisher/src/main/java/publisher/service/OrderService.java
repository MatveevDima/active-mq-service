package publisher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import publisher.dto.OrderDto;
import publisher.dto.OrderRequest;
import publisher.publisher.ActiveMqPublisher;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final ActiveMqPublisher publisher;

    public String createOrder(OrderRequest request) {

        var orderId = UUID.randomUUID().toString();

        log.info("Заявке присвоен id = {}", orderId);

        var order = new OrderDto()
                .setFullName(request.getFullName())
                .setPhoneNumber(request.getPhoneNumber())
                .setMail(request.getMail())
                .setProductName(request.getProductName())
                .setComment(request.getComment())
                .setOrderDate(LocalDateTime.now())
                .setOrderId(orderId);

        publisher.publish(order);

        return orderId;
    }
}