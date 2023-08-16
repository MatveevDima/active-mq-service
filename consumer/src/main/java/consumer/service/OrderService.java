package consumer.service;

import consumer.dto.OrderDto;
import consumer.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public void createOrder(OrderDto orderDto) {
        repository.createOrder(orderDto);
    }
}