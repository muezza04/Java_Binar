package org.binaracademy.challenge5.model.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String productCode;
    private String productName;
    private Long productPrice;
    private String merchantCode;
}
