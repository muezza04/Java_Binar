package org.binaracademy.challenge5.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge5.model.DTO.request.OrdersDetailRequest;
import org.binaracademy.challenge5.model.DTO.request.OrdersRequest;
import org.binaracademy.challenge5.model.DTO.response.OrdersResponse;
import org.binaracademy.challenge5.model.Orders;
import org.binaracademy.challenge5.model.OrdersDetail;
import org.binaracademy.challenge5.repository.OrdersDetailRepository;
import org.binaracademy.challenge5.repository.OrdersRepository;
import org.binaracademy.challenge5.service.impl.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersDetailRepository ordersDetailRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public Orders findOrderId(Long searchOrderId) {
        return ordersRepository.findByOrderId(searchOrderId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdersResponse> readOrders() {
        log.info("Starting view get All orders in service");

        return Optional.ofNullable(ordersRepository)
                .map(orders -> orders.readOrders()
                        .stream()
                        .map(order -> OrdersResponse.builder()
                                .id(order.getOrderId())
                                .address(order.getDestinationAddress())
                                .status(order.getCompleted())
                                .orderTime(order.getOrderTime())
                                .user(order.getUserId().getUsername())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    @Transactional
    public void addOrders(OrdersRequest ordersRequest) {
        log.info("process add new orders in service");

        Orders orders = new Orders();
        orders.setOrderId(ordersRequest.getId());
        orders.setDestinationAddress(ordersRequest.getAddress());
        orders.setOrderTime(new Date());
        orders.setCompleted(ordersRequest.getStatus());
        ordersRepository.save(orders);

        // Pisahkan informasi untuk entitas OrdersDetail
        List<OrdersDetailRequest> ordersDetailRequests = ordersRequest.getOrdersDetail();
        for (OrdersDetailRequest detailRequest : ordersDetailRequests) {

            OrdersDetail ordersDetail = new OrdersDetail();
            ordersDetail.setId(String.valueOf(UUID.randomUUID()));
            ordersDetail.setQuantity(detailRequest.getQty());
            ordersDetail.setOrderId(orders); // Hubungkan dengan entitas Orders
            ordersDetailRepository.save(ordersDetail);
        }

//        return Optional.ofNullable(orders)
//                .map(newOrder -> OrdersRequest.builder()
//                        .id(newOrder.getId())
//                        .address(newOrder.getAddress())
//                        .status(newOrder.getStatus())
//                        .username(newOrder.getUsername())
//                )
//                .map(newOrder -> ordersRepository.save(orders))
//                .map(Objects::nonNull)
//                .orElse(Boolean.FALSE);
    }

    @Override
    @Transactional
    public Boolean updateOrders(OrdersRequest orders, Long orderId) {
        log.info("starting update data order in service");
        return Optional.ofNullable(orders)
                .map(order -> ordersRepository.updateOrder(
                            order.getAddress(),
                            order.getStatus(),
                            orderId
                    )
                )
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    public void deleteOrders(Long orderId) {
        ordersRepository.deleteOrder(orderId);
    }
}
