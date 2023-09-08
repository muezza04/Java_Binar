package org.challenge.repository;

import lombok.Data;

@Data
public class ProductRepository {
    private String[] dataMenu = {"Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk"};
    private Integer[] dataPrice = {15000, 13000, 18000, 3000, 5000};
}
