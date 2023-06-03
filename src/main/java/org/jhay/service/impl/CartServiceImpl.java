package org.jhay.service.impl;


import lombok.RequiredArgsConstructor;
import org.jhay.dto.CartDTO;
import org.jhay.flutterwave.Customer;
import org.jhay.flutterwave.Payment;
import org.jhay.model.Cart;
import org.jhay.model.Order;
import org.jhay.model.Product;
import org.jhay.model.User;
import org.jhay.repository.CartRepository;
import org.jhay.repository.OrderRepository;
import org.jhay.repository.ProductRepository;
import org.jhay.repository.UserRepository;
import org.jhay.service.CartService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final PaymentServiceImpl paymentServiceImpl;

    @Override
    public List<CartDTO> viewAllCart(Long id) {
        User user = userRepository.findStoreUsersById(id).orElse(null);
        if(user != null){
            List<Cart> cartList = cartRepository.findAllByStoreUser(id);
            List<CartDTO> cartDTOList = new ArrayList<>();
            for(Cart cart:cartList){
                Product product = productRepository.findById(cart.getProduct().getId()).orElse(null);
                cartDTOList.add(CartDTO.builder()
                                .id(cart.getId())
                                .product(product)
                                .user(user)
                                .quantity(cart.getQuantity())
                                .build());
            }
            return cartDTOList;
        }
        return null;
    }

    @Override
    public boolean addToCart(Long user_id, Long product_id, int quantity) {
        User user = userRepository.findStoreUsersById(user_id).orElse(null);
        Product product = productRepository.findById(product_id).orElse(null);
        if(user != null && product != null){
            Cart cart = cartRepository.findByStoreUserAndProduct(user_id,product_id).orElse(null);
            if(cart == null){
                cartRepository.save(Cart.builder()
                                .storeUser(user)
                                .product(product)
                                .quantity(1)
                                .build());
                return true;
            } else{
               int isUpdated = cartRepository.updateCartQuantity(cart.getQuantity()+quantity,product_id,user_id);
                return isUpdated > 0;
            }

        }
        return false;
    }

    @Override
    public boolean updateCartQuantity(Long user_id, Long product_id, int quantity, Long cart_id) {
        int oldQuantity = cartRepository.selectCartQuantity(product_id,user_id);
        if(oldQuantity+quantity <= 0){
            cartRepository.deleteById(cart_id);
            return false;
        }
        int isUpdated = cartRepository.updateCartQuantity(oldQuantity+quantity,product_id,user_id);
        return isUpdated > 0;
    }

    @Override
    public void deleteFromCart(Long cart_id) {
        cartRepository.deleteById(cart_id);
    }


    @Override
    public String checkOut(Long id) {
        User user = userRepository.findStoreUsersById(id).orElse(null);
        assert user != null;
        Customer customer = Customer.builder()
                .name(user.getFirstName()+" "+user.getLastName())
                .phonenumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
        double total = getCartTotal(user.getId());
        Payment payment = Payment.builder()
                .tx_ref(UUID.randomUUID().toString())
                .customer(customer)
                .currency("NGN")
                .amount(String.valueOf(total))
                .redirect_url("http://localhost:8080/redirect-transaction")
                .build();
        return paymentServiceImpl.makePayment(payment);
    }

    public void orderMade(Long id){
        List<CartDTO> cartDTOList = viewAllCart(id);
        User user = userRepository.findStoreUsersById(id).orElse(null);
        List<Order> orderList = orderRepository.findAllByStoreUser(id);
        for(CartDTO cart: cartDTOList){
            Product product = productRepository.findById(cart.getProduct().getId()).orElse(null);
            assert product != null;
            orderList.add(Order.builder()
                    .amount(product.getPrice()*cart.getQuantity())
                    .orderDate(LocalDate.now())
                    .price(product.getPrice())
                    .quantity(cart.getQuantity())
                    .product(product)
                    .storeUser(user)
                    .build());
        }
        int isCheckedOut = cartRepository.deleteAllByStoreUser(id);
        if(isCheckedOut > 0){
            orderRepository.saveAll(orderList);
        }
    }

    @Override
    public double getCartTotal(Long user_id) {
        List<CartDTO> cartDTOList = viewAllCart(user_id);
        double cartTotal = 0.0;
        if(cartDTOList.isEmpty()){
            return cartTotal;
        }
        for(CartDTO cartDTO : cartDTOList){
            cartTotal = cartTotal + (cartDTO.getQuantity()*cartDTO.getProduct().getPrice());
        }
        return cartTotal;
    }

}
