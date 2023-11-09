package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.binaracademy.challenge4.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> readProduct() {
        return Optional.ofNullable(productRepository)
                .map(requestUsers -> productRepository.readProduct())
                .orElse(null);
    }

    @Override
    @Transactional
    public Boolean addProduct(Product product) {
        return Optional.ofNullable(product)
                .map(newUsers -> productRepository.insertProduct(product.getProductCode(),product.getProductName(),product.getProductPrice(),product.getMerchantCode()))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    @Transactional
    public Boolean updateProduct(Product product, String productId) {
        return Optional.ofNullable(product)
                .map(newUsers -> productRepository.updateProduct(product.getProductCode(), product.getProductName(), product.getProductPrice(), product.getMerchantCode(), productId))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    @Transactional
    public void deleteProduct(String codeProduct) {
        productRepository.deleteProduct(codeProduct);
    }

    @Override
    public Product findProductCode(String productCode) {
        return productRepository.findByProductCode(productCode);
    }
}
