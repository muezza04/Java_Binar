package org.binaracademy.challenge5.service.impl;

import org.binaracademy.challenge5.model.DTO.request.OrdersRequest;
import org.binaracademy.challenge5.model.DTO.response.OrdersResponse;
import org.binaracademy.challenge5.model.Orders;

import java.util.List;

public interface OrdersService {

    Orders findOrderId(Long searchOrderId);

    List<OrdersResponse> readOrders();
    void addOrders(OrdersRequest orders);

    //Optional
    Boolean updateOrders(OrdersRequest orders, Long orderId);
    //Optional
    void deleteOrders(Long id);

}
