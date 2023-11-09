package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Orders;
import org.binaracademy.challenge4.model.OrdersDetail;
import org.binaracademy.challenge4.repository.OrdersDetailRepository;
import org.binaracademy.challenge4.service.impl.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrdersDetailServiceImpl implements OrdersDetailService {

    @Autowired
    private OrdersDetailRepository ordersDetailRepository;

    @Override
    public List<OrdersDetail> readOrderDetail() {
        return Optional.ofNullable(ordersDetailRepository)
                .map(od -> ordersDetailRepository.readOrderDetailAll())
                .orElse(null);
    }

    @Override
    public List<OrdersDetail> readOrderDetailFilter(Long orderId) {
        return Optional.ofNullable(ordersDetailRepository)
                .map(od -> ordersDetailRepository.readOrderDetailUser(orderId))
                .orElse(null);
    }

    @Override
    @Transactional
    public Boolean addOrdersDetail(OrdersDetail ordersDetail, Orders orders) {
        return Optional.ofNullable(ordersDetail)
                .map(newUsers -> ordersDetailRepository.insertOrdersDetail(ordersDetail.getId(), ordersDetail.getQuantity(), orders.getOrderId(), ordersDetail.getProductCode()))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }
}
