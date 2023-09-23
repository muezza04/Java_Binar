package org.challenge.controller;

import org.challenge.service.ProductService;

public class ProductController {
    private final ProductService productService;

    // Menampilkan menu, memproses menu, dan keluar dari aplikasi
    public ProductController(ProductService productService)  {
        this.productService = productService;
    }

    public void showControllerApp() {
        productService.MainMenu();
        productService.processMenu();
    }

}
