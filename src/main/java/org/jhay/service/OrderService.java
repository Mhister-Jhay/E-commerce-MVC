package org.jhay.service;



import org.jhay.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO>viewUserOrder(Long id);
    List<OrderDTO> viewAllOrder();

    double getOrderTotal(Long user_id);
}
