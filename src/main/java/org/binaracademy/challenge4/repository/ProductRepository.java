package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByProductCode(String productCode);

    @Query(nativeQuery = true,
            value = "SELECT p.product_code, p.product_name, p.product_price, p.merchant_code , m.merchant_name " +
                    "FROM product p " +
                    "LEFT JOIN merchant m ON p.merchant_code = m.merchant_code")
    List<Product> readProduct();

    @Modifying
    @Query(nativeQuery = true,
            value = "insert into product (product_code, product_name, product_price, merchant_code) " +
                    "values (:productCode, :productName, :productPrice, :merchantCode)")
    Integer insertProduct(@Param("productCode") String productCode, @Param("productName") String productName, @Param("productPrice") Long productPrice, @Param("merchantCode") Merchant merchantCode);

    @Modifying
    @Query(nativeQuery = true, value = "update product set product_code = :productCode, product_name = :productName, product_price = :productPrice, merchant_code = :merchantCode where product_code = :productId")
    Integer updateProduct(@Param("productCode") String merchantOpen, @Param("productName") String productName, @Param("productPrice") Long productPrice, @Param("merchantCode") Merchant merchantCode , @Param("productId") String productId);

    @Modifying
    @Query(nativeQuery = true, value = "delete from product p where p.product_code = :productCode")
    Integer deleteProduct(@Param("productCode") String productCode);
}
