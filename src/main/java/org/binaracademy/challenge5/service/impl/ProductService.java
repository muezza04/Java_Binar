package org.binaracademy.challenge5.service.impl;

import org.binaracademy.challenge5.model.DTO.request.ProductRequest;
import org.binaracademy.challenge5.model.DTO.response.ProductResponse;
import org.binaracademy.challenge5.model.Product;

import java.util.List;

public interface ProductService {

    //*All Important
    List<ProductResponse> readProduct();
    Boolean addProduct(ProductRequest productResponse);
    Boolean updateProduct(ProductRequest product, String productCode);
    void deleteProduct(String codeProduct);

    Product findProductCode(String productCode);
}
