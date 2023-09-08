package org.challenge.controller;

import lombok.Data;
import org.challenge.service.ProductService;

@Data
public class ProductController {

    // Menampilkan menu, memproses menu, dan keluar dari aplikasi
    public ProductController(ProductService productService) {
        productService.showMenu();
        productService.processMenu();
        System.exit(0);
    }

}
