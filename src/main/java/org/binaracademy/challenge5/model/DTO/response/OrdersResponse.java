package org.binaracademy.challenge5.model.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponse {

    private Long id;
    private String status;
    private String address;
    private Date orderTime;
    private String user;

}
