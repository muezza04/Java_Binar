package org.binaracademy.challenge5.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge5.model.DTO.request.ProductRequest;
import org.binaracademy.challenge5.model.DTO.response.ProductResponse;
import org.binaracademy.challenge5.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        log.info("getting all product in controller");
        List<ProductResponse> products = productService.readProduct();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> postNewProduct(@RequestBody ProductRequest productRequest) {
        try {
            productService.addProduct(productRequest);
            return new ResponseEntity<>("Product Created success", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{productCode}")
    public ResponseEntity<String> putProduct(@PathVariable("productCode") String productCode, @RequestBody ProductRequest productRequest){
        try {
            productService.updateProduct(productRequest, productCode);
            return new ResponseEntity<>("Product Code " + productCode + " update successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/delete/{productCode}")
    public ResponseEntity<String> deleteMerchant(@PathVariable String productCode){
        try {
            productService.deleteProduct(productCode);
            return new ResponseEntity<>("Product Code " + productCode + " delete successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
