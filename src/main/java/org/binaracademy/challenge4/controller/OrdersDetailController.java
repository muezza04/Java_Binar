package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.Orders;
import org.binaracademy.challenge4.model.OrdersDetail;
import org.binaracademy.challenge4.service.impl.OrdersDetailService;
import org.binaracademy.challenge4.service.impl.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class OrdersDetailController {

//    @Autowired
//    private MenuMainController menuMainController;
    @Autowired
    private OrdersDetailService ordersDetailService;

    @Autowired
    private OrdersService ordersService;

    public Scanner scanner = new Scanner(System.in);

    public void showOrderDetailMenu() {
        while (true) {
            System.out.println("This All Data Order Product");
            System.out.println("Username \t | \t Quantity \t | \t Total Price \t | \t Product Name");
            ordersDetailService.readOrderDetail().forEach(od -> {
                String getUsernameOrderDetail = (od.getOrderId() != null) ? od.getOrderId().getUserId().getUsername() : "null";
                String getProductNameOrderDetail = (od.getProductCode() != null) ? od.getProductCode().getProductName() : "null";

                System.out.println(
                        getUsernameOrderDetail + "\t | \t" +
                                od.getQuantity() + "\t | \t" +
                                od.getTotalPrice() + "\t | \t" +
                                getProductNameOrderDetail
                );
            });

            System.out.println("\n1. Back");
            System.out.println("2. Filter Order Id");
            System.out.println("99. Back menu main");
            System.out.println("0. Out");

            System.out.print("=> ");
            try {
                int choose = scanner.nextInt();
                scanner.nextLine();

                switch (choose) {
                    case 1:
                        break;
                    case 2:
                        this.showDetailOrder();
                        break;
                    case 99:
//                        menuMainController.showMenuMain();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Input Failed...");
                scanner.nextLine();
            }
        }
    }

    public void showDetailOrder() {
        System.out.println("\n====Detail Orders====");
        System.out.print("ID order With will see detail \n=> ");
        Long detailOrderId = scanner.nextLong();
        scanner.nextLine();

        if (detailOrderId == null) {
            System.out.println("Data not found");
            this.showOrderDetailMenu();
        }

        Orders searchDetailOrders = ordersService.findOrderId(detailOrderId);
        System.out.println("Orders detail is success");

        System.out.println("\nThis "+searchDetailOrders.getUserId().getUsername()+" Data Order Detail");
        System.out.println("Username \t | \t Quantity \t | \t Total Price \t | \t Product Name");
        ordersDetailService.readOrderDetailFilter(detailOrderId).forEach(od -> {
            String getUsernameOrderDetail = (od.getOrderId() != null) ? od.getOrderId().getUserId().getUsername() : "null";
            String getProductNameOrderDetail = (od.getProductCode() != null) ? od.getProductCode().getProductName() : "null";

            System.out.println(
                    getUsernameOrderDetail + "\t | \t" +
                            od.getQuantity() + "\t | \t" +
                            od.getTotalPrice() + "\t | \t" +
                            getProductNameOrderDetail
            );
        });

        System.out.println("\n1. Back");
        System.out.println("0. Out");

        System.out.print("=> ");
        try {
            int choose = scanner.nextInt();
            scanner.nextLine();

            switch (choose) {
                case 1:
                    this.showOrderDetailMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input Failed...");
            scanner.nextLine();
        }
    }
}
