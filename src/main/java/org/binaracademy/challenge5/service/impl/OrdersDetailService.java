package org.binaracademy.challenge5.service.impl;

import org.binaracademy.challenge5.model.Orders;
import org.binaracademy.challenge5.model.OrdersDetail;

import java.util.List;

public interface OrdersDetailService {

    List<OrdersDetail> readOrderDetail();

    List<OrdersDetail> readOrderDetailFilter(Long orderId);

    Boolean addOrdersDetail(OrdersDetail ordersDetail, Orders orders);

}
