package org.binaracademy.challenge5.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge5.model.DTO.request.ProductRequest;
import org.binaracademy.challenge5.model.DTO.response.ProductResponse;
import org.binaracademy.challenge5.model.Product;
import org.binaracademy.challenge5.repository.ProductRepository;
import org.binaracademy.challenge5.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> readProduct() {
        log.info("Starting view get All Product");

        return Optional.ofNullable(productRepository)
                .map(requestProduct -> requestProduct.readProductAll()
                        .stream()
                        .map(product -> ProductResponse.builder()
                                .productCode(product.getProductCode())
                                .productName(product.getProductName())
                                .productPrice(product.getProductPrice())
                                .merchantName(product.getMerchantCode().getMerchantName())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    @Transactional
    public Boolean addProduct(ProductRequest productRequest) {
        log.info("add create new product to api");
        return Optional.ofNullable(productRequest)
                .map(newProduct -> productRepository.insertProduct(newProduct.getProductCode(), newProduct.getProductName(), newProduct.getProductPrice(), newProduct.getMerchantCode()))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    @Transactional
    public Boolean updateProduct(ProductRequest product, String productId) {
        log.info("update data product {}", productId);
        return Optional.ofNullable(product)
                .map(updateProduct -> productRepository.updateProduct(updateProduct.getProductCode(), updateProduct.getProductName(), updateProduct.getProductPrice(), updateProduct.getMerchantCode(), productId))
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
