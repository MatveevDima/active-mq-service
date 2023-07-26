package publisher.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import publisher.dto.OrderRequest;
import publisher.dto.OrderResponse;
import publisher.service.OrderService;

import static publisher.dto.Status.SUCCESS;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService service;

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {

        log.info("Входящий запрос: {}", request);

        OrderResponse response = new OrderResponse()
                .setStatus(SUCCESS)
                .setOrderId(service.createOrder(request));

        log.info("Ответ сервиса: {}", response);

        return ResponseEntity.ok(response);
    }
}