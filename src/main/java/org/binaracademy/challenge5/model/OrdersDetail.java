package org.binaracademy.challenge5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_detail")
public class OrdersDetail implements Serializable {

    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_detail_id")
    private String Id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orderId;

    @ManyToOne
    @JoinColumn(name = "product_code")
    private Product productCode;

    private Integer quantity;

    @Column(name = "total_price")
    private Long totalPrice;

}