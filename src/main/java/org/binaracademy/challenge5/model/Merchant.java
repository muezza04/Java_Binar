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
@Table(name = "merchant")
public class Merchant implements Serializable {

    @Id
    @Column(name = "merchant_code")
    private String merchantCode;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_location")
    private String merchantLocation;

    @Column(name = "merchant_open", length = 10)
    private String merchantOpen;

    @OneToMany(mappedBy = "merchantCode", fetch = FetchType.LAZY)
    private List<Product> products;

}
