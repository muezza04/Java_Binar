package org.binaracademy.challenge4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {

    private String merchantCode;
    private String merchantName;
    private String merchantLocation;
    private String merchantOpen;

}
