package org.binaracademy.challenge4.service.impl;

import org.binaracademy.challenge4.model.Product;

import java.util.List;

public interface ProductService {

    //*All Important
    List<Product> readProduct();
    Boolean addProduct(Product product);
    Boolean updateProduct(Product product, String productCode);
    void deleteProduct(String codeProduct);

    Product findProductCode(String productCode);
}
