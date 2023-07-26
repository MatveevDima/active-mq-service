package publisher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderRequest {

    private String fullName;
    private String phoneNumber;
    private String mail;

    private String productName;

    private String comment;
}