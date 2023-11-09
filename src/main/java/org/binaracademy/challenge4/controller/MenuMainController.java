package org.binaracademy.challenge4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class MenuMainController {

    @PostConstruct
    public void init() {
        this.showMenuMain();
    }

    @Autowired
    private UsersController usersController;
    @Autowired
    private ProductController productController;
    @Autowired
    private MerchantController merchantController;
    @Autowired
    private OrdersController ordersController;
    @Autowired
    private OrdersDetailController ordersDetailController;

    public Scanner scanner = new Scanner(System.in);

    public void showMenuMain() {
        while (true) {
            System.out.println("=====This Main Menu=====");

            System.out.println("1. Data Users");
            System.out.println("2. Data Product");
            System.out.println("3. Data Order");
            System.out.println("4. Data Order detail");
            System.out.println("5. Data Merchant");
            System.out.println("0. Out");

            System.out.print("=> ");
            try {
                int choose = scanner.nextInt();
                scanner.nextLine();

                switch (choose) {
                    case 1:
                        usersController.showMenuUsers();
                        break;
                    case 2:
                        productController.showMenuProduct();
                        break;
                    case 3:
                        ordersController.showMenuOrders();
                        break;
                    case 4:
                        ordersDetailController.showOrderDetailMenu();
                        break;
                    case 5:
                        merchantController.showMenuMerchant();
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
}
