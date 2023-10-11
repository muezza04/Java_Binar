package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

@Component
public class UsersController {

    @PostConstruct
    public void init() {
        this.showMenuUsers();
    }

    @Autowired
    private UsersService usersService;

    public Scanner scanner = new Scanner(System.in);

    public void showMenuUsers() {
        while (true) {
            System.out.println("Welcome Menu Users!!\n" +
                    "Silahkan Pilih Menu\n" +
                    "1. Lihat semua Users\n" +
                    "2. Add users\n" +
                    "0. Keluar");

            System.out.print("=> ");
            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        this.showAllUsers();
                        break;
                    case 2:
                        this.showAddNewUsers();
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
    public void showAllUsers() {
        System.out.println("Berikut tampilan akun Users");
        System.out.println("Username \t | \t Email \t | \t Password");
        usersService.getAllUsers().forEach(users -> {
            System.out.println(
                    users.getUsername() + "\t - \t" +
                            users.getEmailAddress() + "\t - \t" + users.getPassword());
        });

        System.out.println("1. Detail akun");
        System.out.println("2. Add users");
        System.out.println("0. Keluar");

        System.out.print("=> ");
        try {
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("nama username => ");
                    String userDetail = scanner.nextLine();
                    this.showDetailUsers(usersService.getUsersDetail(userDetail));
                    break;
                case 2:
                    this.showAddNewUsers();
                    break;
                case 0:
                    System.exit(0);
                default:
                    throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input Failed...");
            scanner.nextLine();
            this.showAllUsers();
        }
    }

    public void showAddNewUsers() {
        System.out.println("\n=====New Users=====");
        System.out.print("new Username : ");
        String newUsername = scanner.nextLine();
        System.out.print("new Email Address : ");
        String newEmail = scanner.nextLine();
        System.out.print("new password : ");
        String newPassword = scanner.nextLine();

        Users newUser = Users.builder()
                .id(UUID.randomUUID().toString())
                .username(newUsername)
                .emailAddress(newEmail)
                .password(newPassword)
                .build();

        usersService.addNewUsers(newUser);
        System.out.println("\nUsers baru ditambahkan!");
        this.showMenuUsers();
    }

    public void showDetailUsers(Users user) {
        System.out.println("\nUsername : " + user.getUsername());
        System.out.println("Email Address : " + user.getEmailAddress());
        System.out.println("Password : " + user.getPassword());
    }
}
