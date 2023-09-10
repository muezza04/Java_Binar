package org.challenge;

import org.challenge.controller.ProductController;
import org.challenge.repository.ProductRepository;
import org.challenge.service.ProductService;
import org.challenge.service.ProductServiceImpl;

public class ChallengeApp {
    public static void main(String[] args)  {

        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductServiceImpl(productRepository);
        ProductController productController = new ProductController(productService);

        productController.toString();
    }
}
