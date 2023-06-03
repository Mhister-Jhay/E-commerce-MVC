package org.jhay.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jhay.model.Product;
import org.jhay.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistDTO {
    private Long id;
    private Product product;
    private User user;
}
