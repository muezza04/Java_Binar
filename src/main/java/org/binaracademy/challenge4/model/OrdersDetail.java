package org.binaracademy.challenge4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDetail {

    private Orders orderId;
    private Product productCode;
    private Integer quantity;
    private Long totalPrice;

}
