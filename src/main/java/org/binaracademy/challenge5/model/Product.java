package org.binaracademy.challenge5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Long productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_code")
    private Merchant merchantCode;

    @OneToMany(mappedBy = "productCode")
    private List<OrdersDetail> ordersDetail;

}
