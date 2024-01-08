package org.binaracademy.challenge5.model.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRequest {

    private Long id;
    private String status;
    private String address;
    private String username;
    private List<OrdersDetailRequest> ordersDetail;
}
