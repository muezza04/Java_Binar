package org.binaracademy.challenge4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private Long orderId;
    private Date order_time;
    private String destination_address;
    private Users userId;
    private String complated;

}
