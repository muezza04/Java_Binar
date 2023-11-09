package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Orders;
import org.binaracademy.challenge4.model.OrdersDetail;
import org.binaracademy.challenge4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Long> {

    @Query(nativeQuery = true,
            value = "select od.*, u.username, p.product_name " +
                    "from orders_detail od " +
                    "left join orders o on od.order_id = o.order_id " +
                    "left join users u on o.user_id = u.user_id " +
                    "left join product p on od.product_code = p.product_code")
    List<OrdersDetail> readOrderDetailAll();

    @Query(nativeQuery = true,
            value = "select od.*, u.username, p.product_name " +
                    "from orders_detail od " +
                    "left join orders o on od.order_id = o.order_id " +
                    "left join users u on o.user_id = u.user_id " +
                    "left join product p on od.product_code = p.product_code " +
                    "where od.order_id = :orderId"
    )
    List<OrdersDetail> readOrderDetailUser(@Param("orderId") Long orderId);

    @Modifying
    @Query(nativeQuery = true,
            value = "INSERT INTO orders_detail (order_detail_id, quantity, total_price, order_id, product_code) " +
                    "SELECT :orderDetailId, :qty, (:qty * product_price), :orderId, :productCode " +
                    "FROM product " +
                    "WHERE product_code = :productCode")
    Integer insertOrdersDetail(@Param("orderDetailId") String orderDetailId ,@Param("qty") Integer qty ,@Param("orderId") Long orderId ,@Param("productCode") Product productCode);
}
