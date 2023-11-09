package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.*;
import org.binaracademy.challenge4.service.impl.OrdersDetailService;
import org.binaracademy.challenge4.service.impl.OrdersService;
import org.binaracademy.challenge4.service.impl.ProductService;
import org.binaracademy.challenge4.service.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

@Component
public class OrdersController {

//    @Autowired
//    private MenuMainController menuMainController;
    @Autowired
    private UsersController usersController;
    @Autowired
    private OrdersDetailController ordersDetailController;

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrdersDetailService ordersDetailService;

    public Scanner scanner = new Scanner(System.in);

    public void showMenuOrders() {
        while (true) {
            System.out.println("This All Data Order Product");
            System.out.println("Id Order \t | \t Username \t | \t Order Time \t | \t Address \t | \t Status");
            ordersService.readOrders().forEach(order -> {
                String getUsernameOrder = (order.getUserId() != null) ? order.getUserId().getUsername() : "null";

                System.out.println(
                        order.getOrderId() + "\t | \t" +
                        getUsernameOrder + "\t | \t" +
                                order.getOrderTime() + "\t | \t" +
                                order.getDestinationAddress() + "\t | \t" +
                                order.getCompleted()
                );
            });

            System.out.println("\n1. Add Order");
            System.out.println("2. Update Order");
            System.out.println("3. Delete Order");
            System.out.println("4. Detail Order All");
            System.out.println("99. Back menu main");
            System.out.println("0. Out");

            System.out.print("=> ");
            try {
                int choose = scanner.nextInt();
                scanner.nextLine();

                switch (choose) {
                    case 1:
                        this.showAddOrder();
                        break;
                    case 2:
                        this.showUpdateOrder();
                        break;
                    case 3:
                        this.showDeleteOrder();
                        break;
                    case 4:
                        ordersDetailController.showOrderDetailMenu();
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

    public void showAddOrder() {
        System.out.println("\n=====Add new Order=====");
        System.out.print("User Id(Number) : ");
        Long newUserId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Username : ");
        String newUsername = scanner.nextLine();
        System.out.print("Address Order : ");
        String newAddressOrder = scanner.nextLine();
        System.out.print("Status (Cancel/Pending/Process/Success)\n => ");
        String newStatusOrder = scanner.nextLine().toLowerCase();

        Users newUsers = usersService.findUsername(newUsername);

        if (newUsers == null) {
            System.out.println("Username tidak terdaftar, silahkan register terlebih dahulu");
            usersController.showAddUsers();
        }

        Orders addOrder = Orders.builder()
                .orderId(newUserId)
                .userId(newUsers)
                .destinationAddress(newAddressOrder)
                .orderTime(new Date())
                .completed(newStatusOrder)
                .build();

        ordersService.addOrders(addOrder);

        System.out.println("\nProduct yang akan di buy *");
        while (true) {
            System.out.print("Product Code : ");
            String newProductCodeOrder = scanner.nextLine();
            System.out.print("Quantity : ");
            Integer newQtyOrder = scanner.nextInt();
            scanner.nextLine();


            Product newProduct = productService.findProductCode(newProductCodeOrder);

            if (newProduct == null) {
                System.out.println("Username tidak terdaftar, silahkan register terlebih dahulu");
                newProduct = null;
            }

            OrdersDetail addOrderDetail = OrdersDetail.builder()
                    .Id(String.valueOf(UUID.randomUUID()))
                    .productCode(newProduct)
                    .quantity(newQtyOrder)
                    .build();

            ordersDetailService.addOrdersDetail(addOrderDetail, addOrder);

            try {
                System.out.print("Do you want order again? (y/n): ");
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("y")) {
                    continue;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Input Failed...");
                scanner.nextLine();
            }
        }

        System.out.println("Add Product success\n");
        this.showMenuOrders();
    }

    public void showUpdateOrder() {
        System.out.println("\n=====Update Order=====");
        System.out.print("User Id(Number) with update\n => ");
        Long updateUserId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Address Order : ");
        String updateAddressOrder = scanner.nextLine();
        System.out.print("Status (Cancel/Pending/Process/Success)\n => ");
        String updateStatusOrder = scanner.nextLine().toLowerCase();

        Orders updateOrder = Orders.builder()
                .orderId(updateUserId)
                .destinationAddress(updateAddressOrder)
                .completed(updateStatusOrder)
                .build();

        ordersService.updateOrders(updateOrder);
        System.out.println("Orders "+ updateUserId + " update success!\n");
        this.showMenuOrders();
    }

    public void showDeleteOrder() {
        System.out.print("User Id which is delete : ");
        Long orderDelete = scanner.nextLong();

        if (orderDelete == null){
            System.out.println("Data not found");
            this.showMenuOrders();
        }

        ordersService.deleteOrders(orderDelete);
        System.out.println("Order delete is success!\n");
        this.showMenuOrders();
    }
}
