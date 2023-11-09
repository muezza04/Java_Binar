package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.service.impl.MerchantService;
import org.binaracademy.challenge4.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class ProductController {
//    @Autowired
//    private MenuMainController menuMainController;

    @Autowired
    private ProductService productService;

    @Autowired
    private MerchantService merchantService;

    public Scanner scanner = new Scanner(System.in);

    public void showMenuProduct() {
        while (true) {
            System.out.println("This All Data Product");
            System.out.println("Product Code \t | \t Product Name \t | \t Product Price \t | \t Merchant Name");
            productService.readProduct().forEach(product -> {
                String merchantName = (product.getMerchantCode() != null) ? product.getMerchantCode().getMerchantName() : "null";
                System.out.println(
                        product.getProductCode() + "\t - \t" +
                                product.getProductName() + "\t - \t" +
                                product.getProductPrice() + "\t - \t" +
                                merchantName
                );
            });

            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("99. Back menu main");
            System.out.println("0. Out");

            System.out.print("=> ");
            try {
                int choose = scanner.nextInt();
                scanner.nextLine();

                switch (choose) {
                    case 1:
                        this.showAddProduct();
                        break;
                    case 2:
                        this.showUpdateProduct();
                        break;
                    case 3:
                        this.showDeleteProduct();
                        break;
                    case 99:
//                        menuMainController.showMenuMain();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Input Failed...");
                scanner.nextLine();
            }
        }
    }

    public void showAddProduct() {
        System.out.println("\n=====Add new Product=====");
        System.out.print("Product Code : ");
        String newProductCode = scanner.nextLine();
        System.out.print("Product Name : ");
        String newProductName = scanner.nextLine();
        System.out.print("Product Price : ");
        Long newProductPrice = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Product Code Merchant : ");
        String newMerchantCode = scanner.nextLine();

        Merchant newMerchant = merchantService.findMerchantCode(newMerchantCode);

        Product addProduct = Product.builder()
                .productCode(newProductCode)
                .productName(newProductName)
                .productPrice(newProductPrice)
                .merchantCode(newMerchant)
                .build();

        productService.addProduct(addProduct);
        System.out.println("Add Product success\n");
        this.showMenuProduct();
    }

    public void showUpdateProduct() {
        System.out.println("\n=====Update Product=====");
        System.out.print("Code Merchant which will update : ");
        String productId = scanner.nextLine();
        System.out.print("Product Code : ");
        String updateProductCode = scanner.nextLine();
        System.out.print("Product Name : ");
        String updateProductName = scanner.nextLine();
        System.out.print("Product Price : ");
        Long updateProductPrice = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Product Code Merchant : ");
        String updateMerchantCode = scanner.nextLine();

        Merchant updateMerchant = merchantService.findMerchantCode(updateMerchantCode);

        Product updateProduct = Product.builder()
                .productCode(updateProductCode)
                .productName(updateProductName)
                .productPrice(updateProductPrice)
                .merchantCode(updateMerchant)
                .build();

        productService.updateProduct(updateProduct, productId);
        System.out.println("Update Product success\n");
        this.showMenuProduct();
    }

    public void showDeleteProduct() {
        System.out.print("Code Product which is delete : ");
        String productDelete = scanner.nextLine();

        productService.deleteProduct(productDelete);
        System.out.println("Product delete is success!\n");
        this.showMenuProduct();
    }
}
