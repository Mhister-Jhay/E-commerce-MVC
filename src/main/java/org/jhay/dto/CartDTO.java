package org.jhay.dto;

import lombok.*;
import org.jhay.model.Product;
import org.jhay.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
    private Long id;
    private Product product;
    private User user;
    private int quantity;
}
