package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    Orders findByOrderId(Long orderId);
    @Query(nativeQuery = true, value = "select * from orders o ")
    List<Orders> readOrders();

    @Modifying
    @Query(nativeQuery = true,
            value = "update orders set destination_address = :addressOrder, completed = :statusOrder where order_id = :orderId")
    Integer updateOrder(@Param("addressOrder") String addressOrder, @Param("statusOrder") String statusOrder, @Param("orderId") Long orderId);

    @Modifying
    @Query(nativeQuery = true, value = "delete from orders o where o.order_id = :orderId")
    Integer deleteOrder(@Param("orderId") Long orderId);
}
