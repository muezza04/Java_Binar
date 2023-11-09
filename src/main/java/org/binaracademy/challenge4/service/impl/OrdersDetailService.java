package org.binaracademy.challenge4.service.impl;

import org.binaracademy.challenge4.model.Orders;
import org.binaracademy.challenge4.model.OrdersDetail;

import java.util.List;

public interface OrdersDetailService {

    List<OrdersDetail> readOrderDetail();

    List<OrdersDetail> readOrderDetailFilter(Long orderId);

    Boolean addOrdersDetail(OrdersDetail ordersDetail, Orders orders);

}
