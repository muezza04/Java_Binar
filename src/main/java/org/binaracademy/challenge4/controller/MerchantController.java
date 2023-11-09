package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.service.impl.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class MerchantController {

//    @Autowired
//    private MenuMainController menuMainController;

    @Autowired
    private MerchantService merchantService;

    public Scanner scanner = new Scanner(System.in);

    public void showMenuMerchant() {
        while (true) {
            System.out.println("This All Data Merchant");
            System.out.println("Merchant Code \t | \t Merchant Name \t | \t Merchant Location \t | \t Merchant Status");
            merchantService.readMerchant().forEach(merchant -> {
                System.out.println(
                        merchant.getMerchantCode() + "\t - \t" +
                                merchant.getMerchantName() + "\t - \t" +
                                merchant.getMerchantLocation() + "\t - \t" +
                                merchant.getMerchantOpen()
                );
            });

            System.out.println("1. Add Merchant");
            System.out.println("2. Update Status Merchant");
            System.out.println("3. Delete Merchant");
            System.out.println("4. Read data Merchant Open(buka)");
            System.out.println("99. Back menu main");
            System.out.println("0. Out");

            System.out.print("=> ");
            try {
                int choose = scanner.nextInt();
                scanner.nextLine();

                switch (choose) {
                    case 1:
                        this.showAddMerchant();
                        break;
                    case 2:
                        this.showUpdateMerchantStatus();
                        break;
                    case 3:
                        this.showDeleteMerchant();
                        break;
                    case 4:
                        this.showReadMerchant();
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

    public void showAddMerchant() {
        System.out.println("\n=====New Merchant=====");
        System.out.print("Merchant Code : ");
        String newMerchantCode = scanner.nextLine();
        System.out.print("Merchant Name : ");
        String newMerchantName = scanner.nextLine();
        System.out.print("Merchant Location : ");
        String newMerchantLocation = scanner.nextLine();
        System.out.print("Merchant Status : ");
        String newMerchantStatus = scanner.nextLine();

        Merchant newMerchant = Merchant.builder()
                .merchantCode(newMerchantCode)
                .merchantName(newMerchantName)
                .merchantLocation(newMerchantLocation)
                .merchantOpen(newMerchantStatus)
                .build();

        merchantService.addMerchant(newMerchant);
        System.out.println("Users New in Add!\n");
        this.showMenuMerchant();
    }

    public void showUpdateMerchantStatus() {
        System.out.println("\n=====Update Status Merchant=====");
        System.out.print("Code Merchant which will update : ");
        String merchantCode = scanner.nextLine();
        System.out.print("update Status Merchant : ");
        String merchantStatus = scanner.nextLine();

        merchantService.updateMerchantStatus(merchantStatus, merchantCode);
        System.out.println("Merchant update success\n");
        this.showMenuMerchant();
    }

    public void showDeleteMerchant() {
        System.out.print("Code Merchant which is delete : ");
        String merchantDelete = scanner.nextLine();

        merchantService.deleteMerchant(merchantDelete);
        System.out.println("Merchant delete is success!\n");
        this.showMenuMerchant();
    }

    public void showReadMerchant() {
        while (true) {
            System.out.println("This Data Merchant Open(buka)");
            System.out.println("Merchant Code \t | \t Merchant Name \t | \t Merchant Location \t | \t Merchant Status");
            merchantService.readMerchantOpen().forEach(merchant -> {
                System.out.println(
                        merchant.getMerchantCode() + "\t - \t" +
                                merchant.getMerchantName() + "\t - \t" +
                                merchant.getMerchantLocation() + "\t - \t" +
                                merchant.getMerchantOpen()
                );
            });

            System.out.println("1. Back");
            System.out.println("0. Out");

            System.out.print("=> ");
            try {
                int choose = scanner.nextInt();
                scanner.nextLine();

                switch (choose) {
                    case 1:
                        this.showMenuMerchant();
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
