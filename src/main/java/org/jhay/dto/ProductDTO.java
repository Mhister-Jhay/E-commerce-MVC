package org.jhay.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jhay.enums.ProductCategory;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private byte[] image;
    private Double price;
    private Integer quantity;
    private ProductCategory productCategory;

}
