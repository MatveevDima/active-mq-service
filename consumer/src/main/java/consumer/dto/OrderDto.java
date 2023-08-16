package consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDto {

    private String fullName;
    private String phoneNumber;
    private String mail;

    private String productName;

    private String comment;

    private LocalDateTime orderDate;
    private String orderId;
}