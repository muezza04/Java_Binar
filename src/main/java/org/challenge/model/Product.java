package org.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private List<String> listMenu = new ArrayList<>();
    private List<Integer> listPrice = new ArrayList<>();

    private List<String> listSelect = new ArrayList<>();
    private List<Integer> listQty = new ArrayList<>();
    private List<Integer> listHarga = new ArrayList<>();

    private final String LINE= "==========================";
}
