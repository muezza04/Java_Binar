package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Orders;
import org.binaracademy.challenge4.repository.OrdersRepository;
import org.binaracademy.challenge4.service.impl.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public Orders findOrderId(Long searchOrderId) {
        return ordersRepository.findByOrderId(searchOrderId);
    }

    @Override
    public List<Orders> readOrders() {
        return Optional.ofNullable(ordersRepository)
                .map(requestUsers -> ordersRepository.readOrders())
                .orElse(null);
    }

    @Override
    public Boolean addOrders(Orders orders) {
        return Optional.ofNullable(orders)
                .map(newOrder -> ordersRepository.save(orders))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    @Transactional
    public Boolean updateOrders(Orders orders) {
        return Optional.ofNullable(orders)
                .map(newUsers -> ordersRepository.updateOrder(
                            orders.getDestinationAddress(),
                            orders.getCompleted(),
                            orders.getOrderId()
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
