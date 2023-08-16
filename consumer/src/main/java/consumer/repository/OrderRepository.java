package consumer.repository;

import consumer.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void createOrder(OrderDto dto) {

        jdbcTemplate.update("insert into orders(fullName, phoneNumber, mail, productName, comment, orderDate, orderId, orderStatus) " +
                "values (:fullName, :phoneNumber, :mail, :productName, :comment, :orderDate, :orderId, :orderStatus)",
                new MapSqlParameterSource()
                        .addValue("fullName", dto.getFullName())
                        .addValue("phoneNumber", dto.getPhoneNumber())
                        .addValue("mail", dto.getMail())
                        .addValue("productName", dto.getProductName())
                        .addValue("comment", dto.getComment())
                        .addValue("orderDate", dto.getOrderDate())
                        .addValue("orderId", dto.getOrderId())
                        .addValue("orderStatus", "not processed")

        );
    }
}

