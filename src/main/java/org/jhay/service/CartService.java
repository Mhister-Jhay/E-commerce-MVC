package org.jhay.service;



import org.jhay.dto.CartDTO;

import java.util.List;

public interface CartService {

    List<CartDTO> viewAllCart(Long id);
//    List<ProductDTO> getProductList(Long id);

    boolean addToCart(Long user_id, Long product_id, int quantity);
    boolean updateCartQuantity(Long user_id, Long product_id, int quantity, Long cart_id);
    void deleteFromCart(Long cart_id);
    String checkOut(Long id);
    double getCartTotal(Long user_id);

}
