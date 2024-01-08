package org.binaracademy.challenge5.model.DTO.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.binaracademy.challenge5.model.Merchant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String productCode;
    private String productName;
    private Long productPrice;
    private String merchantName;

}
