package org.challenge.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Product {
    private List<String> listMenu = new ArrayList<>();
    private List<Integer> listPrice = new ArrayList<>();
    private List<String> listSelect = new ArrayList<>();
    private List<Integer> listQty = new ArrayList<>();
    private List<Integer> listHarga = new ArrayList<>();

    private final String topLine= "==========================";
    private final String underline = "==========================\n";
}
