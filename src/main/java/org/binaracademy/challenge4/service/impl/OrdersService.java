package org.binaracademy.challenge4.service.impl;

import org.binaracademy.challenge4.model.Orders;
import org.hibernate.criterion.Order;

import java.util.List;

public interface OrdersService {

    Orders findOrderId(Long searchOrderId);

    List<Orders> readOrders();
    Boolean addOrders(Orders orders);

    //Optional
    Boolean updateOrders(Orders orders);
    //Optional
    void deleteOrders(Long id);

}
