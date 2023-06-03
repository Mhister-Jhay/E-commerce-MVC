package org.jhay.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jhay.model.Product;
import org.jhay.model.User;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private Product product;
    private Double price;
    private Integer quantity;
    private Double amount;
    private User user;
    private LocalDate date;

}
