package org.challenge.repository;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ProductRepository {
    private List<String> dataMenu = new ArrayList<>(Arrays.asList("Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk"));
    private List<Integer> dataPrice = new ArrayList<>(Arrays.asList(15000, 13000, 18000, 3000, 5000));
}
